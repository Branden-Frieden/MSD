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

void MyVecTests();


#endif /* MyVec_hpp */
