//
//  Functions.cpp
//  NumberConverter
//
//  Created by Branden Frieden on 9/6/22.
//

#include "Functions.hpp"
#include <math.h>
#include <string.h>
#include <string>


int stringToInt(std::string input, const int& base){
    int output = 0;
    int neg_check = 1;
    char hex_char;
    
    if(input[0] == '-'){
        neg_check = -1;
        input.erase(0,1);
    }
        
    for(int i = 1; i <= input.length(); i++){
        if(input[input.length() - i] >='0' && input[input.length() - i] <='9'){
            output += (input[input.length() - i] - '0') * pow(base,i - 1);
        }
        else {
            hex_char = input[input.length() - i];
            hex_char = tolower(hex_char);
            output += (hex_char - 'a' + 10) * pow(base,i - 1);
        }
    }
    
    return output * neg_check;
}


std::string intToDecimalString(int input){
    std::string output;
    int temp = 0;
    bool negative = false;
    
    if(input < 0){
        negative = true;
        input = -input;
    }
    
    while(input > 0){
        temp = input % 10;

        output.insert(0, 1, '0' + temp);
        
        input /= 10;

    }
    
    if(negative){
        output.insert(0, 1, '-');
    }
    
    return output;
}

std::string intToBinaryString(int input){
    std::string output;
    std::string temp_char;
    bool negative = false;
    
    if(input < 0){
        negative = true;
        input *= -1;
    }
    
    while(input > 0){
        
        if(input % 2 == 1){
            output.insert(0, 1, '1');
        }
        else{
            output.insert(0, 1, '0');
        }
        
        input /= 2;
    }
    
    if(negative){
        output.insert(0, 1, '-');
    }

    
    return output;
}




std::string intToHexadecimalString( int input){
    std::string output;
    std::string binary;
    char temp_char;
    int temp = 0;
    bool negative = false;
    
    if(input < 0){
        negative = true;
        input *= -1;
    }

    
    while(input > 0){
        temp = input % 16;
        if(temp > 9){
            temp_char = 'A' + (temp-10);
        }
        else{
            temp_char = '0' + temp;
        }
        output.insert(0, 1, temp_char);
        input /= 16;
    }
    
    output.insert(0, 1, 'x');
    output.insert(0, 1, '0');
    
    if(negative){
        output.insert(0, 1, '-');
    }

    return output;
}




