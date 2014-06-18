package oop.ex7.Sjavac.validations;

import java.util.ArrayList;

import oop.ex7.Sjavac.Type;
import oop.ex7.Sjavac.exception.IllegaIntException;
import oop.ex7.Sjavac.instance.*;

public class ValidateIntValue {
	//the form of a number assignment
	private static final String INT_VAL_FORM = "-?\\d*";
	//the form of digits action
	private static final String INT_VAL_ACTION ="-?\\d*[+-/*]-?\\d*";
	//the form of an preinitialized type
	private static final String INT_VAL_STR = "_?\\[a-zA-z]*\\w*?";
	
	/**
	 * 
	 * @param list
	 * @param s
	 * @param type
	 * @return
	 * @throws IllegaIntException
	 */
	public static boolean validateInt(ArrayList<ArrayList<Instance>> list, String s,Type type) throws IllegaIntException {
		//check if the type is really of an int Type
		if (type.getType().equals("int")){
			
			//if the the string is an int
			if (s.matches(INT_VAL_FORM))
				return true;
			
			//if the string is an action between two integers
			if (s.matches(INT_VAL_ACTION))
				return true;
			
			//if the string might be a name of an initialized member
			if (s.matches(INT_VAL_STR))
				return checkIfInList(list, s);
			
			String[] str;
			//if the string might contain an int and an initialized member
			if (s.matches(INT_VAL_FORM+"[+-/*]"+INT_VAL_STR)||s.matches(INT_VAL_STR+"[+-/*]"+INT_VAL_FORM)){
				str = s.split("[+-/*]");//split the string between the actions
				//if the first substring is a member
				if(str[0].matches(INT_VAL_STR)&&str[1].matches("\\d*"))
					return checkIfInList(list,str[0]);
				//if the second substring is a member
				if(str[1].matches(INT_VAL_STR)&&str[0].matches("\\d*"))
					return checkIfInList(list, str[1]);
						
			}	
			
		}
		
		return false;
	}
	/**
	 * check if list contains an instance with the same name
	 * @param list the list to check if contains
	 * @param str
	 * @return
	 */
	private static boolean checkIfInList (ArrayList<ArrayList<Instance>> list ,String str){
		for(ArrayList<Instance> instArray:list)
			for(Instance inst:instArray)
				return(inst.getType().equals(str));
		return false;
	}
	
	
}
