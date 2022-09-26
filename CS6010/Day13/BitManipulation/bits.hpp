//
//  bits.hpp
//  BitManipulation
//
//  Created by Branden Frieden on 9/9/22.
//

#ifndef bits_hpp
#define bits_hpp

#include <stdio.h>

bool GetBit( uint32_t input, int b );

bool IsNegative( int input );

int NumBitsSet( uint32_t input );

unsigned char GetByte( uint32_t input, int b );

uint32_t SetByte( uint32_t input, uint8_t value, int b );

int Negate( int input );

int Increment( uint32_t x );






#endif /* bits_hpp */
