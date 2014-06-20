package oop.ex7.main.instance;

import oop.ex7.main.Type;

public class FieldInstance extends Instance {

	public FieldInstance(Type type, String name,boolean isArray, boolean init) {
		super(type, name, isArray);
		setInitialized(init);
	}

	@Override
	public boolean compareInstances(Instance otherInstance) {
		return compareInstancesType(otherInstance);
	}

	@Override
	public Instance clone() {
		return new FieldInstance(getType(), getName(),isArray(), isInitialized());
	}
}
