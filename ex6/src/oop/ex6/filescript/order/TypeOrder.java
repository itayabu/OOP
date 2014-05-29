package oop.ex6.filescript.order;

import java.io.File;

/**
 * sorting files by their Type name
 * @author Itay
 *
 */
public class TypeOrder extends Order {

	/**
	 * defult constructor
	 */
	public TypeOrder(){
		
	}
	/**
	 * sort by type type.
	 * if types are equal, sort by name.
	 */
	@Override
	public int compare(File fOne, File fTwo){
		String[] fOneName = fOne.getName().split("\\.");
		String[] fTwoName = fTwo.getName().split("\\.");
		int val = (int)(fOneName[fOneName.length-1].compareTo
				(fTwoName[fTwoName.length-1]));
		return (val !=0)? val:super.compare(fOne, fTwo);	
		}
}
