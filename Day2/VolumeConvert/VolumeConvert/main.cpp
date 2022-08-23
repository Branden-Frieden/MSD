//
//  main.cpp
//  VolumeConvert
//
//  Created by Branden Frieden and Mack Tawa on 8/23/22.
//

#include <iostream>

float ounces;
float cups;
float pints;
float gallons;
float liters;
float cubicInches;

int main() {
    std::cout << "type in the number of ounces to convert \n"; std::cin >> ounces;
    
    cups = ounces / 8;
    pints = ounces / 16;
    gallons = ounces / 128;
    liters = ounces * .0296;
    cubicInches = ounces * 1.8;
    
    std::cout << "Ounces: " << ounces << "\n";
    std::cout << "Cups: " << cups << "\n";
    std::cout << "Pints: " << pints << "\n";
    std::cout << "Gallons: " << gallons << "\n";
    std::cout << "Liters: " << liters << "\n";
    std::cout << "Cubic Inches: " << cubicInches << "\n";
    
    return 0;
}
