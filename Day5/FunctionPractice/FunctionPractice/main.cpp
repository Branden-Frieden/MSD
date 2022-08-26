//
//  main.cpp
//  FunctionPractice
//
//  Created by Branden Frieden on 8/26/22.
//

#include <iostream>
#include <cmath>
#include <ctime>

using namespace std;

float hypotenuse_calculator(float right_angle_side_1, float right_angle_side_2){
    //initialize result variable
    float hypotenuse;
    
    hypotenuse = sqrt(pow(right_angle_side_1, 2) + pow(right_angle_side_2, 2));
    
    //return result
    return hypotenuse;
}

// function to determine if a string starts with a capital letter
bool isCapitalized(std::string input_string){
    
    if(isupper(input_string[0])){
        return true;
    }
    else{
        return false;
    }
}

// function to change a bool true to string "true" and bool false to string "false"
string boolToString(bool input_bool){
    
    if(input_bool){
        return "true";
    }
    else{
        return "false";
    }
}

// function to take user input integer and returns whether it is prime or not
bool isPrime(int input_number){
    
    // loop through all devisions for a number, if any don't have a remainder, then the number can't be a prime number
    for(int i = 2;i < input_number; i++){
        if(input_number % i == 0){
            return false;
        }
    }
    return true;
}

int main(int argc, const char * argv[]) {
    
    //initialize variables
    float right_angle_side_1;
    float right_angle_side_2;
    float hypotenuse;
    
    double user_speed;
    double user_angle;
    double x_velocity;
    double y_velocity;
    
    // prompt and store user value for the right-angle side lengths of a triangle
    std::cout << "Please enter the 2 right side angles of the triangle \n";
    std::cin >> right_angle_side_1 >> right_angle_side_2;
    
    // calculate hypotenuse using pythogorian theorum
    hypotenuse = sqrt(pow(right_angle_side_1, 2) + pow(right_angle_side_2, 2));
    
    // print hypotenuse result
    std::cout << "The hypotenuse of your triagle is: " << hypotenuse << std::endl;
    
    //prompt user for the speed their going and then the angle
    std::cout << "please enter the speed you are going\n"; std::cin >> user_speed;
    std::cout << "please enter the angle you are going\n"; std::cin >> user_angle;
    
    //calculate the velocities based on speed and angle
    x_velocity = user_speed * std::cos(user_angle);
    y_velocity = user_speed * std::sin(user_angle);
    
    // print x and y velocity
    std::cout << "Your x velocity is: " << x_velocity << "\nYour y velocity is: " << y_velocity << std::endl;
    
    
    
    /* it would be difficult to do the speed/velocity task as a function because you need 2 outputs when a function will only get you one. you could output it as and array or you could use pointers and references to change the variable globally within the function. */
    
    std::time_t result = std::time(nullptr);
    std::cout << std::asctime(std::localtime(&result))
              << result << " seconds since the Epoch\n";
    // this code snippit uses the time function, the asctime function and the localtime function.
    
    return 0;
}
