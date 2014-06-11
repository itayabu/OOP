package oop.ex7.Sjavac.codeRead;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.zip.Adler32;

import oop.ex7.Sjavac.exception.BadInputException;
import oop.ex7.Sjavac.exception.BadLineEndingException;
import oop.ex7.Sjavac.exception.NoClosureToParenthesesException;
import oop.ex7.Sjavac.instance.Instance;

/**
 * this class will change, just want to catch the line of CodeReader
 *
 */
public class SomeMainParser {
	
	ArrayList<Instance> instances = new ArrayList();
	
	/**
	 * read from file with LineReader and manage kind of lines
	 * @param path
	 * @return 
	 */
	
	public SomeMainParser(){
		
	}
	
	public int parseMainBlock(String path){
		try{
			// TODO: open new file and write to it
			LineReader reader = new LineReader(path);
			while (reader.hasNext()){
			String text = reader.next();
			if (text.endsWith(";")){
				text = deleteSuffix(text);
				//TODO parse variable
				System.err.println(text);
			}
			else if(text.endsWith("{")){
				//TODO parse variable
				System.out.println("func");
				methodCopy(reader,text);
				text = deleteSuffix(text);
				
			} 
			else{
					// no function or variable
				throw new BadLineEndingException("bad line exception in main block");
			}
			}
			} catch(FileNotFoundException e){
				return 2;
			} catch (BadInputException e){
				e.getMessage();
				return 1;
			}
		return 0;
	}
	
	/**
	 * will print to the new file every line and check that every block is closed
	 * @param reader
	 * @param text
	 * @throws BadInputException 
	 * @throws NoSuchElementException 
	 */
	private void methodCopy(LineReader reader, String text) throws BadInputException{
		int openBlocks=0;
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
		
		
	}
	
	/**
	 * will delete suffix of String
	 */
	private String deleteSuffix(String s){
		s = s.substring(0, s.length()-1);
		return s.trim();
	}
}
