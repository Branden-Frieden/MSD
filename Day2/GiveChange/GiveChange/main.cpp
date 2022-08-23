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

int main(int argc, const char * argv[]) {
    
    
    std::cout << "Enter Item Price in Cents: ";
    std::cin >> price;
    std::cout << "\n";
    std::cout << "Enter Amount Paid in Cents: ";
    std::cin >> amountPaid;
    std::cout << "\n";
    
    changeDue = amountPaid - price;
    
    std::cout << "change due: " << changeDue << "\n";
    
    while(changeDue > 0){
        if(changeDue >= 25){
            quarters++;
            changeDue = changeDue - 25;
        }
        else if(changeDue >= 10){
            dimes++;
            changeDue = changeDue - 10;
        }
        else if(changeDue >= 5){
            nickels++;
            changeDue = changeDue - 5;
        }
        else{
            pennies++;
            changeDue = changeDue - 1;
        }
    }
    
    std::cout << "quarters: " << quarters << "\n";
    std::cout << "dimes: " << dimes << "\n";
    std::cout << "nickels: " << nickels << "\n";
    std::cout << "pennies: " << pennies << "\n";
    
    
    return 0;
}
