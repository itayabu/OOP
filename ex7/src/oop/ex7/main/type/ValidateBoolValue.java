package oop.ex7.main.type;

import java.util.ArrayList;

import oop.ex7.main.instance.Instance;
import oop.ex7.main.instance.ValidateInstanceValue;

/**
 * this class is validator for boolean assignments
 * @author Assaf M. Itay A.
 *
 */
public class ValidateBoolValue {

	// boolean values
	private static final String BOOL_VALUE = "\\s*(true|false)\\s*";

	/**
	 * check if assignment is legal
	 * @param list - main Instance list
	 * @param s - string to check
	 * @param type to be compared to
	 * @return true if assignment is legal, else false.
	 */
	public static boolean validateBool(ArrayList<ArrayList<Instance>> list,
			String s,Type type){

		if (s.matches(BOOL_VALUE)){
			return true;
		}
		return ValidateInstanceValue.checkIfInList(list, s, type);
	}
}
