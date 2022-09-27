//
//  GameFunctions.hpp
//  CS6010_FinalProject
//
//  Created by Branden Frieden on 9/20/22.
//

#ifndef GameFunctions_hpp
#define GameFunctions_hpp

#include <stdio.h>
#include "Projectile.hpp"
#include "InvaderShip.hpp"
#include "InvaderProjectile.hpp"
#include "PlayerShip.hpp"

void run_tests();

void ProjectileChecker(std::vector<Projectile>& projectiles);

void InvaderSpawner(std::vector<InvaderShip>& invaders, const PlayerShip& player);

void InvaderChecker(std::vector<InvaderShip>& invaders, std::vector<InvaderProjectile>& invader_projectiles, const PlayerShip& Player);

bool CollisionDetection(std::vector<Projectile>& projectiles, std::vector<InvaderShip>& invaders, std::vector<InvaderProjectile>& invader_projectiles, PlayerShip& player);

#endif /* GameFunctions_hpp */
