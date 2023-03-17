//
// Created by Branden Frieden on 3/15/23.
//

#include "malloc.h"
#include <sys/mman.h>
#include <iostream>

Malloc::Malloc() {
    map = MyHash();
}

void* Malloc::allocate(size_t bytesToAllocate) {

    int pages = (bytesToAllocate + (4095)) / 4096;
    size_t allocateSize = (size_t) (pages * 4096);
    void* insertAddress = mmap(0, allocateSize, PROT_READ | PROT_WRITE, MAP_ANON | MAP_PRIVATE, 0, 0);

    if(insertAddress == MAP_FAILED){
        throw std::runtime_error("bad mmap");
    }

    HashNode newNode = HashNode(insertAddress, allocateSize);
    map.insert(newNode);
    return insertAddress;
}

void Malloc::deallocate(void* ptr) {
    HashNode node = map.find(ptr);

    if(node.address != nullptr){
        map.remove(ptr);
        munmap(node.address, node.size);
    }
}

