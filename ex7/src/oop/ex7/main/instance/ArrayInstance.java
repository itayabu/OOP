package oop.ex7.main.instance;

import oop.ex7.main.Type;

public class ArrayInstance extends FieldInstance {

	public ArrayInstance(Instance decorateInstance) {
		super(decorateInstance.getType(), decorateInstance.getName(), decorateInstance.isInitialized());
	}

}
