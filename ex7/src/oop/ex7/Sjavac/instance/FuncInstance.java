package oop.ex7.Sjavac.instance;

public class FuncInstance extends Instance {

	String[] funcArguments;
	public FuncInstance(String[] args) {
		super(args[TYPE_PLACE], args[NAME_PLACE]);
		setInitialized(true);
		copyArguments(args);
	}
	
	private void copyArguments(String[] args){
		for (int i =0; i< (args.length-ARGUMENT_PLACE); i++){
			funcArguments[i] = args[i+ARGUMENT_PLACE];
		}
	}
	

}
