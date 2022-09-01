//
//  AnalysisFunctions.cpp
//  BookAnalyzerHW
//
//  Created by Branden Frieden on 9/1/22.
//

#include "AnalysisFunctions.hpp"

std::vector<std::string> FindTitle(const std::vector<std::string>& BookVector){
    std::vector<std::string> Title;
    int i = 0;
    
    while(BookVector[i] != "Title:"){
        i++;
    }
    i++;
    int j = 0;
    while(BookVector[i] != "Author:"){
        Title.push_back(BookVector[i]);
        i++;
        j++;
        if(j > 100){
            Title = {"Unknown"};
            break;
        }
    }
    
    return Title;
}

std::vector<std::string> FindAuthor(const std::vector<std::string>& BookVector){
    
    std::vector<std::string> author;
    int i = 0;
    
    while(BookVector[i] != "Author:"){
        i++;
    }
    i++;
    int j = 0;
    while(BookVector[i] != "Release" && BookVector[i] != "Translator:"){
        author.push_back(BookVector[i]);
        i++;
        j++;
        if(j > 100){
            author = {"Unknown"};
            break;
        }
    }
    return author;
}

std::vector<std::string> FindReleaseDate(const std::vector<std::string>& BookVector){
    int i = 0;
    std::vector<std::string> release_date;
    while(BookVector[i] != "Date: "){
        i++;
    }
    i++;
    while(BookVector[i] != "Language"){
        release_date.push_back(BookVector[i]);
        i++;
        if(i > 100){
            release_date = {"Unknown"};
            break;
        }
    }
    return release_date;
}





int FindTotalChars(const std::vector<std::string>& BookVector){
    int total_chars = 0;
    for(int i = 0; i < BookVector.size(); i++){
        for(int j = 0; j < BookVector[i].length(); j++){
            total_chars++;
        }
    }
    return total_chars;
}

std::string ShortestWord(const std::vector<std::string>& BookVector){
    std::string shortest_word = BookVector[0];
    for(int i = 0; i < BookVector.size(); i++){
        if(BookVector[i].length() < shortest_word.length()){
            shortest_word = BookVector[i];
        }
    }
    return shortest_word;
}

std::string LongestWord(const std::vector<std::string>& BookVector){
    std::string longest_word = BookVector[0];
    for(int i = 100; i < BookVector.size(); i++){
        if(BookVector[i].length() > longest_word.length()){
            longest_word = BookVector[i];
        }
    }
    return longest_word;
}

int KeyWordAppearances(const std::string& Key_Word, const std::vector<std::string>& BookVector, std::vector<int>& key_word_appearance_indexes){
    
    int key_word_appearances = 0;
    
    for(int i = 0; i < BookVector.size(); i++){
        if(BookVector[i] == Key_Word){
            key_word_appearances++;
            key_word_appearance_indexes.push_back(i);
        }
    }
    return key_word_appearances;
}

