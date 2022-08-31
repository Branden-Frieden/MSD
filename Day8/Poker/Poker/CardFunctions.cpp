//
//  CardFunctions.cpp
//  Cards
//
//  Created by Branden Frieden on 8/30/22.
//

#include <iostream>
#include <cstdlib>
#include <time.h>
#include "CardFunctions.hpp"

using namespace std;

std::vector<Card> BuildDeck(){
    std::vector<Card> deck;
    
    std::vector<std::string> suits{"diamonds", "clubs", "hearts", "spades"};
    std::vector<int> ranks{1,2,3,4,5,6,7,8,9,10,11,12,13};
    
    for(int i = 0; i < suits.size(); i++){
        for(int n = 0; n < ranks.size(); n++){
            deck.push_back(Card{ranks[n], suits[i]});
        }
    }
    return deck;
}

void DeckPrinter(const std::vector<Card>& deck){
    for(Card c : deck){
        if(c.rank == 11){
            std::cout << "J" << " of " << c.suit << std::endl;
        }
        else if(c.rank == 12){
            std::cout << "Q" << " of " << c.suit << std::endl;
        }
        else if(c.rank == 13){
            std::cout << "K" << " of " << c.suit << std::endl;
        }
        else if(c.rank == 1){
            std::cout << "A" << " of " << c.suit << std::endl;
        }
        else{
                std::cout << c.rank << " of " << c.suit << std::endl;
            }
    }
}

void DeckShuffle(std::vector<Card>& deck){
    int j = 0;
    Card temp;
    std::srand((unsigned) time(0));
    for(int i = 0; i < deck.size(); i++){
        j = std::rand() % 52;
        temp = deck[i];
        deck[i] = deck[j];
        deck[j] = temp;
        
    }
    
    
}

std::vector<Card> DrawHand(const std::vector<Card>& deck){
    
    std::vector<Card> hand;
    //DeckShuffle(deck);
    
    hand ={deck[0],deck[1],deck[2],deck[3],deck[4]};
    
    return hand;
}

bool isFlush(const std::vector<Card>& hand){
    for(int i = 1; i < hand.size(); i++){
        if(hand[0].suit != hand[i].suit){
            return false;
        }
    }
    return true;
}

bool isStraight(const std::vector<Card>& hand){
    std::vector<int> ranks(5);
    int min;
    int index = 0;
    std::vector<Card> test = hand;
    
    for(int i = 0; i < test.size(); i++){
        min = 15;
        for(int j = 0; j < test.size(); j++){
            if(test[j].rank < min){
                min = test[j].rank;
                index = j;
            }
            
        }
        ranks[i] = min;
        test[index].rank = 20;
    }
    
    if(ranks[4] == 13 && ranks[0] == 1){
        ranks.push_back(14);
        ranks.erase(ranks.begin());
    }
    
    for(int i = 0; i < ranks.size() - 1; i++){
        if(ranks[i + 1] - ranks[i] != 1){
            return false;
        }
    }
    return true;
}

bool isStraightFlush (const std::vector<Card>& hand){
    
    if(isStraight(hand) && isFlush(hand)){
        return true;
    }
    return false;
}

bool isRoyalFlush (const std::vector<Card>& hand){
    if (isStraightFlush(hand)){
        for(int i = 0; i < hand.size(); i++){
            if(hand[i].rank < 10 && hand[i].rank != 1){
                return false;
            }
        }
        return true;
    }
    return false;
}

bool isFullHouse (const std::vector<Card>& hand){
    int count = 0;
    std::vector<int> index1;
    std::vector<int> remaining_indexes = {0,1,2,3,4};
    
    
    for (int i = 0; i < hand.size(); i++){
        index1 = {};
        remaining_indexes = {0,1,2,3,4};
        for (int j = i+1; j < hand.size(); j++){
            
            if (hand[i].rank == hand[j].rank){
                count++;
                index1.push_back(j);
            }
        }
        if (count == 2){
            remaining_indexes.erase(remaining_indexes.begin()+i);
            remaining_indexes.erase(remaining_indexes.begin()+(index1[0] - 1));
            remaining_indexes.erase(remaining_indexes.begin()+(index1[1] - 2));
            
            if (hand[remaining_indexes[0]-1].rank == hand[remaining_indexes[1]-1].rank){
                return true;
            }
        }
    }
    return false;
}

