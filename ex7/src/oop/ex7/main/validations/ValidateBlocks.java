package oop.ex7.main.validations;

public class ValidateBlocks {
	private static final String CONDITION_COMMAND =
			"(if|while)\\s*\\(([^{]+)\\)\\s*\\{(.*)";
	/**
	 * check if outer structure of new block line is legal
	 * @param s the new block line
	 * @return true if lone is legal, else false.
	 */
	public static boolean validateIfOrWhileBlock(String s){
		return s.matches(CONDITION_COMMAND);
	}
}
