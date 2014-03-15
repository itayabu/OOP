/**
 * This class represents a math term which is either a single letter variable 
 * (x,y,a,b,etc..)  or a number (may be a floating point number). The latex 
 * representation is straight forward the name of the variable, or the number 
 * itself. However, in case this term represents a number, the class will allow
 * to user to control the precision of its latex representation, 
 * that is - the number of digits to the right of the floating dot.
 * @author AM
 */
public class SimpleMathTerm extends MathTerm {
	protected String termName;
	
	/**
	 * Constructs a new instance given a simple term "name" 
	 * (which can be a variable or a number). A string of either a single letter 
	 * variable (x,y,z,a,b..) or a number (may be a floating point number).
	 * @param termName
	 */
	public SimpleMathTerm(java.lang.String termName){
		this.termName = termName;
	}
	
	/**
	 * Checks the given name. And determines whether it's numeric.
	 * @return true if this term represents a number.
	 */
	public boolean isNumeric(){
		if(Character.isDigit(this.termName.charAt(0)) || 
				this.termName.charAt(0)=='-'){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Sets the number of digits of precision in case this term represents 
	 * a number.
	 * @param precisionDigits - Number of digits right of the 
	 * 							floating point on the latex representation.
	 */
	public void setPrecisionDigits(int precisionDigits){
		if (isNumeric()){
			int index = this.termName.indexOf('.');
			if (precisionDigits == 0){
				this.termName = termName.substring(0, index);
			}
			else if (this.termName.length()-(index+1)>precisionDigits){
				this.termName = this.termName.substring(0, 
						index+precisionDigits+1);
			}
			
		}
	}
	
	/**
	 * Generates the latex representation of the this simple math term.
	 * the Latex representation. If this term represents a variable, 
	 * this method returns the variable name. Otherwise, if the term represents 
	 * a number it should be trimmed to according to the precision parameter.
	 */
	public java.lang.String toLatex(){
		return print(termName);
	}	
}
