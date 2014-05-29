package oop.ex6.filescript;

import java.io.File;

public class FolderParser {
	File folder;
	static File[] fileList;
	public FolderParser(String folderPath){
		try{
			folder = new File(folderPath);	
			fileList = folder.listFiles();			
		}
		catch(NullPointerException e){
			System.out.println("no directory");
			System.exit(-1);
		}
	}
	
	protected static File[] getFileList(){
		return fileList;
	}
	
	
	
}
