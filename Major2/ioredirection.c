//Name: Lluviana Vasquez
//User ID: lv0155

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <pthread.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <pwd.h>
#include <fcntl.h>
#include <sys/wait.h>
#include "major2.h"

//process for the input/output redirection


void processioredirection(char* userInput){

	//split it and get command redirection symbol and file name
	char *args[28];
	char* filename;
	//char delim[] = " ";

	filename = malloc(sizeof(char)*512);
	int index = 0;
	int test;
	args[index] = strtok(userInput, " ");
	while(1){
		index++;
			args[index]=strtok(NULL, " ");
				if(strcmp(args[index], ">")==0) {
					args[index] = NULL;
					test = 0;
					break;
				}
				if(strcmp(args[index], "<")==0) {
					args[index] = NULL;
					test = -1;
					break;
				}
	}


//output redirection
	pid_t id = fork();

	if (id == 0) {
		//>
		if(test == 0){
			//reads filename
			filename = strtok(NULL, " ");
			//to pipe
			int fd = open(filename, O_CREAT, O_WRONLY, O_TRUNC, 0644);
			if(fd < 0){
				printf("Could not run\n");
				exit(0);
			}
			//creates the pipe
			dup2(fd, 1);
			close(fd);
			execvp(args[0], args);
			printf("%s: command not found\n", args[0]);
			exit(0);
		}
		//<
		else{
			//reads filename
			filename = strtok(NULL, " ");
			//printf("Filename for input redirection %s: \n", filename);
			int fd2 = open(filename, O_RDONLY);
			//bad pipe
			if(fd2 < 0){
				printf("Could not run\n");
				exit(0);
			}

			dup2(fd2, 0);
			close(fd2);
			execvp(args[0], args);
			printf("%s: command not found\n", args[0]);
			exit(0);
		}
	  free(filename);
	}

    //parent waits for child??
    else if(id > 0){
	   wait(NULL);
	}

    else {
	  perror("fork error\n");
	}

}


