package oop.ex7.main;

import java.util.ArrayList;

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

	public String getType(){
		return typeName;
	}
	
	/**
	 * receive String and return only its values
	 * @param s
	 * @return
	 */
	

	/**
	 * check if values of HASAMA are consist with instance
	 * @param list list of all instances
	 * @param currInstance 
	 * @param s managed text
	 * @return true if values are OK
	 * @throws TypeConversionException 
	 */
	public boolean typesConsist(ArrayList<ArrayList<Instance>> list,Type currType , String s) throws TypeConversionException{
//		Instance checkInstance = InstanceArrayValidator.findInstance(list, s);
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
		}
		return false;
	}
	
	/**
	 * check if list contains an instance with the same name
	 * @param list the list to check if contains
	 * @param str
	 * @return
	 */
	public static boolean checkIfInList (ArrayList<ArrayList<Instance>> list ,String str,Type t){
		for(ArrayList<Instance> instArray:list)
			for(Instance inst:instArray)
				if(inst.getName().equals(str))
					return(inst.getType().equals(t));
		return false;
	}

	

}