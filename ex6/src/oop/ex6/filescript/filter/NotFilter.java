package oop.ex6.filescript.filter;

import java.io.File;
/**
 * filter files opposite to the original filter method
 * @author Itay
 *
 */
public class NotFilter extends Filter {

	Filter oldFilter;
	
	/**
	 * basic constructor
	 * @param filter original filter
	 */
	public NotFilter(Filter filter){
		this.oldFilter = filter;
	}
	
	/**
	 * return the opposite answer of the original filter
	 */
	public boolean isPass(File file) {
		return (!oldFilter.isPass(file));
	}

	@Override
	public void PrintFilter() {
		System.out.println("NOT filter ");
	}

}
