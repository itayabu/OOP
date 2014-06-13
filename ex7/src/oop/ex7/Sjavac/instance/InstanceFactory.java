package oop.ex7.Sjavac.instance;

import java.util.ArrayList;
import java.util.regex.Matcher;

import oop.ex7.Sjavac.RegexConstants;
import oop.ex7.Sjavac.Type;
import oop.ex7.Sjavac.exception.BadInputException;
import oop.ex7.Sjavac.validations.ValidateType;
import oop.ex7.Sjavac.validations.exception.BadTypeNameException;

public class InstanceFactory {

	int TYPE_PLACE=0, NAME_PLACE=1;
	
	/**
	 * basic constructor
	 */
	public InstanceFactory(){
		
	}
	
	/**
	 * creatin a new instance for a given instance line
	 * @param line - the params for a new instance
	 * @return	a new instance
	 * @throws BadInputException
	 */
	public Instance createInstance(String line) throws BadInputException{
		String[] splittedLine = line.split(" ");
		String name = splittedLine[NAME_PLACE];

		// get type and delete it from string
		Type currentType = ValidateType.makeType(splittedLine[TYPE_PLACE]);
		line = line.substring(currentType.getType().length());
		
		// check if String is a function
		Matcher m =RegexConstants.RegexPatterns.VARIABLE_DECLARATION.matcher(line);
		if (m.matches()){
			ArrayList <Type> argList = makeArgList(line);
			return new FuncInstance (currentType, name, argList);
		}
		
		// if String is not a function, then it is a field
		else{
			return new FieldInstance (currentType, name, checkLegalInit(line));
		}	
	}

	private boolean checkLegalInit(String s){
		//TODO check it
		return true;
	}
	
	/**
	 * receive all the String inside brackets and return arrayList of all types
	 * @param list all String inside brackets
	 * @return arraylist of all types
	 */
	private ArrayList<Type> makeArgList(String list){
		
		ArrayList<Type> argList = new ArrayList<Type>();
		//TODO: split the String to types
		
		return argList;
	}
}
