/**
 * This class contains a single static method that is used to create spaceships
 * @author Assaf
 *
 */
public class SpaceShipFactory {

	public static final char HUMAN ='h';
	public static final char HUMAN_UPPER ='H';
	
	public static final char RUNNER ='r';
	public static final char RUNNER_UPPER ='R';
	
	public static final char DRUNKARD ='d';
	public static final char DRUNKARD_UPPER ='D';
	
	public static final char SPECIAL ='s';
	public static final char SPECIAL_UPPER ='S';
	
	public static final char BASHER ='b';
	public static final char BASHER_UPPER ='B';
	
	public static final char AGGRESIVE ='a';
	public static final char AGGRESIVE_UPPER ='A';

	/**
	 * Creates the spaceships in the game according to the passed array of 
	 * spaceships names (h,r,f,s,...).
	 * See how it is used in SpaceWars.java main method.
	 * @param spaceships the command line arguments of SpaceWars 
	 * (e.g. spaceships={"h","r","f"}).
	 * @return the array of spaceships.
	 */
	public static SpaceShip[] createSpaceShips(String[] spaceships) {
		//create a new array of spaceships according to user input  
		SpaceShip ships[] =  new SpaceShip[spaceships.length];
		for(int i=0;i<spaceships.length;i++)
			switch (spaceships[i].charAt(0)) {
			case HUMAN: case HUMAN_UPPER:
				ships[i] = new HumanShip();
				break;
				
			case RUNNER: case RUNNER_UPPER:
				ships[i] = new RunnerShip();
				break;
			
			case DRUNKARD: case DRUNKARD_UPPER:
				ships[i] = new DrunkardShip();
				break;
			
			case SPECIAL: case SPECIAL_UPPER:
				ships[i] = new SpecialShip();
				break;
			
			case BASHER: case BASHER_UPPER:
				ships[i] = new BasherShip();
				break;
			
			case AGGRESIVE: case AGGRESIVE_UPPER:
				ships[i] = new AggressiveShip();
				break;

			default:
				break;
			}
		return ships;
	}
}