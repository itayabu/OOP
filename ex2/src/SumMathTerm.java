
/**
 * class sumMathTerm 
 * This class represents a mathematical sum. It comprised of 3 MathTerms: 
 * - Term beneath the sigma sign. - Term above the sigma sign. - Term being summed.
 * @author Itay
 *
 */
public class SumMathTerm extends MathTerm {

	/**
	 * private fields
	 * subTerm - Term beneath the sigma sign. 
	 * superTerm - Term above the sigma sign. 
	 * sumTerm - Term being summed.
	 */
	private MathTerm subTerm, superTerm, sumTerm;

	/**
	 * The constructor receives the 3 MathTerm that comprises the sum math term.
	 * @param subTerm
	 * @param superTerm
	 * @param sumTerm
	 */
	public SumMathTerm(MathTerm subTerm, MathTerm superTerm, MathTerm sumTerm){
		this.subTerm=subTerm;
		this.superTerm=superTerm;
		this.sumTerm=sumTerm;
	}

	/**
	 * Generates the latex representation of this sum math term.
	 */
	@Override
	public String toLatex() {
		// get basic String and send it to latexPrint at MathTerm
		String latex;
		latex= ("\\sum_{ " + subTerm.toLatex() + " }^{ " + superTerm.toLatex() + " }" +
				"{ " + sumTerm.toLatex() + " }" );
		return latexPrint(latex);
	}


}
