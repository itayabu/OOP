package oop.ex7.main.exceptions;

public class MemberDoesNotExistException extends BadInputException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MemberDoesNotExistException(String messege) {
		super(messege);
	}

}
