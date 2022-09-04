package kulthumassignment2;

// Import required packages.
import java.util.LinkedList;
import java.util.Queue;


/**
 * This class implements a queue using LinkedList.
 *
 */
public class KulthumQueue<E>  
{


    // Define a linked list as a data field in the queue class.
    private java.util.LinkedList<E> list = new java.util.LinkedList<E>();
    
    
    // Add an element to the queue.
    public void enqueue(E e)
    {
        list.addLast(e);
    }


    // Remove an elemnt from the queue.
    public E dequeue()
    {
        return list.removeFirst();
    }


    // Return number of elements in the queue.
    public int getSize()
    {
        return list.size();
    }


    // Convert the queue to String and return it.
    public String toString()
    {
        return "Queue: " + list.toString();
    }


    // output method to display the stack.
    public void output()
    {
        System.out.println("Queue: " + list );
    }
}
