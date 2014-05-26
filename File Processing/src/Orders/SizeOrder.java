package Orders;

import java.io.File;

public class SizeOrder extends FileOrder{
	
	public int compare(File o1, File o2){
		int difference = (int) (o1.length() - o2.length());
		if(difference == 0)
			return super.compare(o1, o2);
		return difference;
	}
}
