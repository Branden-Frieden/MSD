CXX = /opt/homebrew/opt/llvm/bin/clang++
CFLAGS = -std=c++20
OPENMP_COMPILING = -fopenmp
OPENMP_LINKING = -lomp
OPEN_LIBRARY_PATH = -L/opt/homebrew/opt/libomp/lib
LINKING = -o
COMPILING = -c
OPENMP_LIB_PATH = -L/opt/homebrew/opt/libomp/lib

$(info CXX path: $(shell which $(CXX)))

all:build
build: main.o
	clang++ $(CFLAGS) main.o $(LINKING) build $(OPENMP_LIB_PATH) $(OPENMP_LINKING)

main.o: $(HEADERS) $(CXXSOURCE) main.cpp
	$(CXX) $(CFLAGS) $(COMPILING) $(OPENMP_COMPILING) main.cpp

.PHONY: clean printM

clean:
	rm -f *.o *.out build
printM:
	echo $(CXXSOURCE)


