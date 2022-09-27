#include <SFML/Graphics.hpp>
#include <SFML/Window.hpp>
#include <SFML/System.hpp>
#include <vector>
#include "PlayerShip.hpp"
#include "Projectile.hpp"
#include "GameFunctions.hpp"
#include "InvaderShip.hpp"
#include <iostream>


int main()
{
    
    // seed rand
    std::srand((unsigned) time(0));
    
    // tests
    run_tests();
    
    // create the window
    sf::RenderWindow window(sf::VideoMode(1000, 1500), "My window");
    window.setFramerateLimit(180);
    
    // create text and images for the game to use
    sf::Text score;
    sf::Text accuracy;
    sf::Font font;
    if (!font.loadFromFile("arcade.ttf"))
    {
        return 1;
    }
    score.setFont(font);
    score.setCharacterSize(100);
    score.setFillColor(sf::Color::Red);
    score.setPosition(84, 42);
    
    accuracy.setFont(font);
    accuracy.setCharacterSize(100);
    accuracy.setFillColor(sf::Color::Red);
    accuracy.setPosition(748, 42);
    
    sf::Text game_over;
    game_over.setFont(font);
    game_over.setCharacterSize(250);
    game_over.setFillColor(sf::Color::Red);
    game_over.setPosition(250, 500);
    game_over.setString("GAME \nOVER");
    
    sf::Text end_score;
    end_score.setFont(font);
    end_score.setCharacterSize(100);
    end_score.setFillColor(sf::Color::Red);
    end_score.setPosition(325, 1100);
    
    sf::Text restart;
    restart.setFont(font);
    restart.setCharacterSize(50);
    restart.setFillColor(sf::Color::Red);
    restart.setPosition(300, 1375);
    restart.setString("PRESS R TO REPLAY");
    
    
    sf::Texture t;
    t.loadFromFile("GameBackground.png");
    sf::Sprite Background(t);
    Background.setScale(1500.0/512.0 , 1500.0/512.0);

    
    // create game objects and object vectors
    std::vector<Projectile> projectiles;
    std::vector<InvaderShip> invaders;
    std::vector<InvaderProjectile> invader_projectiles;
    
    // designate location to restart game and recreate the player ship
RESTART:
    PlayerShip player;
    accuracy.setPosition(748, 42);
    
    // run the program as long as the window is open
    while (window.isOpen())
    {
        // check all the window's events that were triggered since the last iteration of the loop
        sf::Event event;
        while (window.pollEvent(event))
        {
            
            // "close requested" event: we close the window
            if (event.type == sf::Event::Closed)
                
                window.close();
            
            // check what key the player pressed, and act accordingly
            if (event.type == sf::Event::KeyPressed){
                if (event.key.code == sf::Keyboard::Escape)
                    window.close();
                if (event.key.code == sf::Keyboard::Left)
                    player.MoveLeft();
                if (event.key.code == sf::Keyboard::Right)
                    player.MoveRight();
                if(event.key.code == sf::Keyboard::Space && player.getReload() >= (90 - player.getScoreInt() / 2)){
                    Projectile shot(player);
                    player.accuracy_increase();
                    projectiles.push_back(shot);
                    player.resetReload();
                }
                    
                    
            }
            
            // if move key is released, stop movement
            if (event.type == sf::Event::KeyReleased){
                if(event.key.code == sf::Keyboard::Right || event.key.code == sf::Keyboard::Left){
                    player.stop();
                }
            }
    
        }
        
        // generates invaders randomly
        InvaderSpawner(invaders, player);
        
        // check projectiles for out of bounds and updates
        ProjectileChecker(projectiles);
        
        // check invaders for out of bounds and shooting, then updates
        InvaderChecker(invaders, invader_projectiles, player);
        
        
        player.update();
        
        if(CollisionDetection(projectiles, invaders, invader_projectiles, player)){
            // updating accuracy pos
            accuracy.setPosition(200, 1225);
            accuracy.setString("accuracy: " + player.get_accuracy());
            // set score to players score
            end_score.setString("score: " + player.get_score());
            // put the text on the screen
            window.draw(Background);
            window.draw(end_score);
            window.draw(restart);
            window.draw(game_over);
            window.draw(accuracy);
            
            //display game over screen
            window.display();
            
            while (window.isOpen())
            {
                while (window.pollEvent(event))
                {
                    if (event.key.code == sf::Keyboard::Escape)
                        window.close();
                    if (event.key.code == sf::Keyboard::R){
                        invaders.clear();
                        invader_projectiles.clear();
                        projectiles.clear();
                        goto RESTART;
                    }
                }
            }
            window.close();
        }
        
        // print score
        score.setString(player.get_score());
        accuracy.setString(player.get_accuracy());
        

        // clear the window with black color
        window.clear(sf::Color::Black);
        window.draw(Background);

        //print all new objects
        window.draw(player.getShip());
        for(int i = 0; i < projectiles.size(); i++){
            window.draw(projectiles[i].getShot());
        }
        
        for(int i = 0; i < invaders.size(); i++){
            window.draw(invaders[i].getShip());
        }
        
        for(int i = 0; i < invader_projectiles.size(); i++){
            window.draw(invader_projectiles[i].getShot());
        }
        window.draw(score);
        window.draw(accuracy);
        
        
        
    // end the current frame
        window.display();
    }
 

    return 0;
}

