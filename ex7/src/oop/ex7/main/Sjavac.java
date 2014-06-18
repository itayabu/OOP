package oop.ex7.main;

import java.util.NoSuchElementException;

import oop.ex7.main.exceptions.BadInputException;
import oop.ex7.main.exceptions.CompilerError;
import oop.ex7.main.exceptions.IlegalCommentException;
import oop.ex7.main.exceptions.MemberDeclarationException;
import oop.ex7.main.parsers.SomeMainParser;

public class Sjavac {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
		SomeMainParser parser = new SomeMainParser();
		int a=parser.parseMainBlock(args[0]);
		int b=parser.parseMethods(args[0]);
		System.out.println("0");
	}catch (IlegalCommentException e) {
		System.out.println("1");
		System.out.println(e.getMessage());
	} catch (MemberDeclarationException e) {
		System.out.println("1");
		System.out.println(e.getMessage());
	} catch (NoSuchElementException e) {
		System.out.println(e.getMessage());
		System.out.println("2");
		
	} catch (BadInputException e){
		System.out.println("1");
		System.out.println(e.getMessage());
	} catch (CompilerError e) {
		System.out.println("1");
		System.out.println(e.getMessage());
	}

	}

}
