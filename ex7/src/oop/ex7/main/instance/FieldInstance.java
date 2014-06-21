package oop.ex7.main.instance;

import oop.ex7.main.Type;
/**
 * this class represent a variable instance
 * @author Assaf M. Itay A.
 *
 */
public class FieldInstance extends Instance {

	/**
	 * construct a new instance
	 * @param type Type of instance
	 * @param name name of instance
	 * @param isArray flag to check if an array
	 * @param init flag to check if initialized
	 */
	public FieldInstance(Type type, String name,boolean isArray, boolean init) {
		super(type, name, isArray);
		setInitialized(init);
	}

//	@Override
//	public boolean compareInstances(Instance otherInstance) {
//		return compareInstancesType(otherInstance);
//	}

	@Override
	public Instance clone() {
		return new FieldInstance(getType(), getName(),isArray(), isInitialized());
	}
}
