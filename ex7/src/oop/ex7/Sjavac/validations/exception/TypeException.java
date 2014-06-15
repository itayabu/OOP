package oop.ex7.Sjavac.validations.exception;

import oop.ex7.Sjavac.exception.BadInputException;

public abstract class TypeException extends BadInputException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TypeException(String messege) {
		super(messege);
	}

}
