package oop.ex6.filescript.order;
import java.io.File;

/**
 * sorting files by their size
 * @author Itay
 *
 */
public class SizeOrder extends Order {

	/**
	 * default constructor
	 */
	public SizeOrder(){	
	}
	
	/**
	 * sort by size.
	 * if sizes are equal, sort by their name.
	 */
	@Override
	public int compare(File fOne, File fTwo){
		int val = (int)(fOne.length()-fTwo.length());
		return (val !=0)? val:super.compare(fOne, fTwo);
	}
}
