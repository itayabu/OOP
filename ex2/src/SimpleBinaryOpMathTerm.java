
/**
 * This class represents a simple operation between two other terms. For example, "a+b", "c*d" or "c=d".
 * @author Itay
 *
 */
public class SimpleBinaryOpMathTerm extends BinaryMathTerm {
	
	private char sign;

	/**
	 * Instantiate a new SimpleBinaryOpMathTerm.
	 * @param firstTerm
	 * @param secondTerm
	 * @param sign
	 */
	public SimpleBinaryOpMathTerm (MathTerm firstTerm, MathTerm secondTerm, char sign){
		super(firstTerm,secondTerm);
		this.sign=sign;
	}

	
	/**
	 * Generates the latex representation of this arithmetic operation math term.
	 */
	@Override
	public String toLatex() {
		// get basic String and send it to latexPrint at MathTerm
		String latex;
		if (sign != '*'){
		latex= super.firstTerm.toLatex() + sign + super.secondTerm.toLatex();
		}
		else{
			latex= super.firstTerm.toLatex() + " \\cdot " + super.secondTerm.toLatex();
		}
		return latexPrint(latex);
	}

	
}
