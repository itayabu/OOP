package oop.ex6.filescript;

import java.io.File;

/**
 * Open a folder and get its files
 */
public class FolderParser {
	private File folder;
	private static File[] fileList;
	/**
	 * basic constructor- open a folder.
	 * @param folderPath - address of folder
	 */
	public FolderParser(String folderPath)	 {

		try{
		folder = new File(folderPath);
		fileList = folder.listFiles();
		}
		// as explained in exercise guide- we can assume folder address is 
		// valid and its content is valid.
		catch(NullPointerException e){
			System.exit(-1);
		}
		
	}
/**
 * return folder's files arranged in an array.
 * @return folder's files arranged in an array
 */
	public static File[] getFileList(){
		return fileList;
	}



}
