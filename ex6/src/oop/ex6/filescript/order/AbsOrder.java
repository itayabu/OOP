package oop.ex6.filescript.order;

import java.io.File;
/**
 * Sorting files by their absolute name.
 * @author Itay
 *
 */
public class AbsOrder extends Order {

	/**
	 * default constructor
	 */
	public AbsOrder(){
	}

	/**
	 * sort by absolute name
	 */
	@Override
	public int compare(File fOne, File fTwo){
		return super.compare(fOne, fTwo);
	}

}
