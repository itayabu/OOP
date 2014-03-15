/**
 * This class represents a mathematical sum. It comprised of 3 MathTerms: - Term beneath the sigma sign.
 * - Term above the sigma sign. - Term being summed. Use the \sum latex command,
 * to generate the latex representation of this MathTerm.
 * @author AM
 *
 */
public class SumMathTerm extends MathTerm{
	protected MathTerm subTerm;
	protected MathTerm superTerm;
	protected MathTerm sumTerm;

	/**
	 * The constructor receives the 3 MathTerm that comprises the sum math term.
	 * @param subTerm The term beneath the sigma.
	 * @param superTerm The term above the sigma.
	 * @param sumTerm The summed term.
	 */
	public SumMathTerm(MathTerm subTerm,MathTerm superTerm,MathTerm sumTerm){
		this.subTerm = subTerm;
		this.superTerm = superTerm;
		this.sumTerm = sumTerm;
	}
	/**
	 * Generates the latex representation of this sum math term.
	 * latex representation using the \sum command.
	 */
	public java.lang.String toLatex(){
		return "\\sum_{ "+this.subTerm.toLatex()+" }^{ "+this.superTerm.toLatex()+" }{ "+this.sumTerm.toLatex()+" }";
	}
}
