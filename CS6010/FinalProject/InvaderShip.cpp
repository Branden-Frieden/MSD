//
//  InvaderShip.cpp
//  CS6010_FinalProject
//
//  Created by Mack on 9/20/22.
//

#include "InvaderShip.hpp"
#include <SFML/Graphics.hpp>


// creates an invader ship
InvaderShip::InvaderShip(){
    
    // randomize where the ship is spawned in the play area
    xCoord = rand() % 850 + 100;
    yCoord = rand() % 100 + 10;
    
    // randomize the direction the ship is moving
    xVelocity = rand() % 6 - 3;
    
    // ship always moves down at set speed
    yVelocity = 1;
    
    // create shape object and set its begining location
    ship = sf::CircleShape(shipSize);
    ship.setPosition(xCoord, yCoord);
    ship.setFillColor(sf::Color::Transparent);
    
    // set counter to zero
    count = 0;
    
    // set a random value and assign ship color based on the number generated
    int randVal = rand() % 4;
    if(randVal == 0){
        shipColor = sf::Color::Red;
    }
    else if(randVal == 1){
        shipColor = sf::Color::Magenta;
    }
    else if(randVal == 2){
        shipColor = sf::Color::Yellow;
    }
    else if(randVal == 3){
        shipColor = sf::Color::Green;
    }
    else if(randVal == 4){
        shipColor = sf::Color::Cyan;
    }
    
    // set the object outline color to the ship shipColor
    ship.setOutlineColor(shipColor);
    ship.setOutlineThickness(2);

    
}


void InvaderShip::update()
{
    // change the location of the ship based on the velocity
    yCoord += yVelocity;
    xCoord += xVelocity;
    
    // count keeps track of frames to make ship change direction every 2 seconds at 160 fps
    count++;
    if (count > 320)
    {
        // reset count and randomize the x velocity
        count = 0;
        xVelocity = rand() % 6 +(-3);
    }
    
    // set a random value and assign ship color based on the number generated
    if(count % 80 == 0){
        int randVal = rand() % 4;
        if(randVal == 0){
            shipColor = sf::Color::Red;
        }
        else if(randVal == 1){
            shipColor = sf::Color::Magenta;
        }
        else if(randVal == 2){
            shipColor = sf::Color::Yellow;
        }
        else if(randVal == 3){
            shipColor = sf::Color::Green;
        }
        else if(randVal == 4){
            shipColor = sf::Color::Cyan;
        }
        
        // set the object outline color to the ship shipColor
        ship.setOutlineColor(shipColor);
    }
    
    // update the draw objects location to match the class
    ship.setPosition(xCoord, yCoord);
}

int InvaderShip::GetXcoord()
{
    return xCoord;
}

int InvaderShip::GetYcoord()
{
    return yCoord;
}

int InvaderShip::GetShipSize(){
    return shipSize;
}

// returns the circle shape for drawing purposes
sf::CircleShape InvaderShip::getShip(){
    return ship;
}

// used for edge hitting to keep ship in bounds
void InvaderShip::reverseVelocity(){
    xVelocity = -xVelocity;
}

void InvaderShip::setX(int x)
{
    xCoord = x;
}

void InvaderShip::sety(int y)
{
    yCoord = y;
}
