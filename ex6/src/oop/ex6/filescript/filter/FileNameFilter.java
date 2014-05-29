package oop.ex6.filescript.filter;

import java.io.File;

/**
 * filter files by their suffix match
 * @author Itay
 *
 */
public class FileNameFilter extends StringFilter {

	/**
	 * basic constructor
	 * @param string the name to match
	 */
	public FileNameFilter(String string) {
		super(string);
	}

	@Override
	public boolean isPass(File file) {
		return (file.getName().equals(this.stringComp));
	}

	@Override
	public void PrintFilter() {
		System.out.println("filter file name " + this.stringComp);
	}

}
