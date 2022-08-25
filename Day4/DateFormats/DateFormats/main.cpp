//
//  main.cpp
//  DateFormats
//
//  Created by Mack Tawa, Branden Frieden, Ricardo Sanchezyepez 8/25/22.
//

#include <iostream>
using namespace std;
int main() {
    string dateInput;
    string monthWord;
 
    
    
// Asking for input
    cout << "Enter a date in mm/dd/yyyy format. \n";
    cin >> dateInput;
// creating 2 letter string from their input
    monthWord = dateInput[0];
    monthWord += dateInput[1];
// allocating a month to the dates
    if (monthWord == "01") {
        cout << "January";
    }
    else if (monthWord == "02") {
        cout << "February";
    }
    else if (monthWord == "03") {
        cout << "March";
    }
    else if (monthWord == "04") {
        cout << "April";
    }
    else if (monthWord == "05") {
        cout << "May";
    }
    else if (monthWord == "06") {
        cout << "June";
    }
    else if (monthWord == "07") {
        cout << "July";
    }
    else if (monthWord == "08") {
        cout << "August";
    }
    else if (monthWord == "09") {
        cout << "September";
    }
    else if (monthWord == "10") {
        cout << "October";
    }
    else if (monthWord == "11") {
        cout << "November";
    }
    else (cout << "December");
// outputting desired formatted date
    cout << " " << dateInput[3] << dateInput[4] << ", " << dateInput[6] << dateInput[7] << dateInput[8] << dateInput[9] << endl;
    
    
    return 0;
}
