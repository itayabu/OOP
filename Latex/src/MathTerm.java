/**
 * This class represents the base class for all other MathTerms classes you will
 * implement in the exercise. It defines the interface (the public part of the
 * classes) for all other math terms which will extend it. 
 * Most importantly, it defines the method "toLatex" that should be overridden
 * in each of the extending classes. This class is not meant to be instantiated
 * at any point, the only classes that will be instantiated are classes that 
 * extend it. Thus, the "toLatex" method should be left unimplemented 
 * (return ""). It will be implemented, however, in any of this class' 
 * subclasses. The rest of the interface (setExponentTerm, setters and getters,
 * etc.) or any additions you may want to add to this class 
 * (as long as they are hidden do not modify its interface), can and should be 
 * implemented in this class. Later on this course, we will find a more elegant 
 * way to "enforce" subclasses to implement a method rather than leaving 
 * unimplemented methods (abstract, interface). note: The interface allows to 
 * "annotate" the term with bar (upper line), negation or with other MathTerm 
 * as exponent. When more than one of these annotations are set, please evaluate 
 * the latex representation in the following order: exponent, 
 * bar and then negation.
 * @author AM
 */
public class MathTerm extends java.lang.Object {
	//creating class fields
	protected MathTerm exponentTerm;
	protected boolean exponent;
	protected boolean isBarred;
	protected boolean isNegated;

	/**
	 * This method gets a math term to be placed as an exponent for the current 
	 * math term.For example. If our current MathTerm is "a" and the user passes 
	 * "b". Then our Mathterm will be rendered as "a^{ b }".
	 * @param exponentTerm
	 */
	public void setExponentTerm(MathTerm exponentTerm){
		this.exponentTerm = exponentTerm;
		exponent = true;//set the exponent parameter to be true;
	}
	
	/**
	 * Returns the exponent math term.
	 * @return
	 */
	public MathTerm getExponentTerm(){
		return exponentTerm;
	}
	
	/**
	 * Setting whether this MathTerm should be barred or not 
	 * (a straight line on top of the term: see Latex's \overline{}).
	 * @param isBarred
	 */
	public void setIsBarred(boolean isBarred){
		this.isBarred = isBarred;
	}
	/**
	 * returns whether this math term was set to be barred.
	 * @return
	 */
	public boolean getIsBarred(){
		return isBarred;
	}
	
	/**
	 * Sets whether this math term should be negated (see Latex's \neg{}).
	 * @param isNegated
	 */
	public void setIsNegated(boolean isNegated){
		this.isNegated = isNegated;
	}
	
	/**
	 * isNegated getter.
	 * return True if this term should be negated.
	 * @return
	 */
	public boolean getIsNegated(){
		return isNegated;
	}
	
	/**
	 * This method should be implemented in any of MathTerm derivatives 
	 * (inheriting classes). However, we will leave it unimplemented 
	 * (returns empty string).
	 * @return null
	 */
	public java.lang.String toLatex(){
		return null;
	}
	/**
	 * this method takes the String and checks if it is either an exponent 
	 * or barred or negative and returns the corrected form of the String
	 * @param latex - a string
	 * @return latex - the corrected form  
	 */
	protected String print (String latex){
		if(exponent){
			latex =  latex+"^{ "+getExponentTerm().toLatex()+" }";
		}
		if(isBarred){
			latex = "\\overline{ "+latex+" }";
		}
		if(isNegated){
			latex = "\\neg{ "+latex+" }";
		}
		return latex;
	
	}
}

