package oop.ex7.main.instance;

import java.util.ArrayList;
import java.util.regex.Matcher;

import oop.ex7.main.RegexConstants;
import oop.ex7.main.Type;
import oop.ex7.main.exceptions.BadInputException;
import oop.ex7.main.exceptions.CompilerError;
import oop.ex7.main.exceptions.IllegalParameterInput;
import oop.ex7.main.exceptions.MemberDeclarationException;
import oop.ex7.main.exceptions.VoidVarException;
import oop.ex7.main.validations.ValidateArrayValue;
import oop.ex7.main.validations.ValidateInstanceValue;
import oop.ex7.main.validations.ValidateType;
import oop.ex7.main.validationsexceptions.BadTypeNameException;

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
		// so, this is how we catch the array, as you can see it acts like a normal one
		// but we need to make a special validate instance on creation for it on the ValidateArrayValue
		if (line.matches("[A-za-z]*\\[\\].*")){
			Instance newInstance =
					(buildInstance(ValidateArrayValue.disguiseArray(line)));
				newInstance.setArray(true);
			try{
				ValidateInstanceValue.validateValueOnInstaceCreation(list, newInstance, line);
			}catch (IndexOutOfBoundsException e){
				throw new CompilerError("array error on creation");
			}
			return newInstance;
		}
		Instance newInstance =buildInstance (line);
		if (checkIfInit(line)){
			ValidateInstanceValue.validateValueOnInstaceCreation(list, newInstance, line);			
		}
		return newInstance;
	}
	private static Instance buildInstance(String line) throws BadInputException, MemberDeclarationException{
		String[] splittedLine = line.split(" ");
		// get type and delete it from string
		Type currentType = ValidateType.makeType(splittedLine[TYPE_PLACE]);
		//variable cannot be void
		
		String name = getName(splittedLine[NAME_PLACE]);

		//TODO this 1 is for space, magic number?
		line = line.substring(currentType.getType().length()+1);

		// check if String is a function
		Matcher m =RegexConstants.RegexPatterns.METHOD_DECLARATION.matcher(line);
		if (m.matches()){
			ArrayList <Type> argList = makeArgList(line);
			return new FuncInstance (currentType, name, argList);
		}

		// if String is not a function, then it is a field
		else{
			if (currentType.equals(Type.VOID)){
				throw new VoidVarException(line+" has variable as void");
			}
			return new FieldInstance (currentType, name, checkIfInit(line));
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
		String[] args = ValidateInstanceValue.getMethodArgs(list);
		try{
			for (String s: args){
				s= s.trim();
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
		if (s.endsWith(";|{")){
			s = s.substring(0, s.length()-1);
		}
		return s.trim();
	}
}
