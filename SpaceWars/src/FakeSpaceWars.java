import oop.ex3.*;

/**
 * This class is used in the testings at TestSpaceShip.java
 * @authors Assaf
 *
 */
public class FakeSpaceWars extends SpaceWars {

	//because of java execution order, can't make that non static
	private static final String params[] = {"a","h"};
	private boolean isShooted = false;
	
	/**
	 * construct a new fake SpaceShip object
	 */
	public FakeSpaceWars() {
		//calling to super's ctor is just because java obligates us to call it
		super(params);
	}
	
	/**
     * This method demonstrates a shot
     * @param position the position of the firing spaceship.makes no changes
     */
	@Override
	public void addShot(SpaceShipPhysics position){
		this.isShooted = true;
	}
	
	/**
	 * This method will return if a shot really was shot
	 * @return true if was shot, false if not
	 */
	public boolean didShoot(){
		boolean temp = isShooted;
		//reset isShooted
		isShooted = false;
		return temp;
	}
}