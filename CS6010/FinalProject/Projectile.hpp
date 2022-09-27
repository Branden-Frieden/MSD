//
//  PlayerShip.hpp
//  CS6010_FinalProject
//
//  Created by Branden Frieden on 9/19/22.
//

#ifndef Projectile_hpp
#define Projectile_hpp


#include <stdio.h>
#include <SFML/Graphics.hpp>
#include "PlayerShip.hpp"

class Projectile{
    int xCoord, yCoord, velocity, xSize, ySize;
    sf::RectangleShape shot;
    
public:
    Projectile(const PlayerShip& player);
    void update();
    sf::RectangleShape getShot();
    int GetYcoord();
    int GetXcoord();
    
    
};





#endif /* PlayerShip_hpp */
