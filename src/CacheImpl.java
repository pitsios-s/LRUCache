/**
 * @author Stamatis Pitsios
 *
 * Every instance of this class is used as a Cache.
 * 
 * @param <K> The type of the keys that will be stored to the cache.
 * @param <V> The type of the values that will be stored to the cache.
 */
public class CacheImpl<K,V> implements Cache<K,V>
{
	/**
	 * Our priority queue.
	 */
    private MinPQ<Item<K,V>> pq;
   
    /**
     * The binary search tree.
     */
    private BinarySearchTree<K,V> bst;
    
    /**
     * Total number of item lookups.
     */
    private long NumOfLookups;
    
    /**
     * Total number of cache hits.
     */
    private long SuccLookups;
    
    /**
     * Total number of cache misses.
     */
    private long Misses;
    
    /**
     * The capacity of our cache(in number of items).
     */
    private int CacheCapacity;
    
    /**
     * The total number of items that are currently in the cache.
     */
    private int ItemsInCache;
    
    
    /**
     * Constructor
     * 
     * @param number The number of items that can be stored in the cache(i.e cache capacity).
     */
    public CacheImpl(int number)
    {
        NumOfLookups = 0;
        SuccLookups = 0;
        Misses = 0;
        ItemsInCache = 0;
        CacheCapacity = number;
        pq = new MinPQ<Item<K,V>>(number);
        bst = new BinarySearchTree<K,V>();
    }
    
    
    /**
     * Update the times of the items in the priority queue.
     */
    private void updateTimes()
    {
        MinPQ<Item<K,V>> temppq = new MinPQ<Item<K,V>>(CacheCapacity);
        bst.BSTtoPQ(temppq);
        pq = temppq;
    }
    
    
    @Override
    public long getNumberOfLookUps()
    {
        return this.NumOfLookups;
    }
    
    
    @Override
    public long getMisses()
    {
        return this.Misses;
    }
    
    
    @Override
    public long getHits()
    {
        return this.SuccLookups;
    }
    
    
    @Override
    public double getHitRatio()
    {
        return (double)this.SuccLookups / (double)this.NumOfLookups ;
    }
    
    
    @Override
    public void store(K key, V value)
    {
        if(ItemsInCache < CacheCapacity)
        {
            pq.insert(new Item<K,V>(key,value));
            bst.insert(new Item<K,V>(key,value));
            ItemsInCache++;
        }
        
        else
        {
          Item<K,V> temp = pq.getmin();
          bst.remove(temp);
          pq.insert(new Item<K,V> (key,value));
          bst.insert(new Item<K,V> (key,value));
        }
    }
    
    
    @Override
    public V lookUp(K key)
    {
       NumOfLookups++;
       
       Item<K,V> temp = bst.findRecursive(key);
       
       if(temp == null)
       {
           Misses++;
           return null;
       }
       
       else
       {
           SuccLookups++;
           bst.findRecursive(key).setTime();
           updateTimes();
           return temp.getValue();
       }     
    }
}