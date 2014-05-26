package Orders;
import java.io.File;

public class AbsOrder extends FileOrder{
	
	public int compare (File o1, File o2){
		int AbsCompare = o1.getName().compareTo(o2.getName());
		if(AbsCompare == 0){
			return super.compare(o1, o2);
		}
		return AbsCompare;
	}
}
