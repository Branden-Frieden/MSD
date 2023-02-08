#include <iostream>
#include <sstream>
#include <cstdio>
#include <unistd.h>
#include <sys/wait.h>
#include "shelpers.hpp"
#include <cstdlib>
#include <csignal>
#include <filesystem>

int main(int argc, char *argv[]) {
    // initialize variables
    std::string inputString;
    std::vector<std::string> tokens;
    std::string parsed;
    std::vector<int> children;
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
                std::cout << "Process " << *it << " has exited\n";
                children.erase(it);
                fflush(stdout);
            }
        }

        // pull inputString into stringstream
        std::stringstream input_stringstream(inputString);
        // empty tokens
        tokens = {};

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
                dup2(commands[i].fdStdout, 1);
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
                    children.push_back(rc);
                } else
                    wait(NULL);
            }
        }

        // close all the pipes
        for (int i = 0; i < commands.size(); i++) {
            if(commands[i].fdStdout != 1)
                close(commands[i].fdStdout);
            if(commands[i].fdStdin != 0)
                close(commands[i].fdStdin);
        }

        std::cout << std::__fs::filesystem::current_path() << " $ ";;
        fflush(stdout);
    }
    return 0;
}
