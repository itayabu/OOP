package oop.ex7.main.validations;

import java.util.ArrayList;
import java.util.regex.Matcher;

import oop.ex7.main.RegexConstants;
import oop.ex7.main.instance.Instance;

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
