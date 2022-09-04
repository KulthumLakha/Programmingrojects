//Jack Henderson

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "major2.h"
#define INPUT 1024


//defining the linked list pointer struct outside to use in other functions -actual pointer defined in the linked list creation
typedef struct aliasList* list_ptr;

//struct that I will use for size
typedef struct aliasList* aliasSize;

//linked list for alias storing
struct aliasList
{
    char* name;
    char* command;
    list_ptr* next; 
};


//The structural format for an alias in our shell
list_ptr formatAlias(char* name, char* command)
{
    list_ptr newPtr = (aliasList) malloc(sizeof(aliasSize));

    *newPtr = (aliasSize)
    {
        .name = name,
        .command = command,
        .next = NULL
    };

    return newPtr;

    

}

//freeing an alias strucutral format
list_ptr freeAlias(list_ptr newPtr)
{
    if (newPtr != NULL)
    {
        free(newPtr->name);
        free(newPtr->command);

        *newPtr = (aliasSize)
        {
            .name = NULL,
            .command = NULL,
            .next = NULL
        };

        free(newPtr);
    } 
}

//the implementation of this in the main is where the -r flag should be used
list_ptr deleteAlias(list_ptr newPtr, char* name)
{
    if(newPtr == NULL)
    {
        return NULL;
    }


    //remove matching names in order to delete alias
    if(strcmp(newPtr->name, name) == 0)
    {
        
        list_ptr next = newPtr->next;
        alias_free(newPtr);
        return next;
    }

   //also need to include the next variable as this is a linked list
    list_ptr iter = newPtr;

    while(iter-> next != NULL)
    {
        if(strcmp(iter->next->name, name) == 0)
        {
            newPtr next = iter -> next -> next;
            freeAlias(iter->next);
            iter->next = next;
            break;
        }
    }
    //return to new position in list
    iter = iter -> next;
   

   return newPtr;
}


list_ptr addAlias(list_ptr newPtr, char* name, char* command)
{ 

    newPtr = deleteAlias(newPtr, name);

    //strdup to copy the input over
    list_ptr newPtr = formatAlias(strdup(name), strdup(command));

    //setup to get to the end of the linked list
    list_ptr nextPtr = newPtr;
    while(nextPtr->next)
    {
        nextPtr = nextPtr->next;
    }

    //adding to the end of the list
    nextPtr -> next = newPtr;

    return newPtr;

}

//I believe this is where the -c flag should be used when this is implemented into main
list_ptr showAlias(list_ptr newPtr)
{
    if (newPtr)
    {
        printf("%s = %s \n", newPtr -> name, newPtr -> command);
        showAlias(newPtr->next);
    }
}

list_ptr runAlias(char* command, list_ptr newPtr)
{
    if(strncmp(command, "alias -", strlen("alias -")) == 0)
    {
        if(command[7] == 'c') //7th position of string contains c - delete
        {
            newPtr = deleteAlias(newPtr);
        }
        else if(command[7] == 'r') //or if 7th position of string contains r - delete
        {
            newPtr = deleteAlias(newPtr);
        }
        else
        {
            printf("wrong alias input")
        }
    else if(command[5] != '\0') //or if there is nothing after the 5th position create alias
    {
        //The idea here is correct, but i'm not sure how to get it to properly work
        
        //char* aliasName;
        //char* aliasCommand;

        //addAlias(newPtr, aliasName, aliasCommand);
    }
    }
}

//Do i need a main, or should the functions just be called in our main?
/*int main()
{

}
*/
