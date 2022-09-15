//
//  MyVec.cpp
//  DiyVector
//
//  Created by Branden Frieden on 9/13/22.
//

#include "MyVec.hpp"
#include <iostream>
template<typename T>

MyVec<T>::MyVec(size_t initialCapacity){
    int test;
    test = (int)initialCapacity;
    if(test <= 0){
        initialCapacity = 1;
    }
    capacity_ = initialCapacity;
    size_ = 0;
    data = new T[initialCapacity];
}

template<typename T>
void MyVec<T>::growVector(){
    T* temp_data = new T[2 * capacity_];
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

template<typename T>
T MyVec<T>::get(int index) const{
    return data[index];
}

template<typename T>
void MyVec<T>::set(int index, T newValue){
    if(index < 0){
        return;
    }
    if(index >= capacity_){
        T* temp_data = new T[index * 2];
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

template<typename T>
void MyVec<T>::pushBack(T data_insert){
    if(size_ == capacity_){
        growVector();
    }
    
    data[size_] = data_insert;
    size_++;
}

template<typename T>
void MyVec<T>::popBack(){
    size_--;
    data[size_] = 0.0;
}

template<typename T>
MyVec<T>::~MyVec(){
    delete data;
    size_ = 0;
    capacity_ = 0;
}

template<typename T>
size_t MyVec<T>::getSize() const{
    return size_;
}

template<typename T>
size_t MyVec<T>::getCapacity() const{
    return capacity_;
}

template<typename T>
MyVec<T> & MyVec<T>::operator=( const MyVec & rhs){
    if(this == &rhs){
        return *this;
    }
    capacity_ = rhs.capacity_;
    size_ = rhs.size_;
    for( size_t i = 0; i < size_; i++){
        data[i] = rhs.data[i];
    }
    return *this;
}

template<typename T>
MyVec<T>::MyVec( const MyVec& original ){
    capacity_ = original.capacity_;
    size_ = original.size_;
    data = new T[capacity_];
    for( size_t i = 0; i < size_; i++){
        data[i] = original.data[ i ];
    }
}

template<typename T>
T& MyVec<T>::operator[](int index){
    return this->data[index];
}

template<typename T>
const T& MyVec<T>::operator[](int index) const{
    return this->data[index];
}

template<typename T>
bool MyVec<T>::operator==( const MyVec & rhs) const{
    for(size_t i = 0; i < size_; i++){
        if(data[i] != rhs.data[i]){
            return false;
        }
    }
    return true;
}

template<typename T>
bool MyVec<T>::operator!=( const MyVec & rhs) const{
    return !(*this == rhs);
}

template<typename T>
bool MyVec<T>::operator<( const MyVec & rhs) const{
    for(size_t i = 0; i < size_; i++){
        if(data[i] < rhs.data[i]){
            return true;
        }
        else if(data[i] > rhs.data[i]){
            return false;
        }
    }
    return false;
}

template<typename T>
bool MyVec<T>::operator>( const MyVec & rhs) const{
    for(size_t i = 0; i < size_; i++){
        if(data[i] > rhs.data[i]){
            return true;
        }
        else if(data[i] < rhs.data[i]){
            return false;
        }
    }
    return false;
}

template<typename T>
bool MyVec<T>::operator>=( const MyVec & rhs) const{
    for(size_t i = 0; i < size_; i++){
        if(data[i] < rhs.data[i]){
            return false;
        }
    }
    return true;
}

template<typename T>
bool MyVec<T>::operator<=( const MyVec & rhs) const{
    for(size_t i = 0; i < size_; i++){
        if(data[i] > rhs.data[i]){
            return false;
        }
    }
    return true;
}



void MyVecTests(){
    MyVec<double> vec1(5);
    MyVec<double> vec2(-3);
    
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
    
    if(vec1[(int)vec1.getSize()] - 0.0 > .001){
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
    
    MyVec<double> vec3(1);
    vec3 = vec2;
    
    if(!(vec3 == vec2)){
        std::cout << "== error - test 1\n";
    }
    if( vec3 != vec2){
        std::cout << "!= error test 1\n";
    }
    
    if(vec3.get(5) - 6.6 > .001){
        std::cout << "= error - test 1\n";
    }
    if(vec3[5] - 6.6 > .001){
        std::cout << "[ ] error - test 1\n";
    }
    
    vec3[3] = 3.3;
    
    if(vec3[3] - 3.3 > .001){
        std::cout << "[ ] error - test 1\n";
    }
    
    if(vec3 < vec2){
        std::cout << "< error - test 1\n";
    }
    if(!(vec3 > vec2)){
        std::cout << "> error - test 1\n";
    }
    
    if(!(vec3 >= vec2)){
        std::cout << ">= error - test 1\n";
    }
    
    if(vec3 <= vec2){
        std::cout << "<= error - test 1\n";
    }
    vec3[3] = 0.0;
    if(vec3 < vec2){
        std::cout << "< error - test 2\n";
    }
    if(vec3 > vec2){
        std::cout << "> error - test 2\n";
    }
    
    if(!(vec3 >= vec2)){
        std::cout << ">= error - test 2\n";
    }
    
    if(!(vec3 <= vec2)){
        std::cout << "<= error - test 2\n";
    }
}
