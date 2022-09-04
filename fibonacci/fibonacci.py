# Description: Program to find nth fibonacci number using dynamic programming.
# Author: Kulthum Lakha ~kyl0029
# Programming Language: Python 
# Run: python fibonacci.py


# Array with 0 and 1 as the 1st two fibonacci numbers
FibArr = [0, 1]


# Function for nth fibonacci number 
def fibonacci(n):

    # Check if result is calculated, return result if available
    if n <= len(FibArr):
        return FibArr[n - 1]

    # Else calculate the sum of the preceeding two numbers, add it to the array, and return it.
    else:
        FibArr.append(fibonacci(n - 1) + fibonacci(n - 2))
        return FibArr[n - 1]
 

# Main function
def main():

    # Prompt user to enter a number
    a = int(input("Enter n to calculate the nth fibonacci number: "))

    # Check if n is less or equal to 0, and call the fibonacci function for the correct input
    if a <= 0:				
	b = int(input("Enter a number greater than 0: ")) 
        print "Result: %d" %fibonacci(b)
    else:
        print "Result: %d" %fibonacci(a)
    

# Call main program
main()
