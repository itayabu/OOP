package Filters;
import java.io.File;
import java.io.FileFilter;

public class FileNameFilter implements FileFilter {

	String input;
	String filterType;

	public FileNameFilter(String input, String type) {
		this.input = input;
		this.filterType = type;
	}

	public boolean accept(File pathname) {
		switch (filterType) {
		case "prefix":
			return pathname.getName().startsWith(input);
		case "suffix":
			return pathname.getName().endsWith(input);
		case "contains":
			return pathname.getName().contains(input);
		default:
			return pathname.getName().equals(input);
		}
	}
}
