//
//  MyVec.hpp
//  DiyVector
//
//  Created by Branden Frieden on 9/13/22.
//

#ifndef MyVec_hpp
#define MyVec_hpp

#include <stdio.h>

class MyVec{
  
    double* data;
    size_t capacity_,size_;
    
public:
    MyVec(size_t initialCapacity);
    
    MyVec( const MyVec& original );
    
    void growVector();
    
    double get(int index) const;
    
    void set(int index, double newValue);
    
    void pushBack(double data_insert);
    
    void popBack();
    
    ~MyVec();
    
    size_t getSize() const;
    
    size_t getCapacity() const;
    
    MyVec & operator=( const MyVec & rhs);
    
    double& operator[](int index);
    
    const double& operator[](int index) const;
    
    bool operator==( const MyVec & rhs) const;
    
    bool operator!=( const MyVec & rhs) const;
    
    bool operator<( const MyVec & rhs) const;
    
    bool operator>( const MyVec & rhs) const;
    
    bool operator>=( const MyVec & rhs) const;
    
    bool operator<=( const MyVec & rhs) const;
    
};

void MyVecTests();


#endif /* MyVec_hpp */
