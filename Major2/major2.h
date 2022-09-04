/*
 * Group 6
 * Kulthum Lakha, Boniya Philomon, Jack Henderson, Lluviana Vasquez
 * CSCE 3600.002
 * 11/28/2021
 * This header file contains include directives and function prototypes.
 */
 
 
 // Define major2_h_ header file
#ifndef major2_h_
#define major2_h_


// Include necessary libraries
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include <sys/wait.h>
#include <sys/signal.h>
#include <pthread.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <pwd.h>
#include <fcntl.h>


// Group collaboration
void executeCommand(char* tokens[10]);

// Jack Henderson - myhistory and alias
void writeHistory(char* input);			
void printhist();
// alias written and committed but not implemented.
/*
typedef struct aliasList* list_ptr;
list_ptr formatAlias(char* name, char* command);
list_ptr freeAlias(list_ptr newPtr);
list_ptr deleteAlias(list_ptr newPtr, char* name);
list_ptr addAlias(list_ptr newPtr, char* name, char* command);
list_ptr showAlias(list_ptr newPtr);
list_ptr runAlias(char* command, list_ptr newPtr);
*/


// Lluviana Vasquez - exit and ioredirection
void processioredirection(char* userInput);


// Kulthum Lakha - cd and signal handling
// All implemented in main; no function prototypes needed.


// Boniya Philomon - path and piping
// Wrote some code that was committed to git but none was implemented by her.


#endif
