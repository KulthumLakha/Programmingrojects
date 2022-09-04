package kulthumassignment2;


// Import required packages.
import java.util.ArrayList;
import java.util.Arrays;


/**
 * This class : KulthumArrayList implements KulthumList..
 *
 */
public class KulthumArrayList<E> implements KulthumList<E>
{


    // Initialize index of the array.
    public static final int INITIAL_CAPACITY = 16;

    // Create an array for storing elements of the array list.
    private E[] data = (E[]) new Object[INITIAL_CAPACITY];

    // Number of elements in the list.
    private int size = 0;   


    // Create an empty list.
    public KulthumArrayList()
    {
    }


    // Create a list from an array of objects.
    public KulthumArrayList( E[] objects )
    {
        for( int i=0; i<objects.length; i++ )
            add( objects[i] );
    }


    @Override 
    // Add a new element at the specified index.
    public void add(int index, E e)
    {
        // Ensure index is in the right range.
        if( index<0 || index>size )
            throw new IndexOutOfBoundsException
                ( "Index: " + index + ", Size: " + size );
        
        // Call the ensureCapacity method.
        ensureCapacity();
            
        // Move the elements to the right after the specified index.
        for ( int i = size-1;  i >= index;  i-- )
            data[i+1] = data[i];
        
        // Insert a new element to data[index].
        data[index] = e;
        
        // Increase size by 1.
        size++;
    }


    // Create a larger array, double current size + 1.
    public void ensureCapacity()
    {
        if( size >= data.length )
        {
            E[] newData =(E[])( new Object[size*2 + 1] );
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }


    @Override 
    // Clear the list.
    public void clear()
    {
        data = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }


    @Override 
    // Return true if the list contains the specified element.
    public boolean contains(Object e)
    {
        for( int i=0; i<size; i++ )
            if( e.equals(data[i]) ) return true;

        return false;
    }


    @Override 
    // Return the element at the specified index.
    public E get(int index)
    {
        checkIndex(index);
        return data[index];
    }


    // Check if entered index is in the list's range.
    private void checkIndex(int index)
    {
        if( index<0 || index>=size )
            throw new IndexOutOfBoundsException
                ( "Index: " + index + ", Size:" + size );
    }


    @Override 
    // Return the index of the first matching element in this list.
    public int indexOf(Object e)
    {
        for( int i=0; i<size; i++ )
            if( e.equals(data[i]) ) return i;

        // Return -1 if no match.
        return -1;
    }


    @Override 
    // Return the index of the last matching element in this list.
    public int lastIndexOf(E e)
    {
        for(int i=size-1; i>=0; i--)
            if(e.equals(data[i])) return i;

        // Return -1 if no match.
        return -1;
    }


    @Override 
    // Remove the element at the specified position in this list.
    public E remove(int index)
    {
        checkIndex(index);
        E e = data[index];
        
        // Shift any subsequent element to the left.
        for ( int j=index; j<size-1; j++ )
            data[j] = data[j+1];
        
        data[size-1] = null;

        // Decrement size.
        size--;

        // Return the element that was recovered from the list.
        return e;
    }


    @Override 
    // Replace element at specified position with specified element.
    public E set( int index, E e )
    {
        checkIndex(index);
        E old = data[index];
        data[index] = e;
        return old;
    }


    @Override 
    // Convert list to String and return it.
    public String toString()
    {
        StringBuilder result = new StringBuilder( "[" );

        for ( int i=0; i<size; i++ )
        {
            result.append( data[i] );
            if ( i<size-1 ) result.append( ", " );
        }

        return result.toString() + "]";
    }


    // Trims the capacity to current size.
    public void trimToSize()
    {
        // If size is equal to capacity, no need to trim.
        // Trim only if size != capacity.
        if ( size != data.length )
        {
            E[] newData = (E[])( new Object[size] );
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }


    @Override 
    // Override iterator() defined in iterable.
    public java.util.Iterator<E> iterator()
    {
        return new ArrayListIterator();
    }


    private class ArrayListIterator implements java.util.Iterator<E>
    {
        // Current Index
        private int current = 0;

        @Override
        // Check if there is an element after the current index element.
        public boolean hasNext()
        {
            return current < size;
        }

        @Override 
        // Return element in next index.
        public E next()
        {
            return data[current++];
        }

        @Override 
        // Remove the element returned by the last next().
        public void remove()
        {
            // If next() has not been called yet.
            if (current == 0 )
            throw new IllegalStateException();

            KulthumArrayList.this.remove(--current);
        }
    }


    @Override 
    // Returns number of elements in the list.
    public int size()
    {
        return size;
    }


    // Output method to display array.
    public void output()
    {
       for(int i = 0; i<size; i++)
        {
            System.out.print( data[i] + " ");
        }
    }
    
    
}
