//
//  InvaderProjectile.cpp
//  CS6010_FinalProject
//
//  Created by Mack on 9/20/22.
//

#include "InvaderProjectile.hpp"
#include <SFML/Graphics.hpp>

InvaderProjectile::InvaderProjectile(InvaderShip& invaderPos)
{
    xCoord = invaderPos.GetXcoord() + invaderPos.GetShipSize();
    yCoord = invaderPos.GetYcoord() + invaderPos.GetShipSize() * 2;
    yVelocity = 4;
    xSize = 10;
    ySize = 20;
    
    shot = sf::RectangleShape(sf::Vector2f(xSize, ySize));
    shot.setPosition(xCoord, ySize);
}

int InvaderProjectile::getYcoord()
{
    return yCoord + ySize;
}

int InvaderProjectile::getXcoord()
{
    return xCoord + (xSize/2);
}


sf::RectangleShape InvaderProjectile::getShot()
{
    return shot;
}


void InvaderProjectile::update()
{
    yCoord += yVelocity;
    shot.setPosition(xCoord, yCoord);
    
}

void InvaderProjectile::set_x(int x)
{
    xCoord = x;
}

void InvaderProjectile::set_y(int y)
{
    yCoord = y;
}

