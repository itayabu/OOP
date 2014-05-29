package oop.ex6.filescript.filter.exceptions;

import java.io.IOException;

/**
 * abstract exception, all other exception extend it
 * @author Itay
 *
 */
public abstract  class BadFilterException extends IOException {

	private static final long serialVersionUID = 1L;

	public BadFilterException(){
		
	}

}
