package oop.ex7.main.type;

import java.util.ArrayList;

import oop.ex7.main.instance.Instance;
import oop.ex7.main.instance.ValidateInstanceValue;

/**
 * this class validate String assignment
 * @author Assaf M. Itay A.
 *
 */
public class ValidateStringValue {
	
	private static final String STRING_VALUE = "\".*\"";

	/**
	 * check if assignment is legal
	 * @param list main Instance list
	 * @param s string to check
	 * @param type to be compared to
	 * @return true if assignment is legal, else false
	 */
	public static boolean validateString
	(ArrayList<ArrayList<Instance>> list, String s,Type type){
		if (s.matches(STRING_VALUE)){
			return true;
		}
		return ValidateInstanceValue.checkIfInList(list, s, type);
	}
}
