//
//  PlayerShip.hpp
//  CS6010_FinalProject
//
//  Created by Branden Frieden on 9/19/22.
//

#ifndef PlayerShip_hpp
#define PlayerShip_hpp

#include <stdio.h>
#include <SFML/Graphics.hpp>

class PlayerShip {
    int xCoord, yCoord, velocity, reload;
    int shipSize = 60;
    int score;
    int accuracy;
    sf::CircleShape ship;
    
    
    
public:
    PlayerShip();
    sf::CircleShape getShip() const;
    void MoveLeft();
    void MoveRight();
    void stop();
    int getReload() const;
    void resetReload();
    int GetXcoord() const;
    int GetYcoord() const;
    int GetShipSize() const;
    void update();
    int score_increase();
    std::string get_score();
    int accuracy_increase();
    std::string get_accuracy();

    
    
    int getScoreInt() const;
};
#endif /* PlayerShip_hpp */
