package oop.ex7.main.type.exceptions;

import oop.ex7.main.exceptions.BadInputException;

public abstract class TypeException extends BadInputException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TypeException(String messege) {
		super(messege);
	}

}
