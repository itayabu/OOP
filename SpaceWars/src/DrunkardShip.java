import java.util.Random;
import oop.ex3.SpaceShipPhysics;

/**
 * This spaceship is flying to a random direction and shooting if another ship 
 * is near it
 * @author Assaf
 *
 */
public class DrunkardShip extends AIShip {

	private final double SHOOT_ANGLE = 1;
	private final double SHOOT_DISTANCE = 0.1;

	/**
	 * construct new FloaterShip object
	 */
	public DrunkardShip(){
		super();
	}
	/**
	 * return a random direction number between -1 to 1
	 * @param min
	 * @param max
	 * @return a random integer between -1 and 1
	 */
	private static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
    /**
	 * Does the actions of this ship for this round. 
	 * This is called once per round by the SpaceWars game driver.
	 * @param game the game object to which this ship belongs.
	 */
	public void doAction(SpaceWars game) {
		//keeps floating in the same direction
		getPhysics().move(true, randInt(-1,1) );
		SpaceShipPhysics nearest = game.getClosestShipTo(this).getPhysics();
		if(isEndangered(nearest)){
			shoot(game);
		}
		timeTick();
	}
	private boolean isEndangered(SpaceShipPhysics other){
		return (getPhysics().distanceFrom(other) < SHOOT_DISTANCE &&
				(other.angleTo(this.getPhysics())) < SHOOT_ANGLE);	
	}
}