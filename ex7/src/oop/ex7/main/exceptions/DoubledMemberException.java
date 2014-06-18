package oop.ex7.main.exceptions;


/**
 * this exception is thrown when trying to override a method or variable
 * @author Assaf M. Itay A.
 */
public class DoubledMemberException extends IlegalNameException {

	private static final long serialVersionUID = 8624953453896726592L;

	/**
	 * construct new DoubledMemberException object
	 * @param message the message that describe this exception
	 */
	public DoubledMemberException(String message) {
		super(message);
	}

}