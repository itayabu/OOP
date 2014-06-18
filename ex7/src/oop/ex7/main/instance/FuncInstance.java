package oop.ex7.main.instance;

import java.util.ArrayList;

import oop.ex7.main.Type;

public class FuncInstance extends Instance {

	ArrayList<Type> funcArguments;
	public FuncInstance(Type type, String name, ArrayList<Type> args) {
		super(type, name);
		setInitialized(true);
	}
	
	public ArrayList<Type> getArgs(){
		return funcArguments;
	}

	@Override
	public boolean compareInstances(Instance otherInstance) {
		// TODO- all comparisons
		return false;
	}

	@Override
	public Instance clone() {
		return new FuncInstance(this.getType(), this.getName(), funcArguments);
	}
	

}
