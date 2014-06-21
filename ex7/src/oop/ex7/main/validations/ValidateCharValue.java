package oop.ex7.main.validations;

import java.util.ArrayList;

import oop.ex7.main.Type;
import oop.ex7.main.instance.Instance;

/**
 * this class is validator for char assignments
 * @author Assaf M. Itay A.
 *
 */
public class ValidateCharValue {

	// boolean values
	private static final String CHAR_VALUE = "\'.\'";

	/**
	 * check if assignment is legal
	 * @param list - main Instance list
	 * @param s - string to check
	 * @param type - to be compared to
	 * @return true if assignment is legal, else false
	 */
	public static boolean validateChar(ArrayList<ArrayList<Instance>> list, 
			String s,Type type){

		if (s.matches(CHAR_VALUE)){
			return true;
		}
		return ValidateInstanceValue.checkIfInList(list, s, type);
	}
}
