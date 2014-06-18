package oop.ex7.Sjavac.validations;

import java.util.ArrayList;

import oop.ex7.Sjavac.Type;
import oop.ex7.Sjavac.exception.IllegaIntException;
import oop.ex7.Sjavac.instance.*;

public class ValidateDoubleValue {
	//the form of a number assignment
	private static final String DOUBLE_VAL_FORM = "-?\\d*\\.?\\d*?";
	//the form of an preinitialized type
	private static final String DOUBLE_VAL_STR = "_?\\[a-zA-z]*\\w*?";
	
	/**
	 * 
	 * @param list
	 * @param s
	 * @param type
	 * @return
	 * @throws IllegaIntException
	 */
	public static boolean validateDouble(ArrayList<ArrayList<Instance>> list, String s,Type type) throws IllegaIntException {
		//check if the type is really of an double Type
		if (type.getType().equals("int")){
			
			//if the the string is an double
			if (s.matches(DOUBLE_VAL_FORM))
				return true;
			
			//if the string is an action between two doubles
			if (s.matches(DOUBLE_VAL_FORM+"[+-/*]"+DOUBLE_VAL_FORM))
				return true;
			
			//if the string might be a name of an initialized member
			if (s.matches(DOUBLE_VAL_STR))
				return Type.checkIfInList(list, s,Type.DOUBLE);
			
			String[] str = null;
			//if the string might contain an double and an initialized member
			if (s.matches(DOUBLE_VAL_FORM+"[+-/*]"+DOUBLE_VAL_STR)||s.matches(DOUBLE_VAL_STR+"[+-/*]"+DOUBLE_VAL_FORM)){
				str = s.split("[+-/*]");//split the string between the actions
				//if the first substring is a member
				if(str[0].matches(DOUBLE_VAL_STR)&&str[1].matches("\\d*"))
					return Type.checkIfInList(list,str[0],Type.DOUBLE);
				//if the second substring is a member
				if(str[1].matches(DOUBLE_VAL_STR)&&str[0].matches("\\d*"))
					return Type.checkIfInList(list, str[1],Type.DOUBLE);
						
			}	
			if (s.matches(DOUBLE_VAL_STR+"[+-/*]"+DOUBLE_VAL_STR))
				str = s.split("[+-/*]");
				return(Type.checkIfInList(list, str[0],Type.DOUBLE)&&Type.checkIfInList(list, str[1],Type.DOUBLE));
		}
		
		throw new IllegaIntException("Not a legal double assingment");
	}	
}
