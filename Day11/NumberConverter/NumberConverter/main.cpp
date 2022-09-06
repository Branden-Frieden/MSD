//
//  main.cpp
//  NumberConverter
//
//  Created by Branden Frieden on 9/6/22.
//

#include <iostream>
#include "Functions.hpp"

int main(int argc, const char * argv[]) {
    
    // -----------------Tests
    
    if(intToDecimalString(12) != "12"){
        std::cout << "intToDecimalString() fail test 1\n";
    }
    if(intToDecimalString(-1255) != "-1255"){
        std::cout << "intToDecimalString() fail test 2\n";
    }
    if(intToBinaryString(15) != "01111"){
        std::cout << "intToBinaryString() fail test 1\n";
    }
    if(intToBinaryString(-34) != "1100010"){
        std::cout << "intToBinaryString() fail test 2\n";
    }
    if(intToHexadecimalString(15) != "0xF"){
        std::cout << "intToHexidecimalString() fail test 1\n";
    }
    if(intToHexadecimalString(-12) != "-0xC"){
        std::cout << "intToHexadecimalString() fail test 2\n";
    }
    
    // initialize variables
    std::string user_input;
    std::string Decimal;
    std::string Binary;
    std::string Hexadecimal;
    int converted_user_input;
    int base;
    
    
    // prompt user for input string
    std::cout << "Please enter an integer and the numerical base\n";
    std::cin >> user_input >> base;
    
    
    converted_user_input = stringToInt(user_input, base);
    Decimal = intToDecimalString(converted_user_input);
    Binary = intToBinaryString(converted_user_input);
    Hexadecimal = intToHexadecimalString(converted_user_input);
    
    std::cout << "In Decimal form: " << Decimal << std::endl;
    std::cout << "In Binary form: " << Binary << std::endl;
    std::cout << "In Hexadecimal form: " << Hexadecimal << std::endl;
    
    
    
    return 0;
}
