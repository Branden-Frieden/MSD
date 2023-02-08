CXXSOURCE = main.o shelpers.o
CXXFLAGS = --std=c++14
HEADERS = shelpers.hpp
EXECUTABLE = ./build

all: $(EXECUTABLE)
	
all: build
build: $(CXXSOURCE)
	$(CXX) $(CXXFLAGS) -o build $(CXXSOURCE)
	
main.o: main.cpp $(HEADERS)
	$(CXX) $(CXXFLAGS) -c main.cpp
	
shelpers.o: shelpers.cpp $(HEADERS)
	$(CXX) $(CXXFLAGS) -c shelpers.cpp

clean:
	rm -f *.o *.out build
