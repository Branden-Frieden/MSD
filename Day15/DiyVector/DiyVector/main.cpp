//
//  main.cpp
//  DiyVector
//
//  Created by Branden Frieden on 9/13/22.
//

#include <iostream>
#include "MyVector.hpp"
#include "Myvec.hpp"

int main(int argc, const char * argv[]) {
    
    //--------------------------Class
    
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
    
    
    vec1.freeVector();
    vec2.freeVector();
    
    if(vec1.getCapacity() != 0){
        std::cout << "free vector error - test 1\n";
    }
    if(vec2.getCapacity() != 0){
        std::cout << "free vector error - test 1\n";
    }
    
    
    /*------------------------Struct
    //initialize variables
    MyVector vec1 = makeVector(10);
    MyVector vec2 = makeVector(-5);
    
    
    //tests
    if(vec1.capacity != 10){
        std::cout << "makeVector error - test 1\n";
    }
    if(vec2.capacity != 1){
        std::cout << "makeVector error - test 2\n";
    }
    growVector(vec1);
    growVector(vec2);
    
    if(vec1.capacity != 20){
        std::cout << "growVector error - test 1\n";
    }
    if(vec2.capacity != 2){
        std::cout << "growVector error - test 2\n";
    }
    
    pushBack(vec1, 1.5);
    pushBack(vec1, 2.2);
    pushBack(vec2, 3.4);
    pushBack(vec2, 7.9);
    
    if(vec1.data[0] - 1.5 > .001){
        std::cout << "pushBack error - test 1\n";
    }
    if(vec1.data[1] - 7.9 > .001){
        std::cout << "pushBack error - test 1\n";
    }
    
    popBack(vec1);
    popBack(vec2);
    
    if(vec1.data[vec1.size - 1] - 1.5 > .001){
        std::cout << "pushBack error - test 1\n";
    }
    if(vec2.data[vec2.size - 1] - 3.4 > .001){
        std::cout << "pushBack error - test 2\n";
    }
    
    pushBack(vec1, 5.2);
    pushBack(vec1, 1.8);
    pushBack(vec1, 10.7);
    pushBack(vec2, 7.9);
    pushBack(vec2, 4.5);
    
    if(get(vec1, 3) - 10.7 > .001){
        std::cout << "get error - test 1\n";
    }
    if(get(vec2, 2) - 4.5 > .001){
        std::cout << "get error - test 2\n";
    }
    
    set(vec1, 2, 4.4);
    set(vec2, 1, 1.1);
    
    if(get(vec1, 2) - 4.4 > .001){
        std::cout << "set error - test 1\n";
    }
    if(get(vec2, 1) - 1.1 > .001){
        std::cout << "set error - test 2\n";
    }
    
    freeVector(vec1);
    freeVector(vec2);
     */
     
    
    return 0;
}
