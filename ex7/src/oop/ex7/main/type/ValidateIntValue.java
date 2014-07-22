package oop.ex7.main.type;

import java.util.ArrayList;

import oop.ex7.main.instance.Instance;
import oop.ex7.main.instance.ValidateInstanceValue;
import oop.ex7.main.type.exceptions.IllegaIntException;

public class ValidateIntValue {
	//the form of a number assignment
	private static final String INT_VAL_FORM = "\\s*-?\\d+\\s*";
	//the form of an preinitialized type
	private static final String INT_VAL_STR = "-?\\s*_?[a-zA-z]+\\w*\\s*\\(?\\.*?\\)?\\s*";
	private static final String NEG_INT_VAL ="-\\s*_?[a-zA-z]+\\w*\\s*";
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
		if (type.getTypeName().equals("int")){
			s.replaceAll(" ", "");
			if (s.startsWith("-")){
				s= s.substring(s.indexOf('-')+1);
				s= s.trim();
			}
			//if the the string is an int
			if (s.matches(INT_VAL_FORM)) 
				return true;

			//if the string is an action between two integers
			if (s.matches(INT_VAL_FORM+INT_OPR+INT_VAL_FORM))
				return true;

			//if the string might be a name of an initialized member
			if (s.matches(INT_VAL_STR))
				return ValidateInstanceValue.checkIfInList(list, s,Type.INT);

			String[] str = null;
			int i = 0;
			//if the string might contain an int and an initialized member
			if (s.matches(INT_VAL_FORM+INT_OPR+INT_VAL_STR)||s.matches
					(INT_VAL_STR+INT_OPR+INT_VAL_FORM)){
				if(s.matches(NEG_INT_VAL+INT_OPR+INT_VAL_FORM ))
					i++;
				str = s.split(INT_OPR);//split the string between the actions
				//if the first substring is a member
				if(str[i].matches(INT_VAL_STR)&&str[i+1].matches(INT_VAL_FORM))
					return ValidateInstanceValue.checkIfInList(list,str[i],
							Type.INT);
				//if the second substring is a member
				if(str[i+1].matches(INT_VAL_STR)&&str[i].matches(INT_VAL_FORM))
					return ValidateInstanceValue.checkIfInList(list, str[i+1],
							Type.INT);	
			}
			if (s.matches(INT_VAL_STR+INT_OPR+INT_VAL_STR)){
				str = s.split(INT_OPR);
				return(ValidateInstanceValue.checkIfInList(list, 
						str[0],Type.INT) && 
						ValidateInstanceValue.checkIfInList(list, 
								str[1],Type.INT));
			}
		}
		return false;
	}	
}
