package oop.ex7.Sjavac.validations;

import oop.ex7.Sjavac.Type;
import oop.ex7.Sjavac.validations.exception.BadTypeNameException;
public class ValidateType {

	public static boolean isValidInstanceType(String s){
		for (Type p: Type.values()){
			if (p.getType().equals(s)){
				return true;
			}
		}
		return false;
	}

	public static Type makeType(String s) throws BadTypeNameException{
		for (Type p: Type.values()){
			if (p.getType().equals(s)){
				return p;
			}
		}
		throw new BadTypeNameException("type name doesnt match any Type");
	}
}
