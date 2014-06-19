package oop.ex7.main.validations;

import java.util.ArrayList;

import oop.ex7.main.exceptions.BadInputException;
import oop.ex7.main.exceptions.CompilerError;
import oop.ex7.main.exceptions.TypeConversionException;
import oop.ex7.main.instance.Instance;

public class ValidateArrayValue {

	
	public static String disguiseArray(String line){
		int start = (line.indexOf("["));
		int end = (line.indexOf("]"));
		line = line.substring(0, start) + line.substring(end+1);
		return line;
	}
	
	public static void validateArrayValueOnCreation(ArrayList<ArrayList<Instance>> list,Instance inst, String str) throws CompilerError{
		if (!inst.isInitialized()){
			return;
		}
		
		str = manageString(str);
		if (str.equals("")){
			return;
		}
		String[] splitLine= str.split(",");
		for (String s: splitLine){
			s = s.trim();
			
			if (!inst.getType().typesConsist(list, inst.getType(), s)){
				throw new CompilerError("array assignmen val input illegal");
			}
		}
	}
	
	public static void assertSimpleInstance(String sstr) throws BadInputException{
		sstr = manageString(sstr);
		String []splitLine = sstr.split(",");
		for (String s:splitLine){
			ValidateInstanceValue.assetrtSimpleValue(s);
		}
		
	}
	
	private static String manageString(String s)throws IndexOutOfBoundsException{
		int open = (s.indexOf("{"));
		int end = (s.lastIndexOf("}"));
		s = s.substring(open+1, end);
		s = s.replaceAll(","," , ");
		return s;
	}
	/**
	 * this method checks if the place of the value in the array is legal
	 * @param substring the string that represents the place
	 * @return true if the value is a non negative int
	 */
	public static boolean checkIndexBounds(String substring) {
		return (Integer.parseInt(substring)>=0);
	}
	
}
