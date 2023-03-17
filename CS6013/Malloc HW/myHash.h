//
// Created by Branden Frieden on 3/15/23.
//

#ifndef MALLOC_HW_MYHASH_H
#define MALLOC_HW_MYHASH_H

#include <cstddef>

class HashNode{
public:
    void* address;
    size_t size;

    HashNode(void* address, size_t size);
};

class MyHash {
public:

    int INITIAL_CAPACITY = 4;
    int capacity;
    int size;
    bool isDeleted;

    HashNode* hashTable;
    MyHash();
    int hasher(void*);
    void insert(HashNode);
    HashNode find(void* ptr);
    void remove(void* ptr);
    void grow();


};




#endif //MALLOC_HW_MYHASH_H
