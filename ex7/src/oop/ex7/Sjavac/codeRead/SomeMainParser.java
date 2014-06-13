package oop.ex7.Sjavac.codeRead;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.zip.Adler32;

import oop.ex7.Sjavac.exception.BadInputException;
import oop.ex7.Sjavac.exception.BadLineEndingException;
import oop.ex7.Sjavac.exception.NoClosureToParenthesesException;
import oop.ex7.Sjavac.instance.Instance;
import oop.ex7.Sjavac.instance.InstanceFactory;

/**
 * this class will change, just want to catch the line of CodeReader
 *
 */
public class SomeMainParser {

	ArrayList<ArrayList<Instance>> instanceListByBlock = new ArrayList<ArrayList<Instance>>();
	ArrayList<Instance> mainBlockInstances = new ArrayList<Instance>();
	InstanceFactory factory;

	/**
	 * read from file with LineReader and manage kind of lines
	 * @param path
	 * @return 
	 */

	public SomeMainParser(){
		instanceListByBlock.add(mainBlockInstances);
		factory = new InstanceFactory();
	}

	public int parseMainBlock(String path){
		try{
			LineReader reader = new LineReader(path);
			while (reader.hasNext()){
				String text = reader.next();
				if (text.endsWith(";")){
					text = deleteSuffix(text);
					//TODO parse variable
					System.err.println(text);
					mainBlockInstances.add(factory.createInstance(text));
				}
				else if(text.endsWith("{")){
					System.out.println("func");
					methodCheckAndSkip(reader,text);
					text = deleteSuffix(text);
					mainBlockInstances.add(factory.createInstance(text));

					//TODO parse variable
				} 
				
				// no method or variable
				else{
					throw new BadLineEndingException("bad line exception in main block");
				}
			}
			return 0;
		} catch(FileNotFoundException e){
			return 2;
		} catch (BadInputException e){
			e.getMessage();
			return 1;
		}
	}

	public int parseMethods(String path){
		try{
			LineReader reader = new LineReader(path);
			while (reader.hasNext()){
				String text = reader.next();
				if (text.endsWith(";")){
					text= reader.next();
					continue;
				}
				if (text.endsWith("{")){
					//TODO: add to arrayList and send to blockParse
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

	public void parseBlock(LineReader reader){
		
	}

	/**
	 * will skip every method but check if blocks are legal
	 * @param reader
	 * @param text
	 * @return 
	 * @throws BadInputException 
	 * @throws NoSuchElementException 
	 */
	private void methodCheckAndSkip(LineReader reader, String text) throws BadInputException{
		int openBlocks=0;
		do{
			System.out.println(text);
			if (text.endsWith("{")){
				openBlocks++;
			}
			if (text.endsWith("}")){
				openBlocks--;
				if (openBlocks == 0){
					return;
				}
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
