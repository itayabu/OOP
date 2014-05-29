package oop.ex6.filescript.filter;

import java.io.File;

/**
 * filter files by their suffix match
 * @author Itay
 *
 */
public class SuffixFilter extends StringFilter {

	/**
	 * basic constructor
	 * @param string the suffix to match
	 */
	public SuffixFilter(String string) {
		super(string);
	}

	@Override
	public boolean isPass(File file) {
		return (file.getName().endsWith(this.stringComp));
	}

	@Override
	public void PrintFilter() {
		System.out.println("filter suffix "+ this.stringComp);
	}

}
