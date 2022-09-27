//
//  InvaderProjectile.hpp
//  CS6010_FinalProject
//
//  Created by Mack on 9/20/22.
//

#ifndef InvaderProjectile_hpp
#define InvaderProjectile_hpp

#include "InvaderShip.hpp"
#include <SFML/Graphics.hpp>
#include <stdio.h>

class InvaderProjectile
{
private:
    int xCoord, yCoord, yVelocity, xSize, ySize, count;
    sf::RectangleShape shot;
    
public:
    InvaderProjectile(InvaderShip& invaderX);
    void update();
    sf::RectangleShape getShot();
    int getYcoord();
    int getXcoord(); // add half of xSize
    void set_x(int x);
    void set_y(int y);

};

#endif /* InvaderProjectile_hpp */
