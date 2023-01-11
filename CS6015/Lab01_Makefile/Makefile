all:build
build: pants.o caps.o clothing.o main.o
	c++ $^ -o build
pants.o: pants.cpp pants.h
	c++ -c $<
caps.o: caps.cpp caps.h
	c++ -c $<
clothing.o: clothing.cpp clothing.h
	c++ -c $<
main.o: main.cpp
	c++ -c $<

.PHONY: clean printM

clean:
	rm -f *.o *.out program
printM:
	echo $(CXXSOURCE)
