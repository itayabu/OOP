
public abstract class SimpleHashSet implements SimpleSet {

	protected int capacity,size;
	private final int minCapacity = 16;
	private float upperLoadFactor, lowerLoadFactor;
	private final float defUpLoadFactor = (float) 0.75, defLowLoadFactor= (float) 0.25; 
	public SimpleHashSet(){
		capacity = minCapacity;
		upperLoadFactor = defUpLoadFactor;
		lowerLoadFactor = defLowLoadFactor;
	}
	public SimpleHashSet (float upperLoadFactor, float lowerLoadFactor){
		this.upperLoadFactor = upperLoadFactor;
		this.lowerLoadFactor = lowerLoadFactor;
		capacity = minCapacity;
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
	
	public abstract int capacity();
	
	protected void setCapacity(int newCapacity){
		capacity = newCapacity;
	}
	
	protected boolean toExpand(){
		float check = (float) ((float) size / (float) capacity);
		if ( check > upperLoadFactor){
			return true;
		}
		return false;
	}
	
	protected boolean toShrink(){
		float check = ((float) size / (float) capacity);
		if (/*(capacity > minCapacity) && */(check < lowerLoadFactor)){
			return true;
		}
		return false;
	}
}
