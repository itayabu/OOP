/**
 * This class represents a special case of binary math term- fraction.
 * @author Itay
 *
 */
public class FractionMathTerm extends BinaryMathTerm {
	
	/**
	 * Constructs a new Fraction term.
	 * @param firstTerm
	 * @param secondTerm
	 */
	public FractionMathTerm (MathTerm firstTerm, MathTerm secondTerm){
		super(firstTerm, secondTerm);
	}
	
	/**
	 * Generates the latex representation of this fraction math term.
	 * @return latex representation of this fraction math term using the \frac latex command.
	 */
	@Override
	public String toLatex() {
		// get basic String and send it to latexPrint at MathTerm
		String latex= ("\\frac{ "+this.firstTerm.toLatex() + " }{ " + this.secondTerm.toLatex() + " }");
		return this.latexPrint(latex);
	}

}
