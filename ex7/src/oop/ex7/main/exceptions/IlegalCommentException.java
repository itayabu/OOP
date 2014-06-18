package oop.ex7.main.exceptions;

/**
 * this exception is thrown when a comment not closed properly 
 * @author Assaf M. Itay A.
 */
public class IlegalCommentException extends CompilerError {

	private static final long serialVersionUID = 8036032081143460874L;

	/**
	 * construct new IlegalCommentException object
	 * @param message the message that describe this exception
	 */
	public IlegalCommentException(String message) {
		super(message);
	}

}