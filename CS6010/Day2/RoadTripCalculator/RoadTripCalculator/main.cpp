//
//  main.cpp
//  RoadTripCalculator
//
//  Created by Branden Frieden on 8/23/22.
//

#include <iostream>

int   milesDriven;
float milesPerGallonEfficiency;
float costOfGas;    //dollars
float gallonsUsed;
float totalCost;    //dollars

int main() {
    std::cout << "how far did you drive (in whole miles): \n"; std::cin >> milesDriven;
    std::cout << "what is your car's MPG effeciency: \n";      std::cin >> milesPerGallonEfficiency;
    std::cout << "what is the cost of gas in $/gallon: \n";    std::cin >> costOfGas;
    
    gallonsUsed = milesDriven/milesPerGallonEfficiency;
    totalCost   = gallonsUsed * costOfGas;
    
    std::cout << "the total cost of your trip comes out to $" << totalCost << "\n";
    
    return 0;
}
