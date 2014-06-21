package oop.ex7.main.validations;

import java.util.ArrayList;
import java.util.regex.Matcher;

import oop.ex7.main.RegexConstants;
import oop.ex7.main.instance.Instance;

public class ValidateBlocks {
	private static final String CONDITION_COMMAND =
			"(if|while)\\s*\\(([^{]+)\\)\\s*\\{(.*)";
	/**
	 * check if outer structure of new block line is legal
	 * @param s the new block line
	 * @return true if lone is legal, else false.
	 */
	public static boolean validateIfOrWhileBlock(String s){
		return s.matches(CONDITION_COMMAND);
	}
	
	public static Instance validateMethodCall(ArrayList<ArrayList<Instance>> 
	list, String line){
		String[] splitLine = line.split(" ");
		return InstanceArrayValidator.findInstance
				(list, getName(splitLine[1]).trim());
		

	}
	
	private static String getName(String s){
		int start =s.indexOf("(");
		return s.substring(0, start);
	}
}
