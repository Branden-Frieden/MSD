//
//  main.cpp
//  MagicDates
//
//  Created by Branden Frieden on 8/25/22.
//

#include <iostream>

using namespace std;

int main(int argc, const char * argv[]) {
    
    // initialize variables
    
    string inputDate;
    string inputDate_Day;
    string inputDate_Month;
    string inputDate_Year;
    
    int userDay;
    int userMonth;
    int userYear;
    
    // prompt and store date from user
    
    std::cout << "please enter a date in mm/dd/yyyy format: \n"; std::cin >> inputDate;
    
    //seperate day, month, and the last 2 digits of the year into seperate strings
    
    inputDate_Month = inputDate[0];
    inputDate_Month += inputDate[1];
    
    inputDate_Day = inputDate[3];
    inputDate_Day += inputDate[4];
    
    inputDate_Year = inputDate[8];
    inputDate_Year += inputDate[9];
    
    //turn strings to integers for logic purposes
    
    userDay = stoi(inputDate_Day);
    userMonth = stoi(inputDate_Month);
    userYear = stoi(inputDate_Year);
    
    
    // check if day and month are viable options, then check for whether the day times the month is = the last 2 digits of the year
    
    if((userDay >=1 && userDay <=31) && (userMonth>=1 && userMonth <=12)){
        
        if(userDay * userMonth == userYear){
            std::cout << inputDate << " IS a magic date \n";
        }
        else{
            std::cout << inputDate << " is NOT a magic date \n";
        }
    }
    else{
        std::cout << "Invalid date \n";
    }
    
    
    return 0;
}
