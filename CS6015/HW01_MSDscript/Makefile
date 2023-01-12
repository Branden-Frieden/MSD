all:msdscript
msdscript: cmdline.o main.o
	c++ $^ -o msdscript
cmdline.o: cmdline.cpp cmdline.h
	c++ -c $<
main.o: main.cpp
	c++ -c $<
.PHONY: clean
clean:
	rm -f *.o *.out program
