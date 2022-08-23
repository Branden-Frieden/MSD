//
//  main.cpp
//  Average
//
//  Created by Mack Tawa and Branden Frieden on 8/23/22.
// This program only averages 5 float inputs.

#include <iostream>
float totalAverage;
float firstGrade;
float secondGrade;
float thirdGrade;
float fourthGrade;
float fifthGrade;

int main() {
    // insert code here...
    std::cout << "Input first grade\n";
    std::cin >> firstGrade;
    std::cin >> secondGrade;
    std::cin >> thirdGrade;
    std::cin >> fourthGrade;
    std::cin >> fifthGrade;
    totalAverage = (firstGrade+secondGrade+thirdGrade+fourthGrade+fifthGrade)/5;
    
    std::cout << "The averages for the inputed grades is: \n" << totalAverage << "\n";
    
    
    return 0;
}
