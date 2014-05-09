package data_structures;

public class AvlTree {
	
	/**
	 * A defult contructor
	 */
	public AvlTree(){
		
	}

	/**
	 * A data constructor.
	 * a constructor that builds the tree by adding the elements in the input 
	 * array one-by-one.
	 * 
	 * @param data values to add
	 */
	public AvlTree (int[] data){
		
	}

/**
 * A copy constructor
 * a constructor that build a tree copy of an existing tree
 */
	public AvlTree( AvlTree tree){
		
	}

	/**
	 * minimum number of nodes
	 * This method calculates the minimum number of nodes in an AVL tree of 
	 * hight h
	 * 
	 * @param h hight of tree (a non negatve number)
	 * @return minimum number of nodes in the tree
	 */
	public static int findMinNodes (int h){
		return 0;
	}
	
	/**
	 * Add a new node with key newValue into the tree
	 * 
	 * @param new value to add to the tree
	 * @return false iff new value already exist
	 */
	public boolean add (int newValue){
		return true;
	}
	
	/**
	 * Does tree contain a given value
	 * 
	 * @param val value to search
	 * @return if value is found in the tree, return the depth of the value
	 * otherwise return -1
	 */
	public int contains (int searchVal){
		return 0;
	}
	
	/**
	 * remove a node from a tree, if eists.
	 * 
	 * @param toDelete value to delete
	 * @return true iff toDelete is found and deleted.
	 */
	public boolean delete (int toDelete){
		return true;
	}
	
	/**
	 * @return number of nodes in the tree
	 */
	public int size(){
		return 0;
	}
	
}
