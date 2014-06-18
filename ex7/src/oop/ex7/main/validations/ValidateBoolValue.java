package oop.ex7.main.validations;

import java.util.ArrayList;

import oop.ex7.main.Type;
import oop.ex7.main.instance.Instance;

/**
 * this class is validator for boolean assignments
 * @author Assaf M. Itay A.
 *
 */
public class ValidateBoolValue {

	// boolean values
	private static final String BOOL_VALUE = "(true|false)";
	
	/**
	 * 
	 * @param list
	 * @param s
	 * @param type
	 * @return
	 */
	public static boolean validateBool(ArrayList<ArrayList<Instance>> list, String s,Type type){
		if (s.matches(BOOL_VALUE)){
			return true;
		}
		return Type.checkIfInList(list, s, type);
	}
}
