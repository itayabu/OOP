package oop.ex6.filescript;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import oop.ex6.filescript.filter.Filter;
import oop.ex6.filescript.filter.FilterFactory;
import oop.ex6.filescript.order.Order;
import oop.ex6.filescript.order.OrderFactory;
public class Section {

	private String filterString, orderString;
	private int filterLine, orderLine;
	private Filter filter;
	Order order;
	
	/**
	 * construct a new section.
	 */
	public Section (){
		
	}
	
	public void addFilter(String args, int line){
		filterString = args;
		filterLine = line;
	}
	
	public void addOrder(String args, int line){
		orderString = args;
		orderLine = line;
	}
	
	public void setFilter(String args){
		filter = FilterFactory.createFilter(args.split("#"), filterLine);
	}
	
	public void setOrder(String args){
		order = OrderFactory.creatOrder(args.split("#"), orderLine);
	}
	
	
	public ArrayList<File> getSection(File[] files){
		setFilter (filterString);
		setOrder(orderString);
		ArrayList<File> fileList = new ArrayList<File>();
		for (int i=0; i< files.length; i++){
			if (files[i].isDirectory()){
				continue;
			}
			if (filter.isPass(files[i])){
				fileList.add(files[i]);
			}
		}
		Collections.sort(fileList, order);
		return fileList;
	}
}