//
//  MyVector.hpp
//  DiyVector
//
//  Created by Branden Frieden on 9/13/22.
//

#ifndef MyVector_hpp
#define MyVector_hpp

#include <stdio.h>

struct MyVector{
    
    double* data;
    size_t capacity, size;
};

MyVector makeVector(size_t initialCapacity);

void freeVector(MyVector& vec);

void growVector(MyVector& vec);

void pushBack(MyVector& vec, double data_insert);

void popBack(MyVector& vec);

double get(const MyVector& vec, int index);

void set(MyVector& vec, int index, double newValue);

#endif /* MyVector_hpp */
