//
//  main.cpp
//  ForLoopPractice
//
//  Created by Branden Frieden on 8/24/22.
//

#include <iostream>

int main() {
    
    for(int i = 1; i <= 10; i++){
        std::cout << i << " ";
    }
    std::cout << "\n";
    
    
    int i = 1;
    
    while( i<= 10){
        std::cout << i << " ";
        i++;
    }
    std::cout << "\n";
    
    // for loop is more appropriate in this case
    
    
    
    
    
    
    int firstNumber;
    int secondNumber;
    int tempNumber;
    
    std::cout << "please type in 2 whole numbers \n"; std::cin >> firstNumber >> secondNumber;
    
    if(firstNumber > secondNumber){
        tempNumber = firstNumber;
        firstNumber = secondNumber;
        secondNumber = tempNumber;
    }
    
    for(int l = firstNumber; l <= secondNumber; l++){
        std::cout << l << " ";
    }
    std::cout << "\n";
    
    

    
    for(int l = 1; l <= 20; l++){
        if( l % 2 == 1){
            std::cout << l << " ";
        }
    }
    std::cout << "\n";
    
    
    for(int l = 1; l <= 20; l = l + 2){
        std::cout << l << " ";
    }
    std::cout << "\n";
    //the loop withot an if statement is far easier to type and read than a loop with an if statement.
    
    
    
    
    
    
    
    float userNumber = 0;
    float totalNumber = 0;
    
    std::cout << "Please enter numbers to add. type any negative number to display total \n";
    
    while(userNumber >=0){
        totalNumber += userNumber;
        std::cin >> userNumber;
    }
    
    std::cout << "Total = " << totalNumber << "\n";
    
    
    
    int multSolution = 0;
    
    for(int n = 1; n <= 5; n++){
        std::cout << n << "x*: ";
        
        for(int x = 1; x <=5; x++){
            
            multSolution = n * x;
        
            if(multSolution >=0 && multSolution <10){
                std::cout << " " << multSolution << " ";
            }
            else{
                std::cout << multSolution << " ";
            }
        }
        std::cout << "\n \n";
    }
    
    
    return 0;
}
