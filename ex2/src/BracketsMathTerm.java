/**
 * This class represents a math term between brackets.
 * @author Itay
 *
 */
public class BracketsMathTerm extends MathTerm {
	
	/**
	 * private fields
	 * internalMathTerm- the term between the brackets
	 */
	private MathTerm internalMathTerm;
	
	/**
	 * The constructor receives the MathTerm they will be rendered as the term inside the brackets.
	 * @param internalTerm
	 */
	public BracketsMathTerm (MathTerm internalTerm){
		this.internalMathTerm=internalTerm;
	}
	
	/**
	 * Generates the latex representation of for this bracket math term.
	 * @return latex representation of this bracket math term
	 */
	@Override
	public String toLatex() {
		// get basic String and send it to latexPrint at MathTerm
		String latex= ("\\left( "+ internalMathTerm.toLatex()+" \\right)");
	return (this.latexPrint(latex));
	}
}
