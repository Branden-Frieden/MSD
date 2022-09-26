//
//  Functions.hpp
//  NumberConverter
//
//  Created by Branden Frieden on 9/6/22.
//

#pragma include once

#include <stdio.h>
#include <string>


int stringToInt(std::string input, const int& base);

std::string intToDecimalString(int input);

std::string intToBinaryString(int input);

std::string intToHexadecimalString( int input);
