//
//  CardFunctions.cpp
//  Cards
//
//  Created by Branden Frieden on 8/30/22.
//

#include<iostream>
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

void DeckPrinter(std::vector<Card> deck){
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
