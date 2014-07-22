package oop.ex7.main.type;

import java.util.ArrayList;
import oop.ex7.main.instance.Instance;
import oop.ex7.main.type.exceptions.TypeConversionException;

/**
 * this class represents the primitive types available to use in this compiler
 * @author Assaf M. Itay A.
 *
 */
public enum Type {
	INT("int"), 
	DOUBLE("double"), 
	STRING("String"), 
	BOOLEAN("boolean"), 
	CHAR("char"), 
	VOID("void");

	private String typeName;
	
	/**
	 * declare the types kind
	 * @param kind
	 */
	Type (String kind){
		typeName = kind;
	}
	
	/**
	 * return the types name
	 * @return type name
	 */
	public String getTypeName(){
		return typeName;
	}

	/**
	 * check if values of assignment are consist with instance
	 * @param list - list of all instances
	 * @param currInstance - instance to check
	 * @param s - managed text
	 * @return true if values are OK
	 * @throws TypeConversionException 
	 */
	public static boolean checkType(ArrayList<ArrayList<Instance>>
	list,Type currType , String s) throws TypeConversionException {

		switch (currType){
		case INT:
			return ValidateIntValue.validateInt(list, s, currType);
		case STRING:
			return ValidateStringValue.validateString(list, s, currType);
		case DOUBLE:
			return ValidateDoubleValue.validateDouble(list, s, currType);
		case CHAR:
			return ValidateCharValue.validateChar(list, s, currType);
		case BOOLEAN: 
			return ValidateBoolValue.validateBool(list, s, currType);
		case VOID:
			return (s.equals(""));
		}
		return false;
	}
}