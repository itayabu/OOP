package oop.ex7.Sjavac.instance;

import java.util.*;

import oop.ex7.Sjavac.codeRead.LineReader;
import oop.ex7.Sjavac.exception.DoubledMemberException;
import oop.ex7.Sjavac.exception.FinalMemberNoValueException;
import oop.ex7.Sjavac.exception.IlegalCommentException;
import oop.ex7.Sjavac.exception.IlegalNameException;
import oop.ex7.Sjavac.exception.NestedMethodDeclatationException;
import oop.ex7.Sjavac.exception.UnknownTypeException;

/**
 * this class has a method that returns a map of all the global members and methods in file <i>file</i>
 * @author Assaf H. Itay A.
 */
public class Globals {

	/**
	 * returns all the global members and methods in a given file
	 * @param file the file to return values from
	 * @return all the global members and methods declared in <i>file</i>
	 * @throws IlegalCommentException if illegal comment appears 
	 * @throws FinalMemberNoValueException if a global final member is declared without assigning it a value
	 * @throws DoubledMemberException if a member declared twice
	 * @throws UnknownTypeException if a member declaration with illegal type exist
	 * @throws IlegalNameException if a name is illegal
	 * @throws NestedMethodDeclatationException if a declaration of method appears inside another method
	 */
	public static Map<String, Member> getGlobals(LineReader file)
				throws IlegalCommentException, FinalMemberNoValueException, DoubledMemberException,
				UnknownTypeException, IlegalNameException, NestedMethodDeclatationException {
		
		Map<String, Member> signatureMap = new HashMap<String, Member>();
		while(file.hasNext()) {
			String line=file.next();
			switch (ExpressionType.whatExpression(line)) {
			//if line is method
			case METHOD:
				getMethodSignature(line, signatureMap);
				skipLines(file); //skip to the end of the method
				break;
			//if line is a member global declaration
			case MEMBER_DECLARATION:
				getMemberSignature(line, signatureMap);

			default:
				break;
			}
		}
		return signatureMap;
	}

	//get the signature of a member
	private static void getMemberSignature(String line,Map<String, Member> memMap)
		throws FinalMemberNoValueException, UnknownTypeException, DoubledMemberException, IlegalNameException {
		//prepare the line by turning it to array
		String[] memberLine=line.split(" ");
		int current=0;
		//if the member is not declared with a type throw exception
		if(!memberLine[current].matches(Types.TYPES))
			throw new UnknownTypeException("No such type:"+memberLine[current]+"\nat line:"+line);
		current++;//proceed
		//if the name is not of legal form throw exception
		if(!memberLine[current].matches(ExpressionType.NAME+";?"))
			throw new IlegalNameException("choose another name to your variable:"+memberLine[current]);
		//if only declared and no value was placed
		if(memberLine[current].endsWith(";"))
			memberLine[current]=memberLine[current].substring(0, memberLine[current].length()-1);
		//check if the member already exist in the map, if it does throw exception
		if(memMap.containsKey(memberLine[current]))
			throw new DoubledMemberException("You can't declare two members with the same name: "+memberLine[current]+"\n"+line);
		//make the Type of this member
		Types type=Types.valueOf(memberLine[current-1].toUpperCase());
		//insert the member to the map
		memMap.put(memberLine[current], new Member(memberLine[current],type,false));

	}

	//get the signature of a method
	private static void getMethodSignature(String line,Map<String,Member> map)
			throws DoubledMemberException, IlegalNameException {
		//make the return value of the method a Type
		Types type=Types.valueOf(line.substring(0, line.indexOf(' ')).toUpperCase());
		//make the method name after the space and before the brackets
		String name=line.substring(line.indexOf(' ')+1, line.indexOf('('));
		//make the parameters of the method a string
		String params=line.substring(line.indexOf('(')+1, line.indexOf(')'));
		String signature=name+"(";//make the signature of the method
		for(String par:params.split(", ?")) {//divide the parameters
			String[] parArray=par.trim().split(" ");//make the parameter an array
			//if this parameter is not of legal form throw exception
			if((parArray.length!=2 || (!parArray[0].matches(Types.TYPES)) ||
					(!parArray[1].matches(ExpressionType.NAME)))&&par.length()!=0)
				throw new IlegalNameException("Form of parameter "+par+" at line\n"+line+" is ilegal");
			//add to method signature
			signature+=parArray[0].toUpperCase()+",";
		}
		//remove the last comma and add closing bracket and finish the method signature
		signature=signature.replaceAll(",$", "")+")";
		//if signature already exist in map throw exception
		if(map.containsKey(signature))
			throw new DoubledMemberException("two methods can't have the same name and parameters\n"+line);
		//insert to map 
		map.put(signature, new Member(signature,type,true));
		map.get(signature).initialize();
	}

	
	/**
	 * skip lines to the end of the block/scope
	 * @param file the file to check its lines
	 * @throws IlegalCommentException
	 * @throws NestedMethodDeclatationException
	 */
	private static void skipLines(LineReader file) throws IlegalCommentException, NestedMethodDeclatationException {
		String line;
		do {
			line=file.next();

			switch(ExpressionType.whatExpression(line)) {
			//in case block begins
			case BLOCK_START:
				//if block just began do it again
				skipLines(file);
				break;
			//in case block ends return, skipped all lines in block
			case BLOCK_END:
				return;
			//in case a method is declared inside another method throw exception
			case METHOD:
				throw new NestedMethodDeclatationException("can't declare a method inside another\n"+line);

			default:
				break;
			}
			//as long as the line isn't an end of block sign( } ), continue
		}while(!line.equals(ExpressionType._END));

	}

}