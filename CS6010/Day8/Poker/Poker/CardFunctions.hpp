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

void DeckPrinter(const std::vector<Card>& deck);

void DeckShuffle(std::vector<Card>& deck);

std::vector<Card> DrawHand(const std::vector<Card>& deck);

bool isFlush(const std::vector<Card>& hand);

bool isStraight(const std::vector<Card>& hand);

bool isStraightFlush (const std::vector<Card>& hand);

bool isRoyalFlush (const std::vector<Card>& hand);

bool isFullHouse (const std::vector<Card>& hand);
