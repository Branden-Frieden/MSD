//
//  MyVec.cpp
//  DiyVector
//
//  Created by Branden Frieden on 9/13/22.
//

#include "MyVec.hpp"



MyVec::MyVec(size_t initialCapacity){
    int test;
    test = (int)initialCapacity;
    if(test <= 0){
        initialCapacity = 1;
    }
    capacity_ = initialCapacity;
    size_ = 0;
    data = new double[initialCapacity];
}

void MyVec::growVector(){
    double* temp_data = new double[capacity_];
    for(size_t i = 0; i < size_; i++){
        temp_data[i] = data[i];
    }
    for(size_t i = size_; i < (capacity_ * 2); i++){
        temp_data[i] = 0.0;
    }
    capacity_ *= 2;
    delete data;
    data = temp_data;
    temp_data = nullptr;
}



double MyVec::get(int index) const{
    return data[index];
}

void MyVec::set(int index, double newValue){
    
    data[index] = newValue;
    
}

void MyVec::pushBack(double data_insert){
    if(size_ == capacity_){
        growVector();
    }
    
    data[size_] = data_insert;
    size_++;
}

void MyVec::popBack(){
    data[size_] = 0.0;
    size_--;
}

void MyVec::freeVector(){
    delete data;
    size_ = 0;
    capacity_ = 0;
}

size_t MyVec::getSize(){
    return size_;
}

size_t MyVec::getCapacity(){
    return capacity_;
}
