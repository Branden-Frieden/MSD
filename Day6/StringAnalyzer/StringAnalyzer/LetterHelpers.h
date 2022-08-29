//
//  LetterHelpers.h
//  StringAnalyzer
//
//  Created by Branden Frieden on 8/29/22.
//

#pragma include once
#include <string>

bool IsTerminator(char c);
bool IsPunctuation(char c);
bool IsVowel(char input_char);
bool IsConsonant(char c);
int NumVowels(std::string s);
int NumConsonants(std::string s);


