import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import oop.ex7.Sjavac.codeRead.SomeMainParser;
import oop.ex7.Sjavac.exception.BadInputException;
import oop.ex7.Sjavac.instance.FuncInstance;
import oop.ex7.Sjavac.instance.FieldInstance;


public class Dummy {
	static String itay = "ITAY";
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args){
		
		//TODO: change your path!
		SomeMainParser parser = new SomeMainParser();
		int a=parser.parseMainBlock("C:/Users/Itay/git/OOP/ex7/mytest/test007.txt");
		int b=parser.parseMethods("C:/Users/Itay/git/OOP/ex7/mytest/test007.txt");
		System.out.println(a+" "+b);
		
		
//		String[] check = {"int","bla","54","4","a","aa"};
//		FuncInstance a = new FuncInstance(check);
//		System.out.println(a.getName());
//		System.out.println(a.getType());
//		System.out.println(a.isInitialized());
//		String[] aa= a.getArgs();
//		for (int i=0; i< aa.length; i++){
//			System.out.println(aa[i]);
//		}
	}
}
