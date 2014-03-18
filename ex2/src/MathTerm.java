
/**
 * This class represents the base class for all other MathTerms classes 
 * @author Itay
 *
 */
public abstract class MathTerm {
	
	/**
	 * private fields being used by most subclasses:
	 */
	private boolean isBarred, isNegated, hasExpo;
	private MathTerm expo=null;

	/**
	 * Default constructor.
	 */
	public MathTerm(){
		this.isBarred=false;
		this.isNegated=false;
	}

	/**
	 * Returns the exponent math term.
	 * @return
	 */
	public MathTerm getExponentTerm(){
		if (this.hasExpo){
			return this.expo;
		}
		return null;
	}

	/**
	 * This method gets a math term to be placed as an exponent for the current math term.
	 * @param exponentTerm
	 */
	public void setExponentTerm(MathTerm exponentTerm){
		this.hasExpo=true;
		this.expo= exponentTerm;

	}

	/**
	 * isBarred getter.
	 * @return isBarred
	 */
	public boolean getIsBarred(){
		return isBarred;
	}

	/**
	 * isNegated getter
	 * @return isNegated
	 */
	public boolean getIsNegated(){
		return isNegated;
	}

	/**
	 * hasExponend getter
	 * @return true if mathTerm has exponent number
	 */
	protected boolean getHasExpo(){
		return hasExpo;
	}


	/**	
	 * Setting whether this MathTerm should be barred or not
	 * @param isBarred
	 */ 	
	public void setIsBarred (boolean isBarred){
		this.isBarred=isBarred;
	}

	/**
	 * Sets whether this math term should be negated
	 * @param isMegated
	 */
	public void setIsNegated(boolean isMegated){
		this.isNegated=isMegated;
	}

	/**
	 * This method should be implemented in any of MathTerm derivatives
	 * @return String value for Latex
	 */
	public abstract String toLatex();

	/**
	 * This method is used to fix subclasses toLatex methos.
	 * if there is need, method add neg, bar and exponent to the MathTerm
	 * @param latex- MathTerm as a String
	 * @return- fixed String
	 */
	protected String latexPrint (String latex){
		if (this.hasExpo){
			latex= latex +"^{ " + this.expo.toLatex() + " }";
		}
		if (isBarred){
			latex= "\\overline{ " + latex + " }";
		}
		if (isNegated){
			latex= "\\neg{ " + latex + " }";
		}
		return latex;

	}
}

