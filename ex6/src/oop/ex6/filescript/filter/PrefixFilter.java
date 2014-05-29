package oop.ex6.filescript.filter;

import java.io.File;

/**
 * filter files by their prefix match
 * @author Itay
 *
 */
public class PrefixFilter extends StringFilter {

	/**
	 * basic constructor
	 * @param string the prefix to match
	 */
	public PrefixFilter(String string) {
		super(string);
	}

	@Override
	public boolean isPass(File file) {
		return (file.getName().startsWith(this.stringComp));
	}

	@Override
	public void PrintFilter() {
		System.out.println("filter prefix "+ this.stringComp);
	}

}
