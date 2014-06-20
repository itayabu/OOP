package oop.ex7.main.exceptions;

public class BadInputException extends CompilerError {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BadInputException(String messege){
		super(messege);
	}

}
