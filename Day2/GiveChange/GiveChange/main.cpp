//
//  main.cpp
//  GiveChange
//
//  Created by Branden Frieden on 8/23/22.
//

#include <iostream>

int price;
int amountPaid;
int changeDue;
int quarters;
int dimes;
int nickels;
int pennies;

int main() {
    while(true){
        
        std::cout << "Enter Item Price in Cents: \n"; std::cin >> price;
        std::cout << "Enter Amount Paid in Cents: \n"; std::cin >> amountPaid;
        
        changeDue = amountPaid - price;
        
        if(changeDue < 0){
            std::cout << "please pay full price amount \n \n";
            continue;
        }
        std::cout << "Change Due: " << changeDue << "\n";
    
        quarters = changeDue / 25;
        changeDue %= 25;
        
        dimes = changeDue / 10;
        changeDue %= 10;
        
        nickels = changeDue / 5;
        changeDue %= 5;
        
        pennies = changeDue;
        
        std::cout << "Quarters: " << quarters << "\n";
        std::cout << "Dimes: " << dimes << "\n";
        std::cout << "Nickels: " << nickels << "\n";
        std::cout << "Pennies: " << pennies << "\n \n \n";
    }
    return 0;
}
