package oop.ex7.Sjavac.instance;

import java.util.ArrayList;
import java.util.regex.Matcher;

import oop.ex7.Sjavac.RegexConstants;
import oop.ex7.Sjavac.Type;
import oop.ex7.Sjavac.exception.BadInputException;
import oop.ex7.Sjavac.exception.CompilerError;
import oop.ex7.Sjavac.exception.IllegalParameterInput;
import oop.ex7.Sjavac.validations.ValidateInstanceValue;
import oop.ex7.Sjavac.validations.ValidateType;
import oop.ex7.Sjavac.validations.exception.BadTypeNameException;

public class InstanceFactory {

	static int TYPE_PLACE=0;
	static int NAME_PLACE=1;

	/**
	 * basic constructor
	 */
	public InstanceFactory(){

	}

	/**
	 * creating a new instance for a given instance line
	 * @param line - the params for a new instance
	 * @return	a new instance
	 * @throws BadInputException
	 * @throws CompilerError 
	 */
	public static Instance createInstance(ArrayList<ArrayList<Instance>> list,String line) throws BadInputException, CompilerError{
		Instance newInstance;
		String[] splittedLine = line.split(" ");
		String name = getName(splittedLine[NAME_PLACE]);

		// get type and delete it from string
		Type currentType = ValidateType.makeType(splittedLine[TYPE_PLACE]);
		//TODO this 1 is for space, magic number?
		line = line.substring(currentType.getType().length()+1);

		// check if String is a function
		Matcher m =RegexConstants.RegexPatterns.METHOD_DECLARATION.matcher(line);
		if (m.matches()){
//			ValidateInstanceValue.validateValueOnInstaceCreation(list, currentType, line);
			ArrayList <Type> argList = makeArgList(line);
			return new FuncInstance (currentType, name, argList);
		}

		// if String is not a function, then it is a field
		else{
			newInstance = new FieldInstance (currentType, name, checkIfInit(line));
			if (checkIfInit(line)){
				ValidateInstanceValue.validateValueOnInstaceCreation(list, currentType, line);
			}
			return newInstance;
		}	
	}

	
	
	/**
	 * check if variable is initialized
	 * @param s
	 * @return
	 */
	private static boolean checkIfInit(String s){
		if(s.contains("=")){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * receive all the String inside brackets and return arrayList of all types
	 * @param list all String inside brackets
	 * @return arraylist of all types
	 * @throws BadTypeNameException 
	 * @throws IllegalParameterInput 
	 */
	private static ArrayList<Type> makeArgList(String list) throws BadTypeNameException, IllegalParameterInput{

		ArrayList<Type> argList = new ArrayList<Type>();
		String[] args = ValidateInstanceValue.manageMethod(list);
		try{
		for (String s: args){
			argList.add((ValidateType.makeType(s.substring(0, s.indexOf(" ")))));
		}
		return argList;
		}catch (StringIndexOutOfBoundsException e){
			throw new IllegalParameterInput("method declaration doesnt contain type parameter");
		}
	}

	/**
	 * will delete suffix of String
	 */
	private static String getName(String s){
		if (s.endsWith(";")){
			s = s.substring(0, s.length()-1);
		}
		return s.trim();
	}
}
