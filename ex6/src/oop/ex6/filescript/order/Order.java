package oop.ex6.filescript.order;

import java.io.File;
import java.util.Comparator;

/**
 * Order Interface, sorting files by a specific order
 * @author Itay
 *
 */
public abstract class Order implements Comparator<File>{

	/**
	 * implement comparator method.
	 * by default, compare by absolute path
	 */
	public int compare(File fOne, File fTwo){
		return (fOne.getAbsolutePath().compareTo(fTwo.getAbsolutePath()));
	}

}