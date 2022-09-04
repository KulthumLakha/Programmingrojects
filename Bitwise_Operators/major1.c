/*
 * Group 6
 * Boniya Philomon, Jack Henderson, Lluviana Vasquez, and Kulthum Lakha
 * CSCE 3600.002
 * 10/13/2021
 * This code file contains a function to display a menu for the user to select from, prompts user to enter an integer,
 * calls the appropriate function to perform the operation, and displays the result.
 */

// Include header file.
#include "major1.h"

// Declare printMenu and userInput function.
void printMenu();
int userInput();

// Main function.
int main()
{
    // Initialize input variable.  
    int input=0;

    // Loop while input is not equal to 5.
    while (input != 5)
    {    
        printMenu();    // Call printMenu function to print menu options.
        scanf("%i", &input);    // Read input from user.

        // Initialize integers num and forRotate.
        int num = -1;
        int forRotate = -1;

        // Check input
        switch (input)
        {
            // Input=1.
            case 1:
                // Prompt user to enter a 32 bit number and call userInput function.
                while(!(num>=1 && num<=4294967295))
                {
                    num = userInput();      //num = user's input.
                }
                //Print result.
                printf("The number of leading zeroes in %d is %u\n", num, getBinary(num));
                break;
                
            // Input=2
            case 2:
                // Prompt user to enter a 32 bit number and call userInput function.
                while(!(num>=1 && num<=4294967295))
                {
                    num = userInput();      //num = user's input.
                }
                //Print result.
                printf("Endian swap of %d gives %u\n", num, endian(num));
                break;

            // Input=3
            case 3:
                // Prompt user to enter a 32 bit number and call userInput function.
                while(!(num>=1 && num<=4294967295))
                {
                    num = userInput();      //num = user's input.
                }

                // Prompt user to enter number of bits to rotate.
                while(!(forRotate>=1 && forRotate<=32))
                {
                    printf("Enter the number of positions to rotate-right the input (between 0 and 31, inclusively): ");
                    scanf("%d", &forRotate);      //forRotate = user's input.  
                } 
                //Print result.
                printf("%d rotated by %d position gives %u\n", num, forRotate, rotate(num,forRotate));
                break;

            // Input=4
            case 4:
                // Prompt user to enter a 32 bit number and call userInput function.
                while(!(num>=1 && num<=4294967295))
                {
                    num = userInput();      //num = user's input.
                }
                //Print result.
                printf("Parity of %d is %u\n", num, parity(num));
                break;

            // Input=5
            case 5:
                // Terminate program.
                printf("Program terminating. Goodbye...\n");
                break;

            // Invalid input (Not between 1 and 5 inclusively.)
            default:
                // Print error message.
                printf("Error: Invalid option. Please try again.\n");
        }
    }
}

// PrintMenu function
void printMenu()
{
    // Display menu options and prompt user to select one.
    printf("Enter the menu option for the operation to perform:\n");
    printf("(1). Count Leading zeroes\n");
    printf("(2). Endian Swap\n");
    printf("(3). Rotate-right\n");
    printf("(4). Parity\n");
    printf("(5). EXIT\n");
    printf("-->");
}

// userInput function
int userInput()
{
    // Declare num variable.
	int num;

    // Prompt user to enter a 32-bit number.
    printf("Enter a 32-bit number (>= 1 and <= 4294967295, inclusively): ");

    // Read user's input.
    scanf("%d", &num);

    // Return user's input.
    return num;
}



