package oop.ex7.Sjavac.instance;

public abstract class Instance {
	
	/**
	 * places in array of name and place
	 */
	protected final static int NAME_PLACE=1, TYPE_PLACE=0, ARGUMENT_PLACE=2;
	
	
	private String type, name;
	private boolean initialized; 
	
	/**
	 * basic constructor.
	 * @param type the type or return type of instance
	 * @param name the name of instance
	 */
	public Instance(String type, String name){
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
	
	

}
