//
//  PlayerShip.cpp
//  CS6010_FinalProject
//
//  Created by Branden Frieden on 9/19/22.
//

#include <SFML/Graphics.hpp>
#include "PlayerShip.hpp"

// constructor creates a triangle ship at bottom center screen  with shoot delay and 0 velocity
PlayerShip::PlayerShip(){
    xCoord = 500 - shipSize;
    yCoord = 1450 - shipSize;
    velocity = 0;
    reload = 0;
    score = 0;
    accuracy = 0;
    ship = sf::CircleShape(60,3);
    ship.setPosition(xCoord, yCoord);
}

// return the sf::shape for draw purposes
sf::CircleShape PlayerShip::getShip() const{
    return ship;
}

// set velocity to left 3 pixels/frame when called
void PlayerShip::MoveLeft(){
    velocity = -3;
}

// set velocity to right 3 pixels/frame when called
void PlayerShip::MoveRight(){
    velocity = 3;
}

void PlayerShip::stop(){
    velocity = 0;
}

// return reload to check if shoot is viable
int PlayerShip::getReload() const{
    return reload;
}

void PlayerShip::resetReload(){
    reload = 0;
}

int PlayerShip::GetXcoord() const{
    return xCoord;
}

int PlayerShip::GetShipSize() const{
    return shipSize;
}

int PlayerShip::GetYcoord() const{
    return yCoord;
}

// changes x coordinate based on velocity, while keeping ship in bound.
// increments reload to keep track of frames since last shot
// update the ship position to be drawn on screen
void PlayerShip::update(){
    xCoord += velocity;
    if(xCoord > 1000 - shipSize * 2)
        xCoord = 1000 - shipSize * 2;
    if(xCoord < 0)
        xCoord = 0;
    if(reload < 90){
        reload++;
    }
    ship.setPosition(xCoord, yCoord);
}

int PlayerShip::score_increase()
{
    score++;
}

std::string PlayerShip::get_score()
{
    return std::to_string(score);
}

int PlayerShip::accuracy_increase()
{
    return accuracy++;
}
std::string PlayerShip::get_accuracy()
{
    return std::to_string((int)((float)score/(float)accuracy*100)) + "%";
}

int PlayerShip::getScoreInt() const
{
    return score;
}
