package oop.ex7.main.exceptions;


/**
 * this exception is thrown when final member is declared but assigned no value at the declaration
 * @author Assaf M. Itay A.
 */
public class FinalMemberNoValueException extends MemberDeclarationException {

	private static final long serialVersionUID = -2766895170582784438L;

	/**
	 * construct new FinalMemberNoValueException object
	 * @param message the message that describe this exception
	 */
	public FinalMemberNoValueException(String message) {
		super(message);
	}

}