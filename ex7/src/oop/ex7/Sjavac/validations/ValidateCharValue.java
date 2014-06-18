package oop.ex7.Sjavac.validations;

import java.util.ArrayList;

import oop.ex7.Sjavac.Type;
import oop.ex7.Sjavac.instance.Instance;

/**
 * this class is validator for char assignments
 * @author Assaf M. Itay A.
 *
 */
public class ValidateCharValue {

	// boolean values
		private static final String CHAR_VALUE = "\'.\'";
		
		/**
		 * 
		 * @param list
		 * @param s
		 * @param type
		 * @return
		 */
		public static boolean validateChar(ArrayList<ArrayList<Instance>> list, String s,Type type){
			if (s.matches(CHAR_VALUE)){
				return true;
			}
			return Type.checkIfInList(list, s, type);
		}
}
