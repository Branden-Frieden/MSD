//
//  main.cpp
//  CommandLineArgs
//
//  Created by Branden Frieden on 9/2/22.
//

#include <iostream>

int main(int argc, const char * argv[]) {
    
    if(argc > 1){
        for(int i = 1; i < argc; i++){
            std::cout << argv[i] << std::endl;
        }
    }
    else{
        std::cout << "No arguments Passed";
    }
    // the first argument is the path to the executable file
    return 0;
}
