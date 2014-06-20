package oop.ex7.main.validations;

import java.util.ArrayList;

import oop.ex7.main.Type;
import oop.ex7.main.exceptions.IllegaIntException;
import oop.ex7.main.instance.Instance;

public class ValidateIntValue {
	//the form of a number assignment
	private static final String INT_VAL_FORM = "-?\\d+";
	//the form of an preinitialized type
	private static final String INT_VAL_STR = "-?_?[a-zA-z]+\\w*?";
	//the operations
	private static final String INT_OPR = "[*/+-]";

	/**
	 * check if assignment is legal
	 * @param list main Instance list
	 * @param s string to check
	 * @param type to be compared to
	 * @return true if assignment is legal, else false
	 * @throws IllegaIntException
	 */
	public static boolean validateInt(ArrayList<ArrayList<Instance>> list, 
			String s,Type type) throws IllegaIntException {
	
		//check if the type is really of an int Type
		if (type.getType().equals("int")){

			//if the the string is an int
			if (s.matches(INT_VAL_FORM))
				return true;

			//if the string is an action between two integers
			if (s.matches(INT_VAL_FORM+INT_OPR+INT_VAL_FORM))
				return true;

			//if the string might be a name of an initialized member
			if (s.matches(INT_VAL_STR))
				return Type.checkIfInList(list, s,Type.INT);

			String[] str = null;
			//if the string might contain an int and an initialized member
			if (s.matches(INT_VAL_FORM+INT_OPR+INT_VAL_STR)||s.matches
					(INT_VAL_STR+INT_OPR+INT_VAL_FORM)){
				str = s.split(INT_OPR);//split the string between the actions
				//if the first substring is a member
				if(str[0].matches(INT_VAL_STR)&&str[1].matches(INT_VAL_FORM))
					return Type.checkIfInList(list,str[0],Type.INT);
				//if the second substring is a member
				if(str[1].matches(INT_VAL_STR)&&str[0].matches(INT_VAL_FORM))
					return Type.checkIfInList(list, str[1],Type.INT);	
			}
			if (s.matches(INT_VAL_STR+INT_OPR+INT_VAL_STR)){
				str = s.split(INT_OPR);
				return(Type.checkIfInList(list, str[0],Type.INT) && 
						Type.checkIfInList(list, str[1],Type.INT));
			}
		}
		return false;
	}	
}
