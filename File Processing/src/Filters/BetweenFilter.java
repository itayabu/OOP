package Filters;
import java.io.File;
import java.io.FileFilter;

/**
 * 
 * @author Assaf
 *
 */
public class BetweenFilter implements FileFilter{
	double lowerLimit;
	double upperLimit;
	
	/**
	 * constructor
	 * @param lowerLimit
	 * @param upperLimit
	 */
    public BetweenFilter(String lowerLimit, String upperLimit) {
    	this.lowerLimit=Double.parseDouble(lowerLimit);
    	this.upperLimit=Double.parseDouble(upperLimit);
    }
    
	@Override
	public boolean accept(File pathname) {
		return (pathname.length()<=upperLimit && lowerLimit<=pathname.length());
	}
}

