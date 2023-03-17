//
// Created by Branden Frieden on 3/15/23.
//

#include "myHash.h"
#include <sys/mman.h>

HashNode::HashNode(void* address, size_t size) {
    this->address = address;
    this->size = size;
}

MyHash::MyHash(){

    this->capacity = INITIAL_CAPACITY;
    this->size = 0;

    hashTable = (HashNode*) mmap(NULL, capacity, PROT_READ | PROT_WRITE, MAP_ANON | MAP_PRIVATE, 0, 0);

    for(int i = 0; i < capacity; i++){
        hashTable[i].address = nullptr;
        hashTable[i].size = NULL;
    }
}
int MyHash::hasher(void* obj){
    size_t size = reinterpret_cast<size_t> (obj);
    return (size ^ 2) % capacity;
}

void MyHash::insert(HashNode node){

    if(size >= capacity * .5)
        grow();

    int placement = hasher(node.address);

    while(hashTable[placement].size != NULL && hashTable[placement].size != -1){
        placement++;
    }
    hashTable[placement] = node;
    this->size++;
}


HashNode MyHash::find(void* ptr){
    int placement = hasher(ptr);

    while(true){
        int sizer = hashTable[placement].size;
        void* address = hashTable[placement].address;
        if(sizer == NULL){
            return HashNode(nullptr, NULL);
        } else if( address == ptr){
            return hashTable[placement];
        }
        placement = (placement + 1) % capacity;
    }
}

void MyHash::remove(void* ptr){
    int placement = hasher(ptr);

    while(true){
        if(hashTable[placement].size == NULL){
            return;
        } else if(hashTable[placement].address == ptr){
            hashTable[placement].address = nullptr;
            hashTable[placement].size = -1;
            size--;
            return;
        }
        placement = (placement + 1) % capacity;
    }
}

void MyHash::grow(){

    capacity *= 2;
    HashNode* tempHashTable = hashTable;

    hashTable = (HashNode*) mmap(NULL, capacity, PROT_READ | PROT_WRITE, MAP_ANON | MAP_PRIVATE, 0, 0);

    for(int i = 0; i < capacity/2; i++){

        HashNode node = tempHashTable[i];
        if(node.address == nullptr){
            continue;
        }

        size_t temp = reinterpret_cast<size_t> (node.address);
        int placement = (temp ^ 2) % capacity;

        while(hashTable[placement].size != NULL && hashTable[placement].size != -1){
            placement++;
        }
        hashTable[placement] = node;

    }

}