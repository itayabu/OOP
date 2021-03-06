package oop.ex7.main.instance;

import java.util.ArrayList;

import oop.ex7.main.type.Type;


/**
 * this class represent a function instance
 * @author Assaf M. Itay A.
 */
public class FuncInstance extends Instance {

	ArrayList<Instance> funcArguments;

	/**
	 * basic constructor
	 * @param type Type of instance
	 * @param name name of instance
	 * @param isArray flag to check if an array
	 * @param args the arguments function receive
	 */
	public FuncInstance(Type type, String name,boolean isArray, 
			ArrayList<Instance> args) {

		super(type, name, isArray);
		setInitialized(true);
		funcArguments = args;
	}

	@Override
	public Instance clone() {
		return new FuncInstance(this.getType(), this.getName(),
				this.isArray(), funcArguments);
	}
public Instance getArgument(int i){
	if (funcArguments.isEmpty()){
		return null;
	}
	return funcArguments.get(i);
}

}
