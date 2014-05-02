public class OpenHashSet extends SimpleHashSet {

	private final int divFactor=2, doubleFactor=2;
	String [] hashTable;
	String nulled = "nothing here";
	
	public OpenHashSet(){
		super ();
		 buildHashSet();
	}

	public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
		super (upperLoadFactor, lowerLoadFactor);
		 buildHashSet();
	}
	public OpenHashSet(String[] data){
		super ();
		 buildHashSet();
		for (int i = 0; i < data.length; i++){
			this.add(data[i]);
		}
	}
	
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
	private int hash(String value){
		return value.hashCode()&(capacity()-1);
	}

	private void nullify( String[] Table, int sizeOfTable){
		for (int i = 1; i < sizeOfTable; i++){
			Table[i]=null;
		}
	}
	public void printVal(int num){
//		System.out.println(hashTable[num]);
	}
	public void printTable(){
		for (int i =0; i<capacity; i++){
			printVal (i);
		}
	}

	/**
	 * 
	 * @param name
	 * @return number of cell if exist in table, -1 if not.
	 */
	public int checkCell(String name){
		int val= hash(name);
		for (int i = 0; i <= capacity(); i++){
			val= reHash(val, i);
			if (hashTable[val] == null){
				return (-1);
			}
			if (hashTable[val].equals(name)){
				return val;
			}
		}
		return (-1);
	}

	public int reHash(int oldVal, int hashFactor){
		return (oldVal + ((hashFactor * hashFactor + hashFactor)/divFactor)) & (capacity()-1) ;
	}

	private void expand(){
//		System.out.println("------expanding");
		String [] oldTable= hashTable;
		int oldCap = capacity();
		size=0;
		setCapacity (capacity() * doubleFactor);
		hashTable = new String [capacity()];
		for (int i = 0; i < oldCap; i++){
			if ((oldTable[i] != null) && (oldTable[i] != nulled)){
				add(oldTable[i]);
			}
		}
		for (int i = 0; i < oldCap; i++){
//			System.out.println("old " + oldTable[i]);
		}
		for (int i = 0; i< capacity; i++){
//			System.out.println("new " + hashTable[i]);
		}
	}
	private void shrink(){
//		System.out.println("-------------shrinking");
		String [] oldTable= hashTable;
		int oldCap = capacity();
		size=0;
		setCapacity (capacity()/ divFactor);
		hashTable = new String [capacity()];
		for (int i = 0; i < oldCap; i++){
			if ((oldTable[i] != null) && (oldTable[i] != nulled)){
				add(oldTable[i]);
			}
		}
		for (int i = 0; i < oldCap; i++){
//			System.out.println("old " + oldTable[i]);
		}
		for (int i = 0; i< capacity; i++){
//			System.out.println("new " + hashTable[i]);
		}
	}

	@Override
	public int capacity() {
		return capacity;
	}

}
