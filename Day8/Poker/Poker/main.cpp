//
//  main.cpp
//  Poker
//
//  Created by Branden Frieden and Mack Tawa on 8/31/22.
//

// royal flush = 4/2,600,000 =      %0.00015385
// straight flush = 37/2,600,000 =  %0.00142308
// straight = 10153/2,600,000 =     %0.3905
// flush = 5148/2,600,000 =         %0.198
// full house = 3799/2,600,000 =    %0.14611538



#include <iostream>
#include "CardFunctions.hpp"

int main(int argc, const char * argv[]) {
    
    std::srand((unsigned) time(0));
    
    // initialize variables
    std::vector<Card> deck;
    std::vector<Card> hand;
    int count = 0;
    int flushcount = 0;
    int straightcount = 0;
    int straightflushcount = 0;
    int royalflushcount = 0;
    int fullhousecount = 0;
    int runamount = 1000000;
        
    // populate deck
    deck = BuildDeck();
        
    while(count < runamount){
        
        DeckShuffle(deck);
        
        // print deck
        //DeckPrinter(deck);
        
        hand = DrawHand(deck);
        //hand = {Card{10,"spades"}, Card{10,"clubs"}, Card{11,"spades"},Card{11,"spades"},Card{10,"spades"}};
        
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
    
    std::cout << "royal flushes: %" << 100 * (double)royalflushcount/(double)runamount << std::endl;
    std::cout << "straight flushes: %" << 100 * (double)straightflushcount/(double)runamount << std::endl;
    std::cout << "straights: %" << 100 * (double)straightcount/(double)runamount << std::endl;
    std::cout << "flushes: %" << 100 * (double)flushcount/(double)runamount << std::endl;
    std::cout << "full houses: %" << 100 * (double)fullhousecount/(double)runamount << std::endl;
    
    double totalHands = royalflushcount + straightflushcount + straightcount + flushcount + fullhousecount;
    std::cout << "chance of havin a hand with one of the above: %" << (totalHands/runamount) * 100 << std::endl;
    
    
    return 0;
}
