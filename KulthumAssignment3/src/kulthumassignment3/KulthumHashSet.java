package kulthumassignment3;


import java.util.Collection;
import java.util.LinkedList;
import java.util.Iterator;


/**
 *
 * This class implements Collection interface using separate chaining.
 */
public class KulthumHashSet<E> implements Collection<E>  
{
    // Define the default hash table size. Must be a power of 2
    private final static int DEFAULT_INITIAL_CAPACITY = 4;
  
    // Define the maximum hash table size. 1 << 30 is same as 2^30
    private final static int MAXIMUM_CAPACITY = 1 << 30; 
  
    // Current hash table capacity. Capacity is a power of 2
    private int capacity;
  
    // Define default load factor
    private final static float DEFAULT_MAX_LOAD_FACTOR = 0.75f; 

    // Specify a load factor threshold used in the hash table
    private float loadFactorThreshold; 
  
    // The number of elements in the set
    private int size = 0; 
  
    // Hash table is an array with each cell that is a linked list
    private LinkedList<E>[] table;


    // Construct a set with the default capacity and load factor 
    public KulthumHashSet() 
    {  
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);    
    }

  
    // Construct a set with specified initial capacity and default load factor 
    public KulthumHashSet(int initialCapacity) 
    { 
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);    
    }
  

    // Construct a set with the specified initial capacity and load factor
    public KulthumHashSet(int initialCapacity, float loadFactorThreshold) 
    { 
        if (initialCapacity > MAXIMUM_CAPACITY)
            this.capacity = MAXIMUM_CAPACITY;
        else
            this.capacity = trimToPowerOf2(initialCapacity);
    
        this.loadFactorThreshold = loadFactorThreshold;    
        table = new LinkedList[capacity];
    }
  

    @Override 
    // Remove all elements from this set  
    public void clear() 
    {
        size = 0;
        removeElements();
    }


    @Override 
    // Return true if the element is in the set 
    public boolean contains(Object e) 
    {
        int bucketIndex = hash(e.hashCode());
        if (table[bucketIndex] != null) 
        {
            LinkedList<E> bucket = table[bucketIndex]; 
            return bucket.contains(e);
        }
    
        return false;
    }
  

    @Override 
    // Add an element to the set 
    public boolean add(E e) 
    {
        if (contains(e)) // Duplicate element not stored
            return false;
    
        if (size + 1 > capacity * loadFactorThreshold) 
        {
            if (capacity == MAXIMUM_CAPACITY)
                throw new RuntimeException("Exceeding maximum capacity");
    
            rehash();
        }
    
        int bucketIndex = hash(e.hashCode());
    
        // Create a linked list for the bucket if it is not created
        if (table[bucketIndex] == null) 
            table[bucketIndex] = new LinkedList<E>();

        // Add e to hashTable[index]
        table[bucketIndex].add(e);

        size++; // Increase size
    
        return true;
    }


    @Override 
    // Remove the element from the set 
    public boolean remove(Object e) 
    {
        if (!contains(e))
            return false;
    
        int bucketIndex = hash(e.hashCode());
    
        // Create a linked list for the bucket if it is not created
        if (table[bucketIndex] != null) 
        {
            LinkedList<E> bucket = table[bucketIndex]; 
            bucket.remove(e);
        }

        size--; // Decrease size
    
        return true;
    }


    @Override 
    // Return true if the set contains no elements 
    public boolean isEmpty() 
    {
        return size == 0;
    }


    @Override 
    // Return the number of elements in the set 
    public int size() 
    {
        return size;
    }


    @Override 
    // Return an iterator for the elements in this set 
    public java.util.Iterator<E> iterator() 
    {
        return new KulthumHashSetIterator(this);
    }


    // Inner class for iterator 
    private class KulthumHashSetIterator implements java.util.Iterator<E> 
    {
        // Store the elements in a list
        private java.util.ArrayList<E> list;

        // Point to the current element in list
        private int current = 0; 

        private KulthumHashSet<E> set;

        // Create a list from the set 
        public KulthumHashSetIterator(KulthumHashSet<E> set) 
        {
            this.set = set;
            list = setToList();
        }

        @Override 
        // Next element for traversing? 
        public boolean hasNext() 
        {
            return current < list.size();
        }

        @Override 
        // Get current element and move cursor to the next 
        public E next() 
        {
            return list.get(current++);
        }

        @Override 
        // Remove the element returned by the last next() 
        public void remove() 
        {
            if (current == 0)
                throw new IllegalStateException();

            list.remove(--current);
        }
    }  


    // Hash function 
    private int hash(int hashCode) 
    {
        return hashCode & (capacity - 1);
    }


    // Return a power of 2 for initialCapacity 
    private int trimToPowerOf2(int initialCapacity) 
    {
        int capacity = 1;
        while (capacity < initialCapacity) 
          capacity <<= 1;

        return capacity;
    }


    // Remove all e from each bucket 
    private void removeElements() 
    {
        for (int i = 0; i < capacity; i++) 
          if (table[i] != null) 
            table[i].clear();
    }


    // Rehash the set 
    private void rehash() 
    {
        // Copy to a list
        java.util.ArrayList<E> list = setToList();

        // Double capacity      
        capacity <<= 1; 

        // Create a new hash table
        table = new LinkedList[capacity]; 

        // Reset size 
        size = 0;   

        // Add from the old table to the new table
        for (E element: list) 
          add(element);
    }


    // Copy elements in the hash set to an array list 
    private java.util.ArrayList<E> setToList() 
    {
        java.util.ArrayList<E> list = new java.util.ArrayList<>();

        for (int i = 0; i < capacity; i++) 
          if (table[i] != null) 
            for (E e: table[i]) 
              list.add(e);

        return list;
    }


    @Override
    public String toString() 
    {
        java.util.ArrayList<E> list = setToList();
        StringBuilder builder = new StringBuilder("[");

        // Add the elements except the last one to the string builder
        for (int i = 0; i < list.size() - 1; i++) 
          builder.append(list.get(i) + ", ");
      
        // Add the last element in the list to the string builder
        if (list.size() == 0)
          builder.append("]");
        else
          builder.append(list.get(list.size() - 1) + "]");

        return builder.toString();
    }


    @Override
    // Add all elements of arg0 to the set and return true.
    public boolean addAll(Collection<? extends E> arg0) 
    {
        boolean modified = false;
        for (E e: arg0)
            if (add(e))
                modified = true;
        return modified;
    }


    @Override
    // Return true if the set has all elements of arg0
    public boolean containsAll(Collection<?> arg0) 
    {
        boolean has = false;
        for (Object e: arg0)
        {
            if (this.contains(e))
                has = true;
        }
        return has;
    }


    @Override
    // Remove all elements of arg0 from the set and return true.
    public boolean removeAll(Collection<?> arg0) 
    {
        boolean removed = false;
        for (Object o: arg0)
        {
            remove(o);
            removed = true;
        }
        return removed;
    }


    @Override
    // Retain only the elements of arg0 in the set and return true.
    public boolean retainAll(Collection<?> arg0) 
    {
        boolean changed = false;
        Iterator it = iterator();
        while(it.hasNext())
            if (!arg0.contains(it.next()))
            {
                it.remove();
                changed = true;
            }
        return changed;
    }


    @Override
    // Return an array of elements of the set.
    public Object[] toArray() 
    {
        Object[] temp = new Object[size()];
        for ( int i=0; i<size(); i++)
            temp[i] = (Object) (table[i]);
        if ( size()>0 )
            return (E[])temp;
        else
            return null;
    }


    @Override
    public <T> T[] toArray(T[] arg0) 
    {
        return null;
    }
}