package oop.ex6.filescript.filter;

import java.io.File;

/**
 * filter files by their hidden permissions
 * @author Itay
 *
 */
public class HiddenFilter extends ModFilter {
	/**
	 * basic constructor
	 * @param isMod filter hidden or not
	 */
	public HiddenFilter(boolean isMod) {
		super(isMod);
	}

	@Override
	public boolean isPass(File file) {
		return (file.isHidden() == this.isMod);
	}

	@Override
	public void PrintFilter() {
		System.out.println("filter hidden "+ this.isMod);
	}


}
