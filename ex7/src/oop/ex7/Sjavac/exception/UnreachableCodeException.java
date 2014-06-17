package oop.ex7.Sjavac.exception;


/**
 * this class has exception for unreachable code
 * unreachable code appears after method return statement
 * @authorAssaf M. Itay A.
 */
public class UnreachableCodeException extends CompilerError {

	private static final long serialVersionUID = 6317246324134583406L;

	/**
	 * construct new UnreachableCodeException object
	 * @param message the message that describe this exception
	 */
	public UnreachableCodeException(String message) {
		super(message);
	}

}