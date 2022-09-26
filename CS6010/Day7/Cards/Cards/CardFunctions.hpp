//
//  CardFunctions.hpp
//  Cards
//
//  Created by Branden Frieden on 8/30/22.
//

#pragma include once

#include <stdio.h>
#include <string>
#include <vector>

struct Card{
    int rank;
    std::string suit;
};

std::vector<Card> BuildDeck();

void DeckPrinter(std::vector<Card> deck);
