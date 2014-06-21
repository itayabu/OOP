package oop.ex7.main.validations;

import java.util.ArrayList;

import oop.ex7.main.Type;
import oop.ex7.main.exceptions.BadInputException;
import oop.ex7.main.exceptions.TypeConversionException;
import oop.ex7.main.instance.Instance;
import oop.ex7.main.validationsexceptions.BadTypeNameException;
public class ValidateType {

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
		throw new BadTypeNameException("type name doesnt match any Type");
	}
	
	public static boolean typesConsist (ArrayList<ArrayList<Instance>> list
			,Instance currInst , String s) 
					throws TypeConversionException, BadInputException{
		if (!currInst.isArray()){
			s=getArray(s);
			return Type.checkType(list, currInst.getType(), s);
		}
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
	
	private static String[] setSubs(String s){
		if (s.contains("{")){ 
			int start = s.indexOf("{");
			int end = s.indexOf("}");
			s= s.substring(start+1,end);
		}
		return s.split(",");
	}
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
