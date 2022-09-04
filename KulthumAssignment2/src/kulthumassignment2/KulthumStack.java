package kulthumassignment2;

// Import required packages.
import java.util.ArrayList;
import java.util.Stack;


/**
 * This class implements a stack using ArrayList.
 *
 */
public class KulthumStack<E> 
{


    // Define an array list as a data field in the stack class.
    private java.util.ArrayList<E> list = new java.util.ArrayList<E>();


    // Return number of elements in the stack.
    public int getSize()
    {
        return list.size();
    }
    

    // Return the top element in the stack.
    public E peek()
    {
        return list.get( getSize()-1 );
    }


    // Add a new element to the top of the stack.
    public void push(E o)
    {
        list.add(o);
    }


    // Return and remove the top element of the stack
    public E pop()
    {
        E o = list.get( getSize()-1 );
        list.remove( getSize()-1 );
        return o;
    }


    // Return true if the stack is empty.
    public boolean isEmpty()
    {
        return list.isEmpty();
    }


    // Convert Stack to String and return it.
    public String toString()
    {
        return "Stack: " + list.toString();
    }


    // output method to display the stack.
    public void output()
    {
        System.out.println("Stack: " + list );
    }

}
