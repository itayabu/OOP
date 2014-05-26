package Filters;
import java.io.File;
import java.io.FileFilter;

public class SizeFilter implements FileFilter{

	String size;
	String filterType;

	public SizeFilter(String filterType, String size) {
		this.size = size;
		this.filterType = filterType;
	}

	public boolean accept(File pathname) {
		return filterType.equals("bigger_than")? 
				pathname.length()>Double.parseDouble(size):
				pathname.length()<Double.parseDouble(size);
	}
}
