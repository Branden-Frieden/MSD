//
//  main.cpp
//  Palindrome
//
//  Created by Ricardo Sanchez on 8/25/22.
//
//  Partners: Branden Friden and Mack Tawa
//

#include <iostream>
#include <string>

using namespace std;

int main() {
    
// PART 1 - PALINDROMES
    
    string word, reverse;

    cout << "Enter a palindrome word: ";
    cin >> word;
    
    for (int i =0 ; i < word.length(); i++){
        reverse += word[word.length() -1 -i];
    }
    
    cout << word << "\n";

//    Auxiliar code lines
//    cout << reverse << "\n"; // checking reverse word
//    cout << word.length() << "\n"; // Checking number of word characters
//    cout << reverse.length() << "\n"; // checking number of reverse characters

    if (word == reverse)
        cout << word << " is a palindrome";
        else
            cout << word << " in NOT a palindrome";
    
    cout << "\n\n";
    return 0;
}
