package oop.ex7.main.instance;

import java.util.ArrayList;
import oop.ex7.main.Type;
import oop.ex7.main.exceptions.BadInputException;
import oop.ex7.main.exceptions.CompilerError;
import oop.ex7.main.exceptions.IllegalParameterInput;
import oop.ex7.main.exceptions.MemberDeclarationException;
import oop.ex7.main.exceptions.VoidVarException;
import oop.ex7.main.validations.ValidateArrayValue;
import oop.ex7.main.validations.ValidateInstanceValue;
import oop.ex7.main.validations.ValidateType;

/**
 * this class creates the instance
 * @author Assaf M Itay A.
 *
 */
public class InstanceFactory {

	private final static int TYPE_PLACE=0;
	private final static int NAME_PLACE=1;
	private final static String METHOD_DECLERATION =
			"[a-zA-Z][_\\w]* ?\\(.*\\) ?\\{";
	
	/**
	 * creating a new instance for a given instance line
	 * @param line - the params for a new instance
	 * @return	a new instance
	 * @throws BadInputException
	 * @throws CompilerError 
	 */
	public static Instance createInstance(ArrayList
			<ArrayList<Instance>> list,String line) throws CompilerError{
		
		//build a new Instance
		Instance newInstance =buildInstance (line);
		
		//validate input to instance
		ValidateInstanceValue.validateValueOnInstaceCreation
		(list, newInstance, line);
		return newInstance;
	}

	/**
	 * build new instance
	 * @param line - to build the instance from
	 * @return new instance
	 * @throws BadInputException
	 * @throws MemberDeclarationException
	 */
	private static Instance buildInstance(String line) 
			throws BadInputException, MemberDeclarationException{
		
		boolean rememberArray= false;
		
		//hide array signs from constructor
		if (line.matches("[A-za-z]*\\s?\\[\\s?\\].*|[A-za-z]*\\s*\\[\\].*")){
			line = ValidateArrayValue.hideArray(line);
			line = line.trim();
			rememberArray=true;
		}
		String[] splittedLine = line.split(" ");
		
		// get type and delete it from string
		Type currentType = ValidateType.makeType(splittedLine[TYPE_PLACE]);
		line = line.substring(currentType.getTypeName().length()).trim();
		String name = getName(splittedLine[NAME_PLACE]);

		// case string is a function
		if (line.matches(METHOD_DECLERATION)){
			ArrayList <Instance> argList = makeArgList(line);
			return new FuncInstance (currentType, name,rememberArray, argList);
		}

		// case string is a field 
			if (currentType.equals(Type.VOID)){
				throw new VoidVarException(line+" has variable as void");
			}
			return new FieldInstance (currentType, name,rememberArray,
					checkIfInit(line));
	}

	/**
	 * check if variable is initialized
	 * @param s - name of variable
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
	 * @param list - all String inside brackets
	 * @return arraylist of all types
	 * @throws BadInputException 
	 * @throws MemberDeclarationException 
	 */
	private static ArrayList<Instance> makeArgList(String list) 
			throws MemberDeclarationException, BadInputException{
		
		ArrayList<Instance> argList = new ArrayList<Instance>();
		String[] args = ValidateInstanceValue.getMethodArgs(list);
		
		// add all arguments to parameter list
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
	 * will return only the name of instance
	 * @param s - the string representing the signature
	 * @return the instance name
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
