//
//  AnalysisFunctions.hpp
//  BookAnalyzerHW
//
//  Created by Branden Frieden on 9/1/22.
//



#ifndef AnalysisFunctions_hpp
#define AnalysisFunctions_hpp

#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <stdio.h>

std::vector<std::string> FindTitle(const std::vector<std::string>& BookVector);

std::vector<std::string> FindAuthor(const std::vector<std::string>& BookVector);

std::vector<std::string> FindReleaseDate(const std::vector<std::string>& BookVector);

int FindTotalChars(const std::vector<std::string>& BookVector);

std::string ShortestWord(const std::vector<std::string>& BookVector);

std::string LongestWord(const std::vector<std::string>& BookVector);

int KeyWordAppearances(const std::string& Key_word, const std::vector<std::string>& BookVector, std::vector<int>& key_word_appearance_indexes);

#endif /* AnalysisFunctions_hpp */
