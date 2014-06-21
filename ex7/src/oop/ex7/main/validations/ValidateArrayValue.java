package oop.ex7.main.validations;

import java.util.ArrayList;

import oop.ex7.main.Type;
import oop.ex7.main.exceptions.BadInputException;
import oop.ex7.main.exceptions.CompilerError;
import oop.ex7.main.instance.Instance;

public class ValidateArrayValue {

	/**
	 * this method hides the array marks from line
	 * @param line orginal line
	 * @return line with hidden array
	 */
	public static String hideArray(String line){
		int start = (line.indexOf("["));
		int end = (line.indexOf("]"));
		if (start>0){
			line = line.substring(0, start) + line.substring(end+1);
		}
		return line;
	}

	/**
	 * method assert assignment is legal on array instance
	 * @param list main list
	 * @param inst original instance
	 * @param line line to take variable from
	 * @throws CompilerError
	 * @throws BadInputException
	 */
	public static void validateArrayValueOnCreation
	(ArrayList<ArrayList<Instance>> list,Instance inst, String str) 
			throws CompilerError, BadInputException{
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

			inst.getType();
			if (!ValidateType.typesConsist(list, inst, s)){
				throw new CompilerError("array assignmen val input illegal");
			}
		}
	}

	/**
	 * delete assignment brackets
	 * @param s
	 * @return
	 * @throws BadInputException
	 */
	private static String manageString(String s)throws BadInputException{
		int open = (s.indexOf("{"));
		int end = (s.lastIndexOf("}"));
		if (open<1){
			throw new BadInputException("array receive illegal input");
		}
		s = s.substring(open+1, end);
		s = s.replaceAll(","," , ");
		return s;
	}
	/**
	 * this method checks if the place of the value in the array is legal
	 * @param substring the string that represents the place
	 * @return true if the value is a non negative int
	 * @throws BadInputException 
	 * @throws CompilerError 
	 */
	public static boolean checkIndexBounds(ArrayList<ArrayList<Instance>> list, 
			String substring) throws BadInputException, CompilerError {
		if (substring.matches("-\\d*")){
			return false;
		}
		return ValidateIntValue.validateInt(list, substring, Type.INT);
	}

}
