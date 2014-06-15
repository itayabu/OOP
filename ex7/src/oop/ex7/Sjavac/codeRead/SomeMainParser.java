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
	 */
	public int parseMainBlock(String path){
		try{
			LineReader reader = new LineReader(path);
			while (reader.hasNext()){
				String text = reader.next();
				if (text.endsWith(";")){
					System.err.println(text);
					Instance newInstance =factory.createInstance(text); 
					if (instanceNameExistInBlock(newInstance, mainBlockInstances)){
						throw new DuplicateInstaceException("instance "+newInstance.getName()+
								" is declared twice in main block");
					}
					mainBlockInstances.add(newInstance);
					//TODO check legal regexes
				}
				else if(text.endsWith("{")){
					System.out.println("func");
					Instance newInstance =factory.createInstance(text); 
					if (instanceNameExistInBlock(newInstance, mainBlockInstances)){
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
					parseBlock(reader);
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
		methodInstanceListByBlock.add(1, blockList);
		while (reader.hasNext()){
			String text = reader.next();
			Instance currInstance;
			String[] splittedText = text.split(" ");

			//new block
			if (text.endsWith("{")){
				//TODO: check if valid
				parseBlock (reader);
				continue;
			}

			//end of block
			else if (text.endsWith("}")){
				methodInstanceListByBlock.remove(1);
				return;
			}

			// must end with ";"
			else{
				
//				//TODO if its a function
//				if (true){
//					Instance func = findInstance(methodInstanceListByBlock, splittedText[1]);
//				}
				
				// if its a declaration of a new var
				if (ValidateType.isValidInstanceType(splittedText[0])){
					currInstance = InstanceFactory.createInstance(text);					
					if (instanceExistInMethod(methodInstanceListByBlock,currInstance)){
						throw new DuplicateInstaceException("Instance created twice in the same block");					
					}
				}
				
				// using an existing var (no declaration)
				else{
					currInstance = findInstance(methodInstanceListByBlock, splittedText[1]);
					// case var doesnt exist at all
					if (currInstance == null){
						throw new MemberDoesNotExistException
						("searched for member called "+splittedText[1]+" and didnt find it");
					}
					// case var exist
					else{
						
						// case var exist in outer scope/main block, we want to clone it so it
						// wont affect initiate status.
						if (!instanceNameExistInBlock(currInstance, blockList)){
							currInstance = currInstance.clone();							
							blockList.add(currInstance);
						}
						//TODO check if HASAMA is legal
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
	private boolean instanceNameExistInBlock(Instance checkInstance, ArrayList<Instance> instanceInBlock){
		for (Instance instance:instanceInBlock){
			if (checkInstance.getName().equals(instance.getName())){
				return true;
			}
		}
		return false;
	}

	/**
	 * check if instance's name is already in use in current method
	 * @param methodList
	 * @param checkInstance
	 * @return true if name is in use, false else
	 */
	private boolean instanceExistInMethod(ArrayList<ArrayList<Instance>> methodList, Instance checkInstance){
		for (ArrayList<Instance> subList: methodList){
			if (subList.equals(mainBlockInstances)){
				continue;
			}
			if(instanceNameExistInBlock(checkInstance, subList)){
				return true;
			}
		}
		return false;
	}

	/**
	 * check if instance with a given name exist in code.
	 * check in a given method list and in the main list
	 * @param list
	 * @param s
	 * @return instance with the given name if exist, null else.
	 */
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

