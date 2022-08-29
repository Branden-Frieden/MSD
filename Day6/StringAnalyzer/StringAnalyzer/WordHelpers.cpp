//
//  WordHelpers.cpp
//  std::stringAnalyzer
//
//  Created by Branden Frieden on 8/29/22.
//

#include "WordHelpers.hpp"
#include "LetterHelpers.h"
#include <string>

int NumWords(std::string s){
    
    int space_count = 0;
    
    for(int i = 0; i < s.length(); i++){
        if(s[i] == ' '){
            space_count++;
        }
    }
    
    return space_count + 1;
    
}

int Numsentences(std::string s){
    
    int num_sentences = 0;
    
    for(int i = 0; i < s.length(); i++){
        if(IsTerminator(s[i])){
            num_sentences ++;
        }
    }
    
    return num_sentences;
}

double AverageWordLength(std::string s){
    
    double word_number = NumWords(s);
    double average_word_length = (NumConsonants(s) + NumVowels(s))/word_number;
    return average_word_length;
}

double AverageVowelsPerWord(std::string s){
    
    double word_count = NumWords(s);
    double vowel_count = NumVowels(s);
    double vowel_average = vowel_count / word_count;
    
    return vowel_average;
    
}
