package oop.ex7.main.exceptions;


/**
 * this class has exception for illegal use of variables
 * @author Assaf M. Itay A.
 */
public class IlegaMemberUseException extends CompilerError {

	private static final long serialVersionUID = 5213481741928202907L;

	/**
	 * construct new IlegaMemberUseException object
	 * @param message the message that describe this exception
	 */
	public IlegaMemberUseException(String message) {
		super(message);
	}

}