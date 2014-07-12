package oop.ex7.main.type;

import java.util.ArrayList;

import oop.ex7.main.exceptions.IllegaIntException;
import oop.ex7.main.instance.Instance;
import oop.ex7.main.instance.ValidateInstanceValue;

public class ValidateDoubleValue {
	//the form of a number assignment
	private static final String DOUBLE_VAL_FORM ="\\s*-?\\d+\\.?\\d*|-?\\d*\\.\\d+\\s?";
	//the form of an preinitialized type
	private static final String DOUBLE_VAL_STR = "\\s?-?\\s*_?[a-zA-z]+\\w*\\s*\\(?\\.*?\\)?\\s*";
	//the operation form
	private static final String DOUBLE_OPR = "[*/+-]";
	
	/**
	 * check if assignment is legal
	 * @param list main Instance list
	 * @param s string to check
	 * @param type to be compared to
	 * @return true if assignment is legal, else false
	 * @throws IllegaIntException
	 */
	public static boolean validateDouble(ArrayList<ArrayList<Instance>> list,
			String s,Type type) throws IllegaIntException {
		//check if the type is really of an double Type
		if (type.getTypeName().equals("double")){
			
			if (s.startsWith("-")){
				s= s.substring(s.indexOf('-')+1);
			}
			//if the the string is an double
			if (s.matches(DOUBLE_VAL_FORM))
				return true;
			
			//if the string is an action between two doubles
			if (s.matches(DOUBLE_VAL_FORM+DOUBLE_OPR+DOUBLE_VAL_FORM))
				return true;
			
			//if the string might be a name of an initialized member
			if (s.matches(DOUBLE_VAL_STR))
				return ((ValidateInstanceValue.checkIfInList
						(list, s,Type.DOUBLE))||(ValidateInstanceValue.
								checkIfInList(list, s,Type.INT))) ;
			
			String[] str = null;
			//if the string might contain an double and an initialized member
			if (s.matches(DOUBLE_VAL_FORM+DOUBLE_OPR+DOUBLE_VAL_STR)||
					s.matches(DOUBLE_VAL_STR+DOUBLE_OPR+DOUBLE_VAL_FORM )){
				str = s.split("[+-/*]");//split the string between the actions
				//if the first substring is a member
				if(str[0].matches(DOUBLE_VAL_STR) && 
						str[1].matches(DOUBLE_VAL_FORM))
					return (ValidateInstanceValue.
							checkIfInList(list,str[0],Type.DOUBLE) ||
							(ValidateInstanceValue.checkIfInList
									(list,str[0],Type.INT))) ;
				//if the second substring is a member
				if(str[1].matches(DOUBLE_VAL_STR)&&str[0].matches
						(DOUBLE_VAL_FORM))
					return ValidateInstanceValue.checkIfInList
							(list, str[1],Type.DOUBLE);
						
			}	
			if (s.matches(DOUBLE_VAL_STR+DOUBLE_OPR+DOUBLE_VAL_STR)){
				str = s.split(DOUBLE_OPR);
				return(ValidateInstanceValue.checkIfInList(list, str[0],
						Type.DOUBLE) && ValidateInstanceValue.checkIfInList
						(list, str[1],Type.DOUBLE));
			}
		}
		return false;
	}	
}
