package Filters;
import java.io.File;
import java.io.FileFilter;

public class AllFilter implements FileFilter {
	public boolean accept(File pathname) {
		return true;
	}

}
