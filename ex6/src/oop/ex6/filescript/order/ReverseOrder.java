package oop.ex6.filescript.order;

import java.io.File;

/**
 * sorting files opposite to a given sort order
 * @author Itay
 *
 */
public class ReverseOrder extends Order {
	
	// original order
	private Order oriOrder;
	
	/**
	 * base constructor
	 * @param order - the original order
	 */
	public ReverseOrder(Order order){
		oriOrder = order;
	}
	
	/**
	 * sort opposite to the given order
	 */
	public int compare(File fOne, File fTwo){
		// for every int received by original order, return its negative.
		return -(oriOrder.compare(fOne, fTwo));
	}
}
