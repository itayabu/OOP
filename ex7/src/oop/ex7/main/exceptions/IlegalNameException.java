package oop.ex7.main.exceptions;


/**
 * this exception is thrown when a declaration of a method or a variable appears with illegal name
 * @author Assaf M. Itay A.
 */
public class IlegalNameException extends MemberDeclarationException {

	private static final long serialVersionUID = -4169932225631708519L;

	/**
	 * construct new IlegalNameException object
	 * @param message the message that describe this exception
	 */
	public IlegalNameException(String message) {
		super(message);
	}

}