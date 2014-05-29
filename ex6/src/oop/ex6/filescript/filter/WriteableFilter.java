package oop.ex6.filescript.filter;

import java.io.File;
/**
 * filter files by their writable permissions
 * @author Itay
 *
 */
public class WriteableFilter extends ModFilter {

	/**
	 * basic constructor
	 * @param isMod filter if can write or not
	 */
	public WriteableFilter(boolean isMod) {
		super(isMod);
	}

	@Override
	public boolean isPass(File file) {
		return (file.canWrite() == this.isMod);
	}

	@Override
	public void PrintFilter() {
		System.out.println("filter writeable "+ this.isMod);
	}

}
