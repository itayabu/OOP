package oop.ex6.filescript.filter;

import java.io.File;

/**
 * filter files by their execute permissions
 * @author Itay
 *
 */
public class ExecuteFilter extends ModFilter {
	
	/**
	 * basic constructor
	 * @param isMod filter executable or not
	 */
	public ExecuteFilter(boolean isMod) {
		super(isMod);
	}

	@Override
	public boolean isPass(File file) {
		return (file.canExecute() == this.isMod);
	}

	@Override
	public void PrintFilter() {
		System.out.println("filter executable "+ this.isMod);
	}


}
