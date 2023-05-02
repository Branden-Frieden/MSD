#include <iostream>
#include <vector>
#include <thread>
#include <omp.h>



template <typename T>
auto parallel_sum_std(T a[], size_t N, size_t num_threads){

    auto startTime = std::chrono::high_resolution_clock::now();
    std::atomic<T> sum = 0.0;
    size_t blockLength = N/num_threads;

    std::vector<std::thread> threads;
    threads.resize(num_threads);


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

    auto end = std::chrono::high_resolution_clock::now();
    auto time = end - startTime;


    struct sumReturn {
        T sum;
        double time;
    };

    sumReturn output;
    output.sum = sum;
    output.time = time.count();

    return output;
}

template <typename T>
auto parallel_sum_omp1(T a[], size_t N, size_t num_threads){

    auto start = std::chrono::high_resolution_clock::now();

    T sum = 0.0;
    size_t blockLength = N/num_threads;

    omp_set_num_threads((int) num_threads);
#pragma omp parallel
    {
        int id = omp_get_thread_num();
        size_t start = id * blockLength;
        size_t end = (id + 1) * blockLength;

        `T partialSum = 0.0;`
        if(id == num_threads - 1)
            end = N;

        for(size_t i = start; i < end; i++){
            partialSum += a[i];
        }

#pragma omp atomic
        sum += partialSum;
    }
    auto end = std::chrono::high_resolution_clock::now();
    auto time = end - start;

    struct sumReturn {
        T sum;
        double time;
    };

    sumReturn output;
    output.sum = sum;
    output.time = time.count();

    return output;
}

template <typename T>
auto parallel_sum_omp_builtin(T a[], size_t N, size_t num_threads) {

    auto start = std::chrono::high_resolution_clock::now();

    T sum = 0.0;
    size_t i;

    omp_set_num_threads((int) num_threads);
#pragma omp parallel for reduction (+:sum)
        for (i = 0; i < N; i++) {
            sum = sum + a[i];
        }


    auto end = std::chrono::high_resolution_clock::now();
    auto time = end - start;

    struct sumReturn {
        T sum;
        double time;
    };

    sumReturn output;
    output.sum = sum;
    output.time = time.count();


    return output;
}



int main() {

    ///////////Analysis
    /*
    /// strong scaling

    for (size_t N = 100000; N <= 400000; N += 100000) {


        int *a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = i + 1;
        }


        for (size_t numThreads = 1; numThreads < 16; numThreads++) {
            auto stdOutput = parallel_sum_std(a, N, numThreads);
            auto ompOutput = parallel_sum_omp1(a, N, numThreads);
            auto reducedOutput = parallel_sum_omp_builtin(a, N, numThreads);

            std::cout << stdOutput.time / 1000 << "\t" << ompOutput.time / 1000 << "\t" << reducedOutput.time / 1000 << "\n";
        }

        std::cout << std::endl;

        delete[] a;
    }
     */


    /// weak scaling
    for (size_t numThreads = 1; numThreads < 50; numThreads++) {

        size_t N = numThreads * 100000;

        int *a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = i + 1;
        }

        auto stdOutput = parallel_sum_std(a, N, numThreads);
        auto ompOutput = parallel_sum_omp1(a, N, numThreads);
        auto reducedOutput = parallel_sum_omp_builtin(a, N, numThreads);

        std::cout << stdOutput.time / 1000 << "\t" << ompOutput.time / 1000 << "\t" << reducedOutput.time / 1000
                  << "\n";


        delete[] a;
    }

}
