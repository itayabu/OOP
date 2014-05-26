package miscellaneous;

/**
 * a class to compare two numbers
 * @author Assaf
 *
 */
public class NumbersCompare {
	
	/**
	 * method which compares two numbers
	 * @param a to compare
	 * @param b to compare
	 * @return 1 if a is bigger, -1 if b is bigger, 0 if the two are equal 
	 */
	public static int numbersCompare(long a, long b){
		if(a == b)
			return 0;
		if(a < b)
			return -1;
		return 1;
	}
}
