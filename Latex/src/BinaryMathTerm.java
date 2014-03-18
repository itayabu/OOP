//alex kazakov


/**
 * This class represents a term composed of exactly two independent math terms. 
 * Like MathTerm, this class functions as a base class and shouldn't be 
 * instantiated. Hence it has an unimplemented toLatex method (return "";). 
 * Classes that extending it should implement the toLatex method according to 
 * their own definition.
 * @author AM
 *
 */
public abstract class BinaryMathTerm extends MathTerm {
	protected MathTerm firstTerm;
	protected MathTerm secondTerm;
	/**
	 * Constructs an new BinaryMathTerm 
	 * (should be called from within extending classes).
	 * @param firstTerm
	 * @param secondTerm
	 */
	public BinaryMathTerm(MathTerm firstTerm,MathTerm secondTerm){
		this.firstTerm = firstTerm;
		this.secondTerm = secondTerm;	
	}
	/**
	 * Unimplemented in this class. However, should be implemented in any of 
	 * its subclasses.
	 */
	public abstract java.lang.String toLatex();
	
}
