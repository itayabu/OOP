package oop.ex7.main.instance;

/**
 * this class represent a member or method
 * @author Assaf M. Itay A.
 *
 */
public class Member {

	private boolean isInitialized;
	private Types type;
	private boolean readOnly;//if it has been declared  before
	private String name;

	/**
	 * construct a new member
	 * @param name the name (signature of this member)
	 * @param type the type of this member
	 * @param readOnly set this true to indicate that this object is read-only
	 */
	public Member(String name,Types type,boolean readOnly) {
		this.name=name;
		this.readOnly=readOnly;
		this.type=type;
		isInitialized=false;
	}

	/**
	 * @return the name (the signature) of the Member
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return true if this Member is readonly
	 */
	public boolean isReadOnly() {
		return readOnly;
	}

	/**
	 * @return the type of this member
	 * (the return type in methods)
	 */
	public Types getType() {
		return type;
	}

	/**
	 * return the declaration of this member
	 */
	public String toString() {
		return type+" "+name;
	}

	/**
	 * initialize this member
	 */
	public void initialize() {
		isInitialized=true;
	}

	/**
	 * @return true if this member has initialized yet
	 */
	public boolean isInitialized() {
		return isInitialized;
	}
}