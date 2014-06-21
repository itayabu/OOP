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

	private final static int TYPE_PLACE=0;
	private final static int NAME_PLACE=1;
	private final static String METHOD_DECLERATION =
			"[a-zA-Z][_\\w]* ?\\(.*\\) ?\\{";
	

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
	public static Instance createInstance(ArrayList
			<ArrayList<Instance>> list,String line) throws CompilerError{
		Instance newInstance =buildInstance (line);
		ValidateInstanceValue.validateValueOnInstaceCreation
		(list, newInstance, line);
		return newInstance;
	}

	/**
	 * build new instance
	 * @param line to build the instance from
	 * @return new instance
	 * @throws BadInputException
	 * @throws MemberDeclarationException
	 */
	private static Instance buildInstance(String line) 
			throws BadInputException, MemberDeclarationException{
		boolean rememberArray= false;
		if (line.matches("[A-za-z]*\\[\\].*|[A-za-z]*\\s*\\[\\].*")){
			line = ValidateArrayValue.hideArray(line);
			rememberArray=true;
		}
		String[] splittedLine = line.split(" ");
		// get type and delete it from string
		Type currentType = ValidateType.makeType(splittedLine[TYPE_PLACE]);
		//variable cannot be void

		String name = getName(splittedLine[NAME_PLACE]);

		line = line.substring(currentType.getTypeName().length()+1);

		// check if String is a function
//		Matcher m =RegexConstants.RegexPatterns.METHOD_DECLARATION.matcher(line);
		if (line.matches(METHOD_DECLERATION)){
			ArrayList <Instance> argList = makeArgList(line);
			return new FuncInstance (currentType, name,rememberArray, argList);
		}

		// if String is not a function, then it is a field
		else{
			if (currentType.equals(Type.VOID)){
				throw new VoidVarException(line+" has variable as void");
			}
			return new FieldInstance (currentType, name,rememberArray, checkIfInit(line));
		}
	}

	/**
	 * check if variable is initialized
	 * @param s name of var
	 * @return true if initialized, else false
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
	 * @throws BadInputException 
	 * @throws MemberDeclarationException 
	 */
	private static ArrayList<Instance> makeArgList(String list) 
			throws MemberDeclarationException, BadInputException{
		ArrayList<Instance> argList = new ArrayList<Instance>();
		String[] args = ValidateInstanceValue.getMethodArgs(list);
		if (args[0].equals("")){
			return argList;
		}
		try{
			for (String s: args){
				s= s.trim();
				argList.add(buildInstance(s));
			}
			return argList;
		}catch (StringIndexOutOfBoundsException e){
			throw new IllegalParameterInput
			("method declaration doesnt contain type parameter");
		}
	}

	/**
	 * will delete suffix of String
	 * @throws BadInputException 
	 */
	private static String getName(String s) throws BadInputException{
		if (!(s.matches("_?[A-Za-z].*"))){
			throw new BadInputException(s+"is illegal name");
		}
		int end = s.indexOf("(");
		if (end<0){
			return s;
		}
		s = s.substring(0, end);
		return s.trim();
	}
}
