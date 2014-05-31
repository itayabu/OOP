package oop.ex6.filescript;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * receive directory and command file, and print files in directory
 *  according to commands in the commend file
 */
public class MyFileScript {

	/**
	 * receive dir and command file and print files in dir according to commands.
	 * @param args source directory and command file
	 */
	public static void main(String[] args) {
		
		FileParser parse = new FileParser();
		ArrayList<Section> list= null;
		
		// parse command file to sections
		try {
			list = parse.parseFile(args[1]);
		} catch (IOException e) {
			System.err.println("ERROR");
			return;
		}

		// print all sections
		new FolderParser (args[0]);
		File[] listOfFiles= FolderParser.getFileList();
		for (Section s: list){
			printSection(s.getSection(listOfFiles));
		}
	}
	
	/**
	 * print every file in section
	 * @param list
	 */
	private static void printSection(ArrayList<File> list){
		for (File temp:list){
			System.out.println(temp.getName());
		}
	}
}
