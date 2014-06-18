package oop.ex7.main.validations;

public class ValidateFunction {

	/**
	 * return everything inside brackets for a string
	 * @param s string to check
	 * @return string between the farthest away brackets
	 */
	public static String cutBlockBrackets(String s){
		int startIndex = s.indexOf('(');
		int endIndex = s.lastIndexOf(')');
		return s.substring(startIndex+1, endIndex);
	}
}
