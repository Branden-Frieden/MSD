//
//  GameFunctions.cpp
//  CS6010_FinalProject
//
//  Created by Branden Frieden on 9/20/22.
//

#include "GameFunctions.hpp"
#include "PlayerShip.hpp"
#include "Projectile.hpp"
#include "InvaderProjectile.hpp"
#include <cmath>
#include <iostream>
#include <cassert>

void run_tests()
{
    int inv_1_proj_spawn_location;
    std::vector<Projectile> projectiles;
    
    std::vector<InvaderProjectile> invader_projectiles;
    InvaderShip invader1;
    InvaderShip invader2;
    
    PlayerShip player1;
    invader2.setX(446);
    invader2.sety(1340);
    std::vector<InvaderShip> invaders = {invader1, invader2};
    
    assert(player1.GetXcoord() == 440);
    player1.MoveRight();
    for (int i = 0; i < 2; i++)
    {
        player1.update();
    }
    assert(player1.GetXcoord() == 446);
    
    assert(invader1.GetXcoord() > 0 && invader1.GetXcoord() < 1000);
    
    assert(CollisionDetection(projectiles, invaders, invader_projectiles, player1));
    
    invaders.erase(invaders.begin() + 1);

    InvaderProjectile inv_projectile1(invader1);
    invader_projectiles.push_back(inv_projectile1);
    
    assert(!(CollisionDetection(projectiles, invaders, invader_projectiles, player1)));
    
    invader_projectiles[0].set_x(500);
    invader_projectiles[0].set_y(1400);
    
    assert(CollisionDetection(projectiles, invaders, invader_projectiles, player1));
    
    Projectile projectile1(player1);
    projectiles.push_back(projectile1);
    
    invaders[0].sety(1310);
    invaders[0].setX(466);
    
    CollisionDetection(projectiles, invaders, invader_projectiles, player1);
    
    assert(invaders.size() == 0 && projectiles.size() == 0);
    
    projectiles.push_back(projectile1);
    projectiles[0].update();
    
    assert(projectiles[0].GetYcoord() == 1364);
    
    invader_projectiles.pop_back();
    invader_projectiles.push_back(inv_projectile1);
    invader_projectiles[0].update();
    
    assert(invader1.GetYcoord() - (invader_projectiles[0].getYcoord() - 20) == -84);
}

// checks if projectile goes off the back of screen and deletes when it leaves play area
void ProjectileChecker(std::vector<Projectile>& projectiles){
    int i = 0;
    
    // check every projectile and erase is y coord is less than 2 pixels
    while(i < projectiles.size()){
        if(projectiles[i].GetYcoord() < 2){
            projectiles.erase(projectiles.begin() + i);
        }
        i++;
    }
     
    // update remaining projectiles
    for(int j = 0; j < projectiles.size(); j++){
        projectiles[j].update();
    }
}

// generates rand number between 1 and a max, if its 1, spawn an enemy: 1/rand_max chance per frame
void InvaderSpawner(std::vector<InvaderShip>& invaders, const PlayerShip& player){
    
    int Invader_Spawn_Prob = 400 - (player.getScoreInt() * 2);
    
    if((int)rand() % Invader_Spawn_Prob == 1){
        InvaderShip enemy;
        invaders.push_back(enemy);
    }
}

// if invader ships hit the edges, reverse velocity to return to play area, then update the ships
void InvaderChecker(std::vector<InvaderShip>& invaders, std::vector<InvaderProjectile>& invader_projectiles, const PlayerShip& player){
    int randVal = 0;
    int Invader_Projectile_Spawn_Prob = 400 - (player.getScoreInt());
    
    for(int i = 0; i < invaders.size(); i++){
        // check if the ship is at or past the edge of the screen, if it is, reverse its x velocity
        if(invaders[i].GetXcoord() <= 0 || invaders[i].GetXcoord() >= 1000 - invaders[i].GetShipSize()){
            invaders[i].reverseVelocity();
        }
        
        // delete invader if it gets past the screen
        if(invaders[i].GetYcoord() >= 1600){
            invaders.erase(invaders.begin() + i);
        }
        
        // update invader
        invaders[i].update();
        if(rand() % Invader_Projectile_Spawn_Prob == 1){
            InvaderProjectile invaderShot(invaders[i]);
            invader_projectiles.push_back(invaderShot);
        }
    }
    
    // delete projectiles if they go off the screen
    for(int i = 0; i < invader_projectiles.size(); i++){
        if(invader_projectiles[i].getYcoord() >= 1600){
            invader_projectiles.erase(invader_projectiles.begin() + i);
        }
        invader_projectiles[i].update();
    }
}




// Checks for collisions between Invaders and Projectiles, Player and Invaders, and Player and InvaderProjectiles
bool CollisionDetection(std::vector<Projectile>& projectiles, std::vector<InvaderShip>& invaders, std::vector<InvaderProjectile>& invader_projectiles, PlayerShip& player){
    int InvaderXcoord;
    int InvaderYcoord;
    int ProjectileXcoord;
    int ProjectileYcoord;
    int InvaderProjectileXcoord;
    int InvaderProjectileYcoord;
    int distanceX;
    int distanceY;

    
    //colision for invader ship and player projectile
    int i = 0;
    int j = 0;
    
    //collision for invader ship and player
    int z = 0;

    // only run if invaders and projectiles exist at the same time
    if(invaders.size() > 0){
        
        
        while(i < invaders.size()){
            
            // get coordinates for the invader ship center
            InvaderXcoord = invaders[i].GetXcoord() + invaders[i].GetShipSize();
            InvaderYcoord = invaders[i].GetYcoord() + invaders[i].GetShipSize();
            
            // locate and find distance from invader to player points.
            int bottom_left_x_distance = player.GetXcoord()+8 - InvaderXcoord;
            int bottom_left_y_distance = player.GetYcoord()+90 - InvaderYcoord;
            int top_x_distance = player.GetXcoord() + player.GetShipSize() - InvaderXcoord;
            int mid_y_distance = player.GetYcoord() - InvaderYcoord;
            int bottom_right_x_distance = (player.GetXcoord() + player.GetShipSize()*2 - 8) - InvaderXcoord;
            
            // reset projectile counter
            j = 0;
            
            while(j < projectiles.size()){
                
                // get coordinates for the projectile tip
                ProjectileXcoord = projectiles[j].GetXcoord();
                ProjectileYcoord = projectiles[j].GetYcoord();
                
                // check the distance between the projectile point, and the enemy center
                distanceX = abs(InvaderXcoord - ProjectileXcoord) -4;
                distanceY = InvaderYcoord - ProjectileYcoord;
                
                // if the distance between the 2 is less than or equal to the radius of the ship,
                // its a hit, delete the projectile and the ship
                if(sqrt (pow(distanceX,2) + pow(distanceY,2)) <= invaders[i].GetShipSize()){
                    projectiles.erase(projectiles.begin() + j);
                    invaders.erase(invaders.begin() + i);
                    i--;
                    j--;
                    player.score_increase();
                }
                
                j++;
            }
            
            // check if the player points are overlayed with invader[i], if true, return true to end game
            if (sqrt(pow(bottom_left_x_distance,2) + pow(bottom_left_y_distance,2)) < 40 ){
                return true;
            }
            if (sqrt(pow(top_x_distance,2) + pow(mid_y_distance,2)) < 40){
                return true;
            }
            if (sqrt(pow(bottom_right_x_distance,2) + pow(bottom_left_y_distance,2)) < 40){
                return true;
            }
            i++;
        }
    }
    
    int xOffset;
    int yOffset;
    
    // loop through each projectile and check for collision
    for(int i = 0; i < invader_projectiles.size(); i++){
        // calculate offsets from the triangle tip
        yOffset = invader_projectiles[i].getYcoord() - (player.GetYcoord());
        xOffset = invader_projectiles[i].getXcoord() - (player.GetXcoord() + 60);
        
        // checks if projectile skiped over player space due to velocity and corrects for it
        if(yOffset > 90 && yOffset < 94 ){
            yOffset = 90;
        }
        
        //check if shot is in player area, return true if it is
        if(yOffset >= 0 && yOffset <= 90 && (abs(xOffset) - 4) <= (double)yOffset / 1.73077){
                return true;
        }
    }
}
