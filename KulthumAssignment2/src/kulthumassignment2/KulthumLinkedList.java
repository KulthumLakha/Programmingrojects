package kulthumassignment2;


// Import required packages.
import java.util.ArrayList;


public class KulthumLinkedList<E> implements KulthumList<E> 
{

    private Node<E> head, tail;

    // Number of elements in the list
    private int size = 0; 


    // Create an empty list
    public KulthumLinkedList() 
    {

    }


    // Create a list from an array of objects
    public KulthumLinkedList(E[] objects) 
    {
        for (int i = 0; i < objects.length; i++) 
        {
          add(objects[i]);
        }
    }


    // Return the head element in the list
    public E getFirst() 
    {
        if (size == 0) 
            return null;
        else 
            return head.element;
    }


    // Return the last element in the list
    public E getLast() 
    {
        if (size == 0) 
            return null;
        else 
            return tail.element;        
    }


    // Add an element to the beginning of the list
    public void addFirst(E e) 
    {
        // Create a new node
        Node<E> newNode = new Node<>(e); 

        // Link new node with the head.
        newNode.next = head; 

        // Head points to new node.
        head = newNode; 

        // Increase size by 1.
        size++; 

        // The new node is the only node in list
        if (tail == null) 
            tail = head;
    }


    // Add an element to the end of the list
    public void addLast(E e) 
    {
        // Create a new node for e
        Node<E> newNode = new Node<>(e); 

        // The new node is the only node in list
        if (tail == null) 
            head = tail = newNode; 
        else 
        {
            // Link the new node with the last node
            tail.next = newNode; 

            // tail now points to the last node
            tail = newNode; 
        }

        // Increase size
        size++;
    }


    @Override
    //Add a new element at the specified index in this list. 
    public void add(int index, E e) 
    {
        // If index is 0, add e to the beginning of the list.
        if (index == 0) 
            addFirst(e);

        // If index is greater or equal to size, add e to the list's end.
        else if (index >= size) 
            addLast(e);

        // Else add to the specified index.
        else 
        {
            Node<E> current = head;
            for (int i = 1; i < index; i++) 
                current = current.next;            
            Node<E> temp = current.next;
            current.next = new Node<>(e);
            (current.next).next = temp;
            size++;
        }
    }


    // Remove and return the head node value.
    public E removeFirst() 
    {
        if (size == 0) 
            return null;
        else 
        {
            E temp = head.element;
            head = head.next;
            size--;
            if (head == null) 
                tail = null;            
            return temp;
        }
    }


    // Remove and return the last node value.
    public E removeLast() 
    {
        if (size == 0) 
            return null;
        else if (size == 1)  
        {
            E temp = head.element;
            head = tail = null;
            size = 0;
            return temp;
        } 
        else 
        {
            Node<E> current = head;
            for (int i = 0; i < size - 2; i++) 
                current = current.next;    
            E temp = tail.element;
            tail = current;
            tail.next = null;
            size--;
            return temp;
        }
    }


    @Override
    // Remove and return the element at the specified index.
    public E remove(int index) 
    {
        // If index is out of range, return null.
        if (index < 0 || index >= size) 
            return null;
         
        // If index is 0, remove and return head node.
        else if (index == 0) 
            return removeFirst();

        // If index is 0, remove and return last node.
        else if (index == size - 1) 
            return removeLast();

        // Else remove and return element at the specified index.
        else 
        {
            Node<E> previous = head;
            for (int i = 1; i < index; i++) 
                previous = previous.next;          
            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }


    @Override
    // Override toString() to return elements in the list
    public String toString() 
    {
        StringBuilder result = new StringBuilder("[");
        Node<E> current = head;
        for (int i = 0; i < size; i++) 
        {
            result.append(current.element);
            current = current.next;
            if (current != null) 
                result.append(", "); // Separate two elements with a comma
            else 
                result.append("]"); // Insert the closing ] in the string            
        }

        return result.toString();
    }


    @Override
    // Clear the list.
    public void clear()
    {
        size = 0;
        head = tail = null;
    }


    @Override
    // Return true if this list contains the element e
    public boolean contains(Object e) 
    {
        // Check if any node's value equals e
        for (Node<E> n = head; n != null; n = n.next) 
        {
            if (n.element.equals(e)) 
                return true;
        }

        //Return false if not found
        return true;
    }


    @Override
    // Return the element at the specified index
    public E get(int index) 
    {
        // Return null if index is invalid 
        if (index < 0 || index >= size) 
            return null;
        
        Node<E> n = head;

        // Find node at specified index
        for (int i = 0; i < index; i++) 
            n = n.next;
        
        // Return the value.
        return n.element;
    }


    @Override
    //Return the index of the first matching element in this list. 
    public int indexOf(Object e) 
    {
        int index = 0;

        // Search for node with element = e
        for (Node<E> n = head; n != null; n = n.next) 
        {
            // Return index number, if e is found.
            if (n.element.equals(e)) 
               return index;
            
            // Increment index.
            index++;
        }

        //Return -1 if not found.
        return -1;
    }


    @Override
    // Return the index of the last matching element in this list
    public int lastIndexOf(E e) 
    {
        // Loop in reverse.
        for (int i = size - 1; i >= 0; i--) 
        {
            // Use get method to find e at index i 
            if (get(i).equals(e)) 
                return i;
        }

        // Return -1 if not found
        return -1;
    }


    @Override
    /**
     * Replace the element at the specified position in this list with the
     * specified element.
     */
    public E set(int index, E e) 
    {
        // Check if index is within the range.
        if (index < 0 || index >= size) 
            return null;
        
        // Find node at specified index.
        Node<E> n = head;
        for (int i = 0; i < index; i++) 
            n = n.next;
        
        // Store old value.
        E old = n.element;

        // Replace with new value.
        n.element = e;

        // Return old
        return old;
    }


    @Override
    // Override iterator() 
    public java.util.Iterator<E> iterator() 
    {
        return new LinkedListIterator();
    }


    private class LinkedListIterator implements java.util.Iterator<E> 
    {
        private Node<E> current = head;

        // Current index
        private int previous = -1;

        private boolean safeToRemove = false; 

        @Override
        public boolean hasNext() 
        {
            return (current != null);
        }

        @Override
        public E next() 
        {
            E e = current.element;
            current = current.next;

            // Increment previous index
            previous++;

            // Set safeToRemove as true.
            safeToRemove = true;

            // Return next element.
            return e;
        }


        @Override
        public void remove() 
        {
            // Throw exception if it is not safe to remove
            if (!safeToRemove) 
            {
                throw new IllegalStateException
                ("remove() is invoked before calling next()");
            }

            // Remove node at previous position
            KulthumLinkedList.this.remove(previous);

            // Decrement previous index.
            previous--;

            // Set safeToRemove as false.
            safeToRemove = false;
        }
    }


    private static class Node<E> 
    {
        E element;
        Node<E> next;
        public Node(E element) 
        {
            this.element = element;
        }
    }


    @Override
    // Return the number of elements in this list
    public int size() 
    {
        return size;
    }


    // Output method to display array.
    public void output()
    {   
        Node<E> temp = head;
        if(temp != null)
        {
            System.out.print(temp.element + " ");
            while(temp.next!=null)
            {
                temp = temp.next;
                System.out.print(temp.element + " ");
            }
        }
    }
}