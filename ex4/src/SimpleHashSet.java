
public abstract class SimpleHashSet implements SimpleSet {

	/**
	 * capacity for array size, size for number of elements in array.
	 */
	protected int capacity,size;
	private final int DIF_CAPACITY = 16;
	private float upperLoadFactor, lowerLoadFactor;
	private final static float DEFULT_UPPER_LF = (float) 0.75, 
			DEFULT_LOWER_LF= (float) 0.25;
	
	/**
	 * default constructor
	 */
	public SimpleHashSet(){
		this(DEFULT_UPPER_LF, DEFULT_LOWER_LF);
	}
	
	/**
	 * bound percentage constructor.
	 *  receive upper and lower percentage of numbers in array bounds
	 * @param upperLoadFactor- the upper bound of numbers
	 * @param lowerLoadFactor- the lower bound of numbers
	 */
	public SimpleHashSet (float upperLoadFactor, float lowerLoadFactor){
		this.upperLoadFactor = upperLoadFactor;
		this.lowerLoadFactor = lowerLoadFactor;
		capacity = DIF_CAPACITY;
		size=0;
	}
	
	/**
	 * Add a specified element to the set.
	 * @param newValue New value to add to the set
	 * @return False iff newValue already exists in the set
	 */
	public abstract boolean add(String newValue);
	
	/**
	 * Look for a specified value in the set.
	 * @param searchVal Value to search for
	 * @return True iff searchVal is found in the set
	 */
	public abstract boolean contains(String searchVal);
	
	/**
	 * Remove the input element from the set.
	 * @param toDelete Value to delete
	 * @return True iff toDelete is found and deleted
	 */
	public abstract boolean delete(String toDelete);
	
	/**
	 * @return The number of elements currently in the set
	 */
	public int size(){
		return size;
	}
	
	/**
	 * @return The current capacity (number of cells) of the table
	 */
	public abstract int capacity();
	
	protected float getUpperLoadFactor(){
		return upperLoadFactor;
	}
	
	protected float getLowerLoadFactor(){
		return lowerLoadFactor;
	}
}
