import java.util.*;
/**
 * time checking class for data structors
 * @author Itay
 *
 */
public class SimpleSetPerformanceAnalyzer {
	
	static TimeCounts [] times;
	static String[] dataListOne, dataListTow;
	static final int OPEN_HASH_SET=0, CHAINED_HASH_SET=1, 
			LINKED_LIST=2, TREE_SET=3, HASH_SET=4, NUM_OF_SETS=5;
	
	
	public static void main(String[] args) {
		
		long timeBefore, timeAfter;
		dataListOne = Ex4Utils.file2array("src/data1.txt");
		dataListTow = Ex4Utils.file2array("src/data2.txt");
		times = new TimeCounts[NUM_OF_SETS];

		for (int i = 0; i<5; i++){
			switch(i){
			case OPEN_HASH_SET:
				//data1 related
				times[i]= new TimeCounts("OpenHashCell");
				timeBefore= new Date().getTime();
				times[i].set = new OpenHashSet();
				for (int j=0; j<dataListOne.length; j++){
					times[i].set.add(dataListOne[j]);
				}
				timeAfter= new Date().getTime();
				times[i].dataOneTime = timeAfter-timeBefore;
				fillTimesDataOne(times[i]);
				//data2 related
				timeBefore= new Date().getTime();
				times[i].set = new OpenHashSet(dataListTow);
				for (int j=0; j<dataListTow.length; j++){
					times[i].set.add(dataListTow[j]);
				}
				timeAfter= new Date().getTime();
				times[i].dataTowTime = timeAfter-timeBefore;
				fillTimesDataTow(times[i]);
				compareInitializeTimes(i);

				break;
			case CHAINED_HASH_SET:
				//data1 related
				times[i]= new TimeCounts("ChainedHashSet");
				timeBefore= new Date().getTime();
				times[i].set = new ChainedHashSet(dataListOne);
				for (int j=0; j<dataListOne.length; j++){
					times[i].set.add(dataListOne[j]);
				}
				timeAfter= new Date().getTime();
				times[i].dataOneTime = timeAfter-timeBefore;
				fillTimesDataOne(times[i]);
				//data2 related
				timeBefore= new Date().getTime();
				times[i].set = new ChainedHashSet(dataListTow);
				for (int j=0; j<dataListTow.length; j++){
					times[i].set.add(dataListTow[j]);
				}
				timeAfter= new Date().getTime();
				times[i].dataTowTime = timeAfter-timeBefore;
				fillTimesDataTow(times[i]);
				compareInitializeTimes(i);

				break;
			case LINKED_LIST:
				//data1 related
				times[i]= new TimeCounts("LinkedList");
				timeBefore= new Date().getTime();
				times[i].set = 
						new CollectionFacadeSet(new LinkedList<String>());
				for (int j=1; j< dataListOne.length; j++){
					times[i].set.add(dataListOne[i]);
				}
				timeAfter= new Date().getTime();
				times[i].dataOneTime = timeAfter-timeBefore;
				fillTimesDataOne(times[i]);
				
				//data2 related
				timeBefore= new Date().getTime();
				times[i].set = 
						new CollectionFacadeSet(new LinkedList<String>());
				for (int j=1; j< dataListOne.length; j++){
					times[i].set.add(dataListOne[i]);
				}
				timeAfter= new Date().getTime();
				times[i].dataTowTime = timeAfter-timeBefore;
				fillTimesDataTow(times[i]);
				compareInitializeTimes(i);

				break;
			case TREE_SET:
				//data1 related
				times[i]= new TimeCounts("TreeSet");
				timeBefore= new Date().getTime();
				times[i].set = 
						new CollectionFacadeSet(new TreeSet<String>());
				for (int j=1; j< dataListOne.length; j++){
					times[i].set.add(dataListOne[i]);
				}
				timeAfter= new Date().getTime();
				times[i].dataOneTime = timeAfter-timeBefore;
				fillTimesDataOne(times[i]);
				
				//data2 related
				timeBefore= new Date().getTime();
				times[i].set = new CollectionFacadeSet(new TreeSet<String>());
				for (int j=1; j< dataListOne.length; j++){
					times[i].set.add(dataListOne[i]);
				}
				timeAfter= new Date().getTime();
				times[i].dataTowTime = timeAfter-timeBefore;
				fillTimesDataTow(times[i]);
				compareInitializeTimes(i);

				break;
			case HASH_SET:
				//data1 related
				times[i]= new TimeCounts("HashSet");
				timeBefore= new Date().getTime();
				times[i].set = new CollectionFacadeSet(new HashSet<String>());
				for (int j=1; j< dataListOne.length; j++){
					times[i].set.add(dataListOne[i]);
				}
				timeAfter= new Date().getTime();
				times[i].dataOneTime = timeAfter-timeBefore;
				fillTimesDataOne(times[i]);
				
				//data2 related
				timeBefore= new Date().getTime();
				times[i].set = new CollectionFacadeSet(new HashSet<String>());
				for (int j=1; j< dataListOne.length; j++){
					times[i].set.add(dataListOne[i]);
				}
				timeAfter= new Date().getTime();
				times[i].dataTowTime = timeAfter-timeBefore;
				fillTimesDataTow(times[i]);
				compareInitializeTimes(i);

				break;
			}
		}
		printData();
	}

	/**
	 * fill the data1 related fields of the TimeCount type 
	 * @param curr
	 */
	private static void fillTimesDataOne (TimeCounts curr){
		long timeBefore, timeAfter;
		timeBefore= new Date().getTime();
		curr.set.contains("Hi");
		timeAfter= new Date().getTime();
		curr.dOneContainHi = timeAfter-timeBefore;
		timeBefore= new Date().getTime();
		curr.set.contains("-13170890158");
		timeAfter= new Date().getTime();
		curr.dOneContainNum = timeAfter-timeBefore;
		curr.dOneContainCompareTime = curr.dOneContainNum - curr.dOneContainHi;
	}

	/**
	 * fill the data2 related fields of the TimeCount type 
	 * @param curr
	 */
	private static void fillTimesDataTow (TimeCounts curr){
		long timeBefore, timeAfter;
		timeBefore= new Date().getTime();
		curr.set.contains("hi");
		timeAfter= new Date().getTime();
		curr.dTowContainHi = timeAfter-timeBefore;
		timeBefore= new Date().getTime();
		curr.set.contains("23");
		timeAfter= new Date().getTime();
		curr.dTowContainNum = timeAfter-timeBefore;
		curr.dTowCompareContainTime = curr.dTowContainNum - curr.dTowContainHi;
	}

	/**
	 * manage all printings
	 */
	private static void printData() {
		compareSetsDataOne();
		compareSetsDataTow();
		printCompareInitTimes();
		dataOneContainHi();
		dataOneContainNum();
		compareDataOneContain();
		dataTowContainHi();
		dataTowContainNum();
		compareDataTowContain();

		
	}
	
	/**
	 * compare initializing for data1.txt
	 */
	private static void compareSetsDataOne(){
		int j=0;
		System.out.println("for data1.txt, the time it took each" +
				" structure to initialize:");
		for(int i = 0; i < 5; i++){
			if (times[i].dataOneTime < times[j].dataOneTime){
				j=i;
			}
			System.out.println(times[i].name + " " + times[i].dataOneTime);
		}
		System.out.println("quickest structure: "+ times[j].name);
	}

	/**
	 * data1, print the time took to search for number.
	 */
	private static void dataOneContainHi(){
		int j=0;
		System.out.println("for data1.txt, the time took structures to " +
				"return contains hi");
		for(int i = 0; i < 5; i++){
			if (times[i].dOneContainHi < times[j].dOneContainHi){
				j=i;
			}
			System.out.println(times[i].name + " " + times[i].dOneContainHi);
		}
		System.out.println("best time: " + times[j].name);
	}

	/**
	 * data1, print the time took to search for number.
	 */
	private static void dataOneContainNum(){
		int j=0;
		System.out.println("for data1.txt, the time took structures to " +
				"return contains number");
		for(int i = 0; i < 5; i++){
			if (times[i].dOneContainNum < times[j].dOneContainNum){
				j=i;
			}
			System.out.println(times[i].name + " " + times[i].dOneContainNum);
		}
		System.out.println("best time: " + times[j].name);
	}

	/**
	 * data1, printing the difference of comparing number an hi
	 */
	private static void compareDataOneContain(){
		System.out.println("for data1");
		for(int i = 0; i < 5; i++){
			System.out.println(times[i].name + " finding the number took " + 
		(times[i].dOneContainNum- times[i].dOneContainHi) + " longer than Hi");
		}
	}

	/**
	 * compare initializing for data2.txt
	 */
	private static void compareSetsDataTow(){
		int j=0;
		System.out.println("for data2.txt, the time it took each " +
				"structure to initialize:");
		for(int i = 0; i < 5; i++){
			if (times[i].dataOneTime < times[j].dataOneTime){
				j=i;
			}
			System.out.println(times[i].name + " " + times[i].dataTowTime);
		}
		System.out.println("quickest structure: "+ times[j].name);
	}

	/**
	 * data2, print the time took to search for hi.
	 */
	private static void dataTowContainHi(){
		int j=0;
		System.out.println("for data2.txt, the time took structures to" +
				" return contains hi");
		for(int i = 0; i < 5; i++){
			if (times[i].dTowContainHi < times[j].dTowContainHi){
				j=i;
			}
			System.out.println(times[i].name + " " + times[i].dTowContainHi);
		}
		System.out.println("best time: " + times[j].name);
	}
	
	/**
	 * data2, print the time took to search for number.
	 */
	private static void dataTowContainNum(){
		int j=0;
		System.out.println("for data2.txt, the time took structures to " +
				"return contains number");
		for(int i = 0; i < 5; i++){
			if (times[i].dOneContainNum < times[j].dTowContainNum){
				j=i;
			}
			System.out.println(times[i].name + " " + times[i].dTowContainNum);
		}
		System.out.println("best time: " + times[j].name);
	}
	
	/**
	 * data2, printing the difference of comparing number an hi
	 */
	private static void compareDataTowContain(){
		System.out.println("for data2");
		for(int i = 0; i < 5; i++){
			System.out.println(times[i].name + " finding hi took " + 
		(times[i].dTowContainHi - times[i].dTowContainNum) + 
					" longer than the number");
		}
	}

	/**
	 * printing the difference between initialize times.
	 */
	private static void printCompareInitTimes(){
		for(int i = 0; i < 5; i++){
			System.out.println("for " + times[i].name + "data1 initialize " +
					"was " + times[i].initCompareTime + " longer than data2");
		}
	}

	/**
	 * compare between initialize time of data1 and data2
	 * @param num- the number of structure so compare
	 */
	private static void compareInitializeTimes(int num){
		times[num].initCompareTime = 
				(times[num].dataOneTime - times[num].dataTowTime);
	}

	/**
	 * private class to represent all the fields needed for the analyzer.
	 * class hold name, SimpleSet to work with and many long numbers to check 
	 * for time analysts
	 * @author Itay
	 */
	static class TimeCounts{
		String name;
		SimpleSet set;
		long dataOneTime, dataTowTime, dOneContainHi, dTowContainHi, dOneContainNum, dTowContainNum,
		initCompareTime, dOneContainCompareTime, dTowCompareContainTime;
		
		/**
		 * inialize a new TimeCounts
		 * @param name
		 */
		private TimeCounts(String name){
			this.name = name;
		}
	}

}
