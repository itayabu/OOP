package oop.ex7.main.validations;

import java.util.ArrayList;

import oop.ex7.main.Type;
import oop.ex7.main.exceptions.AssignmentTypesArntConsist;
import oop.ex7.main.exceptions.BadInputException;
import oop.ex7.main.exceptions.CompilerError;
import oop.ex7.main.instance.Instance;
import oop.ex7.main.instance.InstanceFactory;

/**
 * helper class validate inputs for Instances
 * @author Itay
 *
 */
public class ValidateInstanceValue {

	/**
	 * method assert assignment is legal
	 * @param list main list
	 * @param inst original instance
	 * @param line line to take variable from
	 * @throws CompilerError
	 * @throws BadInputException
	 */
	public static void validateValueOnInstaceCreation 
	(ArrayList<ArrayList<Instance>> list, Instance inst, String line)
			throws CompilerError{
		
		//case no assignment
		if (!line.contains("=")){
			return;
		}
		
		//case is array
		if(inst.isArray()){
			ValidateArrayValue.validateArrayValueOnCreation(list, inst, line);
			return;
		}
		
		//case is variable
		line = manageVar(line);
		if (!ValidateType.typesConsist(list, inst, line)){
			throw new AssignmentTypesArntConsist(
					line+"has non-consist type problem");
		}
	}

	

	/**
	 * this method get the arguments of var assignments
	 * @param line
	 * @return
	 */
	private static String manageVar(String line){
		int place = line.indexOf("=");
		line = line.substring(place+1, line.length()-1);
		return line.trim();
	}

	/**
	 * this method split mwthod's string and return the arguments of it.
	 * @param line- method string
	 * @return method string arguments in an array.
	 */
	public static String[] getMethodArgs(String line){
		int start = line.indexOf("(");
		int end = line.lastIndexOf(")");
		line = line.substring(start+1, end);
		return line.split(",");
	}

//	/**
//	 * this method receive string contain method call and return its type.
//	 * @param list
//	 * @param string
//	 * @return the return type of method
//	 * @throws CompilerError
//	 * @throws BadInputException 
//	 */
//	public static Type getMethodTypeFtomFuncString(ArrayList<ArrayList
//			<Instance>> list, String string) 
//					throws CompilerError, BadInputException{
//		
//		int open = (string.indexOf("("));
//		String name = string.substring(0, open);
//		Instance func = InstanceArrayValidator.findInstance(list, name);
//		validateMethodArgs(list, func, string);
//		return func.getType();
//	}

	/**
	 * this method update new block and check if block is legal
	 * @param list main list
	 * @param blockList new list with params in block
	 * @param text opening block line
	 * @return blockList with filled instances
	 * @throws CompilerError
	 */
	public static ArrayList<Instance> updateList(ArrayList<ArrayList<Instance>>
		list, ArrayList<Instance> blockList, String text) throws CompilerError{
		
		String[] args = getMethodArgs(text);
		if (text.startsWith("if")|| text.startsWith("while")){
			if (!ValidateBoolValue.validateBool(list, args[0], Type.BOOLEAN)){
				throw new BadInputException(text+"has bad input");
			}
		}
		else{
			for (String s:args){
				if (s.equals("")){
					return blockList;
				}
				s=s.trim();
				Instance inst = InstanceFactory.createInstance(list, s);
				inst.setInitialized(true);
				if (InstanceArrayValidator.instanceNameExistInBlock
						(inst, blockList)){
					throw new BadInputException(s +"exist twice as instance");
				}
				blockList.add(inst);
			}
		}
		return blockList;
	}

	/**
	 * check if list contains an instance with the same name
	 * @param list the list to check if instance is contained
	 * @param str name of assignment variable to be checked
	 * @return true if string can be assigned
	 */
	public static boolean checkIfInList (ArrayList<ArrayList<Instance>> list
			,String str,Type t){
		for(ArrayList<Instance> instArray:list)
			for(Instance inst:instArray)
				if(inst.getName().equals(str))
					if(inst.getType().equals(t)){
						return inst.isInitialized();
					}
		return false;
	}

}
