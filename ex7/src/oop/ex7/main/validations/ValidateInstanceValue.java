package oop.ex7.main.validations;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oop.ex7.main.Type;
import oop.ex7.main.exceptions.AssignmentTypesArntConsist;
import oop.ex7.main.exceptions.BadInputException;
import oop.ex7.main.exceptions.CompilerError;
import oop.ex7.main.exceptions.TypeConversionException;
import oop.ex7.main.exceptions.VariableNotSimpleInGlobalException;
import oop.ex7.main.instance.Instance;
import oop.ex7.main.instance.InstanceFactory;

public class ValidateInstanceValue {

	private static String VAR_PATTERN =("_?-?([A-Za-z0-9_]*\\.?[0-9]*?)(\\s*=\\s*([^;]+))?|\".*\"|'.'|" +
			"-?[A-Za-z0-9]*[*/+-]?-?[A-Za-z0-9]*");
	private static String METHOD_PATTERN =
			"([\\sA-Za-z0-9_]*)\\s*\\((.*?)\\)\\s*\\{(.*)";
	private static String SIMPLE_PATTERN ="'.'|\".*\"|true|false|-?\\d*\\.?\\d*[/+\\*-]?-?\\d*\\.?\\d*|[A-Za-z0-9]*";
	private static Pattern SIMPLE_VALUE = Pattern.compile("-?\\d.*|\".*\"|true|false|'.'");
	private static Pattern COMPLEX_PATTERN = Pattern.compile(".+[\\*\\-+/].*|.*\\(.*\\).*");


	public static void validateValueOnInstaceCreation 
	(ArrayList<ArrayList<Instance>> list, Instance inst, String line) throws CompilerError, BadInputException{
		if (!line.contains("=")){
			return;
		}
		if(inst.isArray()){
			ValidateArrayValue.validateArrayValueOnCreation(list, inst, line);
		}
		else {
			validateVarValue(list, inst, line);
		}
	}
	private static void validateVarValue(ArrayList<ArrayList<Instance>> list, Instance inst, String line) throws CompilerError, BadInputException{
		line = manageVar(line);
		//		if (line.matches(VAR_PATTERN)){
		if (!Type.typesConsist(list, inst, line)){
			throw new AssignmentTypesArntConsist(line+"has non-consist type problem");
		}
		//		}
		//		else{
		//			throw new AssignmentTypesArntConsist(line+"has non-consist type problem");
		//		}
	}

	public static void validateValueOnInstanceCall
	(ArrayList<ArrayList<Instance>> list, Instance inst, String line) throws CompilerError, BadInputException{
		if (line.matches(METHOD_PATTERN)){
			String[] paramAsArray = getMethodArgs(line);
			for (String s:paramAsArray){
				if (!Type.typesConsist(list, inst, line)){
					throw new AssignmentTypesArntConsist(line+"has non-consist type problem");
				}
			}
		}
	}

	public static void validateMethodArgs(
			ArrayList<ArrayList<Instance>> list, Instance instance, String line) throws CompilerError, BadInputException{
		line = ValidateFunction.cutBlockBrackets(line);
		String[] args = line.split(",");
		for (int i =0; i<args.length; i++){
			args [i] = args[i].trim();
			if (!instance.getType().typesConsist(list, instance, args[i])){
				throw new CompilerError(args[i] + "had a problem");
			}
		}
	}


	/**
	 * this method get the arguments of var assignments
	 * @param line
	 * @return
	 */
	private static String manageVar(String line){
		int place = line.indexOf("=");
		line = line.substring(place+1, line.length()-1);
		return line.trim();
	}
	/**
	 * this method split mwthod's string and return the arguments of it.
	 * @param line- method string
	 * @return method string arguments in an array.
	 */
	public static String[] getMethodArgs(String line){
		int start = line.indexOf("(");
		int end = line.lastIndexOf(")");
		line = line.substring(start+1, end);
		String[] lineAsArray=line.split(",");
		//		if(end-start<2)
		//			return lineAsArray;
		return line.split(",");
	}

	/**
	 * this method asserts the assignment is a simple assignment
	 * @param line
	 * @throws BadInputException
	 */
	public static void assetrtSimpleValue(String line) throws BadInputException{
		return;
		//		if (line.contains("=")){
		//			if (line.contains("{")){
		//				ValidateArrayValue.assertSimpleInstance(line);
		//			}
		//			else{
		//				String subLine = manageVar(line);
		//				subLine = subLine.trim();
		////				Matcher match = COMPLEX_PATTERN.matcher(subLine);
		////				if (match.matches()){
		////				Matcher m= SIMPLE_PATTERN.matcher(input)
		//				if (!subLine.matches(SIMPLE_PATTERN)){
		//					throw new VariableNotSimpleInGlobalException (line + "has a complex assignment in global");
		//				}
		//			}
		//		}
	}

	/**
	 * this method receive string contain method call and return its type.
	 * @param list
	 * @param string
	 * @return the return type of method
	 * @throws CompilerError
	 * @throws BadInputException 
	 */
	public static Type getMethodTypeFtomFuncString(ArrayList<ArrayList<Instance>> list, 
			String string) throws CompilerError, BadInputException{
		int open = (string.indexOf("("));
		String name = string.substring(0, open);
		Instance func = InstanceArrayValidator.findInstance(list, name);
		validateMethodArgs(list, func, string);
		return func.getType();
	}

	public static ArrayList<Instance> fillList(ArrayList<ArrayList<Instance>> list, ArrayList<Instance> blockList, String text) throws BadInputException, CompilerError{
		//		if (text.matches(METHOD_PATTERN)){
		String[] args = getMethodArgs(text);
		if (text.startsWith("if")|| text.startsWith("while")){
			if (!ValidateBoolValue.validateBool(list, args[0], Type.BOOLEAN)){
				throw new BadInputException(text+"has bad input");
			}
		}
		else{
			for (String s:args){
				if (s.equals("")){
					return blockList;
				}
				s=s.trim();
				Instance inst = InstanceFactory.createInstance(list, s);
				inst.setInitialized(true);
				if (InstanceArrayValidator.instanceNameExistInBlock(inst, blockList)){
					throw new BadInputException("bad input");
				}
				blockList.add(inst);
				//			}
			}
		}
		return blockList;
	}
	/**
	 * a method to check special declartions
	 * @param str
	 * @return
	 */
	public static String checkSpecial(String str){
		String start ="[";
		return (String) (str.subSequence(0,str.indexOf(start)));
	}

}
