#include <iostream>
#include "malloc.h"
#include <chrono>

void runTests(){
    Malloc manager = Malloc();

    // Run Tests

    /// allocate

    std::string toStore = "data of length 17";
    char* startPoint = (char*) manager.allocate((size_t) toStore.length());
    for(int i = 0; i < toStore.length(); i++){
        startPoint[i] = toStore[i];
    }

    std::string recreatedString = "";
    for(int i = 0; i < toStore.length(); i++){
        recreatedString += startPoint[i];
    }

    std::cout << recreatedString;


    // test 2
    std::string toStore2 = "data with length 19";
    char* startPoint2 = (char*) manager.allocate((size_t) toStore2.length());
    for(int i = 0; i < toStore2.length(); i++){
        startPoint2[i] = toStore2[i];
    }

    recreatedString = "";
    for(int i = 0; i < toStore2.length(); i++){
        recreatedString += startPoint2[i];
    }
    std::cout << recreatedString;


    // test 3
    std::string toStore3 = "other data with length 25";
    char* startPoint3 = (char*) manager.allocate((size_t) toStore3.length());
    for(int i = 0; i < toStore3.length(); i++){
        startPoint3[i] = toStore3[i];
    }

    recreatedString = "";
    for(int i = 0; i < toStore3.length(); i++){
        recreatedString += startPoint3[i];
    }
    std::cout << recreatedString;

    // check again after growth
    std::cout << std::endl;

    recreatedString = "";
    for(int i = 0; i < toStore.length(); i++){
        recreatedString += startPoint[i];
    }
    for(int i = 0; i < toStore2.length(); i++){
        recreatedString += startPoint2[i];
    }
    for(int i = 0; i < toStore3.length(); i++){
        recreatedString += startPoint3[i];
    }

    std::cout << recreatedString;

    /// deallocate

    // print hash table
    std::cout << std::endl << std::endl;

    for(int i = 0; i < manager.map.capacity; i++){
        std::cout << manager.map.hashTable[i].address << " " << manager.map.hashTable[i].size << std::endl;
    }

    manager.deallocate(startPoint);
    manager.deallocate(startPoint2);
    manager.deallocate(startPoint3);


    std::cout << std::endl << std::endl;

    for(int i = 0; i < manager.map.capacity; i++){
        std::cout << manager.map.hashTable[i].address << " " << manager.map.hashTable[i].size << std::endl;
    }

    /// grow in presence of lazy deleted data
    void* point1 = manager.allocate(4096);
    void* point2 = manager.allocate(4095);
    void* point3 = manager.allocate(4097);
    void* point4 = manager.allocate(8192);
    void* point5 = manager.allocate(8193);

    std::cout << std::endl << std::endl;

    for(int i = 0; i < manager.map.capacity; i++){
        std::cout << manager.map.hashTable[i].address << " " << manager.map.hashTable[i].size << std::endl;
    }


    /// Benchmark
    auto myTimeStart = std::chrono::high_resolution_clock::now();
    manager.allocate(4096);
    auto myTimeEnd = std::chrono::high_resolution_clock::now();
    auto builtInTimeStart = std::chrono::high_resolution_clock::now();
    malloc(4096);
    auto builtInTimeEnd = std::chrono::high_resolution_clock::now();

    long myTime = std::chrono::duration_cast<std::chrono::nanoseconds>(myTimeEnd-myTimeStart).count();
    long builtInTime = std::chrono::duration_cast<std::chrono::nanoseconds>(builtInTimeEnd-builtInTimeStart).count();

    std::cout << "my malloc time: " + std::to_string(myTime) + " ns VS built-in malloc time: " + std::to_string(builtInTime) + "ns";

}


int main() {

    runTests();

    return 0;
}
