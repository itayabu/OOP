package Orders;
import java.util.Comparator;
import java.io.File;

public class FileOrder implements Comparator<File>{

	public int compare(File o1, File o2) {
		return o1.getAbsolutePath().compareTo(o2.getAbsolutePath());
	}
}
