/**
 * This class represents a math term between brackets 
 * (See latex's \right( and \left)).
 * @author AM
 *
 */
public class BracketsMathTerm extends MathTerm {
	protected MathTerm internalTerm;
	/**
	 * The constructor receives the MathTerm they will be rendered as the 
	 * term inside the brackets.
	 * @param internalTerm - The term that resides within the brackets.
	 */
	public BracketsMathTerm(MathTerm internalTerm){
		this.internalTerm = internalTerm;
	}
	/**
	 * Generates the latex representation of for this bracket math term.
	 * the following string representation: 
	 * "\left( " + internal term representation + " \right)" 
	 * (notice the space before and after the internal term).
	 */
	public java.lang.String toLatex(){
		return "\\left( "+this.internalTerm.toLatex()+" \\right)";
	}
}
