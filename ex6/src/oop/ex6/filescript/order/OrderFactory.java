package oop.ex6.filescript.order;

import oop.ex6.filescript.order.exceptions.BadOrderException;
import oop.ex6.filescript.order.exceptions.BadOrderNameExcepton;
import oop.ex6.filescript.order.exceptions.BadREVERSENameException;

public class OrderFactory {
	public static Order creatOrder (String[] orderStr, int orderLine){
		Order order=null;
		int i=0;
		try{
			if (orderStr[i].equals("size")){
				order = new SizeOrder();
			}

			else if (orderStr[i].equals("abs")||orderStr[0].equals("") ){
				order = new AbsOrder();
			}

			else if (orderStr[i].equals("type")){
				order = new TypeOrder();
			}
			// if no order name matched
			else{
				throw new BadOrderNameExcepton();
			}

			// manage the REVERSE filter
			if (++i < orderStr.length){
				if (orderStr[i].equals("NOT")){
					order = new ReverseOrder(order);
				}
				else{
					throw new BadREVERSENameException ();
				}
			}
		}
		
		// if there was any problem, print warning and initiate absOrder
		catch (BadOrderException e){
			System.out.println("Warning in line "+ orderLine);
			order = new AbsOrder();
		}

		return order;
	}
}
