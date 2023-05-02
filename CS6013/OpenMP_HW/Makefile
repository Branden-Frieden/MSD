CXX = /opt/homebrew/opt/llvm/bin/clang++
CFLAGS = -std=c++20
OPENMP_COMPILING = -fopenmp
OPENMP_LINKING = -lomp
OPEN_LIBRARY_PATH = -L/opt/homebrew/opt/libomp/lib

all:build
build: main.o
	$(CXX) $(CFLAGS) main.o $(LINKING) build $(OPENMP_LIB_PATH) $(OPENMP_LINKING)

main.o: $(HEADERS) $(CXXSOURCE) main.cpp
	$(CXX) $(CFLAGS) $(COMPILING) main.cpp

.PHONY: clean printM

clean:
	rm -f *.o *.out program
printM:
	echo $(CXXSOURCE)


