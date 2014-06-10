package oop.ex7.Sjavac.instance;

public class VarInstance extends Instance {

	public VarInstance(String[] args) {
		super(args[TYPE_PLACE], args[NAME_PLACE]);
		if (args[ARGUMENT_PLACE] != null){
			if (args[ARGUMENT_PLACE].equals('=')){
				setInitialized(true);
			}
			else{
				//TODO throw exception
			}
		}
		//no real need in this.
		else{
			setInitialized(false);
		}
	}

}
