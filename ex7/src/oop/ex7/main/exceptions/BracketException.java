package oop.ex7.main.exceptions;


/**
 * this exception is thrown when brackets are not closed
 * @author Assaf M. Itay A.
 */
public class BracketException extends CompilerError {

	private static final long serialVersionUID = 4517131094522203480L;

	/**
	 * construct new BracketException object
	 * @param message the message that describe this exception
	 */
	public BracketException(String message) {
		super(message);
	}

}