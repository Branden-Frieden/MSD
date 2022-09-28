#include <SFML/Graphics.hpp>

int main()
{
    // create the window
    sf::RenderWindow window(sf::VideoMode(800, 600), "My window");

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
        }

        // clear the window with black color
        window.clear(sf::Color::Black);

        sf::CircleShape shape(50,3);
        shape.setPosition(0, 0);
        sf::RectangleShape shape2(sf::Vector2f(100, 50));
        shape2.setPosition(100, 100);

// set the shape color to green
        shape.setFillColor(sf::Color(100, 250, 50));
        shape2.setFillColor(sf::Color(120, 200, 200));
        
        window.draw(shape);
        window.draw(shape2);

	// end the current frame
        window.display();
    }

    return 0;
}