//
//  MyVec.cpp
//  DiyVector
//
//  Created by Branden Frieden on 9/13/22.
//

#include "MyVec.hpp"
#include <iostream>

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
    if(index < 0){
        return;
    }
    if(index >= capacity_){
        double* temp_data = new double[index * 2];
        for(size_t i = 0; i < size_; i++){
            temp_data[i] = data[i];
        }
        for(size_t i = size_; i < (capacity_ * 2); i++){
            temp_data[i] = 0.0;
        }
        capacity_ = index * 2;
        delete data;
        data = temp_data;
        temp_data = nullptr;

    }
    
    data[index] = newValue;
    
    if(index > size_){
        size_ = index + 1;
    }
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

void MyVecTests(){
    MyVec vec1(5);
    MyVec vec2(-3);
    
    if(vec1.getCapacity() != 5){
        std::cout << "make vector error - test 1\n";
    }
    if(vec2.getCapacity() != 1){
        std::cout << "make vector error - test 1\n";
    }
    
    vec1.growVector();
    vec2.growVector();
    
    if(vec1.getCapacity() != 10){
        std::cout << "grow vector error - test 1\n";
    }
    if(vec2.getCapacity() != 2){
        std::cout << "grow vector error - test 1\n";
    }
    
    vec1.pushBack(1.2);
    vec1.pushBack(1.6);
    vec1.pushBack(1.9);
    vec2.pushBack(3.4);
    vec2.pushBack(3.6);
    vec2.pushBack(8.7);
    
    if( vec1.get(2) - 1.9 > .001){
        std::cout << "get or pushback error - test 1\n";
    }
    
    if(vec2.get(2) - 8.7 > .001){
        std::cout << "get or pushback error - test 2\n";
    }
    
    vec1.popBack();
    vec2.popBack();
    
    if(vec1.get((int)vec1.getSize()-1) - 1.6 > .001){
        std::cout << "popback error - test 1\n";
    }
    if(vec2.get((int)vec2.getSize()-1) - 3.6 > .001){
        std::cout << "popback error - test 2\n";
    }
    
    vec1.set(8, 5.5);
    vec2.set(5, 6.6);
    
    if(vec1.get(8) - 5.5 > .001){
        std::cout << "set error - test 1\n";
    }
    if(vec2.get(5) - 6.6 > .001){
        std::cout << "set error - test 2\n";
    }
    
    
    vec1.freeVector();
    vec2.freeVector();
    
    if(vec1.getCapacity() != 0){
        std::cout << "free vector error - test 1\n";
    }
    if(vec2.getCapacity() != 0){
        std::cout << "free vector error - test 1\n";
    }
}
