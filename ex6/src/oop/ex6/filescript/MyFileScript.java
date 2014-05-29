package oop.ex6.filescript;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MyFileScript {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileParser parse = new FileParser();
		ArrayList<Section> list= null;
		try {
			list = parse.parseFile(args[1]);
		} catch (IOException e) {
			System.out.println("ERROR");
			return;
		}

		new FolderParser (args[0]);
		for (Section s: list){
			try{
			printSection(s.getSection(FolderParser.getFileList()));
			}
			catch(NullPointerException e){
				System.out.println("ERROR");
				return;
			}
		}

	}
	private static void printSection(ArrayList<File> list){
		for (File temp:list){
			System.out.println(temp.getName());
		}

	}

}
