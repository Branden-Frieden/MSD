CXXSOURCE = ConcurrentQueueTest.o
CXXFLAGS = --std=c++14
HEADERS = SerialQueue.hpp ConcurrentQueue.h

all:build
build: $(CXXSOURCE)
	$(CXX) $(CXXFLAGS) -o build $(CXXSOURCE)

ConcurrentQueueTest.o: ConcurrentQueueTest.cpp $(HEADERS)
	$(CXX) $(CXXFLAGS) -c ConcurrentQueueTest.cpp

.PHONY: clean printM

clean:
	rm -f *.o *.out program
printM:
	echo $(CXXSOURCE)
