package oop.ex6.filescript.filter;

import java.io.File;

/**
 * abstract class for filters dealing with file's mod
 * @author Itay
 *
 */
public abstract class ModFilter extends Filter {

	boolean isMod;
	
	/**
	 * basic constructor
	 * @param isMod filter mod or not
	 */
	public ModFilter(boolean isMod){
		this.isMod = isMod;
	}
	
	@Override
	public abstract boolean isPass(File file);

	@Override
	public abstract void PrintFilter();

}
