package oop.ex7.Sjavac.validations;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oop.ex7.Sjavac.Type;
import oop.ex7.Sjavac.exception.BadInputException;
import oop.ex7.Sjavac.exception.VariableNotSimpleInGlobalException;
import oop.ex7.Sjavac.instance.Instance;

public class ValidateInstanceValue {

	private static Pattern VAR_PATTERN = Pattern.compile("([A-Za-z0-9_]*)(\\s*=\\s*([^;]+))?;");
	private static Pattern METHOD_PATTERN = Pattern.compile(
			"([A-Za-z0-9_]*)\\s*\\((.*?)\\)\\s*\\{(.*)",
			Pattern.DOTALL);
	private static Pattern SIMPLE_VALUE = Pattern.compile("\\d.*|\".*\"|true|false");


	public static void validateValueOnInstaceCreation 
	(ArrayList<ArrayList<Instance>> list, Type instanceType, String line){
		Matcher match = VAR_PATTERN.matcher(line);
		if (match.matches()){
			line = manageVar(line);
			instanceType.typesConsist(list, instanceType, line);
		}
		//TODO throw error if not
	}

	public static void validateValueOnInstanceCall
	(ArrayList<ArrayList<Instance>> list, Type instanceType, String line){
		Matcher match = METHOD_PATTERN.matcher(line);
		if (match.matches()){
			String[] paramAsArray = manageMethod(line);
			for (String s:paramAsArray){
				instanceType.typesConsist(list, instanceType, line);
			}
		}
	}

	private static String manageVar(String line){
		int place = line.indexOf("=");
		line = line.substring(place+1, line.length()-1);
		return line.trim();
	}

	public static String[] manageMethod(String line){
		int start = line.indexOf("(");
		int end = line.lastIndexOf(")");
		line = line.substring(start+1, end);
		return line.split(",");
	}

	public static void assetrtSimpleValue(String line) throws BadInputException{
		if (line.contains("=")){
			String subLine = manageVar(line);
			Matcher match = SIMPLE_VALUE.matcher(subLine);
			if (!match.matches()){
				throw new VariableNotSimpleInGlobalException (line + "has a complex assignment in global");
			}
		}
	}


}
