//
//  main.cpp
//  RomanNumerals
//
//  Created by Branden Frieden on 8/25/22.
//

#include <iostream>

using namespace std;

int main(int argc, const char * argv[]) {
    
    // initialize variables
    int user_Input;
    string roman_Numeral;
    
    while(true){
        
    //reset roman numeral string
    roman_Numeral = "";

        //prompting user for decimal number
        std::cout << "Enter decimal number: \n"; std::cin >> user_Input;
        
        // check for reasonable inputs
        if(user_Input > 0){
            
            // loop until input is zero, add letters to string as roman numerals rules apply
            while(user_Input > 0){
                
                if(user_Input >= 1000){
                    roman_Numeral += "M";
                    user_Input -= 1000;
                }
                else if(user_Input >= 900){
                    roman_Numeral += "CM";
                    user_Input -= 900;
                }
                else if(user_Input >= 500){
                    roman_Numeral += "D";
                    user_Input -= 500;
                }
                else if(user_Input >= 400){
                    roman_Numeral += "CD";
                    user_Input -= 400;
                }
                else if(user_Input >= 100){
                    roman_Numeral += "C";
                    user_Input -= 100;
                }
                else if(user_Input >= 90){
                    roman_Numeral += "XC";
                    user_Input -= 90;
                }
                else if(user_Input >= 50){
                    roman_Numeral += "L";
                    user_Input -= 50;
                }
                else if(user_Input >= 40){
                    roman_Numeral += "XL";
                    user_Input -= 40;
                }
                else if(user_Input >= 10){
                    roman_Numeral += "X";
                    user_Input -= 10;
                }
                else if(user_Input >= 9){
                    roman_Numeral += "IX";
                    user_Input -= 10;
                }
                else if(user_Input >= 5){
                    roman_Numeral += "V";
                    user_Input -= 5;
                }
                
                else if(user_Input >= 4){
                    roman_Numeral += "IV";
                    user_Input -= 4;
                }
                else if(user_Input >= 1){
                    roman_Numeral += "I";
                    user_Input -= 1;
                }
            }
            
            // output roman numeral version of the number
            
            std::cout << "Roman numeral Version: \n" << roman_Numeral << endl << endl;
        }
        else{
            
            //if the user input is negative, output Invalid input
            
            std::cout << "Invalid input\n \n";
        }
    }
    
    return 0;
}
