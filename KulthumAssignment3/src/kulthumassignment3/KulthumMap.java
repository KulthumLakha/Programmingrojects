package kulthumassignment3;


/**
 *
 * The KulthumMap interface defines all common operations for maps.
 */
public interface KulthumMap< K,V >  
{


    // Remove all of the entries from this map.
    public void clear();

    // Return true if the specified key is in the map.
    public boolean containsKey( K key );

    // Return true if the specified in the map from this class.
    public boolean containsValue( V value );

    // Return a set of entries in the map.
    public java.util.Set< Entry< K,V > > entrySet();

    // Return the values that match the specified key.
    public V get( K key );

    // Return true if the map does not contain any entry.
    public boolean isEmpty();
    
    // Return a set of keys in this map.
    public java.util.Set<K> keySet();

    // Add an entry (key, value) into the map.
    public V put( K key,  V value );
    
    // Remove an entry from the specified key.
    public void remove( K key );

    // Return the number of mappings in this key.
    public int size();

    // Return a set of values in this map.
    public java.util.Set<V> values();
    

    // Define an inner class for entry.
    public static class Entry< K,V >
    {
        K key;
        V value;
        
        public Entry( K key, V value )
        {
            this.key = key;
            this.value = value;
        }

        public K getKey()
        {
            return key;
        }
        
        public V getValue()
        {
            return value;
        }

        @Override
        public String toString()
        {
            return "[" + key + ", " + value + "]";
        }
    }
}
