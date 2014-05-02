import java.util.*;
public class SimpleSetPerformanceAnalyzer {
	static TimeCounts [] times;
	static String[] dataListOne, dataListTow;
	//	static SimpleSet checkOne,checkTow;
	public static void main(String[] args) {
		long timeBefore, timeAfter;
		dataListOne = Ex4Utils.file2array("src/data1.txt");
		dataListTow = Ex4Utils.file2array("src/data2.txt");
		times = new TimeCounts[5];
		for (int i = 0; i< 5; i++){
			times[i] = new TimeCounts();
		}
		for (int i = 1; i<5; i++){
			switch(i){
			case 0:
				timeBefore= new Date().getTime();
				times[0].set = new OpenHashSet(dataListOne);
				timeAfter= new Date().getTime();
				times[0].data1Time = timeAfter-timeBefore;
				fillTimesDataOne(times[i]);
				timeBefore= new Date().getTime();
				times[0].set = new OpenHashSet(dataListTow);
				timeAfter= new Date().getTime();
				times[0].data2Time = timeAfter-timeBefore;
				fillTimesDataTow(times[i]);
				System.out.println("initiate time 1 " + times[i].data1Time);
				System.out.println("initiate time 2 " + times[i].data2Time);
				System.out.println("contain Hi in d1 " + times[1].d1ContainHi);
				System.out.println("contain Hi in d2 " + times[i].d2ContainHi);
				System.out.println("contain num in d1 " + times[i].d1ContainNum);
				System.out.println("contain num in d2 " + times[i].d2ContainNum);
				break;
			case 1: 
				timeBefore= new Date().getTime();
				times[i].set = new ChainedHashSet(dataListOne);
				timeAfter= new Date().getTime();
				times[i].data1Time = timeAfter-timeBefore;
				fillTimesDataOne(times[i]);
				timeBefore= new Date().getTime();
				times[i].set = new OpenHashSet(dataListTow);
				timeAfter= new Date().getTime();
				times[i].data2Time = timeAfter-timeBefore;
				fillTimesDataTow(times[i]);
				System.out.println("initiate time 1 " + times[i].data1Time);
				System.out.println("initiate time 2 " + times[i].data2Time);
				System.out.println("contain Hi in d1 " + times[i].d1ContainHi);
				System.out.println("contain Hi in d2 " + times[i].d2ContainHi);
				System.out.println("contain num in d1 " + times[i].d1ContainNum);
				System.out.println("contain num in d2 " + times[i].d2ContainNum);
				break;
			case 2:
			timeBefore= new Date().getTime();
			times[i].set = new CollectionFacadeSet(new LinkedList<String>());
			for (int j=1; j< dataListOne.length; j++){
				times[i].set.add(dataListOne[i]);
			}
			timeAfter= new Date().getTime();
			times[i].data1Time = timeAfter-timeBefore;
			fillTimesDataOne(times[i]);
			timeBefore= new Date().getTime();
			times[i].set = new CollectionFacadeSet(new LinkedList<String>());
			for (int j=1; j< dataListOne.length; j++){
				times[i].set.add(dataListOne[i]);
			}
			timeAfter= new Date().getTime();
			times[i].data2Time = timeAfter-timeBefore;
			fillTimesDataTow(times[i]);
			System.out.println("initiate time 1 " + times[i].data1Time);
			System.out.println("initiate time 2 " + times[i].data2Time);
			System.out.println("contain Hi in d1 " + times[i].d1ContainHi);
			System.out.println("contain Hi in d2 " + times[i].d2ContainHi);
			System.out.println("contain num in d1 " + times[i].d1ContainNum);
			System.out.println("contain num in d2 " + times[i].d2ContainNum);
			break;
			case 3:
				timeBefore= new Date().getTime();
				times[i].set = new CollectionFacadeSet(new TreeSet<String>());
				for (int j=1; j< dataListOne.length; j++){
					times[i].set.add(dataListOne[i]);
				}
				timeAfter= new Date().getTime();
				times[i].data1Time = timeAfter-timeBefore;
				fillTimesDataOne(times[i]);
				timeBefore= new Date().getTime();
				times[i].set = new CollectionFacadeSet(new TreeSet<String>());
				for (int j=1; j< dataListOne.length; j++){
					times[i].set.add(dataListOne[i]);
				}
				timeAfter= new Date().getTime();
				times[i].data2Time = timeAfter-timeBefore;
				fillTimesDataTow(times[i]);
				System.out.println("initiate time 1 " + times[i].data1Time);
				System.out.println("initiate time 2 " + times[i].data2Time);
				System.out.println("contain Hi in d1 " + times[i].d1ContainHi);
				System.out.println("contain Hi in d2 " + times[i].d2ContainHi);
				System.out.println("contain num in d1 " + times[i].d1ContainNum);
				System.out.println("contain num in d2 " + times[i].d2ContainNum);
				break;
			case 4:
				timeBefore= new Date().getTime();
				times[i].set = new CollectionFacadeSet(new HashSet<String>());
				for (int j=1; j< dataListOne.length; j++){
					times[i].set.add(dataListOne[i]);
				}
				timeAfter= new Date().getTime();
				times[i].data1Time = timeAfter-timeBefore;
				fillTimesDataOne(times[i]);
				timeBefore= new Date().getTime();
				times[i].set = new CollectionFacadeSet(new HashSet<String>());
				for (int j=1; j< dataListOne.length; j++){
					times[i].set.add(dataListOne[i]);
				}
				timeAfter= new Date().getTime();
				times[i].data2Time = timeAfter-timeBefore;
				fillTimesDataTow(times[i]);
				System.out.println("initiate time 1 " + times[i].data1Time);
				System.out.println("initiate time 2 " + times[i].data2Time);
				System.out.println("contain Hi in d1 " + times[i].d1ContainHi);
				System.out.println("contain Hi in d2 " + times[i].d2ContainHi);
				System.out.println("contain num in d1 " + times[i].d1ContainNum);
				System.out.println("contain num in d2 " + times[i].d2ContainNum);
				break;
			}
		}
	}

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
		
	}
	
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
		
	}

	
	private static void compareInitTimes(int num){
		times[num].initCompTime = (times[num].data1Time - times[num].data2Time);
	}

	private static void containTimesHi(SimpleSet setOne, SimpleSet setTow, int num){
		long timeBefore, timeAfter;
		//first list
		OpenHashSet checkOne, checkTow;
		timeBefore = new Date().getTime();
		checkOne = new OpenHashSet(dataListOne);
		timeAfter =  new Date().getTime();
		times[0].data1Time = timeAfter-timeBefore;
		//second list
		timeBefore = new Date().getTime();
		checkTow = new OpenHashSet(dataListTow);
		timeAfter =  new Date().getTime();
		times[0].data2Time = timeAfter-timeBefore;
	}

	static class TimeCounts{
		SimpleSet set;
		long data1Time, data2Time, d1ContainHi, d2ContainHi, d1ContainNum, d2ContainNum, initCompTime, initContTime;
		private TimeCounts(){

		}
	}

}
