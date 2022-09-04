/*
 * Group 6
 * Author: Kulthum Lakha
 * CSCE 3600.002
 * 10/10/2021
 * This source code contains a function that performs the rotation operation
 *  using two integer operands passed to this function.
 */


// Include header file
#include "major1.h"

/*
 * rotate function rotates n right by x bits
 * @n	number to be rotated
 * @b	number of bit positions that the first operand must be rotated by.
 */ 
int rotate(int num, int b)
{
    // Shift everything right by b bits
	// Last b bits at the left side of n will become 0.
	unsigned int shift = (unsigned int)(num >> b); 
	
	// Shift first b bits of n from the right to the left. 
	// (Replace the bits that were 0 by the b bits)
	int rotate =( num << (32-b));

	// Return the rotated integer by doing bitwise or of shift with rotate.
	return (shift | rotate);
}
