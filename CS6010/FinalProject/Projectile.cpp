//
//  PlayerShip.cpp
//  CS6010_FinalProject
//
//  Created by Branden Frieden on 9/19/22.
//

#include "Projectile.hpp"
#include "PlayerShip.hpp"
#include <SFML/Graphics.hpp>

Projectile::Projectile(const PlayerShip& player){
    // create projectile at player ship location, at the tip of the triangle
    xCoord = player.GetXcoord() + 60 - 5;
    yCoord = 1450 - 80;
    
    // set velocity to 6 pixels/frame
    velocity = 6;
    
    // create object size
    xSize = 8;
    ySize = 16;
    
    // create the drawable tectangle object at the start position
    shot = sf::RectangleShape(sf::Vector2f(xSize, ySize));
    shot.setPosition(xCoord,yCoord);
    int randVal = rand() % 4;
    if(randVal == 0){
        shot.setFillColor(sf::Color::Red);
    }
    else if(randVal == 1){
        shot.setFillColor(sf::Color::Magenta);
    }
    else if(randVal == 2){
        shot.setFillColor(sf::Color::Yellow);
    }
    else if(randVal == 3){
        shot.setFillColor(sf::Color::Green);
    }
    else if(randVal == 4){
        shot.setFillColor(sf::Color::Cyan);
    }
}

// returns the rectangle object for drawing purposes
sf::RectangleShape Projectile::getShot(){
    return shot;
}

int Projectile::GetYcoord(){
    return yCoord;
}

int Projectile::GetXcoord(){
    return xCoord + xSize/2;
}


void Projectile::update(){
    // move projectile up the screen by velocity pixels
    yCoord -= velocity;
    
    // change the rectangle objects location to draw
    shot.setPosition(xCoord, yCoord);
}


