// Include header file
#include "major2.h"


/*******************************************
**********Jack Henderson jhh0139************
********myhistory built-in command**********
********************************************/

extern char History[20][512];
extern int ind;

//this function SHOULD take care of overwriting the previous 
void writeHistory(char* input)
{
    ind = (ind + 1) % 20;
    strcpy(History[ind], input);

}

// print 20 commands from history
void printhist() 
{   
    // history is empty
    if (ind == -1)
        return;

    // loop backwards in the history array from current
    if ((strcmp(History[ind], "")) != 0)
        for(int i=0; i<20; i++)
        {
            printf("%s\n", History[ind]);
            ind = (ind - 1 + 20) % 20; 
        }

    printf("\n");
}