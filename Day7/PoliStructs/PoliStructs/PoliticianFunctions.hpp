//
//  PoliticianFunctions.hpp
//  PoliStructs
//
//  Created by Branden Frieden on 8/30/22.
//

#ifndef PoliticianFunctions_hpp
#define PoliticianFunctions_hpp

#include <stdio.h>
#include <string>
#include <vector>

struct Politician {
    std::string name;
    std::string party;
    std::string state_or_federal;
};

bool IsJavacan(Politician pol);

bool IsFederal(Politician pol);

std::vector<Politician> Javacans(std::vector<Politician>);

std::vector<Politician> federalCplusers(std::vector<Politician>);

std::vector<Politician> CreatePoliticianVector();

#endif /* PoliticianFunctions_hpp */
