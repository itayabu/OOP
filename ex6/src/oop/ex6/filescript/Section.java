package oop.ex6.filescript;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import oop.ex6.filescript.filter.Filter;
import oop.ex6.filescript.filter.FilterFactory;
import oop.ex6.filescript.order.Order;
import oop.ex6.filescript.order.OrderFactory;

/**
 * hold and manage filter and order instances 
 *
 */
public class Section {

	private String filterString, orderString;
	private int filterLine, orderLine;
	private Filter filter;
	private Order order;
	
	/**
	 * construct a new section.
	 */
	public Section (){
		
	}
	
	/**
	 * add a new filter parameters to section
	 * @param filterParams - parameters of filter.
	 * @param line - line in command file that the parameters are
	 */
	public void addFilter(String filterParams, int line){
		filterString = filterParams;
		filterLine = line;
	}
	
	/**
	 * add a new order parameters to section
	 * @param orderParams - parameters of order.
	 * @param line - line in command file that the parameters are
	 */
	public void addOrder(String orderParams, int line){
		orderString = orderParams;
		orderLine = line;
	}
	
	/**
	 * turn filterString to real filter
	 * @param args - filter string
	 */
	private void setFilter(){
		filter = FilterFactory.createFilter
				(filterString.split("#"), filterLine);
	}
	
	/**
	 * turn orderString to real order
	 * @param args - order string
	 */
	private void setOrder(){
		order = OrderFactory.creatOrder
				(orderString.split("#"), orderLine);
	}
	
	/**
	 * filter and order a list of files, 
	 * according to filter and order in section
	 * @param files - array of files to filter and order.
	 * @return ArrayList of filtered and ordered files.
	 */
	public ArrayList<File> getSection(File[] files){
		
		// initialize filter, order and the new array
		setFilter ();
		setOrder();
		ArrayList<File> fileList = new ArrayList<File>();
		
		//filter
		for (int i=0; i< files.length; i++){
			if (files[i].isDirectory()){
				continue;
			}
			if (filter.isPass(files[i])){
				fileList.add(files[i]);
			}
		}
		
		//order
		Collections.sort(fileList, order);
		return fileList;
	}
}