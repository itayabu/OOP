public class OpenHashSet extends SimpleHashSet {

	private final int DIV_FACTOR=2, DOUBLE_FACTOR=2;
	
	String [] hashTable;
	String nulled = "nothing here";
	
	/**
	 * A default constructor.
	 * Constructs a new, empty table with default initial capacity (16), 
	 * upper load factor (0.75) and lower load factor (0.25).
	 */
	public OpenHashSet(){
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
	public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
		super (upperLoadFactor, lowerLoadFactor);
		 buildHashSet();
	}
	
	/**
	 * Data constructor - builds the hash set by adding the elements one by one. 
	 * The new table has the default values of initial capacity (16), 
	 * upper load factor (0.75), and lower load factor (0.25).
	 * @param data
	 */
	public OpenHashSet(String[] data){
		super ();
		 buildHashSet();
		for (int i = 0; i < data.length; i++){
			this.add(data[i]);
		}
	}
	
	/**
	 * construct a new hash table
	 */
	private void buildHashSet(){
		hashTable = new String[capacity];
		nullify(hashTable, capacity);
	}
	
	@Override
	public boolean add(String newValue) {
		int val = hash(newValue);
		if (this.contains(newValue)){
			return false;
		}
		int i = 0;
		while (true){
			val = reHash(val, i);
			if ((hashTable[val] == null) || (hashTable[val] == nulled)){
				hashTable[val] = newValue;
				size++;
				if (toExpand()){
					expand();
				}
				return true;
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
				shrink();
			}
			return true;
		}
//		System.out.println("didnt delete " + toDelete);
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
		return (oldVal + ((hashFactor * hashFactor +
				hashFactor)/DIV_FACTOR)) & (capacity()-1) ;
	}

	/**
	 * initialize the hash table
	 * @param Table to be initialized
	 */
	private void nullify( String[] Table, int sizeOfTable){
		for (int i = 1; i < sizeOfTable; i++){
			Table[i]=null;
		}
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
			if (hashTable[val].equals(name)){
				return val;
			}
			i++;
		}
	}

	/**
	 * expand the table by 2
	 */
	private void expand(){
		// save the old table stats and build a new table
		String [] oldTable= hashTable;
		int oldCap = capacity();
		size=0;
		capacity *= DOUBLE_FACTOR;
		hashTable = new String [capacity()];
		// copy values from the old table to the new
		for (int i = 0; i < oldCap; i++){
			if ((oldTable[i] != null) && (oldTable[i] != nulled)){
				add(oldTable[i]);
			}
		}
	}
	
	/**
	 * shrink table capacity by 2
	 */
	private void shrink(){
		// save the old table stats and build a new table
		String [] oldTable= hashTable;
		int oldCap = capacity();
		size=0;
		capacity = (capacity()/ DIV_FACTOR);
		hashTable = new String [capacity()];
		// copy values from the old table to the new
		for (int i = 0; i < oldCap; i++){
			if ((oldTable[i] != null) && (oldTable[i] != nulled)){
				add(oldTable[i]);
			}
		}
	}

	@Override
	public int capacity() {
		return capacity;
	}
}
