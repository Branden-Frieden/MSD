//
//  MyVec.cpp
//  DiyVector
//
//  Created by Branden Frieden on 9/13/22.
//

#include "MyVec.hpp"
#include <iostream>
#include <algorithm>
#include <numeric>

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

void MyVecTests2(){
    MyVec<int> vec1(5);
    MyVec<int> vec2(-3);
    
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
    
    MyVec<int> vec3(1);
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

bool isEven(int val){
    return (val % 2 == 0 );
}

void MyVecTests3(){
    
    MyVec<double> vec1(5);
    MyVec<double> vec2(-3);
    MyVec<int> vec3(5);
    
    vec1.pushBack(3.2);
    vec1.pushBack(1.6);
    vec1.pushBack(1.9);
    vec1.pushBack(1.3);
    vec1.pushBack(1.8);
    vec1.pushBack(2.2);
    
    
    vec2.pushBack(3.4);
    vec2.pushBack(3.6);
    vec2.pushBack(8.7);
    vec2.pushBack(2.2);
    vec2.pushBack(3.1);
    vec2.pushBack(8.4);
    
    vec3.pushBack(2);
    vec3.pushBack(3);
    vec3.pushBack(4);
    vec3.pushBack(5);
    vec3.pushBack(8);
    vec3.pushBack(6);
    
    std::sort(vec1.begin(), vec1.end());
    if(vec1[0] != 1.3){
        std::cout << "sort error - test 1\n";
    }
    if(vec1[5] != 3.2){
        std::cout << "sort error - test 2\n";
    }

    
    if(*std::min_element(vec2.begin(), vec2.end()) != 2.2){
        std::cout << "std::min_element error - test 1\n";
    }
    if(std::min_element(vec2.begin(), vec2.end()) != vec2.begin() + 3){
        std::cout << "std::min_element error - test 1\n";
    }
    
    if(std::accumulate(vec2.begin(), vec2.end(), 0.0) != 29.4){
        std::cout << "std::accumulate error - test 1\n";
    }
    
    if(std::count_if(vec3.begin(), vec3.end(), [] (const int& val){ return (val % 2 == 0);}) != 4){
        std::cout << "std::count_if error - test 1\n";
    }
    
    if(std::count_if(vec3.begin(), vec3.end(), isEven) != 4){
        std::cout << "std::count_if error - test 2\n";
    }
    
    std::remove_if(vec3.begin(), vec3.end(), [] (int & val) {return (val % 2 == 0);});
    if(vec3[0] != 3){
        std::cout << "std::remove_if error - test 1\n";
    }
}
