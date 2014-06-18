package oop.ex7.main.instance;

import java.util.Map;

import oop.ex7.main.exceptions.CompilerError;

/**
 * this is the enum which holds the basic types
 * @author Assaf H. Itay A.
 */
public enum Types {
	INT,
	CHAR,
	STRING,
	DOUBLE,
	BOOLEAN,
	VOID;

	// the types allowed by the compiler
	public static final String TYPES="(int|double|String|boolean|char)";

	// the format of the values allowed by each type
	private static final String INT_FORMAT="-?\\d+";
	private static final String DOUBLE_FORMAT="-?\\d+(\\.\\d+)?";
	private static final String BOOLEAN_FORMAT="(true|false)";
	private static final String CHAR_FORMAT="'.'";
	private static final String STRING_FORMAT="\".*\"";

	/**
	 * this method returns the "Types" object that represents the value of the 
	 * expression
	 * @param expression string to process
	 * @return "Types" object that represents the value of the expression
	 */
	public static Types whatType(String expression) {
		//check if the expression might be one of the format types
		if(expression.matches(INT_FORMAT))
			return INT;

		if(expression.matches(DOUBLE_FORMAT))
			return DOUBLE;

		if(expression.matches(CHAR_FORMAT))
			return CHAR;

		if(expression.matches(BOOLEAN_FORMAT))
			return BOOLEAN;

		if(expression.matches(STRING_FORMAT))
			return STRING;

		return VOID;//default return if none were matched
	}
	/**
	 * return a Types object that represent the value of object
	 * considering that all the variables and method in known do exist in the called scope
	 * @param expression the String to process
	 * @param variables all known variables and methods in the scope of the calling block
	 * @return a "Types" object that represent the value of object
	 * @throws SjavaError in case of an unknown error
	 * @throws BracketException if there are unclosed brackets
	 * or a closing unopened bracket
	 * @throws MemberDontExistExcetion if that method does not exist or trying to use unknown member
	 */
	public static Types typeOf(String expression, Map<String, Member> variables) throws CompilerError {
		//if the Type is not a VOID type
		if(whatType(expression)!=VOID)
			return whatType(expression);

		if(variables.containsKey(expression))
			return variables.get(expression).getType();

		if(expression.length()==0)
			return VOID;

		expression=expression.replaceAll(" ", "");
		if(expression.matches(ExpressionType.METHOD_CALL_FORMAT+"?"))
			//#####################
			//@TODO MethodCall method
			//#####################
			return VOID;
		return VOID;
	}
	/**
	 * this method returns true if the value of the expression is really of
	 * that type
	 * @param type the required type to check match
	 * @param exp the value to check if really is of the designated type
	 * @return true if is of the form allowed
	 */
	public static boolean isType(Types type,String exp) {
		switch (type) {

		case INT:
			return exp.matches(INT_FORMAT);

		case STRING:
			return exp.matches(STRING_FORMAT);

		case CHAR:
			return exp.matches(CHAR_FORMAT);

		case DOUBLE:
			return exp.matches(DOUBLE_FORMAT);

		case BOOLEAN:
			return exp.matches(BOOLEAN_FORMAT);

		case VOID:
			return exp.length()==0;//because that is the nature of void expression

		default:
			return false;
		}
	}
}