package oop.ex7.Sjavac.exception;


/**
 * this class has exception for illegal return type
 * this happens when a return statement in a method returns another value than the method return type
 * @author Assaf M. Itay A.
 */
public class IlegalReturnType extends TypeConversionException {

	private static final long serialVersionUID = 5554893433740254503L;

	/**
	 * construct new IlegalReturnType object
	 * @param message the message that describe this exception
	 */
	public IlegalReturnType(String message) {
		super(message);
	}

}