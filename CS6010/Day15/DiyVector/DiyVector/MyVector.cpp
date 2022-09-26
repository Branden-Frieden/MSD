//
//  MyVectortor.cpp
//  DiyVector
//
//  Created by Branden Frieden on 9/13/22.
//

#include "MyVector.hpp"


MyVector makeVector(size_t initialCapacity){
    MyVector vec;
    int test = (int)initialCapacity;
    if(test <= 0){
        initialCapacity = 1;
    }
    vec.capacity = initialCapacity;
    vec.size = 0;
    vec.data = new double[initialCapacity];
    
    return vec;
}

void freeVector(MyVector& vec){
    delete vec.data;
    vec.size = 0;
    vec.capacity = 0;
}

void growVector(MyVector& vec){
    double* temp_data = new double[vec.capacity];
    for(size_t i = 0; i < vec.size; i++){
        temp_data[i] = vec.data[i];
    }
    for(size_t i = vec.size; i < (vec.capacity * 2); i++){
        temp_data[i] = 0.0;
    }
    vec.capacity *= 2;
    delete vec.data;
    vec.data = temp_data;
    temp_data = nullptr;
}

void pushBack(MyVector& vec, double data_insert){
    if(vec.size == vec.capacity){
        growVector(vec);
    }
    
    vec.data[vec.size] = data_insert;
    vec.size++;
}

void popBack(MyVector& vec){
    vec.data[vec.size] = 0.0;
    vec.size--;
}

double get(const MyVector& vec, int index){
    return vec.data[index];
}

void set(MyVector& vec, int index, double newValue){
    
    vec.data[index] = newValue;
    
}


