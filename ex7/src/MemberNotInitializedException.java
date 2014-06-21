

import oop.ex7.main.exceptions.IlegaMemberUseException;


/**
 * this class has exception for using a variable without initializing it
 * @author Assaf M. Itay A.
 */
public class MemberNotInitializedException extends IlegaMemberUseException {

	private static final long serialVersionUID = 6362834649620509073L;

	/**
	 * construct new MemberNotInitializedException object
	 * @param message the message that describe this exception
	 */
	public MemberNotInitializedException(String message) {
		super(message);
	}

}