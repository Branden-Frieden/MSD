//
//  main.cpp
//  Poker
//
//  Created by Branden Frieden and Mack Tawa on 8/31/22.
//

#include <iostream>
#include "CardFunctions.hpp"

int main(int argc, const char * argv[]) {
    
    
    // initialize variables
    std::vector<Card> deck;
    std::vector<Card> hand;
    int count = 0;
    int flushcount = 0;
    int straightcount = 0;
    int straightflushcount = 0;
    int royalflushcount = 0;
    int fullhousecount = 0;
        
    // populate deck
    deck = BuildDeck();
        
    while(count < 1000000){
        
        DeckShuffle(deck);
        
        // print deck
        //DeckPrinter(deck);
        
        hand = DrawHand(deck);
        
        if(isRoyalFlush(hand)){
            royalflushcount++;
        }
        else if(isStraightFlush(hand)){
            straightflushcount++;
        }
        else if(isStraight(hand)){
            straightcount++;
        }
        else if(isFlush(hand)){
            flushcount++;
        }
        else if(isFullHouse(hand)){
            fullhousecount++;
        }
    
        count++;
}
    
    std::cout << "royal flushes: " << royalflushcount << std::endl;
    std::cout << "straight flushes: " << straightflushcount << std::endl;
    std::cout << "straights: " << straightcount << std::endl;
    std::cout << "flushes: " << flushcount << std::endl;
    std::cout << "full houses: " << fullhousecount << std::endl;
    
    return 0;
}
