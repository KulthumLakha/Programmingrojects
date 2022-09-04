/*
 * Author: Kulthum Lakha
 * EUID: kyl0029
 * Program: This program check is expression is balanced.
 */

#include "stack.hpp"

using namespace std;


int main()
{
	freopen("input_balanced.txt", "r", stdin);
	string s,r;
	int line_counter;

	while (cin >> r)
	{
		cin >> s;
		Stack<char> stack;
		bool isBalanced = true;

		bool solution;
		if (r[0] == 'Y' || r[0] == 'y')
			solution = true;
		else
			solution = false;
		// The input file is in the format "expected_solution expression"
		// so variable solution tells you whether 'expression' is balanced or not

		// Traverse the expression.
		for (int i = 0; i < s.length(); ++i) 
		{
			// WRITE CODE HERE so that isBalanced indicates whether 's' is balanced

			// If current element of s is an opening bracket, push into the stack.
			if (s[i] == '(' || s[i] == '[' || s[i] == '{')
			{
				// Push element to stack.
				stack.push(s[i]);
				continue;
			}

			if (stack.size() < 1)
				isBalanced = false;
			else
			{
				// Declare char variable x to store top element of the stack.
				char x;

				// Check if current element of s is a closing bracket.
				switch (s[i])
				{

					// Current element is a round bracket:
				case ')':
					x = stack.peek();
					stack.pop();	// Remove it from stack.
					// If closing bracket doesn't match starting bracket then s isn't balanced.
					if (x == '{' || x == '[')
						isBalanced = false;
					break;

					// Current element is a square bracket:
				case ']':
					x = stack.peek();
					stack.pop();	// Remove it from stack.				
					// If closing bracket doesn't match starting bracket then s isn't balanced.
					if (x == '{' || x == '(')
						isBalanced = false;
					break;

					// Current element is a square bracket:
				case '}':
					x = stack.peek();
					stack.pop();	// Remove it from stack.
					// If closing bracket doesn't match starting bracket then s isn't balanced.
					if (x == '(' || x == '[')
						isBalanced = false;
					break;
				}
			}

		}

		if (stack.size()>1)
			isBalanced = false;

		// checking if you stored in isBalanced the correct value
		if (isBalanced == solution)
			cout << "line " << line_counter << ": OK [" << solution << " " << isBalanced << "]" << endl;
		else
			cout << "line " << line_counter << ": ERROR [" << solution << " " << isBalanced << "]" << endl;

		line_counter++;
	}
}
