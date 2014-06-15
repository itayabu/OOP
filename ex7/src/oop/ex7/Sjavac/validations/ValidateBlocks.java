package oop.ex7.Sjavac.validations;

import java.util.ArrayList;
import java.util.regex.Matcher;

import oop.ex7.Sjavac.RegexConstants;
import oop.ex7.Sjavac.instance.Instance;

public class ValidateBlocks {

	/**
	 * check if outer structure of new block line is legal
	 * @param s the new block line
	 * @return true if lone is legal, else false.
	 */
	public static boolean validateBlockOuterStructure(String s){
		return (validateMethodBlock(s) || validateIfOrWhileBlock(s));
	}
	
	public static boolean validateMethodBlock(String s){
		Matcher m = RegexConstants.RegexPatterns.METHOD_DECLARATION.matcher(s);
		return m.matches();
	}
	
	public static boolean validateIfOrWhileBlock(String s){
		Matcher m = RegexConstants.RegexPatterns.CONDITION_COMMAND.matcher(s);
		return m.matches();
	}
	
	public static boolean validateConditionIsLegal(ArrayList<ArrayList<Instance>> list, String s){
		s = ValidateFunction.cutBlockBrackets(s);
		s = s.trim();
		//TODO I have this function (findInstance) in mainParser, need to think where to put it.
		return true;
	}
}
