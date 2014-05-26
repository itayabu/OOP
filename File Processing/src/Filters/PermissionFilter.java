package Filters;
import java.io.File;
import java.io.FileFilter;

public class PermissionFilter implements FileFilter{

	String filterType;
	boolean permission;

	public PermissionFilter(String filterType, String permission) {
		this.filterType = filterType;
		this.permission = permission.equals("YES");
	}

	public boolean accept(File pathname) {
		switch (filterType){
		case "writeable":
			return permission == pathname.canWrite();
		case "executable":
			return permission == pathname.canExecute();
		default:
			return permission == pathname.isHidden();
		}
	}
}
