package oop.ex7.main;

import java.util.ArrayList;

import oop.ex7.main.exceptions.BadInputException;
import oop.ex7.main.exceptions.IllegaIntException;
import oop.ex7.main.exceptions.TypeConversionException;
import oop.ex7.main.instance.Instance;
import oop.ex7.main.validations.InstanceArrayValidator;
import oop.ex7.main.validations.ValidateBoolValue;
import oop.ex7.main.validations.ValidateCharValue;
import oop.ex7.main.validations.ValidateDoubleValue;
import oop.ex7.main.validations.ValidateIntValue;
import oop.ex7.main.validations.ValidateStringValue;

public enum Type {
	INT("int"), DOUBLE("double"), STRING("String"), BOOLEAN("boolean"), CHAR("char"), VOID("void");

	private String typeName;

	Type (String kind){
		typeName = kind;
	}

	public String getTypeName(){
		return typeName;
	}

	/**
	 * check if values of assignment are consist with instance
	 * @param list list of all instances
	 * @param currInstance 
	 * @param s managed text
	 * @return true if values are OK
	 * @throws TypeConversionException 
	 */
	public static boolean checkType(ArrayList<ArrayList<Instance>>
	list,Type currType , String s) throws TypeConversionException{
	
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