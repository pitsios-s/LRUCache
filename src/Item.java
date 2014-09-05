/**
 * @author Stamatis Pitsios
 *
 *	Every instance of this class represents an item in our cache.
 *
 * @param <K> The type of the key-part of the item.
 * @param <V> The type of the value-part of the item.
 */
public class Item<K,V> implements Comparable<Item<K,V>>
{
	/**
	 * The key-part of the item.
	 */
	private K key;
	
	/**
	 * The value-part of the item.
	 */
	private V value;
	
	/**
	 * The time at which the item was received.
	 */
	private long timestamp;
   
   
   /**
    * Constructor
    * 
    * @param key The key-part of the item.
    * @param item The value-part of the item.
    */
   public Item(K key , V value)
   {
       this.key = key;
       this.value = value;
       this.timestamp = System.currentTimeMillis() ;
   }
   
   
   /**
    * Constructor
    * 
    * @param key The key-part of the item.
    */
   public Item(K key)
   {
       this.key = key;
       this.value=null;
       this.timestamp = System.currentTimeMillis();
   }
   
   
   public K getKey()
   {
	   return this.key;
   }
   
   
   public void setkey(K key)
   {
       this.key=key;
   }
   
   
   public V getValue()
   {
       return this.value;
   }
   
   
   public void setValue(V item)
   {
       this.value = item;
   }
   
   
   public long getTime()
   {
       return this.timestamp;
   }
   
   
   public void setTime()
   {
       this.timestamp = System.currentTimeMillis();
   }
   
      
   @Override
   public boolean equals(Object obj)
   {
       if(obj == null) return false;
       
       if(this == obj) return true;
       
       if(!(obj instanceof Item)) return false;
       
       @SuppressWarnings("unchecked")
       Item<K,V> item = (Item<K,V>)obj;
       
       return this.key.equals(item.key);
    }
   	
   	
    @Override
   public int compareTo(Item<K,V> obj)
   {
    	DefaultComparator cmp = new DefaultComparator();
        if(cmp.compare (this.timestamp , obj.timestamp) >0 ) return 1;
        else if (cmp.compare (this.timestamp , obj.timestamp) <0 ) return -1;
        else return 0;
   }
    
    
   @Override
   public String toString()
   {
       return "Key : " + this.getKey().toString()+" , Value : " + this.getValue().toString()+" , Timestamp : "+this.getTime();
   }
}