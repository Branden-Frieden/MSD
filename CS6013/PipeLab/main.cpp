#include <iostream>
#include <unistd.h>
#include <sys/wait.h>
#include <array>

int main(int argc, char *argv[]){

    int fd[2];
    if(pipe(fd) < 0){
        perror("error in opening pipe");
        exit(1);
    }
    int rc = fork();

    if(rc < 0){
        perror("error in forking");
        exit(1);
    } else if(rc == 0){

        close(fd[1]);
        int size = 0;
        read(fd[0], &size, sizeof(size));
        char buff[size];
        read(fd[0], buff, size);
        std::cout << buff;
        close(fd[0]);

    } else{
        if(argc < 2){
            perror("no printable argument given");
            exit(1);
        }
        close(fd[0]);
        int size = strlen(argv[1]);
        write(fd[1], &size , sizeof(size));
        write(fd[1], argv[1], strlen(argv[1]));
        wait(NULL);
        close(fd[1]);

    }

    return 0;
}