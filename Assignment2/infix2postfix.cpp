/*
 * Author: Kulthum Lakha
 * EUID: kyl0029
 * Program: This program converts infix expressions to postfix.
 */

#include "stack.hpp"

using namespace std;


// Auxiliary method, you probably find it useful
// Operands are all lower case and upper case characters
bool isOperand(char c)
{
    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
}


// Auxiliary method, you probably find it useful
int precedence(char c)
{
    if (c == '+' || c == '-')
    {
        return 0;
    }
    if(c == '*' || c == '/')
    {
        return 1;
    }
    if(c == '^')
    {
        return 2;
    }
    return -1;
}


int main()
{
    freopen("input_infix2postfix.txt", "r", stdin);
    string input;
    string solution;
    int line_counter = 0;

    while(cin >> solution)
    {
        cin >> input;
        Stack<char> stack;
        string result;

        //The input file is in the format "expected_solution infix_expression", 
        //where expected_solution is the infix_expression in postfix format

        for (int i = 0; i < input.length(); ++i)
        {
            // WRITE CODE HERE to store in 'result' the postfix transformation of 'input'

            // If the current element is an operand, add it to result string.
            if (isOperand(input[i]))
                result += input[i];

            // If the current element is ‘(‘, push it to the stack .
            else if (input[i] == '(')
                stack.push('(');

            // If the current element is ‘)’, 
            // pop it from the stack and add it to result 
            // until ‘(‘ is encountered.
            else if (input[i] == ')') 
            {
                while (stack.peek() != '(')
                {
                    result += stack.peek();
                    stack.pop();
                }
                stack.pop();
            }

            // If current elements is an operator,
            // Compare the precedence level of the current element of the string and the top element of the stack.
            else 
            {
                // While the precedence level of the current element is lower,
                // Add top element of stack to the result string.
                // Remove top element of stack.
                while ((!stack.size()<1) && (precedence(input[i]) <= precedence(stack.peek()))) 
                {
                    result += stack.peek();
                    stack.pop();
                }
                // Push current element to stack.
                stack.push(input[i]);
            }
        }

        // You need to do some extra stuff here to store in 'result' the postfix transformation of 'input'
        // Add elements from the stack to the result string until stack is empty.
        while (!stack.size()<1) 
        {
            result += stack.peek();
            stack.pop();
        }

        // Checking whether the result you got is correct
        if(solution == result)
            cout << "line " << line_counter << ": OK [" << solution << " " << result << "]" << endl;
        else
            cout << "line " << line_counter << ": ERROR [" << solution << " " << result << "]" << endl;

        line_counter++;
    }
}
