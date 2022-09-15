//
//  MyVec.cpp
//  DiyVector
//
//  Created by Branden Frieden on 9/13/22.
//

#include "MyVec.hpp"
#include <iostream>

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
