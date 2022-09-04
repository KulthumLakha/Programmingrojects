/*
Name: Lluviana Vasquez
Date: 10/14/2021
Class: CSCE 3600.002
Assignment: Major Assignment 1
*/

//code for parity

/*
Description: contains a single function to perform the parity computation
on the input integer passed to this function.
*/


#include "major1.h"

int parity(unsigned int n)
{
	int count = 0;

	for(int i = 0; i < 32; i++){
		if((n & 1) == 1)
			count += n%2; //finds parity of number
		n >>= 1; //shift right
	}


	int ans =  count % 2; //finds parity
	return ans;
}
