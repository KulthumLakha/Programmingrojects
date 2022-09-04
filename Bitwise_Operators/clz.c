/*
 * Group 6
 * Author: Boniya Philomon
 * CSCE 3600.002
 * 10/14/2021
 * This source code contains a function that count the number to lead input argument passed to the  function.
 */






#include "major1.h"

int getBinary(unsigned n)
{
int count=0;
for(unsigned i = 1 << 31; i > 0; i = i / 2)
{
if(n & i)
{
break;
}
else
{
count++;
}
}
return count;
}

