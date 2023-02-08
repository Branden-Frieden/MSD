#include <iostream>
#include <sstream>
#include <cstdio>
#include <unistd.h>
#include <sys/wait.h>
#include <cstdlib>
#include <csignal>
#include <filesystem>
#include <sys/file.h>
#include <array>

int main(int argc, char *argv[]){


    int fd[2];
    if(pipe(fd) < 0){
        std::cout << "error in opening pipe";
        return -1;
    }

    int rc = fork();

    if(rc < 0){
        std::cout << "error\n";
    } else if(rc == 0){

        std::cout << "child\n";
        close(fd[1]);
        char buff[64];
        read(fd[0], buff, 64);

        std::cout << buff;
        fflush(stdout);

        close(fd[0]);

    } else{

        std::cout << "parent\n";
        fflush(stdout);
        close(fd[0]);
        write(fd[1], argv[1], strlen(argv[1]));

        wait(NULL);
        close(fd[1]);


    }

    return 0;
}