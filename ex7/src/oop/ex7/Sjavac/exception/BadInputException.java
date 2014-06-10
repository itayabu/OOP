package oop.ex7.Sjavac.exception;

public class BadInputException extends Exception {

	String problem;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BadInputException(String messege){
		this.problem = messege;
	}
	
	
	public String getException(){
		return problem;
	}

}
