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
    
    void growVector();
    
    double get(int index) const;
    
    void set(int index, double newValue);
    
    void pushBack(double data_insert);
    
    void popBack();
    
    void freeVector();
    
    size_t getSize();
    
    size_t getCapacity();
};




#endif /* MyVec_hpp */
