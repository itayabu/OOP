package oop.ex7.main.instance;

import oop.ex7.main.Type;

public class FieldInstance extends Instance {

	public FieldInstance(Type type, String name, boolean init) {
		super(type, name);
		setInitialized(init);
	}

	@Override
	public boolean compareInstances(Instance otherInstance) {
		return compareInstancesType(otherInstance);
	}

	@Override
	public Instance clone() {
		return new FieldInstance(getType(), getName(), isInitialized());
	}
}
