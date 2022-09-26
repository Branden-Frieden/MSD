//
//  main.cpp
//  StringAnalyzer
//
//  Created by Branden Frieden on 8/26/22.
//

#include <iostream>
#include "LetterHelpers.h"
#include "WordHelpers.hpp"

using namespace std;

int main(int argc, const char * argv[]) {
    
    //initialize variables
    std::string user_input;
    int word_num;
    int sentences_num;
    int vowels_num;
    int consonants_num;
    double average_word_length;
    double average_vowels_per_word;

    while(true){
        
        //prompt user for input string
        std::cout << "Enter a string containing one or more sentences: \n";
        
        //get user input string
        std::getline( std::cin, user_input);
        
        if(user_input == "done"){
            std::cout << "Goodbye.\n";
            break;
        }
        
        // find number of words, sentences, vowels, and consonants.
        //also average word length and average vowels per word
        word_num =                  NumWords(user_input);
        sentences_num =             Numsentences(user_input);
        vowels_num =                NumVowels(user_input);
        consonants_num =            NumConsonants(user_input);
        average_word_length =       AverageWordLength(user_input);
        average_vowels_per_word =   AverageVowelsPerWord(user_input);
        
        // print results
        std::cout << "Analysis:\n";
        std::cout << "Number of words: " << word_num << std::endl;
        std::cout << "Number of sentences: " << sentences_num << std::endl;
        std::cout << "Number of vowels: " << vowels_num << std::endl;
        std::cout << "Number of consonants: " << consonants_num << std::endl;
        std::cout << "Reading level (average word length): " << average_word_length << std::endl;
        std::cout << "Average vowels per word: " << average_vowels_per_word << "\n \n";
        
    }
    
    return 0;
}
