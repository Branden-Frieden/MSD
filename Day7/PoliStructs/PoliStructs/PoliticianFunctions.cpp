//
//  PoliticianFunctions.cpp
//  PoliStructs
//
//  Created by Branden Frieden on 8/30/22.
//

#include "PoliticianFunctions.hpp"


bool IsJavacan(Politician pol){
    return pol.party == "Javacan";
}

bool IsFederal(Politician pol){
    return pol.state_or_federal == "Federal";
}

std::vector<Politician> Javacans(std::vector<Politician> all_Politicians){
    
    std::vector<Politician> javacan_politicians;
    
    for(int i = 0; i < all_Politicians.size(); i++){
        if(IsJavacan(all_Politicians[i])){
            javacan_politicians.push_back(all_Politicians[i]);
        }
    }
    
    return javacan_politicians;
}

std::vector<Politician> federalCplusers(std::vector<Politician> all_Politicians){
    std::vector<Politician> federal_Cplusers;
    for(int i = 0; i < all_Politicians.size(); i++){
        if(!IsJavacan(all_Politicians[i]) && IsFederal(all_Politicians[i])){
            federal_Cplusers.push_back(all_Politicians[i]);
        }
    }

    return federal_Cplusers;
}

std::vector<Politician> CreatePoliticianVector(){
    std::vector<Politician> politicians;
    
    politicians.push_back(Politician {"James", "Javacan", "Federal"});
    politicians.push_back(Politician {"Branden", "Cpluser", "Federal"});
    politicians.push_back(Politician {"Ricardo", "Javacan", "state"});
    politicians.push_back(Politician {"Mack", "Cpluser", "State"});
    politicians.push_back(Politician {"Varun", "Javacan", "Federal"});
    politicians.push_back(Politician {"Randi", "Cpluser", "Federal"});
    politicians.push_back(Politician {"Katie", "Cpluser", "Federal"});
    
    return politicians;
}


