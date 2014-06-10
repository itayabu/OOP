package oop.ex7.Sjavac.codeRead;

import java.util.NoSuchElementException;
import java.util.zip.Adler32;

import oop.ex7.Sjavac.exception.BadInputException;
import oop.ex7.Sjavac.exception.BadLineEndingException;
import oop.ex7.Sjavac.exception.NoClosureToParenthesesException;

/**
 * this class will change, just want to catch the line of CodeReader
 *
 */
public class SomeMainParser {

	/**
	 * read from file with LineReader and manage kind of lines
	 * @param path
	 */
	public SomeMainParser(String path){
		try {
			// TODO: open new file and write to it
			LineReader reader = new LineReader(path);
			while (reader.hasNext()){
			String text = reader.next();
			if (text.endsWith(";")){
				//TODO parse variable
				System.err.println(text);
			}
			else if(text.endsWith("{")){
				//TODO parse variable
				System.out.println("func");
				functionCopy(reader,text);
				
			} 
			else{
					// no function or variable
				throw new BadLineEndingException("bad line exception outside of function");
			}
			}
			System.out.println("done");
			
		} catch (BadInputException e) {
			System.out.println(e.getException());
			//TODO if reader finishes all lines? we need to find more elegant solution
		} catch (NoSuchElementException e){
			return;
		}
	}
	
	/**
	 * will print to the new file every line and check that every block is closed
	 * @param reader
	 * @param text
	 * @throws BadInputException 
	 * @throws NoSuchElementException 
	 */
	private void functionCopy(LineReader reader, String text) throws BadInputException{
		int openBlocks=0;
		try{
		do{
			System.out.println(text);
			if (text.endsWith("{")){
				openBlocks++;
			}
			if (text.endsWith("}")){
				openBlocks--;
			}
			if (reader.hasNext()){
			text= reader.next();
			}
			else{
				if (openBlocks > 0){
					throw new NoClosureToParenthesesException("No closure to parenthesis");
				}
			}
		}
		while (openBlocks > 0);
		} catch (NoSuchElementException e)	{
			throw new NoClosureToParenthesesException("No closure to parenthesis");
		}
		
		
	}
	
}
