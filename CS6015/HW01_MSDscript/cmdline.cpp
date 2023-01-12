#include "cmdline.h"
#include <iostream>

void use_arguments(int argc, char** argv){
    bool tested = false;
    for(int i = 1; i < argc; i++)
    {
        if(strcmp(argv[i], "--help") == 0)
        {
            std::cout << "The following commands are allowed\n--help provides allowed commands\n--test runs tests on the program\n";
            exit(1);
        }
        else if(strcmp(argv[i], "--test") == 0)
        {
            if(tested)
            {
                std::cerr << "Already tested, exiting...";
                exit(1);
            }
            std::cout << "Tests passed\n";
            tested = true;
        }
        else
        {
            std::cerr << "argument not recognized, exiting...";
            exit(1);
        }
    }
    return;
}
