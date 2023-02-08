#include <iostream>
#include <sstream>
#include <cstdio>
#include <unistd.h>
#include <sys/wait.h>
#include "shelpers.hpp"
#include <cstdlib>

int main(int argc, char *argv[]) {
    // initialize variables
    std::string inputString;
    std::vector<std::string> tokens;
    std::string parsed;

    while (getline(std::cin, inputString)) {
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
            int rc = fork();
            if (rc < 0) {
                std::cout << "fork failed";
                exit(1);
            } else if (rc == 0) {
                // change input/output
                dup2(commands[i].fdStdout, 1);
                dup2(commands[i].fdStdin, 0);
                if(i > 0){
                    close(commands[i-1].fdStdout);
                }
                execvp(commands[i].exec.c_str(), const_cast<char *const *>(commands[i].argv.data()));
            } else {
                if(i > 0){
                    close(commands[i-1].fdStdout);
                }
                // in parent, wait
                wait(NULL);

            }
        }

        for (int i = 0; i < commands.size(); i++) {
            if(commands[i].fdStdout != 1)
                close(commands[i].fdStdout);
            if(commands[i].fdStdin != 0)
                close(commands[i].fdStdin);
        }

    }
    return 0;
}
