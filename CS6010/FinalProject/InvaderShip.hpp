//
//  InvaderShip.hpp
//  CS6010_FinalProject
//
//  Created by Mack on 9/20/22.
//

#ifndef InvaderShip_hpp
#define InvaderShip_hpp

#include <stdio.h>
#include <SFML/Graphics.hpp>

//rand if 0 shoot
class InvaderShip
{
private:
    int xCoord, yCoord, xVelocity, yVelocity, count;
    int shipSize = 40;
    sf::CircleShape ship;
    sf::Color shipColor;
    
    
public:
    sf::CircleShape getShip();
    int GetXcoord();
    int GetYcoord();
    int GetShipSize();
    void update();
    InvaderShip();
    void reverseVelocity();
    void setX(int x);
    void sety(int y);
    
    
};

#endif /* InvaderShip_hpp */
