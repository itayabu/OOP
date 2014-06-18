package oop.ex7.main.exceptions;


/**
 * this exception is thrown when illegal declaration appears
 * @author Assaf M. Itay A.
 */
public class MemberDeclarationException extends CompilerError {

	private static final long serialVersionUID = 3970794305516476951L;

	/**
	 * construct new MemberDeclarationException object
	 * @param message the message that describe this exception
	 */
	public MemberDeclarationException(String message) {
		super(message);
	}

}