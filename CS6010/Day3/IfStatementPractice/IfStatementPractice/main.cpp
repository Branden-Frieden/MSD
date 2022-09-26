//
//  main.cpp
//  IfStatementPractice
//
//  Created by Branden Frieden on 8/24/22.
//

#include <iostream>

int main() {
    int userAge;
    
    // prompt and store user age
    std::cout << "Please type your age: "; std::cin >> userAge;
    
    //check for user voting elligibility
    if(userAge >= 18){
        std::cout << "Congrats! you are old enough to vote \n";
    }
    else{
        std::cout << "You are not old enough to vote \n";
    }
    
    //check for age eligibility for the senate
    if(userAge >=30){
        std::cout << "You are old enough to run for the Senate \n";
        
    }
    else{
        std::cout << "You are not old enough to run for the Senate";
    }
    
    // find the users generation
    if(userAge > 80){
        std::cout << "you are part of the greatest generation\n";
    }
    else if(userAge > 60){
        std::cout << "you are a baby boomer\n";
    }
    else if(userAge > 40){
        std::cout << "you are a part of generation X\n";
    }
    else if(userAge > 20){
        std::cout << "you ara a millennial\n";
    }
    else{
        std::cout << "you are an ikid\n";
    }
    
    
    //______________________________________________________PART 2
    
    // prompt user and store whether its a weekday or not
    char weekdayChar =  'L';
    char holidayChar =  'L';
    char childrenChar = 'L';
    
    std::cout << "Is today a weekday (Y/N)? \n";            std::cin >> weekdayChar;
    std::cout << "Is today a holliday (Y/N)? \n";           std::cin >> holidayChar;
    std::cout << "Do you have young children (Y/N)? \n";    std::cin >> childrenChar;
    
    if( childrenChar == 'Y'){
        std::cout << "You can't sleep in today \n";
    }
    else if (holidayChar == 'Y' || weekdayChar == 'N'){
        std::cout << "You get to sleep in today! \n";
    }
    else {
        std::cout << "You can't sleep in today \n";
    }
    return 0;
}
