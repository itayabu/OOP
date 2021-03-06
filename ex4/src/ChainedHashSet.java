import java.lang.String;
import java.util.LinkedList;
public class ChainedHashSet extends SimpleHashSet {

	private final double DIV_FACTOR=0.5, DOUBLE_FACTOR=2;
	private MyLinkedListFadace [] hashTable;

	/**
	 * A default constructor.
	 * Constructs a new, empty table with default initial capacity (16), 
	 * upper load factor (0.75) and lower load factor (0.25).
	 */
	public ChainedHashSet(){
		super ();
		buildHashSet();
	}

	/**
	 * ChainedHashTable constructor
	 * Constructs a new, empty table with the specified load factors,
	 * and the default initial capacity (16).
	 * @param upperLoadFactor
	 * @param lowerLoadFactor
	 */
	public ChainedHashSet(float upperLoadFactor, float lowerLoadFactor){
		super (upperLoadFactor, lowerLoadFactor);
		buildHashSet();
	}

	/**
	 * Data constructor. builds the hash set by adding the elements one by one. 
	 * The new table has the default values of initial capacity (16), 
	 * upper load factor (0.75), and lower load factor (0.25).
	 * @param data
	 */
	public ChainedHashSet(String[] data){
		super ();
		buildHashSet();
		for (int i=0; i<data.length; i++){
			add(data[i]);
		}
	}

	/**
	 * construct a new hash table
	 */
	private void buildHashSet(){
		hashTable = new MyLinkedListFadace[capacity];
		nullify(hashTable);
	}

	@Override
	public boolean add(String newValue) {
		if ( ! this.contains(newValue)){
			hashTable[hash(newValue)].add(newValue);
			size++;
			if(toExpand()){
				changeCapacity(DOUBLE_FACTOR);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(String searchVal) {
		if (hashTable[hash(searchVal)].contains(searchVal)){
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String toDelete) {
		if (this.contains(toDelete)){
			hashTable[hash(toDelete)].delete(toDelete);
			size--;
			if (toShrink()){
				changeCapacity(DIV_FACTOR);
			}
			return true;
		}
		return false;
	}

	/**
	 * hash function for strings
	 * @param value- a string to be hashed
	 * @return the hashed value.
	 */
	private int hash(String value){
		return value.hashCode()&(capacity-1);
	}

	/**
	 * initialize the hash table
	 * @param Table to be initialized
	 */
	private void nullify( MyLinkedListFadace[] table){
		for (int i = 0; i < table.length; i++){
			table[i]=new MyLinkedListFadace();
		}
	}

	/**
	 * change table capacity by factor.
	 * @param factor to change capacity by.
	 */
	private void changeCapacity(double factor){

		// save the old table stats and build a new table
		MyLinkedListFadace[] oldTable= hashTable;
		int oldCap = capacity;
		size=0;
		capacity = (int)(capacity* factor);
		hashTable = new MyLinkedListFadace[capacity];
		nullify(hashTable);
		// copy values from the old table to the new
		for (int i = 0; i < oldCap; i++){
			reHashList(oldTable[i]);
		}
	}

	/**
	 * hash all elements from a list to the hashTable
	 * @param list MyLinkedListFadace type from witch poll values 
	 * 			to the hashTable
	 */
	private void reHashList(MyLinkedListFadace list){
		while (! list.isEmpty()){
			add(list.pollString());
		}
	}

	@Override
	public int capacity() {
		return capacity;
	}
	/**
	* check if set needs to expand.
	* check if upper load bound exceeded.
	* @return true if upper bound exceeded
			*/
			private boolean toExpand(){
		float check = (float) ((float) size / (float) capacity);
		if ( check > getUpperLoadFactor()){
			return true;
		}
		return false;
	}
	
	/**
	 * check if set needs to shrink.
	 * check if lower load bound exceeded.
	 * @return true if upper bound exceeded
	 */
	private boolean toShrink(){
		float check = ((float) size / (float) capacity);
		if ((check < getLowerLoadFactor()) && (capacity>1)){
			return true;
		}
		return false;
	}

	/**
	 * easy wrap for linkedList
	 * similar to CollectionFacade but include more options
	 * @author Itay
	 *
	 */
	private class MyLinkedListFadace{

		private LinkedList<String> list;

		/**
		 * default constructor, initiate a linkedList
		 */
		private MyLinkedListFadace() {
			list = new LinkedList<String>();
		}

		/**
		 * Add a specified element to the set.
		 * @param newValue New value to add to the set
		 */
		private void add(String newValue) {
			list.add(newValue);
		}

		/**
		 * Look for a specified value in the list
		 * @param searchVal 
		 * @return true if element exist in list
		 */
		private boolean contains(String searchVal) {
			if ((!list.isEmpty()) && (list.contains(searchVal))){
				return true;
			}
			return false;
		}

		/**
		 * delete an element from list
		 * @param toDelete
		 * @return
		 */
		private void delete(String toDelete) {
			this.list.remove(toDelete);
		}

		/**
		 * delete and return the first string in list
		 * @return the first string in list
		 */
		private String pollString(){
			return list.pollFirst();
		}

		/**
		 * check if list is empty
		 * @return true if list is empty, false else
		 */
		private boolean isEmpty(){
			return list.isEmpty();
		}
	}
}

