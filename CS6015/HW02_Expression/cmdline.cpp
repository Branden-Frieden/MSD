#define CATCH_CONFIG_RUNNER
#include "cmdline.h"
#include "expr.h"
#include "catch.h"
#include <iostream>


void use_arguments(int argc, char** argv){
    bool tested = false;
    for(int i = 1; i < argc; i++)
    {
        if(strcmp(argv[i], "--help") == 0)
        {
            std::cout << "The following commands are allowed\n--help provides allowed commands\n--test runs tests on the program\n";
            exit(1);
        }
        else if(strcmp(argv[i], "--test") == 0)
        {
            if(tested)
            {
                std::cerr << "Already tested, exiting...";
                exit(1);
            }

            Catch::Session().run(1, argv);

            std::cout << "Tests passed\n";
            tested = true;
        }
        else
        {
            std::cerr << "argument not recognized, exiting...";
            exit(1);
        }
    }
}

TEST_CASE( "equals" ) {
    CHECK( (new NumExpr(1))->equals(new NumExpr(1)) == true );
    CHECK( (new NumExpr(1))->equals(new NumExpr(2)) == false );
    CHECK( (new VarExpr("x"))->equals(new VarExpr("y")) == false );
    CHECK( (new VarExpr("x"))->equals(new NumExpr(1)) == false );
    CHECK( (new VarExpr("x"))->equals(new VarExpr("x")) == true );
    CHECK( (new AddExpr(new NumExpr(2),new NumExpr(3)))->equals(new AddExpr(new NumExpr(2),new NumExpr(3)))==true );
    CHECK( (new AddExpr(new NumExpr(2),new NumExpr(3)))->equals(new AddExpr(new NumExpr(3),new NumExpr(2)))==false );
    CHECK( (new MultExpr(new NumExpr(2),new NumExpr(2)))->equals(new AddExpr(new NumExpr(1),new NumExpr(2)))==false );
    CHECK( (new MultExpr(new NumExpr(2),new NumExpr(2)))->equals(new AddExpr(new NumExpr(2),new NumExpr(2)))==false );
    CHECK( (new AddExpr(new NumExpr(2),new NumExpr(2)))->equals(new MultExpr(new NumExpr(2),new NumExpr(2)))==false );
    CHECK( (new MultExpr(new NumExpr(2),new NumExpr(2)))->equals(new MultExpr(new NumExpr(2),new NumExpr(2)))==true );
    CHECK( (new MultExpr(new NumExpr(1),new NumExpr(2)))->equals(new MultExpr(new NumExpr(2),new NumExpr(1)))==false );
}