package oop.ex7.main.exceptions;

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
