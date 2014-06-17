package oop.ex7.Sjavac.instance;
/**
 * this enum has constants and method for parsing Sjava code and classifying it
 * @author Assaf M. Itay A.
 *
 */
public enum ExpressionType {
	METHOD,
	BLOCK_END,
	BLOCK_START,
	MEMBER_DECLARATION,
	METHOD_CALL,
	RETURN,
	COMMAND,
	UNKNOWN;//error

	//this character appear each time a block is started
	private static final char _START = '{';
	
	//this character appear each time a block ends
	public static final char _END = '}';

	//legal types that legal for methods types
	public static final String METHOD_TYPES = "("+Types.TYPES+"|void)";

	//legal method or members names it might start with "_"
	public static final String NAME = "_?[a-zA-Z][_\\w]*";
	
	//list of legal block types
	public static final String BLOCKS_TYPES = "(while|if)";

	//member declaration format
	public static final String MEMBER_DECLARE = Types.TYPES+" "+NAME+"( ?=.*)?;$";

	//method declaration format
	public static final String METHOD_DECLARE = METHOD_TYPES+" [a-zA-Z][_\\w]* ?\\(.*\\) ?\\"+_START;

	//match the return statement
	public static final String RETURN_STAT = "return .*;";

 	//match block start statement
	public static final String BLOCK=BLOCKS_TYPES+" ?\\(.*\\) ?\\"+_START;

	//match method call statements
	public static final String METHOD_CALL_FORMAT = NAME+"\\(.*\\);";

	//the format of a command
	public static final String COMMAND_FORMAT=NAME+" ?=.*;$";

	/**
	 * return the type of the given String
	 * @param line the line to test
	 * @return an ExpressionType, representing the kind of the given String
	 */
	public static ExpressionType whatExpression(String line) {

		//first check if line is even legal by checking if it ends with ";","{","}"
		//these are the only legal line endings
		switch (line.charAt(line.length()-1)) {
		case ';':

			if(line.matches(MEMBER_DECLARE))
				return MEMBER_DECLARATION;

			if(line.matches(RETURN_STAT))
				return RETURN;

			if(line.matches(METHOD_CALL_FORMAT))
				return METHOD_CALL;

			if(line.matches(COMMAND_FORMAT))
				return COMMAND;
			break;

		case _START:

			if(line.matches(METHOD_DECLARE))
				return METHOD;

			if(line.matches(BLOCK))
				return BLOCK_START;
			break;

		case _END:

			return BLOCK_END;

		}

		return UNKNOWN;
	}

}