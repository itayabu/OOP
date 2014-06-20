package oop.ex7.main.validations;

import java.util.ArrayList;

import oop.ex7.main.Type;
import oop.ex7.main.instance.Instance;
import oop.ex7.main.validationsexceptions.BadTypeNameException;
public class ValidateType {

	public static boolean isValidInstanceType(String s){
		for (Type p: Type.values()){
			if (p.getType().equals(s)){
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
			if (p.getType().equals(s)){
				return p;
			}
		}
		throw new BadTypeNameException("type name doesnt match any Type");
	}

	/**
	 * check if a name has instace with the same type.
	 * @param instance original instance
	 * @param list main list
	 * @param s word with maybe another instance
	 * @return
	 */
	public static boolean existInstanceWithValidType
	(Instance instance, ArrayList<ArrayList<Instance>> list, String s){
		Instance checkInstance = InstanceArrayValidator.findInstance(list, s);
		return instance.compareInstances(checkInstance);
	}
}
