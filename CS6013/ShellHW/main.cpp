#include <iostream>
#include <sstream>
#include <cstdio>
#include <unistd.h>
#include <sys/wait.h>
#include "shelpers.hpp"
#include <cstdlib>
#include <csignal>
#include <filesystem>
#include <fstream>

int main(int argc, char *argv[]) {
    // initialize variables
    std::string inputString;
    std::vector<std::string> tokens;
    std::string parsed;
    std::vector<int> children;
    std::vector<int> childOutputFiles;
    std::cout << std::__fs::filesystem::current_path() << " $ ";
    fflush(stdout);



    while (getline(std::cin, inputString)) {
        // check for exit command
        if(inputString == "exit"){
            return 0;
        }

        // check if background processes have finished
        for(std::vector<int>::iterator it = children.begin(); it < children.end(); it++) {
            if (0 != kill((pid_t) *it, 0)) {
                std::cout << "Process " << *it << " has exited\n \tOutput:\n";
                children.erase(it);
                int secondFork = fork();

                if (secondFork == 0) {
                    std::string outputString;

                    dup2(childOutputFiles[it - children.begin()], 0);
                    while (std::cin >> outputString) {
                        std::cout << outputString << "\n";
                    }
                    exit(1);
                } else{
                    wait(NULL);
                }

                close(childOutputFiles[it - children.begin()]);
                childOutputFiles.erase(childOutputFiles.begin() + (it - children.begin()));
            }
        }

        // pull inputString into stringstream
        std::stringstream input_stringstream(inputString);
        // empty tokens
        tokens.clear();

        // parse input string by spaces, add to tokens vector
        while (getline(input_stringstream, parsed, ' ')) {
            tokens.push_back(parsed);
        }

        //gets commands vector to execute
        std::vector<Command> commands = getCommands(tokens);
        if(commands.empty()) continue;




        // loop through commands
        for (int i = 0; i < commands.size(); i++) {

            // take care of built-in commands
            if(commands[i].exec == "cd"){
                if(commands[i].argv.size() > 2){
                    chdir(commands[i].argv[1]);
                } else
                    chdir(getenv("HOME"));
                continue;
            }



            //fork
            int rc = fork();
            if (rc < 0) {
                std::cout << "fork failed";
                exit(1);
            } else if (rc == 0) {


                // change input/output
                if(commands[i].background && i == commands.size() - 1){
                    close(commands[i].outputPipe[0]);
                    dup2(commands[i].outputPipe[1], 1);
                    close(commands[i].outputPipe[1]);
                } else {
                    dup2(commands[i].fdStdout, 1);
                }
                dup2(commands[i].fdStdin, 0);

                //close previous commands pipe to allow EOF signal to fire
                if(i > 0){
                    close(commands[i-1].fdStdout);
                }
                // execute command
                execvp(commands[i].exec.c_str(), const_cast<char *const *>(commands[i].argv.data()));


            } else {

                //close previous commands pipe to allow EOF signal to fire
                if(i > 0){
                    close(commands[i-1].fdStdout);
                }

                // in parent, wait if not background, else, save pid
                if(commands[i].background) {
                    wait(NULL);
                    if (i == commands.size() - 1) {
                        close(commands[i].outputPipe[1]);
                        children.push_back(rc);
                        childOutputFiles.push_back(commands[i].outputPipe[0]);
                    }
                } else {
                    //wait(NULL);
                    waitpid(rc, NULL, 0);

                }
            }
        }

        closeFileDescriptors(commands);

        std::cout << std::__fs::filesystem::current_path() << " $ ";
        fflush(stdout);
    }
    return 0;
}
