package oop.ex7.main;


import java.util.ArrayList;
import java.util.NoSuchElementException;
import oop.ex7.main.exceptions.BadInputException;
import oop.ex7.main.exceptions.BadLineEndingException;
import oop.ex7.main.exceptions.BadStructureOfBlockLineException;
import oop.ex7.main.exceptions.CompilerError;
import oop.ex7.main.exceptions.DuplicateInstaceException;
import oop.ex7.main.exceptions.MemberDoesNotExistException;
import oop.ex7.main.exceptions.NoClosureToParenthesesException;
import oop.ex7.main.instance.Instance;
import oop.ex7.main.instance.InstanceFactory;
import oop.ex7.main.parsers.LineReader;
import oop.ex7.main.validations.InstanceArrayValidator;
import oop.ex7.main.validations.ValidateArrayValue;
import oop.ex7.main.validations.ValidateBlocks;
import oop.ex7.main.validations.ValidateInstanceValue;
import oop.ex7.main.validations.ValidateType;

/**
 * Sjavac class. 
 * class receive a file path as parameter and check if it compiles.
 *@author Assaf M. Itay A.
 */
public class Sjavac {

	private static final String METHOD_CALL =
			"\\s*([A-Za-z][A-Za-z0-9_]*)\\s*\\(([^\\n]*)\\)\\s*;?\\s*";
	private static final int RETURN=6;
	// large array of all instances
	static ArrayList<ArrayList<Instance>> methodInstanceListByBlock = 
			new ArrayList<ArrayList<Instance>>();
	// list of all instance in main block
	static ArrayList<Instance> mainBlockInstances = new ArrayList<Instance>();

	/**
	 * this method is the main method in which the program gets parameters of
	 * files to compile
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			methodInstanceListByBlock.clear();
			mainBlockInstances.clear();
			methodInstanceListByBlock.add(mainBlockInstances);
			Sjavac parser = new Sjavac();
			parser.parseMainBlock(args[0]);
			parser.parseMethods(args[0]);
			System.out.println("0");

		} catch (NoSuchElementException e) {
			System.err.println(e.getMessage());
			System.err.println("2");
		} catch (CompilerError e) {
			System.out.println("1");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * parse the main block.
	 * make an Instance of every field and method in main block.
	 * skip any other block.
	 * @param path - the string which represents the whole code
	 * @return 0 - if everything is OK
	 * @throws CompilerError 
	 *  
	 */
	public int parseMainBlock(String path) throws CompilerError, 
	BadInputException{

		LineReader reader = new LineReader(path);
		while (reader.hasNext()){//
			String text = reader.next();//the next line to check
			//if line ends with ";"
			if (text.endsWith(";")){
				Instance newInstance =InstanceFactory.createInstance
						(methodInstanceListByBlock, text); 
				//if the instance already exist throw exception
				if (InstanceArrayValidator.instanceNameExistInBlock
						(newInstance, mainBlockInstances)){
					throw new DuplicateInstaceException("instance "+newInstance.
							getName()+" is declared twice in main block");
				}		
				//add this member to the main block array of instances
				mainBlockInstances.add(newInstance);
			}
			//if line ends with "{"
			else if(text.endsWith("{")){
				Instance newInstance =InstanceFactory.createInstance
						(methodInstanceListByBlock, text); 
				//if this instance already exist throw exception
				if (InstanceArrayValidator.instanceNameExistInBlock
						(newInstance, mainBlockInstances)){
					throw new DuplicateInstaceException
					("instance "+newInstance.getName()+
							" is declared twice in main block");
				}
				//add this instance
				mainBlockInstances.add(newInstance);
				methodCheckAndSkip(reader,text);
			} 
			// no method or variable
			else{
				throw new BadLineEndingException("bad line end in main block");
			}
		}
		return 0;
	}

	/**
	 * this function read the methods. ignores every instance in main block
	 * @param path - the string which represents the file	
	 * @return 0 - if everything is OK
	 * @throws CompilerError
	 * @throws NoSuchElementException
	 */
	public int parseMethods(String path) throws CompilerError,
	NoSuchElementException{

		LineReader reader = new LineReader(path);//read a new line
		while (reader.hasNext()){
			String text = reader.next();
			//continue until the end of the block
			if (text.endsWith(";")){
				continue;
			}
			//if reached the end of block
			if (text.endsWith("{")){
				//check if this call is legal
				Instance checkInstace = ValidateBlocks.validateMethodCall(
						methodInstanceListByBlock,text);
				parseBlock(reader,checkInstace,text);
			}
		}
		return 0;
	}

	/**
	 * parse blocks recursively 
	 * @param reader - the file to compile
	 * @param checkInstance - the return type should be returning
	 * @param text - the next line to read
	 * @throws NoSuchElementException
	 * @throws BadInputException
	 * @throws CompilerError
	 */
	private void parseBlock(LineReader reader, Instance checkInstance,
			String text) throws NoSuchElementException, CompilerError{
		
		ArrayList <Instance> blockList = new ArrayList <Instance>();
		blockList = ValidateInstanceValue.updateList(methodInstanceListByBlock,
				blockList, text);
		methodInstanceListByBlock.add(0, blockList);
		//as long as there is a next line to read in the block
		while (reader.hasNext()){
			text = reader.next();
			Instance currInstance;
			String[] splittedText = text.split(" ");

			//new block
			if (text.endsWith("{")){
				if (!ValidateBlocks.validateIfOrWhileBlock(text)){
					throw new BadStructureOfBlockLineException
					("block line doesnt have an if/ while structure"); 
				}
				parseBlock (reader, checkInstance, text);
				continue;
			}

			//end of block
			if (text.endsWith("}")){
				methodInstanceListByBlock.remove(0);
				return;
			}

			//if its a return statement it must end with ";"
			if(text.matches("return.*")){
				text = text.substring(RETURN, text.length()-1);
				text = text.trim();
				if (!ValidateType.typesConsist
						(methodInstanceListByBlock, checkInstance, text)){
					throw new BadInputException(text+"bad input");
				}
				continue;
			}

			if (text.matches(METHOD_CALL)){
				Instance func = InstanceArrayValidator.findInstance
						(methodInstanceListByBlock, splittedText[0]);
				if (func == null){
					throw new CompilerError("DAMN THOSE ERRORS!!!!");
				}
				ValidateInstanceValue.validateMethodArgs
				(methodInstanceListByBlock, func, text);
			}

			// if its a declaration of a new var
			if (ValidateType.isValidInstanceType(splittedText[0])){
				currInstance = InstanceFactory.createInstance
						(methodInstanceListByBlock, text);					
				if (instanceExistInMethod
						(methodInstanceListByBlock,currInstance)){
					throw new DuplicateInstaceException
					("Instance created twice in the same block");					
				}
				blockList.add(currInstance);
				continue;
			}

			// using an existing var (no declaration)
			if (splittedText[0].endsWith("]")){
				if(ValidateArrayValue.checkIndexBounds
						(methodInstanceListByBlock, splittedText[0].substring
								(splittedText[0].indexOf("[")+1, 
										splittedText[0].indexOf("]")))==false)
					throw new MemberDoesNotExistException
					("Index out of bounds at "+splittedText[0]);
				splittedText[0]=(String) splittedText[0].subSequence
						(0, splittedText[0].indexOf("["));
			}
			currInstance = InstanceArrayValidator.
					findInstance(methodInstanceListByBlock, splittedText[0]);

			// case var doesnt exist at all
			if (currInstance == null){
				throw new MemberDoesNotExistException
				("searched for member called "+
						splittedText[1]+" and didnt find it");
			}
			// case var exist
			else{
				if (!InstanceArrayValidator.
						instanceNameExistInBlock(currInstance, blockList)){
					currInstance = currInstance.clone();							
					blockList.add(currInstance);
				}
			}
		}
	}

	/**
	 * will skip every method but check if blocks are legal
	 * @param reader - the whole code
	 * @param text the next line
	 * @throws compileError 
	 */
	private void methodCheckAndSkip(LineReader reader, String text) throws
	CompilerError{
		
		int openBlocks=0;
		do{
			//if the block begins with "{" continue 
			if (text.endsWith("{")){
				openBlocks++;
			}
			//if the block ends with "}" return
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
					throw new NoClosureToParenthesesException
					("No closure to parenthesis");
				}
			}
		}
		while (openBlocks > 0);
	}

	/**
	 * check if instance's name is already in use in current method
	 * @param methodList - the list of method names
	 * @param checkInstance - the instance to compare
	 * @return true if name is in use, false else
	 */
	private boolean instanceExistInMethod(ArrayList<ArrayList<Instance>> 
	methodList, Instance checkInstance){
		
		for (ArrayList<Instance> subList: methodList){
			if (subList.equals(mainBlockInstances)){
				continue;
			}
			if(InstanceArrayValidator.instanceNameExistInBlock
					(checkInstance, subList)){
				return true;
			}
		}
		return false;
	}
	
}

