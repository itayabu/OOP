package oop.ex6.filescript.filter;

import java.io.File;

/**
 * abstract class for filters dealing with Strings
 * @author Itay
 *
 */
public abstract class StringFilter extends Filter {
	protected String stringComp;
	
	/**
	 * basic constructor
	 * @param string filter file that match request or not
	 */
	public StringFilter (String string){
		stringComp = string;
	}
	@Override
	public abstract boolean isPass(File file);
	

	@Override
	public abstract void PrintFilter();

}
