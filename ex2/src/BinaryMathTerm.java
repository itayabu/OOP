
/**
 * This class represents a term composed of exactly two independent math terms.
 * @author Itay
 *
 */
public abstract class BinaryMathTerm extends MathTerm {
	
	/**
	 * protected fields
	 * firstTerm- the upper/left term
	 * secondTerm- the lower/right term
	 */
	protected MathTerm firstTerm, secondTerm;
	
	/**
	 * Constructs an new BinaryMathTerm (should be called from within extending classes).
	 */
	public BinaryMathTerm(MathTerm firstTerm, MathTerm secondTerm){
		super();
		this.firstTerm = firstTerm;
		this.secondTerm = secondTerm;
	}
	
	
	@Override
	public abstract String toLatex();
}
