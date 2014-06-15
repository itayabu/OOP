package oop.ex7.Sjavac.codeRead;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.zip.Adler32;

import oop.ex7.Sjavac.exception.BadInputException;
import oop.ex7.Sjavac.exception.BadLineEndingException;
import oop.ex7.Sjavac.exception.DuplicateInstaceException;
import oop.ex7.Sjavac.exception.MemberDoesNotExistException;
import oop.ex7.Sjavac.exception.NoClosureToParenthesesException;
import oop.ex7.Sjavac.instance.Instance;
import oop.ex7.Sjavac.instance.InstanceFactory;
import oop.ex7.Sjavac.validations.ValidateType;

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

	/**
	 * parse the main block.
	 * make an Instance of every field and method in main block.
	 * skip any other block.
	 * @param path
	 * @return
	 */
	public int parseMainBlock(String path){
		try{
			LineReader reader = new LineReader(path);
			while (reader.hasNext()){
				String text = reader.next();
				if (text.endsWith(";")){
					System.err.println(text);
					mainBlockInstances.add(factory.createInstance(text));
				}
				else if(text.endsWith("{")){
					System.out.println("func");
					mainBlockInstances.add(factory.createInstance(text));
					methodCheckAndSkip(reader,text);
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

	private void parseBlock(LineReader reader) throws NoSuchElementException, BadInputException{
		ArrayList <Instance> blockList = new ArrayList <Instance>();
		instanceListByBlock.add(1, blockList);
		while (reader.hasNext()){
			String text = reader.next();
			Instance currInstance;

			//new block
			String[] splittedText = text.split(" ");
			if (text.endsWith("{")){
				//TODO: check if valid
				parseBlock (reader);
			}
			
			//end of block
			else if (text.endsWith("}")){
				//TODO: change instance's initialize back
				instanceListByBlock.remove(1);
			}
			
			// must end with ";"
			else if (ValidateType.isValidInstanceType(splittedText[0])){
				currInstance = InstanceFactory.createInstance(text);
				if (InstanceNameExistInBlock(currInstance,blockList)){
					throw new DuplicateInstaceException("Instance created twice in the same block");					
				}
			}
			else{
				currInstance = findInstance(instanceListByBlock, splittedText[1]);
				if (currInstance == null){
					throw new MemberDoesNotExistException
					("searched for member called "+splittedText[1]+" and didnt find it");
				}
				//TODO: continue here
			}

		}
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
	 * check if already exist instance in block
	 * @param checkInstance the instance to check
	 * @param instanceInBlock all instances already exist in current block
	 * @return
	 */
	private boolean InstanceNameExistInBlock(Instance checkInstance, ArrayList<Instance> instanceInBlock){
		for (Instance instance:instanceInBlock){
			if (checkInstance.getName().equals(instance.getName())){
				return true;
			}
		}
		return false;
	}

	private Instance findInstance(ArrayList<ArrayList<Instance>> list, String s){
		for (ArrayList<Instance> subList: list){
			for (Instance instance: subList){
				if (instance.getName().equalsIgnoreCase(s)){
					return instance;
				}
			}
		}
		return null;
	}
}
