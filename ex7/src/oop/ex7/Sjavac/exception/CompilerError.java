package oop.ex7.Sjavac.exception;

/**
 * this exception is thrown when an error occurs in S-java code
 * @author Assaf M. Itay A.
 */
public class CompilerError extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompilerError(String message) {
		super(message);
	}
}