
/**
 * This class represents a math term which is either a single letter variable (x,y,a,b,etc..)
 * or a number (may be a floating point number).
 * The latex representation is straight forward the name of the variable, or the number itself.
 * However, in case this term represents a number,
 * the class will allow to user to control the precision of its latex representation,
 * that is - the number of digits to the right of the floating dot.
 * @author Itay
 *
 */
public class SimpleMathTerm extends MathTerm {

	/**
	 * private fields
	 * name- MathTerm's value
	 * isNumber- true if term is number, false if it is variable
	 */
	private String name;
	private boolean isNumber= false;
	
	/**
	 * Constructs a new instance given a simple term "name" (which can be a variable or a number)
	 */
	public SimpleMathTerm(String termName){
		super();
		this.name=termName;
		if (!Character.isLetter(name.charAt(0))){
			isNumber=true;
		}
	}

	/**
	 * Checks the given name.
	 * @return true if the term is numeric, false else.
	 */
	public boolean isNumeric(){
		return isNumber;
	}

	/**
	 * Sets the number of digits of precision in case this term represents a number.
	 * @param precisionDigits
	 */
	public void setPrecisionDigits(int precisionDigits){
		
		//if there is no need to precision, return.
		if (!isNumber || precisionDigits<0 || !name.contains(".")){
			return;
		}
		
		//copy the integer part and prepare for the precision
		int index = name.indexOf(".");
		String tmpStr = name.substring(0,index);
		if (precisionDigits>0){
			tmpStr+=name.charAt(index);
			index++;
		}
		
		// copy the precision numbers
		while (index < name.length() && precisionDigits>0 ){
			tmpStr+=name.charAt(index);
			index++;
			precisionDigits--;
		}
		name = tmpStr;
	}

	/**
	 * Generates the latex representation of the this simple math term.
	 */
	@Override
	public String toLatex() {
		// get basic String and send it to latexPrint at MathTerm
		String latex = name;
		return latexPrint(latex);
	}
}
