package oop.ex6.filescript.filter;

import java.io.File;

import oop.ex6.filescript.filter.exceptions.NegativeNumberParameterException;
import oop.ex6.filescript.filter.exceptions.WrongOrderOfNumbersException;

/**
 * filter files that are between two numbers
 *
 */
public class BetweenFilter extends NumericFilter {


	double highVal,lowVal;

	/**
	 * simple constructor
	 * @param lowValue- the value needed to be greater than
	 * @param highValue- the value needed to be smaller than
	 * @throws NegativeNumberParameterException 
	 * @throws WrongOrderOfNumbersException
	 */
	public BetweenFilter(double lowValue, double highValue) 
			throws NegativeNumberParameterException,
			WrongOrderOfNumbersException {
		
		super(0);
		if (!checkOrderOfParameters(lowValue, highValue)){
			throw new WrongOrderOfNumbersException();
		}
		this.lowVal = lowValue;
		this.highVal = highValue;
	}
	
	/**
	 * check if lowValue is lower than highValue
	 * @param lowValue
	 * @param highValue
	 * @return true if lowValue is lower
	 */
	private boolean checkOrderOfParameters(double lowValue, double highValue){
		return (lowValue<highValue);
	}
	
	@Override
	public void PrintFilter() {
		System.out.println("filter between "+lowVal + " and " + highVal);
	}

	@Override
	public boolean isPass(File file) {
		return ((file.length()/BYTECONVERT)<=highVal &&
				(file.length()/BYTECONVERT)>=lowVal);
	}


}
