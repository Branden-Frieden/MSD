//
//  LetterHelpers.cpp
//  std::stringAnalyzer
//
//  Created by Branden Frieden on 8/29/22.
//

#include <stdio.h>
#include <string>
#include "LetterHelpers.h"

bool IsTerminator(char c){
    if(c == '.' || c == '?' || c == '!'){
        return true;
    }
    else{
        return false;
    }
}

bool IsPunctuation(char c){
    if(c == '.' || c == '?' || c == '!' || c == ','){
        return true;
    }
    else{
        return false;
    }
}

bool IsVowel(char input_char){
    
    char c = tolower(input_char);
    
    if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y'){
        return true;
    }
    else{
        return false;
    }
}

bool IsConsonant(char c){
    
    if(IsPunctuation(c) || IsVowel(c) || c == ' '){
        return false;
    }
    else{
        return true;
    }
    
}

int NumVowels(std::string s){
    
    int num_vowels = 0;
    
    for(int i = 0; i < s.length(); i++){
        if(IsVowel(s[i])){
            num_vowels++;
        }
    }
    return num_vowels;
}


int NumConsonants(std::string s){
    int num_consonants = 0;
    
    for(int i = 0; i < s.length(); i++){
        if(IsConsonant(s[i])){
            num_consonants++;
        }
    }
    return num_consonants;
}
