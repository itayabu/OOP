package oop.ex6.filescript.filter;

import java.io.File;
/**
 * filter that passes every file
 * @author Itay
 *
 */
public class AllFilter extends Filter {
	
	/**
	 * simple constructor
	 */
	public AllFilter(){
		
	}

	/**
	 * passes every file
	 */
	public boolean isPass(File file){
		return true;
	}
	
	public void PrintFilter(){
		System.out.println("filter: no filter");
	}
}
