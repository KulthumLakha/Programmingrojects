/*
 * Group 6
 * Lluviana Vasquez, Boniya Philomon, Jack Henderson, and Kulthum Lakha
 * CSCE 3600.002
 * 11/28/2021
 * This code file contains code for execution of commands by forking.
 */

// Include header file.
#include "major2.h"

//Execute commands that are not built-in.
void executeCommand(char* toks[10])
{	
	
	int status; // wait status on child process

	// create child process for running the shell command
	pid_t pid = fork();

	// If process is a child, execute shell command.
	if (pid == 0)
	{
		/******************************kyl0029*****************************/
                /**************************signal handling*************************/

		signal(SIGINT, SIG_DFL);	// Command gets ctrl+c signals
		signal(SIGSTOP, SIG_DFL);	// Command gets kill signals
		signal(SIGTSTP, SIG_DFL);	// Command gets ctrl+z signals
		
		/*******************************************************************/

		// Execute shell command and check for error
		if (execvp(toks[0], toks) == -1)
			perror("Command does not exist.\n");
		exit(0); // child ends
	}
	else if (pid > 0) 	// If process is a parent, wait for child.
		wait(&status);
	else 	// child process creation failed
	{
		perror("fork");
		exit(-2);
	}
}

