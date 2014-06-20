package oop.ex7.main.instance;

import java.util.ArrayList;

import oop.ex7.main.Type;

public class FuncInstance extends Instance {

	ArrayList<Instance> funcArguments;
	public FuncInstance(Type type, String name,boolean isArray, ArrayList<Instance> args) {
		super(type, name, isArray);
		setInitialized(true);
	}
	
	public ArrayList<Instance> getArgs(){
		return funcArguments;
	}

	@Override
	public boolean compareInstances(Instance otherInstance) {
		// TODO- all comparisons
		return false;
	}

	@Override
	public Instance clone() {
		return new FuncInstance(this.getType(), this.getName(),this.isArray(), funcArguments);
	}
	

}
