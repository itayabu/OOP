package oop.ex6.filescript.filter;

import java.io.File;

import oop.ex6.filescript.filter.exceptions.NegativeNumberParameterException;

/**
 * abstract class for filters dealing with numbers
 * @author Itay
 *
 */
abstract class NumericFilter extends Filter {
	protected double value; 
	final protected int BYTECONVERT=1024;
	
	/**
	 * numeric filter constructor. hold double value for filtering
	 * @param value number to filter
	 * @throws NegativeNumberParameterException
	 */
	public NumericFilter(double value) throws NegativeNumberParameterException{
		if (!isPositiveValue(value)){
			throw new NegativeNumberParameterException();
		}
		this.value = value;
	}
	
	/**
	 * check if number is positive value
	 * @param value to check
	 * @return true if value equal or greater than 0, false else
	 */
	protected boolean isPositiveValue(double value){
		return (value >= 0);
	}
	
	public abstract boolean isPass(File file);
}
