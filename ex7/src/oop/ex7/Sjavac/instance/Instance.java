package oop.ex7.Sjavac.instance;

import oop.ex7.Sjavac.Type;

public abstract class Instance {
	
	
	private String name;
	private Type type;
	private boolean initialized; 
	
	/**
	 * basic constructor.
	 * @param type the type or return type of instance
	 * @param name the name of instance
	 */
	public Instance(Type type, String name){
		this.name = name;
		this.type =  type;
		initialized = false;
	}
	
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
	public String getType() {
		return type.getType();
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
	
	

}
