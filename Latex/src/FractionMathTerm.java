/**
 * This class represents a special case of binary math term. 
 * It should be rendered as a fraction ("kav shever" in hebrew). 
 * Implement the toLatex method using Latex \frac command.
 * @author AM
 *
 */
public class FractionMathTerm extends BinaryMathTerm {

	/**
	 * Constructs a new Fraction term. Keep in mind that the base class, 
	 * BinaryMathTerm, should be be instantiated explicitly 
	 * (see: SimpleBinaryOpMathTerm.SimpleBinaryOpMathTerm(MathTerm, 
	 * MathTerm, char)).
	 */

	public FractionMathTerm(MathTerm firstTerm, MathTerm secondTerm) {
		super(firstTerm,secondTerm);
		this.firstTerm = firstTerm;
		this.secondTerm = secondTerm;
	}

	/**
	 * Generates the latex representation of this fraction math term.
	 */
	public String toLatex() {
		return "\\frac{ "+this.firstTerm.toLatex()+" }{ "+
				this.secondTerm.toLatex()+" }";
	}

}
