package oop.ex7.Sjavac.codeRead;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.zip.Adler32;

import oop.ex7.Sjavac.RegexConstants;
import oop.ex7.Sjavac.Type;
import oop.ex7.Sjavac.exception.BadInputException;
import oop.ex7.Sjavac.exception.BadLineEndingException;
import oop.ex7.Sjavac.exception.BadStructureOfBlockLineException;
import oop.ex7.Sjavac.exception.CompilerError;
import oop.ex7.Sjavac.exception.DuplicateInstaceException;
import oop.ex7.Sjavac.exception.IlegalCommentException;
import oop.ex7.Sjavac.exception.IllegalParameterInput;
import oop.ex7.Sjavac.exception.MemberDeclarationException;
import oop.ex7.Sjavac.exception.MemberDoesNotExistException;
import oop.ex7.Sjavac.exception.NoClosureToParenthesesException;
import oop.ex7.Sjavac.instance.Instance;
import oop.ex7.Sjavac.instance.InstanceFactory;
import oop.ex7.Sjavac.validations.InstanceArrayValidator;
import oop.ex7.Sjavac.validations.ValidateBlocks;
import oop.ex7.Sjavac.validations.ValidateInstanceValue;
import oop.ex7.Sjavac.validations.ValidateType;

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
	 */
	public int parseMainBlock(String path){
		try{
			LineReader reader = new LineReader(path);
			while (reader.hasNext()){
				String text = reader.next();
				if (text.endsWith(";")){
					System.err.println(text);
					Instance newInstance =InstanceFactory.createInstance(methodInstanceListByBlock, text); 
					if (InstanceArrayValidator.instanceNameExistInBlock(newInstance, mainBlockInstances)){
						throw new DuplicateInstaceException("instance "+newInstance.getName()+
								" is declared twice in main block");
					}
					ValidateInstanceValue.validateValueOnInstaceCreation(methodInstanceListByBlock, newInstance.getType(), text);
					
					ValidateInstanceValue.assetrtSimpleValue(text);
					mainBlockInstances.add(newInstance);
				}
				else if(text.endsWith("{")){
					System.out.println("func");
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
		}catch (IlegalCommentException e) {
			// TODO Auto-generated catch block
			return 1;
		} catch (MemberDeclarationException e) {
			// TODO Auto-generated catch block
			return 1;
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			return 2;
		} catch (BadInputException e){
			e.getMessage();
			return 1;
		} catch (CompilerError e) {
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
					parseBlock(reader);
				}
			}
		} /**catch(FileNotFoundException e){
			return 2;
		} **/catch (BadInputException e){
			e.getMessage();
			return 1;
		} catch (IlegalCommentException e) {
			// TODO Auto-generated catch block
			return 1;
		} catch (MemberDeclarationException e) {
			// TODO Auto-generated catch block
			return 1;
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			return 1;
		} catch (CompilerError e) {
			e.getMessage()
;			return 1;
		} 
		return 0;
	}

	private void parseBlock(LineReader reader) throws NoSuchElementException, BadInputException, CompilerError{
		ArrayList <Instance> blockList = new ArrayList <Instance>();
		methodInstanceListByBlock.add(0, blockList);
		Matcher m;
		while (reader.hasNext()){
			String text = reader.next();
			Instance currInstance;
			String[] splittedText = text.split(" ");

			//new block
			if (text.endsWith("{")){
				if (!ValidateBlocks.validateIfOrWhileBlock(text)){
					throw new BadStructureOfBlockLineException("block line doesnt have an if/ while structure"); 
				}
				parseBlock (reader);
				continue;
			}

			//end of block
			else if (text.endsWith("}")){
				methodInstanceListByBlock.remove(0);
				return;
			}

			// must end with ";"
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
				}

				// using an existing var (no declaration)
				else{
					currInstance = InstanceArrayValidator.findInstance(methodInstanceListByBlock, splittedText[0]);
					// case var doesnt exist at all
					if (currInstance == null){
						throw new MemberDoesNotExistException
						("searched for member called "+splittedText[1]+" and didnt find it");
					}
					// case var exist
					else{

						// case var exist in outer scope/main block, we want to clone it so it
						// wont affect initiate status.
						if (!InstanceArrayValidator.instanceNameExistInBlock(currInstance, blockList)){
							currInstance = currInstance.clone();							
							blockList.add(currInstance);
						}
						ValidateInstanceValue.validateValueOnInstaceCreation(methodInstanceListByBlock, currInstance.getType(), text);
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










}

