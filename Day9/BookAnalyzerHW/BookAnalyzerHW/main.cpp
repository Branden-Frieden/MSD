//
//  main.cpp
//  BookAnalyzerHW
//
//  Created by Branden Frieden on 9/1/22.
//

#include "AnalysisFunctions.hpp"
#include <iostream>
#include <fstream>
#include <string>
#include <vector>

int main(int argc, const char * argv[]) {
    
    //initialize variables
    std::string file_name;
    std::string Key_Word;
    std::vector<std::string> BookVector;
    std::string current_word;
    std::vector<std::string> Title;
    std::vector<std::string> author;
    std::vector<std::string> release_Date;
    std::string longest_word;
    std::string shortest_word;
    std::string first_line;
    std::vector<int> key_word_appearance_indexes;
    int key_word_appearances;
    int words = 0;
    int total_chars = 0;
    
    //prompt user for inputs
    std::cout << "Please input a file name with .txt extension and a key word to search for: \n";
    std::cin >> file_name >> Key_Word;
    
    // open book file
    std::ifstream myStream(file_name);
    if(myStream.fail()){
        std::cout << "Failed to open file: " << file_name <<"\n";
        exit(1);
    }
    
    while( myStream >> current_word){
        BookVector.push_back(current_word);
        words++;
    }
    
    //find title
    Title = FindTitle(BookVector);
    
    //find the author
    author = FindAuthor(BookVector);
    
    // find total character excluding whitespace
    total_chars = FindTotalChars(BookVector);
    
    // find the shortest word in the book
    shortest_word = ShortestWord(BookVector);
    
    // find the longest word in the book
    longest_word = LongestWord(BookVector);
    
    //find the number of appearances and locations of the users key word
    key_word_appearances = KeyWordAppearances(Key_Word, BookVector, key_word_appearance_indexes);
    
    std::cout << "Statistics for ";
    for(int i = 0; i < Title.size(); i++){
        std::cout << Title[i] << " ";
    }
    
    
    std::cout << "by ";
    for(int i = 0; i < author.size(); i++){
        std::cout << author[i] << " ";
    }
    std::cout << "\n\n";
   
    
    std::cout << "Number of words: " << words << "\n";
    std::cout << "Number of characters: " << total_chars << "\n";
    std::cout << "The shortest word is '" << shortest_word << "'" ;
    std::cout << " and the longest word is '" << longest_word << "'\n";
    std::cout << "The word '" << Key_Word << "' appears " << key_word_appearances << " times:\n";
    for(int i = 0; i < key_word_appearance_indexes.size(); i++){
        std::cout << "at " << (((float)key_word_appearance_indexes[i]/(float)words)*100) << "% ";
        std::cout << "'" << BookVector[key_word_appearance_indexes[i]-1] << " ";
        std::cout << BookVector[key_word_appearance_indexes[i]] << " ";
        std::cout << BookVector[key_word_appearance_indexes[i]+1] << "'\n" ;
    }
    
    return 0;
}
