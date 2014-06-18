package oop.ex7.main.exceptions;


/**
 * this exception is thrown when referring to a member but it's not declared first
 * @author Assaf M. Itay A.
 */
public class MemberDontExistExcetion extends IlegaMemberUseException {

	private static final long serialVersionUID = 1920936547324941172L;

	/**
	 * construct new MemberDontExistExcetion object
	 * @param message the message that describe this exception
	 */
	public MemberDontExistExcetion(String message) {
		super(message);
	}

}