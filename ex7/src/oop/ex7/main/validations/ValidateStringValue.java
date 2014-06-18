package oop.ex7.main.validations;

import java.util.ArrayList;

import oop.ex7.main.Type;
import oop.ex7.main.instance.Instance;

/**
 * this class validate String assignment
 * @author Assaf M. Itay A.
 *
 */
public class ValidateStringValue {
	// boolean values
		private static final String STRING_VALUE = "\".*\"";
		
		/**
		 * 
		 * @param list
		 * @param s
		 * @param type
		 * @return
		 */
		public static boolean validateString(ArrayList<ArrayList<Instance>> list, String s,Type type){
			if (s.matches(STRING_VALUE)){
				return true;
			}
			return Type.checkIfInList(list, s, type);
		}
}
