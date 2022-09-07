//
//  main.cpp
//  NumberRepresentations
//
//  Created by Branden Frieden on 9/7/22.
//

#include <iostream>
#include <cstdint>
#include <string>
#include <iomanip>
#include <fstream>
#include "functions.hpp"

int main(int argc, const char * argv[]) {
    
    
    std::cout << "the size of int8_t is: " << sizeof(int8_t);
    std::cout << "\nthe size of int16_t is: " << sizeof(int16_t);
    std::cout << "\nthe size of int64_t is: " << sizeof(int64_t) << std::endl;
    
    uint8_t min_uint8_t = 0x0;
    uint8_t max_uint8_t = 0xff;
    
    uint16_t min_uint16_t = 0x0;
    uint16_t max_uint16_t = 0xffff;
    
    uint64_t min_uint64_t = 0x0;
    uint64_t max_uint64_t = 0xffffffff;
    
    std::cout << "min val of uint8_t: "<< +min_uint8_t << std::endl;
    std::cout << "max val of uint8_t: "<< +max_uint8_t << std::endl;
    
    std::cout << "min val of uint16_t: "<< min_uint16_t << std::endl;
    std::cout << "max val of uint16_t: "<< max_uint16_t << std::endl;
    
    std::cout << "min val of uint64_t: "<< min_uint64_t << std::endl;
    std::cout << "max val of uint64_t: "<< max_uint64_t << std::endl;
    
    // signed integers
    
    int8_t min_int8_t = 0x80;
    int8_t max_int8_t = 0x7f;
    
    int16_t min_int16_t = 0x8000;
    int16_t max_int16_t = 0x7fff;
    
    int64_t min_int64_t = 0x80000000;
    int64_t max_int64_t = 0x7fffffff;
    
    std::cout << "min val of int8_t: "<< +min_int8_t << std::endl;
    std::cout << "max val of int8_t: "<< +max_int8_t << std::endl;
    
    std::cout << "min val of int16_t: "<< min_int16_t << std::endl;
    std::cout << "max val of int16_t: "<< max_int16_t << std::endl;
    
    std::cout << "min val of int64_t: "<< min_int64_t << std::endl;
    std::cout << "max val of int64_t: "<< max_int64_t << std::endl;
    
    max_int8_t += 1;
    min_int8_t -= 1;
    
    std::cout << "min val of int8_t -1: "<< +min_int8_t << std::endl;
    std::cout << "max val of int8_t +1: "<< +max_int8_t << std::endl;
  
    
    // ---------------------------- Part 2: Floating point
    
    double num = .1+.2;
    std::cout << std::setprecision(18);
    std::cout << num << std::endl;
    //    assert(num == .3);
    
    float num2 = .1+.2;
    std::cout << num2 << std::endl;
    
    assert(approxEquals(num, .3, .001));
    
    // -----------------------------Part 3: Unicode/UTF-8
    
    fileReader("UTF-8-demo.txt");
    
    return 0;
}
