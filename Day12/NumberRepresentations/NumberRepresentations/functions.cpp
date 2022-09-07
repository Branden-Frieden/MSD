//
//  functions.cpp
//  NumberRepresentations
//
//  Created by Branden Frieden on 9/7/22.
//

#include "functions.hpp"
#include <cmath>
#include <string>
#include <fstream>
#include <iostream>

bool approxEquals( double a, double b, double tolerance){
    return (abs(a - b) < tolerance);
}

void fileReader(std::string file_name){
    
    char c;
    int ascii_counter = 0;
    int utf8_counter = 0;
    
    std::ifstream myStream(file_name);
    if(myStream.fail()){
        std::cout << "Failed to open file: " << file_name <<"\n";
        exit(1);
    }
    
    while( myStream >> c){
        std::cout << c << std::endl;
        if(c >= 0 && c <= 127){
            ascii_counter++;
        }
        else{
            utf8_counter++;
        }
    }
    std::cout << "\nASCII characters: " << ascii_counter;
    std::cout << "\nUTF-8 characters: " << utf8_counter << std::endl;
}
