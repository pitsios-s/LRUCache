/** 
 * @author Stamatis Pitsios
 * 
 * This class represents a Binary Search Tree(BST), which will be used
 * for storing and accessing the data of our cache.
 *
 * @param <K> The type of the keys that will be stored to the tree.
 * @param <V> The type of the values that will be stored to the tree.
 */
public class BinarySearchTree<K, V> 
{
	/**
	 * @author Stamatis Pitsios
	 *
	 * An inner class that every instance of it, represents
	 * a node of our binary search tree.
	 * 
	 * @param <K> The type of the keys that will be stored to the node.
	 * @param <V> The type of the values that will be stored to the node.
	 */
	public static class TreeNode<K, V>
	{ 
		/**
		 * The item of the node.
		 */
		private Item<K,V> obj;
		
		/**
		 * The current node's parent node.
		 */
		private TreeNode<K,V> parent;
		
		/**
		 * The current node's left child node.
		 */
		private TreeNode<K,V> left;
		
		/**
		 * The current node's right child node.
		 */
		private TreeNode<K,V> right;
		
		
		/**
		 * Default constructor.
		 */
		public TreeNode()
		{
			obj=null;
			parent=null;
			left=null;
			right=null;
		}
		
		
		public TreeNode(Item<K,V> item)
		{
			this(item,null,null,null);
		}
		
		
		public TreeNode(Item<K,V> obj,TreeNode<K,V> parent,TreeNode<K,V> left,TreeNode<K,V> right)
		{
			this.obj=obj;
			this.left=left;
			this.right=right;
		}
	}
	
	
	/**
	 * The root of the tree. 
	 */
	 private TreeNode<K,V> root;
	 
	 /**
	  * The following comparator will be used for comparing
	  * the items on the tree.
	  */
	 private DefaultComparator cmp;
	 
	 
	 /**
	  * Default constructor
	  */
	 public BinarySearchTree()
     {
		 root=null;
		 cmp=new DefaultComparator();
	 }
	 
	 
	 /**
	  * Returns the root node of the tree.
	  * 
	  * @return root The root of the tree.
	  */
	 public TreeNode<K,V> getRoot()
     {
		 return root;
     }	 
	 
		
	/**
	 * Recursively inserts an item as a leaf node.
	 * 
	 * @param h The node from which we will start.
	 * @param item The item to be inserted.
	 * @param parent The parent node of the 
	 * 
	 * @return The new root of the tree.
	 */
	 private TreeNode<K,V> insertR(TreeNode<K,V> h, Item<K,V> item, TreeNode<K,V> parent) 
     {  
		 if (h == null)
		 {
			 TreeNode<K,V> node = new TreeNode<K,V>(item);
			 node.parent = parent;
			 return node;
		 }
		
		 int result = cmp.compare(h.obj.getKey(), item.getKey());
		 
		 if (result > 0) h.left = insertR(h.left, item, h); 				
		 else if(result < 0)h.right = insertR(h.right, item, h);
		 
		 return h; 
     }
	 
	 
	 /**
	  * Inserts a item to the tree.
	  * 
	  * @param item The item to be inserted
	  */
	 public void insert(Item<K,V> item) 
     { 
		if(item != null) root = insertR(root, item, null); 
	 }
	
	 
	/**
	 * Recursively searches an item in the tree.
	 *  
	 * @param node The node from which we will start searching.
	 * @param element The item to look for.
	 * 
	 * @return The item, if that was found, null otherwise.
	 */
    private Item<K,V> findRecursive(TreeNode<K,V> node, Object element)
    {
        if (node == null) return null;
        
        int result = cmp.compare(element.toString(), node.obj.getKey());
        
        if (result == 0) return node.obj;
        
        if (result<0) return findRecursive(node.left, element);
        else return findRecursive(node.right, element);
    }
   
    
    /**
     * Recursively searches an item in the tree.
     * 
     * @param element The element to look for.
     * 
     * @return The item, if that was found, null otherwise.
     */
    public Item<K,V> findRecursive(Object element)
    {
        return findRecursive(root, element);
    } 
	
	             
	/**
	 * Searches for an item in the tree.
	 * 
	 * @param element The item to search for.
	 * 
	 * @return p The node at which the item was found in.
	 */
    public TreeNode<K,V> find(Item<K,V> element) 
    {
    	TreeNode<K,V> p = root;
        
    	while (p != null) 
    	{
            //Compare element with the element in the current subtree.
    		int result = cmp.compare(element.getKey(), p.obj.getKey());
            
    		if (result == 0) break;
            
    		//Go left or right based on comparison result.
            p = result < 0 ? p.left : p.right;
        }
        
    	return p;
    }
    
	 
	/**
	 * Remove a node from the tree.
	 * 
	 * @param node The node to be removed.
	 */
	private void Remove(TreeNode<K,V> node)
	{	   
		//If node has two children find its successor, then remove it
		if (node.left != null && node.right != null)
		{
			TreeNode<K,V> succ = succ(node);
			node.obj = succ.obj;
	        node = succ;
	    }
	    	
		TreeNode<K,V> parent = node.parent;
	    TreeNode<K,V> child = node.left != null ? node.left : node.right;
	    
	    //The root is being removed.
	    if (parent == null) 
	    {
	    	root = child;
	    }
	    
	    //Bypass node.
	    else if (node == parent.left) 
	    {
	    	parent.left = child;
	    } 
	    
	    else 
	    {
	    	parent.right = child;
	    }
	    
	    if (child != null)
	    {
	    	child.parent = parent;
	    }              
	    
	    node.parent = node.right = node.left = null;      
	}
	
	
	/**
	 *  Finds the in-order successor of a given node.
	 *   
	 * @param q The node that we look for it's successor.
	 * 
	 * @return The successor of the given node q.
	 */
	private TreeNode<K,V> succ(TreeNode<K,V> q) 
	{
		// The successor is the leftmost leaf of q’s right subtree.
		if (q.right != null) 
		{
	            TreeNode<K,V> p = q.right;
	            while (p.left != null) p = p.left;
	            return p;
		}
	        
		// The successor is the nearest ancestor on the right.
		else 
		{
			TreeNode<K,V> p = q.parent;
			TreeNode<K,V> ch = q;
	            
			while (p != null && ch == p.right) 
			{
				ch = p;
				p = p.parent;
			}
			
			return p;
		}
	}
	    	
	
	/**
	 * Removes an item from the tree.
	 * 
	 * @param element The item to be removed.
	 * 
	 * @return True, if the item was contained in the tree, false otherwise.
	 */
	public boolean remove(Item<K,V> element)
	{
		TreeNode<K,V> n = find(element);
		
		if (n == null) return false;
		
		Remove(n);
		return true;
	}
      
    
	/**
	 * Prints the contents of the BST, using the in-order style.
	 * 
	 * @param n The node from which the method will start printing.
	 */
	private void RecInorderPrint(TreeNode<K,V> n)
	{
		if(n==null) return;
		RecInorderPrint(n.left);
		System.out.print(n.obj.toString()+" ");
		RecInorderPrint(n.right);
	}
	
	
	/**
	 * Prints the contents of the BST, using the in-order style.
	 */
	public void InorderPrint()
	{
		this.RecInorderPrint(root);
		System.out.println();
	}
	
	
	/**
     * Copies the contents of the BST to a given priority queue.
     * 
     * @param pq The priority queue in which we will copy the contents of the BST.
     * @param n The node from which we will start copying elements.
     */
    private void BSTtoPQ(MinPQ<Item<K, V>> pq, TreeNode<K,V> n)
    {
        if(n == null) return;
        BSTtoPQ(pq, n.left);
        pq.insert(n.obj);
        BSTtoPQ(pq, n.right);   
    }  
    
    
	/**
	 * Copies the contents of the BST to a given priority queue.
	 * 
	 * @param pq The priority queue in which we will copy the contents of the BST.
	 */
    public void BSTtoPQ(MinPQ<Item<K, V>> pq)
    {
        BSTtoPQ(pq , root);
    }    
}