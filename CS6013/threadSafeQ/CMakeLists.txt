cmake_minimum_required(VERSION 3.24)
project(threadSafeQ)

set(CMAKE_CXX_STANDARD 17)

add_executable(threadSafeQ ConcurrentQueueTest.cpp)
