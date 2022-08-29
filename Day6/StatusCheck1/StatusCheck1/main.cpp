//
//  main.cpp
//  StatusCheck1
//
//  Created by Branden Frieden on 8/29/22.
//

#include <iostream>

int main(int argc, const char * argv[]) {
    
    double user_input = 0;
    double total = 0;
    int i = 1;
    
    while(i <= 4 ){
        
        std::cout << "please enter a number from 1 to 10, type -99 to quit";
        std::cin >> user_input;
        
        if(user_input == -99){
            break;
        }
        else if(user_input >= 1 && user_input <=10){
            total += user_input;
            i++;
        }
        else{
            std::cout >> "invalid input \n";
        }
    
}
    return 0;
}
