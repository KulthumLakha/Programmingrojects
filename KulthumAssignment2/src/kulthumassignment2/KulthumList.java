package kulthumassignment2;

// Import required packages.
import java.util.Collection;


/**
 * This class implements a List.
 * List extends collection class.
 *
 */
public interface KulthumList<E> extends Collection<E> 
{
    // Add a new element at the specified index in this list.
    public void add(int index, E e);

    // Return element at specified index.
    public E get(int index);

    // Return the index of the first matching element.
    public int indexOf(Object e);

    // Return the index of the last matching element.
    public int lastIndexOf(E e);

    // Remove and return element at specified index, move subsequent to left.
    public E remove(int index);

    // Replace element at specified index and return the new set.
    public E set(int index, E e);


    @Override
    // Add a new element at the end of the list.
    public default boolean add(E e)
    {
        add(size(), e);
        return true;
    }
    

    @Override
    //Return true if the list contains no elements.
    public default boolean isEmpty()
    {
        return size()==0;
    }


    @Override
    // Remove first occurence of e, move subsequent to left.
    public default boolean remove(Object e)
    {
        if(indexOf(e) >= 0)
        {
            remove(indexOf(e));

            // Return true if the element e is removed.
            return true;
        }
        else
            return false;
    }
    

    @Override
    // Return true if list contains all the elements in the specified collection.
    public default boolean containsAll(Collection<?> c)
    {   
        boolean contains = false;
        for (Object e: c)
        {
            if (this.contains(e))
                contains = true;
        }
        return contains;
    }


    @Override
    // Add all the elements in specified collection to this collection.
    // Return true if elements are added.
    public default boolean addAll(Collection<? extends E> c)
    {
        boolean modified = false;
        for (E e: c)
            if (add(e))
                modified = true;
        return modified;
    }


    @Override
    // Removes all of the list's elements that are also in the specified c.
    // Return true if elements are removed.
    public default boolean removeAll(Collection<?> c)
    {   
        boolean removed = false;
        for (Object o: c)
        {
            if(this.remove(o))
                removed = true;
        }
        return removed;
    }


    @Override
    // Retain only the elements in this collection that are in c.
    public default boolean retainAll(Collection<?> c)
    {
        boolean changed = false;
        for ( int i = size()-1; i>=0; i--)
        {
            Object o = get(i);
            if (!c.contains(o))
            {
                remove(i);
                changed = true;
            }
        }
        return true;
    }


    @Override
    public default Object[] toArray()
    {
        Object[] temp = new Object[size()];
        for ( int i=0; i<size(); i++)
            temp[i] = (Object) (this.get(i));
        if ( size()>0 )
            return (E[])temp;
        else
            return null;
    }


    @Override
    // Return an array containing all of the elements in this list.
    public default <T> T[] toArray(T[] array)
    {
        // Reinitialize array if it is not big enough.
        if (array.length<size())
            array = (T[]) new Object[size()];

        // Copy all elements to array.
        for (int i=0; i<size(); i++)
            array[i] = (T) get(i);

        return array;
    }

    // Output method.
    public default void output()
    {
        for (int i=0; i<size(); i++)
           System.out.println(get(i));        
    }

}
