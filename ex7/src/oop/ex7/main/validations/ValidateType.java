package oop.ex7.main.validations;

import java.util.ArrayList;

import oop.ex7.main.Type;
import oop.ex7.main.exceptions.BadInputException;
import oop.ex7.main.exceptions.TypeConversionException;
import oop.ex7.main.instance.Instance;
import oop.ex7.main.validationsexceptions.BadTypeNameException;
public class ValidateType {

	/**
	 * check if type instance names are legal
	 * @param s name to check
	 * @return true if are legal Type name, false else
	 */
	public static boolean isValidInstanceType(String s){
		for (Type p: Type.values()){
			if (p.getTypeName().equals(s)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * create a new type based on a given string
	 * @param s- name of a new type
	 * @return new Type
	 * @throws BadTypeNameException
	 */
	public static Type makeType(String s) throws BadTypeNameException{
		for (Type p: Type.values()){
			if (p.getTypeName().equals(s)){
				return p;
			}
		}
		throw new BadTypeNameException(s+"type name doesnt match any Type");
	}
	
	/**
	 * 
	 * @param list of all instances
	 * @param currInst the instance to check against
	 * @param s line with instances names
	 * @return true if types of instances are consist
	 * @throws TypeConversionException
	 * @throws BadInputException
	 */
	public static boolean typesConsist (ArrayList<ArrayList<Instance>> list
			,Instance currInst , String s) 
					throws TypeConversionException, BadInputException{
		
		//case normal var
		if (!currInst.isArray()){
			s=getArray(s);
			return Type.checkType(list, currInst.getType(), s);
		}
		//case array
		if (s.contains("{")){
			if (s.contains("{}")){
				return true;
			}
			currInst.setArray(false);
		}
		String[] splitLine = setSubs(s);
		for(String str:splitLine){
			if (!Type.checkType(list, currInst.getType(), str)){
				return false;
			}
		}
		currInst.setArray(true);
		return true;
	}
	
	/**
	 * sets a list of arguments out of an array inputs
	 * @param s
	 * @return
	 */
	private static String[] setSubs(String s){
		if (s.contains("{")){ 
			int start = s.indexOf("{");
			int end = s.indexOf("}");
			s= s.substring(start+1,end);
		}
		return s.split(",");
	}
	
	/**
	 * check array index is legal and return the name of array
	 * @param s string of name
	 * @return the same string without the array content
	 * @throws BadInputException
	 */
	private static String getArray(String s) throws BadInputException{
		int start = s.indexOf("[");
		int end = s.indexOf("]");
		if (! (start < 0)){
			String num = s.substring(start+1, end);
			if (!num.matches("\\s*\\d*\\s*")){
				throw new BadInputException (s+"has bad input");
			}
			s= s.substring(0,start);
		}
		return s;
	}
}
