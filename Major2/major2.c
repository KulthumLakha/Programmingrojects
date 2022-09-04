/*
 * Group 6
 * Jack Henderson, Lluviana Vasquez, Boniya Philomon, and Kulthum Lakha
 * CSCE 3600.002
 * 11/28/2021
 * This code file simulates a simple linux shell.
 */

 // Include header file.
#include "major2.h"

// Define constants
#define a 50
#define b 100

char History[20][512];
int ind = -1;


// Main Function
int main(int argc, char** argv)
{

	// Kulthum Lakha - Signal Handling
	signal(SIGINT, SIG_IGN);		// Our shell ignores ctrl+c signals
	signal(SIGSTOP, SIG_IGN);		// Our shell ignores kill signals
	signal(SIGTSTP, SIG_IGN);		// Our shell ignores ctrl+z signals

	int exit_flag = 0;

	if (argc == 2)										// Batch mode
	{
		printf("\n---Batch Mode---\n");

		char input[b]; 		// Command entered 
		char list[a][b];						// Char List
		int fore = 0;
		int back = 0;

		for (int i = 0; i < 20; i++) 	// Get history of 1st 20 commands
		{
			History[i][0] = '\0';
		}

		// Open file to read from
		FILE *file;		
		file = freopen(argv[1], "r", stdin);
		if (file == NULL)							// Batch file doesn't exist
		{
			perror("The file doesn't exist.");
			exit(0);
		}

		// read each line from file
		while(!feof(file))
		{
			scanf("%[^\n]", input); 		// read line from user
			printf("\nCommand to be executed: %s\n", input);	// Print command
			getchar();				// Get characters
			writeHistory(input);   			// Write command in history
			

			/****************Luviana Vasquez******************/
			//handle the redirection operators 
			if (strstr(input, ">") || strstr(input, "<"))
				processioredirection(input);

			if (strcmp(input, "exit") == 0)
				exit_flag++;

			if (exit_flag == 1)
				exit(0);
			/*****************************************************/


			// Shell accepts multiple commands separated by semicolons(;)
			memset(list, 0, sizeof(list[0][0]) * a * b);

			int counter = 0;						// Initialize index counter
			char* split = strtok(input, ";");		// Split command into tokens using ; as a delimiter

			while (split != NULL)					// while split has commands
			{
				strcpy(list[counter], split);		// Copy it to the list at specified index
				counter++;							// Increment index counter
				split = strtok(NULL, ";");			// Update split
			}

			int index = 0;
			// Iterate through list to execute commands
			while ((index < sizeof(list)) && (strcmp(list[index], "\0") != 0))
			{
				//checking for a blank command as a space
				if (strcmp(list[index], " ") == 0)
					goto skip;

				char* toks[10];
				char* tkn = strtok(list[index], " ");

				// If a command is empty, skip it.
				if (tkn == NULL || list[index] == NULL)
					continue;

				// Check for multiple commands
				int multiple = 0;
				if (strcmp(list[index + 1], "\0") != 0)
					multiple = 1;
				else if (strcmp(list[index + 1], "\0") == 0)
					multiple = 0;

				if (!strcmp(tkn, "quit"))
					break;

				//if there is exit but multiple commands
				if ((strcmp(tkn, "exit") == 0) && (multiple != 0))
				{
					fore = 1;			// set fore as 1 and 
					goto skip;
				}

				// There is just one command so exit.
				if (((strcmp(list[index], "\0")) != 0) && (fore == 1) && (multiple == 0))
					back = 1;

				// Array of commands and arguments
				int i = 0;
				while (tkn != NULL)
				{
					toks[i] = tkn;
					i += 1;
					tkn = strtok(NULL, " ");
				}
				toks[i] = NULL;


				/***************************************
				**************Kulthum Lakha*************
				****************kyl0029*****************
				****************************************/
				// cd if directory is not included
				if ((strcmp(toks[0], "cd") == 0) && (toks[1] == NULL))
				{
					chdir(getenv("HOME")); 	// Call chdir() to home directory.
				}
				// cd if directory included
				else if ((strcmp(toks[0], "cd") == 0) && (toks[1] != NULL))
				{
					if (chdir(toks[1]) != 0) // Print error message if directory is not valid
						perror("Entered directory does not exist!\n");
					else 		// Else call chdir() to change directory.
						chdir(toks[1]);
				}
				/*************************************************************************/

				// Jack Henderson
				else if ((strcmp(toks[0], "myhistory") == 0) && (toks[1] == NULL))
					printhist();

				else if (strcmp(toks[0], "cd") != 0)		//To avoid double execution of cd
					// Execute multiple commands
					executeCommand(toks);

				if (back != 0)            // If multiple commands are executed already and one of them was exit, exit.
					exit(0);

				skip:
				index += 1;
			}
		}
		//fclose(file);
	}
	else if (argc == 1)									// Interactive mode
	{
		printf("\n---Interactive Mode---\n");

		char input[b]; 		// Command entered 
		char list[a][b];						// Char List
		int fore = 0;
		int back = 0;

		for (int i = 0; i < 20; i++) 	// Get history of 1st 20 commands
		{
			History[i][0] = '\0';
		}

		while (1)							// Infinite loop
		{
			input[0] = '\0';

			printf("newshell> "); 			// print name of shell
			scanf("%[^\n]", input); 		// read line from user
			getchar();						// Get characters
			writeHistory(input);   			// Write command in history


			/****************Luviana Vasquez******************/
			//handle the redirection operators 
			if (strstr(input, ">") || strstr(input, "<"))
				processioredirection(input);

			if (strcmp(input, "exit") == 0)
				exit_flag++;

			if (exit_flag == 1)
				exit(0);
			/*****************************************************/


			// Shell accepts multiple commands separated by semicolons(;)
			memset(list, 0, sizeof(list[0][0]) * a * b);

			int counter = 0;						// Initialize index counter
			char* split = strtok(input, ";");		// Split command into tokens using ; as a delimiter

			while (split != NULL)					// while split has commands
			{
				strcpy(list[counter], split);		// Copy it to the list at specified index
				counter++;							// Increment index counter
				split = strtok(NULL, ";");			// Update split
			}

			int index = 0;
			// Iterate through list to execute commands
			while ((index < sizeof(list)) && (strcmp(list[index], "\0") != 0))
			{
				//checking for a blank command as a space
				if (strcmp(list[index], " ") == 0)
					goto jump;

				char* toks[10];
				char* tkn = strtok(list[index], " ");

				// If a command is empty, skip it.
				if (tkn == NULL || list[index] == NULL)
					continue;

				// Check for multiple commands
				int multiple = 0;
				if (strcmp(list[index + 1], "\0") != 0)
					multiple = 1;
				else if (strcmp(list[index + 1], "\0") == 0)
					multiple = 0;

				if (!strcmp(tkn, "quit"))
					break;

				//if there is exit but multiple commands
				if ((strcmp(tkn, "exit") == 0) && (multiple != 0))
				{
					fore = 1;			// set fore as 1 and 
					goto jump;
				}

				// There is just one command so exit.
				if (((strcmp(list[index], "\0")) != 0) && (fore == 1) && (multiple == 0))
					back = 1;

				// Array of commands and arguments
				int i = 0;
				while (tkn != NULL)
				{
					toks[i] = tkn;
					i += 1;
					tkn = strtok(NULL, " ");
				}
				toks[i] = NULL;


				/***************************************
				**************Kulthum Lakha*************
				****************kyl0029*****************
				****************************************/
				// cd if directory is not included
				if ((strcmp(toks[0], "cd") == 0) && (toks[1] == NULL))
				{
					chdir(getenv("HOME")); 	// Call chdir() to home directory.
				}
				// cd if directory included
				else if ((strcmp(toks[0], "cd") == 0) && (toks[1] != NULL))
				{
					if (chdir(toks[1]) != 0) // Print error message if directory is not valid
						perror("Entered directory does not exist!\n");
					else 		// Else call chdir() to change directory.
						chdir(toks[1]);
				}
				/*************************************************************************/

				// Jack Henderson
				else if ((strcmp(toks[0], "myhistory") == 0) && (toks[1] == NULL))
					printhist();

				 
				else if (strcmp(toks[0], "cd") != 0)		//To avoid double execution of cd
					// Execute multiple commands
					executeCommand(toks);

				if (back != 0)            // If multiple commands are executed already and one of them was exit, exit.
					exit(0);

				jump:
				index += 1;
			}
		}
	}
	else                                                // Error
	{
		perror("Invalid Number of Arguments!");
		exit(0);
	}

	return 0;
}

