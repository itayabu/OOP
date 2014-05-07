
public class OpenHashSet extends SimpleHashSet {

	private final double DIV_FACTOR=0.5, DOUBLE_FACTOR=2;
	String [] hashTable;
	String nulled = "nothing here";

	/**
	 * A default constructor.
	 * Constructs a new, empty table with default initial capacity (16), 
	 * upper load factor (0.75) and lower load factor (0.25).
	 */
	public OpenHashSet(){
		super ();
		hashTable = new String[capacity];
		}

	/**
	 * ChainedHashTable constructor
	 * Constructs a new, empty table with the specified load factors,
	 * and the default initial capacity (16).
	 * @param upperLoadFactor
	 * @param lowerLoadFactor
	 */
	public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
		super (upperLoadFactor, lowerLoadFactor);
		hashTable = new String[capacity];
		}

	/**
	 * Data constructor - builds the hash set by adding the elements one by one. 
	 * The new table has the default values of initial capacity (16), 
	 * upper load factor (0.75), and lower load factor (0.25).
	 * @param data
	 */
	public OpenHashSet(String[] data){
		super ();
		hashTable = new String[capacity];
		for (int i = 0; i < data.length; i++){
			this.add(data[i]);
		}
	}

	/**
	 * construct a new hash table
	 */
	
	@Override
	public boolean add(String newValue) {
		if (this.contains(newValue)){
			return false;
		}
		quickAdd (newValue);
		if (toExpand()){
			changeCapacity(DOUBLE_FACTOR);
		}
		return true;
	}
	
	/**
	 * add String to table without checking it.
	 * @param newValue to add
	 */
	private void quickAdd(String newValue){
		int val= hash(newValue);
		int i = 0;
		
		// this is not an endless loop: according to excersice's guideline,
		// rehashing will find an empty slot at some point. 
		while (true){
			val = reHash(val, i);
			if ((hashTable[val] == null) || (hashTable[val] == nulled)){
				hashTable[val] = newValue;
				size++;
				return;
			}
			i++;
		}
	}

	@Override
	public boolean contains(String searchVal) {
		if (checkCell(searchVal) >= 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String toDelete) {
		int place = checkCell (toDelete);
		if (place >= 0){
			hashTable[place] = nulled;
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
		return value.hashCode()&(capacity()-1);
	}

	/**
	 * find the next hash value of integer.
	 * @param oldVal- the original vale
	 * @param hashFactor- the next step for hash computing
	 * @return
	 */
	private int reHash(int oldVal, int hashFactor){
		return (int) (oldVal + ((hashFactor * hashFactor +
				hashFactor)*DIV_FACTOR)) & (capacity()-1) ;
	}

	/**
	 * check table  for cell contain name
	 * @param name- string to search
	 * @return number of cell if exist in table, -1 if not.
	 */
	private int checkCell(String name){
		int val= hash(name);
		int i=0;
		// by def. of hash function and open cells in array, 
		//reHash will always reach null cell at some point
		while (true){
			val= reHash(val, i);
			// if reached null, the name is no further away.
			if (hashTable[val] == null){
				return (-1);
			}
			if (hashTable[val].equals(name) &&
					(!(System.identityHashCode(hashTable[val]) == 
					System.identityHashCode(nulled)))){
				return val;
			}
			i++;
		}
	}

	/**
	 * Change table capacity by a given factor
	 * @param factor to double the table by.
	 */
	private void changeCapacity(double factor){
		// save the old table stats and build a new table
		String [] oldTable= hashTable;
		int oldCap = capacity;
		size=0;
		capacity = (int) (oldCap*factor);
		hashTable = new String [capacity()];
		// copy values from the old table to the new
		for (int i = 0; i < oldCap; i++){
			if ((oldTable[i] != null) && (oldTable[i] != nulled)){
				quickAdd(oldTable[i]);
			}
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
}
