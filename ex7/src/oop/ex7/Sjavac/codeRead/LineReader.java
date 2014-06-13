package oop.ex7.Sjavac.codeRead;
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

import oop.ex7.Sjavac.exception.BadInputException;
import oop.ex7.Sjavac.exception.BadLineEndingException;
import oop.ex7.Sjavac.exception.IllegalLineException;
import oop.ex7.Sjavac.exception.NoFileFoundException;
import oop.ex7.Sjavac.exception.WrongCommentException;
/**
 * This class reads files.
 * this class also do some operations on 
 * the file to make it easier to compile it.
 * this operations includes: removing comments and extra spaces, 
 * and add spaces before and after "=" equal characters.
 * @author Assaf M. & Itay A
 */
public class LineReader {

	private static Scanner scanner;//init the scanner instance

	private static String filename;//init the file name to use with reset

	/**
	 * construct a new CodeReader object
	 * @param filename the name of the file to open
	 * @throws WrongCommentException if the file is starting with illegal comment
	 */
	public LineReader(String filename) throws FileNotFoundException {
		this.filename = filename;
		reset();//reset the scanner
	}

	/**
	 * Advances this FileRead past the current line and returns the 
	 * input that was skipped.
	 * This method returns the rest of the current line, 
	 * excluding any line separator, comments or spaces at the end.
	 * The position is set to the beginning of the next line of code. 
	 * @return line that was skipped excluding any comments/spaces at the end.
	 * @throws WrongCommentException the next lines has illegal comment
	 */
	public String next() throws BadInputException, NoSuchElementException {
		String result;
		do {
			result = scanner.nextLine().//advance the line
					replace("=", " = ").//correct the equal form
					replaceAll("(//.*$)", " ").//remove comments 
					replaceAll("\\s+", " ").//make sure spaces are not doubled
					trim();//remove any trailing or leading spaces
			if (!((result.endsWith("{")) || (result.endsWith(";")) || (result.endsWith("}"))) ){
				throw new BadLineEndingException("bad line!");
			}
			trim();//remove trailing comments, useful for hasNext() method
		}
		while(result.length()<1); //ignore empty lines
		return result;
	}

	/**
	 * Returns true if the next token matches the pattern 
	 * constructed from the specified string.
	 * The FileRead does not advance past any input.
	 * @param regexp a string specifying the pattern to scan
	 * @return true if and only if this FileRead has another 
	 * token matching the specified pattern
	 */
	public boolean hasNext(String regexp) {
		return scanner.hasNext(regexp);
	}

	/**
	 * returns true if this FileRead has another token in it's input
	 * @return true if and only if this scanner has another token
	 */
	public boolean hasNext() {
		return scanner.hasNext();
	}

	//run to the next line of code 
	private void trim() throws BadInputException {
		while(scanner.hasNext("/.*")) {

			if(scanner.hasNext("/[^/\\*]"))
				throw new IllegalLineException("illigal expression at line\n"+scanner.nextLine());
			//TODO this is also illegal
			if(scanner.hasNext("/\\*.*"))//long comment
				scanner.findWithinHorizon("\\*/", 0);

			scanner.nextLine();
		}
	}

	/**
	 * take this FileRead back to the start of the file
	 * @throws BadInputException 
	 */
	public static void reset() throws FileNotFoundException {
		
			scanner=new Scanner(new File(filename));
		
	}

}
