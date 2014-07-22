package oop.ex7.main.type.exceptions;

import oop.ex7.main.exceptions.IlegaMemberUseException;


/**
 * this class has exception for trying to put a value where it's not belongs
 * @author Assaf M. Itay A.
 */
public class TypeConversionException extends IlegaMemberUseException {

	private static final long serialVersionUID = 9219441845001028445L;

	/**
	 * construct new MemberNotInitializedException object
	 * @param message the message that describe this exception
	 */
	public TypeConversionException(String message) {
		super(message);
	}

}