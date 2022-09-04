package kulthumassignment3;


import java.util.Collection;
import java.util.Iterator;


/**
 * The KulthumTree interface defines all common operations for trees.
 * Define tree to be a subtype of Collection.
 * 
 */
public interface KulthumTree<E> extends Collection<E>  
{
    // Return true if element is in the tree.
    public boolean search( E e );

    // Insert e into the binary tree and return true.
    public boolean insert( E e );

    // Delete e from binary tree and return true.
    public boolean delete( E e );

    // Get number of elements in the tree.
    public int getSize();

    // Inorder traversal from the root.
    public default void inorder() {}

    // PostOrder traversal from the root.
    public default void postorder() {}

    // Preorder traversal from the root.
    public default void preorder() {}


    @Override
    // Return true if the tree is empty. 
    public default boolean isEmpty()
    {
        return size() == 0;
    }

    
    // Return true if the tree contains e.
    @Override
    public default boolean contains( Object e )
    {
        return search( (E)e );
    }

    
    // Add e to the tree and return true if successful.
    @Override
    public default boolean add( E e )
    {
        return insert( e );
    }


    // Remove e from the tree and return true if successful.
    @Override
    public default boolean remove( Object e )
    {
        return delete ( (E)e );
    }


    // Return number of elements in the tree.
    @Override
    public default int size()
    {
        return getSize();
    }


    @Override
    // Return true if tree contains all the elements in the specified collection.
    public default boolean containsAll(Collection<?> c)
    {   
        boolean has = false;
        for (Object e: c)
        {
            if (this.contains(e))
                has = true;
        }
        return has;
    }


    @Override
    // Add all the elements in specified collection to the tree.
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
    // Removes all of the tree's elements that are also in the specified c.
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
    // Retain only the elements in the tree that are in c.
    public default boolean retainAll(Collection<?> c)
    {
        boolean changed = false;
        Iterator it = iterator();
        while(it.hasNext())
            if (!c.contains(it.next()))
            {
                it.remove();
                changed = true;
            }
        return changed;
    }
}
