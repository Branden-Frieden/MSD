#include <iostream>
#include <vector>
#include <thread>
#include "/opt/homebrew/Cellar/libomp/16.0.2/include/omp.h"

template <typename T>
auto parallel_sum_std(T a[], size_t N, size_t num_threads){

    std::atomic<T> sum = 0.0;
    size_t blockLength = N/num_threads;

    std::vector<std::thread> threads;
    threads.reserve(num_threads);

    // create threads
    for(int i = 0; i < num_threads; i++){
        // calculate start and stop positions
        size_t start = i * blockLength;
        size_t end = (i+1) * blockLength - 1;

        if( i == num_threads-1)
            end = N;

        threads[i] = std::thread([&a, &sum, start, end](){
            T partialSum = 0;

            for(size_t j = start; j <= end; j++){
                partialSum += a[j];
            }

            sum += partialSum;
        });
    }

    // join the threads,
    for(int i = 0; i < num_threads; i++){
        threads[i].join();
    }
    T output = sum;
    return output;
}

template <typename T>
auto parallel_sum_omp1(T a[], size_t N, size_t num_threads){

    T sum = 0.0;
    size_t blockLength = N/num_threads;

    omp_set_num_threads((int) num_threads);
#pragma omp parallel
    {
        int id = omp_get_thread_num();
        size_t start = id * blockLength;
        size_t end = (id + 1) * blockLength;

        T partialSum = 0.0;
        if(id == num_threads - 1)
            end = N;

        for(size_t i = start; i < end; i++){
            partialSum += a[i];
        }

#pragma omp atomic
        sum += partialSum;
    }
    return sum;
}

template <typename T>
auto parallel_sum_omp_builtin(T a[], size_t N, size_t num_threads) {

    

}



int main() {
    size_t size = 10000;
    size_t numThreads = 10;
    int *a = new int[size];

    for(int i = 0; i < size; i++){
        a[i] = i + 1;
    }

    std::cout << "serial sum: " << parallel_sum_std(a, size, numThreads) << std::endl;
    std::cout << "parallel sum: " << parallel_sum_omp1(a, size, numThreads) << std::endl;

    return 0;
}
