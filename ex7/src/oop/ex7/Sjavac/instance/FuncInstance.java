package oop.ex7.Sjavac.instance;

import java.util.ArrayList;

import oop.ex7.Sjavac.Type;

public class FuncInstance extends Instance {

	ArrayList<Type> funcArguments;
	public FuncInstance(Type type, String name, ArrayList<Type> args) {
		super(type, name);
		setInitialized(true);
	}
	
	public ArrayList<Type> getArgs(){
		return funcArguments;
	}
	

}
