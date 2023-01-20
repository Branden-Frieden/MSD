CXXSOURCE = cmdline.o main.o
CXXFLAGS = --std=c++14
HEADERS = expr.h cmdline.h catch.h

all:build
build: $(CXXSOURCE)
	$(CXX) $(CXXFLAGS) -o build $(CXXSOURCE)

cmdline.o: cmdline.cpp $(HEADERS)
	$(CXX) $(CXXFLAGS) -c cmdline.cpp

main.o: main.cpp $(HEADERS)
	$(CXX) $(CXXFLAGS) -c main.cpp

.PHONY: clean printM

clean:
	rm -f *.o *.out program
printM:
	echo $(CXXSOURCE)

