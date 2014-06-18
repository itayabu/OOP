package oop.ex7.main.validations;

import oop.ex7.main.instance.Instance;

public class ValidateArrayValue {

	
	public static String disguiseArray(String line){
		int start = (line.indexOf("["));
		int end = (line.indexOf("]"));
		line = line.substring(0, start) + line.substring(end+1);
		return line;
	}
	
	public static void validateArrayValueOnCreation(Instance inst, String str){
		str = manageString(str);
		String[] splitedLine= str.split(",");
		for (int i = 0; i<splitedLine.length; i++){
			
		}
	}
	
	
	private static String manageString(String s){
		int open = (s.indexOf("{"));
		int end = (s.lastIndexOf("}"));
		s = s.substring(open+1, end);
		return s;
	}
	
}
