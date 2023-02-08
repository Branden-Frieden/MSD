CXXSOURCE = main.o
CXXFLAGS = --std=c++14
HEADERS =
EXECUTABLE = ./build

all: $(EXECUTABLE)
	
all: build
build: $(CXXSOURCE)
	$(CXX) $(CXXFLAGS) -o build $(CXXSOURCE)
	
main.o: main.cpp
	$(CXX) $(CXXFLAGS) -c main.cpp

clean:
	rm -f *.o *.out build
