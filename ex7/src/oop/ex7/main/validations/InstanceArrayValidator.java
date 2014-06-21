package oop.ex7.main.validations;

import java.util.ArrayList;

import oop.ex7.main.instance.Instance;

public class InstanceArrayValidator {

	/**
	 * check if instance already exist in block
	 * @param checkInstance - the instance to check
	 * @param instanceInBlock - all instances already exist in current block
	 * @return
	 */
	public static boolean instanceNameExistInBlock(Instance checkInstance, 
			ArrayList<Instance> instanceInBlock){

		for (Instance instance:instanceInBlock){
			if (checkInstance.getName().equals(instance.getName())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * check if instance with a given name exist in code.
	 * check in a given method list and in the main list
	 * @param list
	 * @param s
	 * @return instance with the given name if exist, null else.
	 */
	public static Instance findInstance(ArrayList<ArrayList<Instance>> list, 
			String s){
		
		for (ArrayList<Instance> subList: list){
			for (Instance instance: subList){
				if (instance.getName().equalsIgnoreCase(s)){
					return instance;
				}
			}
		}
		return null;
	}
}
