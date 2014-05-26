package Filters;
import java.io.FileFilter;

public class FilterFactory {

	public static FileFilter createFilter(String filterName) throws WrongFilterException {
		String [] values = filterName.split("#");
		try {
			switch (values[0]) {
			case "greater_than": 
				return new SizeFilter(values[1],values[0]);
			case "between":
				if (Integer.parseInt(values[1]) >= Integer.parseInt(values[2]))
					throw new WrongFilterException();
				return new BetweenFilter(values[1],values[2]);
			case "smaller_than":
				return new SizeFilter(values[1],values[0]);
			case "file":
				return new FileNameFilter(values[1],values[0]);
			case "prefix":
				return new FileNameFilter(values[1],values[0]);
			case "suffix":
				return new FileNameFilter(values[1],values[0]);
			case "writable":
				if (!(values[1].equals("YES") || values[1].equals("NO"))) 
					throw new WrongFilterException();
				return new PermissionFilter(values[1],values[0]);
			case "executable":
				if (!(values[1].equals("YES") || values[1].equals("NO"))) 
					throw new WrongFilterException();
				return new PermissionFilter(values[1],values[0]);
			case "hidden":
				if (!(values[1].equals("YES") || values[1].equals("NO"))) 
					throw new WrongFilterException();
				return new PermissionFilter(values[1],values[0]);
			default:
				throw new WrongFilterException();
			}
		} 
		catch (IndexOutOfBoundsException e) {
			throw new WrongFilterException();
		}
	}
}