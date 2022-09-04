#include "stack.hpp"

using namespace std;

int main()
{
    freopen("input_postfixEval.txt", "r", stdin);
    string s;
    int solution;
    int line_counter = 0;
    
    while(cin>>solution)
    {
        cin>>s;
        Stack<int> stack;
    
        // The input file is in the format "expected_solution postfix_expression"
        // We assume that all operands in the input are one digit (so they range from 0 to 9)
    
        for(int i=0; i<s.length(); ++i)
        {
            // WRITE CODE HERE to evaluate the postfix expression in s
            
            // If current element is an operand(digit), push into the stack.
            if (isdigit(s[i]))
                // s[i] is used to push digit and not ASCII code of the digit.
                stack.push(s[i]-'0');

            // If current element is an operator, pop 2 values from stack and apply operand to them.
            else
            {
                // Initialize 2 variables to hold the two operands popped from the stack.
                int v1 = stack.pop();
                int v2 = stack.pop();

                // Use switch to determine and apply operator.
                switch (s[i])
                {
                    // When operator is '+', push the sum into the stack.
                    case '+': 
                        stack.push(v2 + v1); 
                        break;

                    // When operator is '-', push the diference into the stack.
                    case '-': 
                        stack.push(v2 - v1); 
                        break;

                    // When operator is '+', push the product into the stack.
                    case '*': 
                        stack.push(v2 * v1); 
                        break;

                    // When operator is '+', push the quotient into the stack.
                    case '/': 
                        stack.push(v2 / v1); 
                        break;
                }
            }
      
            // At the end of the loop, stack.pop() should contain the value of the postfix expression
        }

        // Checking whether the value you calculated is correct ...
        int value = stack.pop();

        if(solution == value)
            cout << "line " << line_counter << ": OK [" << solution << " " << value << "]" << endl;
        else
            cout << "line " << line_counter << ": ERROR [" << solution << " " << value << "]" << endl;
    
        line_counter++;
    }
}
