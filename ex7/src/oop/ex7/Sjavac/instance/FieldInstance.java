package oop.ex7.Sjavac.instance;

import oop.ex7.Sjavac.Type;

public class FieldInstance extends Instance {

	public FieldInstance(Type type, String name, boolean init) {
		super(type, name);
		setInitialized(init);
	}

	@Override
	public boolean compareInstances(Instance otherInstance) {
		return compareInstancesType(otherInstance);
	}
}
