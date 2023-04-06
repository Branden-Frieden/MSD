//
// Created by Branden Frieden on 4/6/23.
//

#include <vector>
#include <thread>

#include "SerialQueue.hpp"
#include "ConcurrentQueue.h"
#include <iostream>

bool testQueue(int num_producers, int num_consumers, int num_ints){
    std::vector<std::thread> threads;
    threads.reserve(num_consumers + num_producers);
    auto concurrentQueue = new ConcurrentQueue<int>();

    auto producer = [] (int num_ints, ConcurrentQueue<int>* q){
        for(int i = 0; i < num_ints; i++){
            q->enqueue(i);
        }
    };

    auto consumer = [] (int num_ints, ConcurrentQueue<int>* q){
        int check;
        for(int i = 0; i < num_ints; i++){
            q->dequeue(&check);
        }
    };

    for(int i = 0; i < num_producers; i++){
        threads.push_back(std::thread(producer,num_ints, concurrentQueue));
    }
    for(int i = 0; i < num_consumers; i++){
        threads.push_back(std::thread(consumer,num_ints, concurrentQueue));
    }

    for(int i = 0; i < num_producers+num_consumers; i++){
        threads[i].join();
    }


    return (concurrentQueue->size() == (num_producers - num_consumers) * num_ints);
}

int main(int argc, char *argv[]) {
    /////////////////////Serial Queue
    auto q = new SerialQueue<int>();

    assert(q->size() == 0);
    q->enqueue(5);
    assert(q->size() == 1);
    q->enqueue(6);
    assert(q->size() == 2);
    q->enqueue(-75);
    assert(q->size() == 3);

    int check;
    assert(q->dequeue(&check));
    assert(check == 5);
    assert(q->size() == 2);
    assert(q->dequeue(&check));
    assert(check == 6);
    assert(q->size() == 1);
    assert(q->dequeue(&check));
    assert(check == -75);
    assert(q->size() == 0);
    assert(!q->dequeue(&check));
    assert(check == -75);
    assert(q->size() == 0);

    ///////////////// Concurrent Queue tests


    if(argc != 4){
        std::cout << "invalid inputs, please put num_producers num_consumers num_ints";
    }

    assert(testQueue(atoi(argv[1]), atoi(argv[2]), atoi(argv[3])));

    std::cout << "tests passed";

    return 0;

}
