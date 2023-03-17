//
// Created by Branden Frieden on 3/15/23.
//

#ifndef MALLOC_HW_MALLOC_H
#define MALLOC_HW_MALLOC_H

#include <cstddef>
#include <sys/mman.h>
#include "myHash.h"

class Malloc {
public:

    MyHash map;

    Malloc();
    void* allocate(size_t bytesToAllocate);
    void deallocate(void* ptr);
};


#endif //MALLOC_HW_MALLOC_H
