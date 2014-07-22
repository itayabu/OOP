package oop.ex7.main.type.exceptions;

import oop.ex7.main.instance.exceptions.MemberDeclarationException;

/**
 * this exception is thrown when assignment type doesnt consist with var type
 * @author Assaf M. Itay A.
 *
 */
public class AssignmentTypesArntConsist extends MemberDeclarationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AssignmentTypesArntConsist(String message) {
		super(message);
	}

}
