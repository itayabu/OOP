package oop.ex7.main.validations;

import java.util.ArrayList;

import oop.ex7.main.instance.Instance;

/**
 * this class holds methods which check the blocks authenticity
 * @author Assaf M. Itay A.
 *
 */
public class ValidateBlocks {
	
	private static final String CONDITION_COMMAND =
			"(if|while)\\s*\\(([^{]+)\\)\\s*\\{(.*)";
	
	/**
	 * check if outer structure of new block line is legal
	 * @param s - the new block line
	 * @return true if lone is legal, else false.
	 */
	public static boolean validateIfOrWhileBlock(String s){
		return s.matches(CONDITION_COMMAND);
	}
	
	/**
	 * check if the block signature call is valid
	 * @param list - the list of instances
	 * @param line - the line which represents the condition signature
	 * @return
	 */
	public static Instance validateMethodCall(ArrayList<ArrayList<Instance>> 
	list, String line){
		
		String[] splitLine = line.split(" ");
		return InstanceArrayValidator.findInstance
				(list, getName(splitLine[1]).trim());
	}
	
	/**
	 * return the name of the condition
	 * @param s - the string which represents the method signature
	 * @return the method's name
	 */
	private static String getName(String s){
		
		int start =s.indexOf("(");
		return s.substring(0, start);
	}
}
