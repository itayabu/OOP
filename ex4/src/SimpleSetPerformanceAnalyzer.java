import java.util.*;
public class SimpleSetPerformanceAnalyzer {
	
	static TimeCounts [] times;
	static String[] dataListOne, dataListTow;
	
	public static void main(String[] args) {
		
		long timeBefore, timeAfter;
		dataListOne = Ex4Utils.file2array("src/data1.txt");
		dataListTow = Ex4Utils.file2array("src/data2.txt");
		times = new TimeCounts[5];

		for (int i = 0; i<5; i++){
			switch(i){
			case 0:
				//data1 related
				times[i]= new TimeCounts("OpenHashCell");
				timeBefore= new Date().getTime();
				times[i].set = new OpenHashSet(dataListOne);
				timeAfter= new Date().getTime();
				times[i].data1Time = timeAfter-timeBefore;
				fillTimesDataOne(times[i]);
				//data2 related
				timeBefore= new Date().getTime();
				times[i].set = new OpenHashSet(dataListTow);
				timeAfter= new Date().getTime();
				times[i].data2Time = timeAfter-timeBefore;
				fillTimesDataTow(times[i]);
				compareInitializeTimes(i);

				break;
			case 1:
				//data1 related
				times[i]= new TimeCounts("ChainedHashSet");
				timeBefore= new Date().getTime();
				times[i].set = new ChainedHashSet(dataListOne);
				timeAfter= new Date().getTime();
				times[i].data1Time = timeAfter-timeBefore;
				fillTimesDataOne(times[i]);
				
				//data2 related
				timeBefore= new Date().getTime();
				times[i].set = new ChainedHashSet(dataListTow);
				timeAfter= new Date().getTime();
				times[i].data2Time = timeAfter-timeBefore;
				fillTimesDataTow(times[i]);
				compareInitializeTimes(i);

				break;
			case 2:
				//data1 related
				times[i]= new TimeCounts("LinkedList");
				timeBefore= new Date().getTime();
				times[i].set = new CollectionFacadeSet(new LinkedList<String>());
				for (int j=1; j< dataListOne.length; j++){
					times[i].set.add(dataListOne[i]);
				}
				timeAfter= new Date().getTime();
				times[i].data1Time = timeAfter-timeBefore;
				fillTimesDataOne(times[i]);
				
				//data2 related
				timeBefore= new Date().getTime();
				times[i].set = new CollectionFacadeSet(new LinkedList<String>());
				for (int j=1; j< dataListOne.length; j++){
					times[i].set.add(dataListOne[i]);
				}
				timeAfter= new Date().getTime();
				times[i].data2Time = timeAfter-timeBefore;
				fillTimesDataTow(times[i]);
				compareInitializeTimes(i);

				break;
			case 3:
				//data1 related
				times[i]= new TimeCounts("TreeSet");
				timeBefore= new Date().getTime();
				times[i].set = new CollectionFacadeSet(new TreeSet<String>());
				for (int j=1; j< dataListOne.length; j++){
					times[i].set.add(dataListOne[i]);
				}
				timeAfter= new Date().getTime();
				times[i].data1Time = timeAfter-timeBefore;
				fillTimesDataOne(times[i]);
				
				//data2 related
				timeBefore= new Date().getTime();
				times[i].set = new CollectionFacadeSet(new TreeSet<String>());
				for (int j=1; j< dataListOne.length; j++){
					times[i].set.add(dataListOne[i]);
				}
				timeAfter= new Date().getTime();
				times[i].data2Time = timeAfter-timeBefore;
				fillTimesDataTow(times[i]);
				compareInitializeTimes(i);

				break;
			case 4:
				//data1 related
				times[i]= new TimeCounts("HashSet");
				timeBefore= new Date().getTime();
				times[i].set = new CollectionFacadeSet(new HashSet<String>());
				for (int j=1; j< dataListOne.length; j++){
					times[i].set.add(dataListOne[i]);
				}
				timeAfter= new Date().getTime();
				times[i].data1Time = timeAfter-timeBefore;
				fillTimesDataOne(times[i]);
				
				//data2 related
				timeBefore= new Date().getTime();
				times[i].set = new CollectionFacadeSet(new HashSet<String>());
				for (int j=1; j< dataListOne.length; j++){
					times[i].set.add(dataListOne[i]);
				}
				timeAfter= new Date().getTime();
				times[i].data2Time = timeAfter-timeBefore;
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
		curr.d1ContainHi = timeAfter-timeBefore;
		timeBefore= new Date().getTime();
		curr.set.contains("-13170890158");
		timeAfter= new Date().getTime();
		curr.d1ContainNum = timeAfter-timeBefore;
		curr.d1ContainCompareTime = curr.d1ContainNum - curr.d1ContainHi;
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
		curr.d2ContainHi = timeAfter-timeBefore;
		timeBefore= new Date().getTime();
		curr.set.contains("23");
		timeAfter= new Date().getTime();
		curr.d2ContainNum = timeAfter-timeBefore;
		curr.d2CompareContainTime = curr.d2ContainNum - curr.d2ContainHi;
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
		System.out.println("for data1.txt, the time it took each structure to initialize:");
		for(int i = 0; i < 5; i++){
			if (times[i].data1Time < times[j].data1Time){
				j=i;
			}
			System.out.println(times[i].name + " " + times[i].data1Time);
		}
		System.out.println("quickest structure: "+ times[j].name);
	}

	/**
	 * data1, print the time took to search for number.
	 */
	private static void dataOneContainHi(){
		int j=0;
		System.out.println("for data1.txt, the time took structures to return contains hi");
		for(int i = 0; i < 5; i++){
			if (times[i].d1ContainHi < times[j].d1ContainHi){
				j=i;
			}
			System.out.println(times[i].name + " " + times[i].d1ContainHi);
		}
		System.out.println("best time: " + times[j].name);
	}

	/**
	 * data1, print the time took to search for number.
	 */
	private static void dataOneContainNum(){
		int j=0;
		System.out.println("for data1.txt, the time took structures to return contains number");
		for(int i = 0; i < 5; i++){
			if (times[i].d1ContainNum < times[j].d1ContainNum){
				j=i;
			}
			System.out.println(times[i].name + " " + times[i].d1ContainNum);
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
		(times[i].d1ContainNum - times[i].d1ContainHi) + " longer than Hi");
		}
	}

	/**
	 * compare initializing for data2.txt
	 */
	private static void compareSetsDataTow(){
		int j=0;
		System.out.println("for data2.txt, the time it took each structure to initialize:");
		for(int i = 0; i < 5; i++){
			if (times[i].data1Time < times[j].data1Time){
				j=i;
			}
			System.out.println(times[i].name + " " + times[i].data2Time);
		}
		System.out.println("quickest structure: "+ times[j].name);
	}

	/**
	 * data2, print the time took to search for hi.
	 */
	private static void dataTowContainHi(){
		int j=0;
		System.out.println("for data2.txt, the time took structures to return contains hi");
		for(int i = 0; i < 5; i++){
			if (times[i].d2ContainHi < times[j].d2ContainHi){
				j=i;
			}
			System.out.println(times[i].name + " " + times[i].d2ContainHi);
		}
		System.out.println("best time: " + times[j].name);
	}
	
	/**
	 * data2, print the time took to search for number.
	 */
	private static void dataTowContainNum(){
		int j=0;
		System.out.println("for data2.txt, the time took structures to return contains number");
		for(int i = 0; i < 5; i++){
			if (times[i].d1ContainNum < times[j].d2ContainNum){
				j=i;
			}
			System.out.println(times[i].name + " " + times[i].d2ContainNum);
		}
		System.out.println("best time: " + times[j].name);
	}
	
	/**
	 * data2, printing the difference of comparing number an hi
	 */
	private static void compareDataTowContain(){
		System.out.println("for data2");
		for(int i = 0; i < 5; i++){
			System.out.println(times[i].name + " finding the number took " + 
		(times[i].d2ContainNum - times[i].d2ContainHi) + " longer than Hi");
		}
	}

	/**
	 * printing the difference between initialize times.
	 */
	private static void printCompareInitTimes(){
		for(int i = 0; i < 5; i++){
			System.out.println("for " + times[i].name + "data1 initialize was" + 
					times[i].initCompareTime + "longer than data2");
		}
	}

	/**
	 * compare between initialize time of data1 and data2
	 * @param num- the number of structure so compare
	 */
	private static void compareInitializeTimes(int num){
		times[num].initCompareTime = (times[num].data1Time - times[num].data2Time);
	}

	static class TimeCounts{
		String name;
		SimpleSet set;
		long data1Time, data2Time, d1ContainHi, d2ContainHi, d1ContainNum, d2ContainNum,
		initCompareTime, d1ContainCompareTime, d2CompareContainTime;
		private TimeCounts(String name){
			this.name = name;
		}
	}

}
