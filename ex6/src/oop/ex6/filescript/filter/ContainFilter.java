package oop.ex6.filescript.filter;

import java.io.File;

/**
 * filter files by their suffix match
 * @author Itay
 *
 */
public class ContainFilter extends StringFilter {

	/**
	 * basic constructor
	 * @param string the string to match if contained
	 */
	public ContainFilter(String string) {
		super(string);
	}

	@Override
	public boolean isPass(File file) {
		return (file.getName().contains(this.stringComp));
	}

	@Override
	public void PrintFilter() {
		System.out.println("Filter contain " + this.stringComp);
	}

}
