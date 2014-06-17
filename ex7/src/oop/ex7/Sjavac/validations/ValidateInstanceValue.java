package oop.ex7.Sjavac.validations;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oop.ex7.Sjavac.Type;
import oop.ex7.Sjavac.instance.Instance;

public class ValidateInstanceValue {
	
	private static Pattern VAR_PATTERN = Pattern.compile("([A-Za-z0-9_]*)(\\s*=\\s*([^;]+))?;");
	private static Pattern METHOD_PATTERN = Pattern.compile(
            "([A-Za-z0-9_]*)\\s*\\((.*?)\\)\\s*\\{(.*)",
            Pattern.DOTALL);
	
	
	public static void validateValueOnInstace (ArrayList<ArrayList<Instance>> list,
			Instance instance, String line){
		Type instanceType = instance.getType();
		Matcher match = VAR_PATTERN.matcher(line);
		if (match.matches()){
			line = manageVar(line);
			instanceType.typesConsist(list, instance, line);
		}
		match = METHOD_PATTERN.matcher(line);
		if (match.matches()){
			
		}
		
	}

	private static String manageVar(String line){
		int place = line.indexOf("=");
		line = line.substring(place+1, line.length()-1);
		return line.trim();
	}

}
