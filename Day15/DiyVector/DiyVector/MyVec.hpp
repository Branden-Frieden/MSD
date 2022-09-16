//
//  MyVec.hpp
//  DiyVector
//
//  Created by Branden Frieden on 9/13/22.
//

#ifndef MyVec_hpp
#define MyVec_hpp

#include <stdio.h>
template<typename T>
class MyVec{
  
    T* data;
    size_t capacity_,size_;
    
public:
    MyVec(size_t initialCapacity);
    
    MyVec( const MyVec& original );
    
    void growVector();
    
    T get(int index) const;
    
    void set(int index, T newValue);
    
    void pushBack(T data_insert);
    
    void popBack();
    
    ~MyVec();
    
    size_t getSize() const;
    
    size_t getCapacity() const;
    
    MyVec & operator=( const MyVec<T> & rhs);
    
    T& operator[](int index);
    
    const T& operator[](int index) const;
    
    bool operator==( const MyVec & rhs) const;
    
    bool operator!=( const MyVec & rhs) const;
    
    bool operator<( const MyVec & rhs) const;
    
    bool operator>( const MyVec & rhs) const;
    
    bool operator>=( const MyVec & rhs) const;
    
    bool operator<=( const MyVec & rhs) const;
    
};

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


void MyVecTests();

void MyVecTests2();


#endif /* MyVec_hpp */
