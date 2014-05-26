package Filters;
import java.io.File;
import java.io.FileFilter;

public class NOTFilter implements FileFilter {
	FileFilter filter;
	
	public NOTFilter(FileFilter filter) {
		this.filter = filter;
	}
	
	public boolean accept(File pathname) {
		return !filter.accept(pathname);
	}
}
