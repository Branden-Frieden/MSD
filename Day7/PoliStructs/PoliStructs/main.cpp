//
//  main.cpp
//  PoliStructs
//
//  Created by Branden Frieden on 8/30/22.
//

#include <iostream>
#include "PoliticianFunctions.hpp"

int main(int argc, const char * argv[]) {
    
    //initialize vectors
    std::vector<Politician> politicians;
    std::vector<Politician> javacan_politicians;
    std::vector<Politician> federal_Cplusers;
    
    // create the base politicians vector
    politicians = CreatePoliticianVector();
    
    //___________________________________ testing sub functions
    
    // should evaluate to falsse because Branden is a Cpluser
    if(IsJavacan(politicians[1])){
        std::cout << "IsJavacan() - test 1\n";
        return 1;
    }
    
    // should evaluate to false because James is Javacan
    if(!IsJavacan(politicians[0])){
        std::cout << "IsJavacan() - test 2\n";
        return 2;
    }
    
    // should evaluate to false because James is Federal
    if(!IsFederal(politicians[0])){
        std::cout << "IsFederal() - test 1\n";
        return 3;
    }
    
    // should evaluate to false becuase Ricardo is State
    if(IsFederal(politicians[2])){
        std::cout << "IsFederal() - test 2\n";
        return 4;
    }
    
    //_______________________ call functions to create sub vectors
    javacan_politicians = Javacans(politicians);
    
    federal_Cplusers = federalCplusers(politicians);
    
    //______________________________________ test vectors for accuracy
    if(javacan_politicians[0].name != "James"){
        std::cout << "Javacans - test 1\n";
        return 5;
    }
    if(javacan_politicians[1].name != "Ricardo"){
        std::cout << "Javacans - test 2\n";
        return 6;
    }
    
    if(federal_Cplusers[0].name != "Branden"){
        std::cout << "federalCplusers() - test 1\n";
        return 7;
    }
    if(federal_Cplusers[1].name != "Randi"){
        std::cout << "federalCplusers() - test 2\n";
        return 8;
    }
    
    //______________________________ print the sub vectors to ensure the programs work correctly
    std::cout << "Javacan Politicians: \n";
    for(Politician s : javacan_politicians){
        std::cout << s.name << std::endl;
    }
    
    std::cout << "Federal Cpluser Politicians: \n";
    for(Politician s : federal_Cplusers){
        std::cout << s.name << std::endl;
    }
    
    
    
    
    
    return 0;
}
