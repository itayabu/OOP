package oop.ex7.main.instance;

import oop.ex7.main.Type;

public abstract class Instance {
	
	
	private String name;
	private Type type;
	private boolean initialized; 
	private boolean isArray = false;
	
	/**
	 * basic constructor.
	 * @param type the type or return type of instance
	 * @param name the name of instance
	 */
	public Instance(Type type, String name, boolean isArray){
		this.name = name;
		this.type =  type;
		initialized = false;
		this.isArray = isArray;
	}

	/**
	 * create a new deep copy of this instance
	 */
	public abstract Instance clone();
	
	/**
	 * name getter
	 * @return name of instance
	 */
	public String getName() {
		return name;
	}

	/**
	 * type name getter
	 * @return String of type name
	 */
	public Type getType() {
		return type;
	}

	/**
	 * initialized getter
	 * @return true if instance is initialized, flse else
	 */
	public boolean isInitialized() {
		return initialized;
	}

	/**
	 * set initialized true or false
	 * @param initialized
	 */
	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}
	
	/**
	 * check Instance is the same as other instance
	 * @param otherInstance
	 * @return true if instances are the same, flase else
	 */
	public abstract boolean compareInstances(Instance otherInstance);
	
	/**
	 * check if the type of instances are the same
	 * @param otherInstance
	 * @return
	 */
	protected boolean compareInstancesType(Instance otherInstance){
		return (this.type.equals(otherInstance.getType()));
	}	
	
	public String getTypeName(){
		return type.getTypeName();
	}

	public boolean isArray() {
		return isArray;
	}

	public void setArray(boolean isArray) {
		this.isArray = isArray;
	}

}
