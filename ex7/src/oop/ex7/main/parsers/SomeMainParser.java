package oop.ex7.main.parsers;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.zip.Adler32;

import oop.ex7.main.RegexConstants;
import oop.ex7.main.Type;
import oop.ex7.main.exceptions.BadInputException;
import oop.ex7.main.exceptions.BadLineEndingException;
import oop.ex7.main.exceptions.BadStructureOfBlockLineException;
import oop.ex7.main.exceptions.CompilerError;
import oop.ex7.main.exceptions.DuplicateInstaceException;
import oop.ex7.main.exceptions.IlegalCommentException;
import oop.ex7.main.exceptions.IllegalParameterInput;
import oop.ex7.main.exceptions.MemberDeclarationException;
import oop.ex7.main.exceptions.MemberDoesNotExistException;
import oop.ex7.main.exceptions.NoClosureToParenthesesException;
import oop.ex7.main.instance.Instance;
import oop.ex7.main.instance.InstanceFactory;
import oop.ex7.main.validations.InstanceArrayValidator;
import oop.ex7.main.validations.ValidateArrayValue;
import oop.ex7.main.validations.ValidateBlocks;
import oop.ex7.main.validations.ValidateInstanceValue;
import oop.ex7.main.validations.ValidateType;

/**
 * this class will change, just want to catch the line of CodeReader
 *
 */
public class SomeMainParser {

	ArrayList<ArrayList<Instance>> methodInstanceListByBlock = new ArrayList<ArrayList<Instance>>();
	ArrayList<Instance> mainBlockInstances = new ArrayList<Instance>();
	InstanceFactory factory;

	/**
	 * read from file with LineReader and manage kind of lines
	 * @param path
	 * @return 
	 */
	public SomeMainParser(){
		methodInstanceListByBlock.add(mainBlockInstances);
		factory = new InstanceFactory();
	}

	/**
	 * parse the main block.
	 * make an Instance of every field and method in main block.
	 * skip any other block.
	 * @param path
	 * @return
	 * @throws CompilerError 
	 * @throws CompilerError 
	 * @throws BadInputException 
	 */
	public int parseMainBlock(String path) throws CompilerError, BadInputException{

			LineReader reader = new LineReader(path);
			while (reader.hasNext()){
				String text = reader.next();
				if (text.endsWith(";")){
					Instance newInstance =InstanceFactory.createInstance(methodInstanceListByBlock, text); 
					if (InstanceArrayValidator.instanceNameExistInBlock(newInstance, mainBlockInstances)){
						throw new DuplicateInstaceException("instance "+newInstance.getName()+
								" is declared twice in main block");
					}
//					ValidateInstanceValue.validateValueOnInstaceCreation(methodInstanceListByBlock, newInstance.getType(), text);
					
					ValidateInstanceValue.assetrtSimpleValue(text);
					mainBlockInstances.add(newInstance);
				}
				else if(text.endsWith("{")){
					Instance newInstance =InstanceFactory.createInstance(methodInstanceListByBlock, text); 
					if (InstanceArrayValidator.instanceNameExistInBlock(newInstance, mainBlockInstances)){
						throw new DuplicateInstaceException("instance "+newInstance.getName()+
								" is declared twice in main block");
					}
					mainBlockInstances.add(newInstance);
					methodCheckAndSkip(reader,text);
				} 
				// no method or variable
				else{
					throw new BadLineEndingException("bad line exception in main block");
				}
			}
			return 0;
		
	}

	public int parseMethods(String path) throws CompilerError, NoSuchElementException, BadInputException{
		
			LineReader reader = new LineReader(path);
			while (reader.hasNext()){
				String text = reader.next();
				if (text.endsWith(";")){
					continue;
				}
				if (text.endsWith("{")){
					String[] splitLine = text.split(" ");
					Instance checkInstance = InstanceArrayValidator.findInstance(methodInstanceListByBlock, getName(splitLine[1]).trim());
					parseBlock(reader,checkInstance,text);
				}
			}
		
		return 0;
	}

	/**
	 * parse block
	 * @param reader
	 * @param checkInstance
	 * @param text
	 * @throws NoSuchElementException
	 * @throws BadInputException
	 * @throws CompilerError
	 */
	private void parseBlock(LineReader reader, Instance checkInstance, String text) throws NoSuchElementException, BadInputException, CompilerError{
		ArrayList <Instance> blockList = new ArrayList <Instance>();
		blockList = ValidateInstanceValue.fillList(methodInstanceListByBlock, blockList, text);
		methodInstanceListByBlock.add(0, blockList);
		Matcher m;
		while (reader.hasNext()){
			text = reader.next();
			Instance currInstance;
			String[] splittedText = text.split(" ");

			//new block
			if (text.endsWith("{")){
				if (!ValidateBlocks.validateIfOrWhileBlock(text)){
					throw new BadStructureOfBlockLineException("block line doesnt have an if/ while structure"); 
				}
				parseBlock (reader, checkInstance, text);
				continue;
			}

			//end of block
			else if (text.endsWith("}")){
				methodInstanceListByBlock.remove(0);
				return;
			}

			// must end with ";"
			else if(text.matches("return.*")){
				//TODO MAGIC NUMBER?!?!?!
				text = text.substring(6, text.length()-1);
				text = text.trim();
				if (!Type.typesConsist(methodInstanceListByBlock, checkInstance, text)){
					throw new BadInputException(text+"bad input");
				}
			}
			else{
				
				m = RegexConstants.RegexPatterns.METHOD_CALL.matcher(text);
				if (m.matches()){
					//TODO how to check if it is a func instance?
					Instance func = InstanceArrayValidator.findInstance(methodInstanceListByBlock, splittedText[0]);
					if (func == null){
						throw new CompilerError("DAMN THOSE ERRORS!!!!");
					}
					ValidateInstanceValue.validateMethodArgs(methodInstanceListByBlock, func, text);
				}

				// if its a declaration of a new var
				if (ValidateType.isValidInstanceType(splittedText[0])){
					currInstance = InstanceFactory.createInstance(methodInstanceListByBlock, text);					
					if (instanceExistInMethod(methodInstanceListByBlock,currInstance)){
						throw new DuplicateInstaceException("Instance created twice in the same block");					
					}
					blockList.add(currInstance);
				}

				// using an existing var (no declaration)
				else{
					if (splittedText[0].endsWith("]")){
						if(ValidateArrayValue.checkIndexBounds(methodInstanceListByBlock, splittedText[0].substring(splittedText[0].indexOf("[")+1, splittedText[0].indexOf("]")))==false)
							throw new MemberDoesNotExistException("Index out of bounds at "+splittedText[0]);
						splittedText[0]=(String) splittedText[0].subSequence(0, splittedText[0].indexOf("["));
					}
					currInstance = InstanceArrayValidator.findInstance(methodInstanceListByBlock, splittedText[0]);
					// case var doesnt exist at all
					if (currInstance == null){
						throw new MemberDoesNotExistException
						("searched for member called "+splittedText[1]+" and didnt find it");
					}
					// case var exist
					else{

						//vv case var exist in outer scope/main block, we want to clone it so it
						// wont affect initiate status.
						if (!InstanceArrayValidator.instanceNameExistInBlock(currInstance, blockList)){
							currInstance = currInstance.clone();							
							blockList.add(currInstance);
						}
//						ValidateInstanceValue.validateValueOnInstaceCreation(methodInstanceListByBlock, currInstance.getType(), text);
					}
				}
			}
		}
	}

	/**
	 * will skip every method but check if blocks are legal
	 * @param reader
	 * @param text
	 * @return 
	 * @throws BadInputException 
	 * @throws IlegalCommentException 
	 * @throws NoSuchElementException 
	 */
	private void methodCheckAndSkip(LineReader reader, String text) throws BadInputException, IlegalCommentException{
		int openBlocks=0;
		do{
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
	 * check if instance's name is already in use in current method
	 * @param methodList
	 * @param checkInstance
	 * @return true if name is in use, false else
	 */
	private boolean instanceExistInMethod(ArrayList<ArrayList<Instance>> 
			methodList, Instance checkInstance){
		for (ArrayList<Instance> subList: methodList){
			if (subList.equals(mainBlockInstances)){
				continue;
			}
			if(InstanceArrayValidator.instanceNameExistInBlock(checkInstance, subList)){
				return true;
			}
		}
		return false;
	}
private String getName(String s){
	int start =s.indexOf("(");
	return s.substring(0, start);
}




}

