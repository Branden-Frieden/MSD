#include <iostream>
#include <unistd.h>
#include <sys/wait.h>
#include <array>

int main(int argc, char *argv[]){

    // initialize pipe
    int fd[2];
    if(pipe(fd) < 0){
        perror("error in opening pipe");
        exit(1);
    }
    // fork to create parent and child
    int rc = fork();

    // check for in parent or child
    if(rc < 0){
        perror("error in forking");
        exit(1);
    } else if(rc == 0){

        close(fd[1]);

        // read in message length
        int size = 0;
        read(fd[0], &size, sizeof(size));

        // read in message
        char buff[size];
        read(fd[0], buff, size);
        std::cout << buff;
        close(fd[0]);

    } else{
        // check for argument error
        if(argc < 2){
            perror("no printable argument given");
            exit(1);
        }
        close(fd[0]);

        // send size of message
        int size = strlen(argv[1]);
        write(fd[1], &size , sizeof(size));

        // send message
        write(fd[1], argv[1], strlen(argv[1]));
        wait(NULL);
        close(fd[1]);

    }

    return 0;
}