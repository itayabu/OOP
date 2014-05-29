package oop.ex6.filescript.filter;

import java.io.File;
/**
 * abstract class for all filters used.
 * @author Itay
 *
 */
public abstract class Filter {

	/**
	 * check if a file passes the filter
	 * @param file to check
	 * @return true if filter match, false if not
	 */
	public boolean isPass(File file){
		return true;
	}
	/**
	 * print filter name and parameters
	 */
	public void PrintFilter(){
	}
}
