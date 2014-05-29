package oop.ex6.filescript.filter;

import java.io.File;

import oop.ex6.filescript.filter.exceptions.NegativeNumberParameterException;

/**
 * filter files that are greater than a given value
 *
 */
public class GreaterThanFilter extends NumericFilter {

	/**
	 * greater than constructor.
	 * @param value value of filter
	 * @throws NegativeNumberParameterException 
	 */
	public GreaterThanFilter(double value) throws NegativeNumberParameterException{
		super(value);
	}
	
	@Override
	public boolean isPass(File file) {
		double space = (double)((double)file.length()/BYTECONVERT);
		return (space > value);
	}
	
	@Override
	public void PrintFilter() {
		System.out.println("filter: greater than " + value);
	}

}
