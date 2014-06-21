package oop.ex7.main.validations;

import java.util.ArrayList;

import oop.ex7.main.exceptions.CompilerError;
import oop.ex7.main.instance.Instance;

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
	
	/**
	 * this method assert legal input for method arguments on call
	 * @param list main list
	 * @param instance- instance of method
	 * @param line method call
	 * @throws CompilerError
	 */
	public static void validateMethodArgs(ArrayList<ArrayList<Instance>> list,
			Instance instance, String line) throws CompilerError{
		
		line = ValidateFunction.cutBlockBrackets(line);
		String[] args = line.split(",");
		
		//check validity of every parameter
		for (int i =0; i<args.length; i++){
			args [i] = args[i].trim();
			if (!ValidateType.typesConsist(list, instance, args[i])){
				throw new CompilerError(args[i] + "is not valid argument");
			}
		}
	}
}
