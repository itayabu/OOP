package oop.ex6.filescript.filter;
import java.io.File;

import oop.ex6.filescript.filter.exceptions.NegativeNumberParameterException;

/**
 * filter files that are smaller than a given value
 *
 */
public class SmallerThanFilter extends NumericFilter {

	/**
	 * simple constructor
	 * @param value value of filter
	 * @throws NegativeNumberParameterException 
	 */
	public SmallerThanFilter(double value) 
			throws NegativeNumberParameterException {
		super(value);
	}

	@Override
	public boolean isPass(File file) {
		double space = (double)(file.length()/BYTECONVERT);
		return (space < value);
	}

	@Override
	public void PrintFilter() {
		System.out.println("filter: smaller than " + value);
		
	}

}
