package oop.ex7.main.instance;

import oop.ex7.main.Type;

/**
 * this class represents an instance 
 * @author Assaf M. Itay A.
 *
 */
public abstract class Instance {
	
	//instance constants
	private String name;//name
	private Type type;//type: int/double/char/string/void.
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
	 * get name of type in this Instance
	 * @return name of type
	 */
	public String getTypeName(){
		return type.getTypeName();
	}

	/**
	 * check if this instance is array
	 */
	public boolean isArray() {
		return isArray;
	}

	/**
	 * set array parameter true or false
	 * @param isArray
	 */
	public void setArray(boolean isArray) {
		this.isArray = isArray;
	}

}
