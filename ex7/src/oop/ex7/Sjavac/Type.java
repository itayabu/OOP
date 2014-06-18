package oop.ex7.Sjavac;

import java.util.ArrayList;

import oop.ex7.Sjavac.instance.Instance;
import oop.ex7.Sjavac.validations.InstanceArrayValidator;
import oop.ex7.Sjavac.validations.ValidateIntValue;

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
	 */
	public boolean typesConsist(ArrayList<ArrayList<Instance>> list,Type currType , String s){
		Instance checkInstance = InstanceArrayValidator.findInstance(list, s);
		switch (currType){
		case INT:
			ValidateIntValue.validateInt(list, s);
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
				if(inst.getType().equals(str))
					return(inst.getType().equals(t));
		return false;
	}

	

}