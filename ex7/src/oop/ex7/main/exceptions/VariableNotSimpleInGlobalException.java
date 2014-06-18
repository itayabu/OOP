package oop.ex7.main.exceptions;

/**
 * this exception is thrown when a variable in global block  has a complex
 * assignment
 * @author Assaf M. Itay A.
 *
 */
public class VariableNotSimpleInGlobalException extends BadInputException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VariableNotSimpleInGlobalException(String messege) {
		super(messege);
	}

}
