//
//  main.cpp
//  StringAnalyzer
//
//  Created by Branden Frieden on 8/26/22.
//

#include <iostream>

using namespace std;

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

int NumWords(string s){
    
    int space_count = 0;
    
    for(int i = 0; i < s.length(); i++){
        if(s[i] == ' '){
            space_count++;
        }
    }
    
    return space_count + 1;
    
}

int Numsentences(string s){
    
    int num_sentences = 0;
    
    for(int i = 0; i < s.length(); i++){
        if(IsTerminator(s[i])){
            num_sentences ++;
        }
    }
    
    return num_sentences;
}


int NumVowels(string s){
    
    int num_vowels = 0;
    
    for(int i = 0; i < s.length(); i++){
        if(IsVowel(s[i])){
            num_vowels++;
        }
    }
    return num_vowels;
}


int NumConsonants(string s){
    int num_consonants = 0;
    
    for(int i = 0; i < s.length(); i++){
        if(IsConsonant(s[i])){
            num_consonants++;
        }
    }
    return num_consonants;
}

double AverageWordLength(string s){
    
    double word_number = NumWords(s);
    double average_word_length = (NumConsonants(s) + NumVowels(s))/word_number;
    return average_word_length;
}

double AverageVowelsPerWord(string s){
    double word_count = NumWords(s);
    double vowel_count = NumVowels(s);
    double vowel_average = vowel_count / word_count;
    
    return vowel_average;
    
}



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
        std::cout << "Number of words: " << word_num << std::endl;
        std::cout << "Number of sentences: " << sentences_num << std::endl;
        std::cout << "Number of vowels: " << vowels_num << std::endl;
        std::cout << "Number of consonants: " << consonants_num << std::endl;
        std::cout << "Reading level (average word length): " << average_word_length << std::endl;
        std::cout << "Average vowels per word: " << average_vowels_per_word << "\n \n";
        
    }
    
    return 0;
}
