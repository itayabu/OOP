package oop.ex7.main.instance.exceptions;

import oop.ex7.main.type.exceptions.IlegalNameException;

/**
 * this exception is thrown when parameter is illegal
 */
public class IllegalParameterInput extends IlegalNameException {

	
	private static final long serialVersionUID = 1L;

	/**
	 * construct new IllegalParameterInput object
	 * @param message the message that describe this exception
	 */
	public IllegalParameterInput(String message) {
		super(message);
	}

}
