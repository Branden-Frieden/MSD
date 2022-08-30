//
//  main.cpp
//  Cards
//
//  Created by Branden Frieden on 8/30/22.
//

#include <iostream>
#include "CardFunctions.hpp"

int main(int argc, const char * argv[]) {
    
    // initialize variables
    std::vector<Card> deck;
    
    // populate deck
    deck = BuildDeck();
    
    // print deck
    DeckPrinter(deck);
    
    return 0;
}
