package oop.ex6.filescript;

import java.io.*;
import java.util.ArrayList;

import oop.ex6.filescript.sectionexceptions.NoFilterSubSectionException;
import oop.ex6.filescript.sectionexceptions.NoOrderSubSectionException;

/**
 * parse a file to sections of filter/order
 *
 */
public class FileParser {
	private int lineNum;
	private ArrayList<Section> toReturn = new ArrayList<Section>();
	
	/**
	 * default constructor
	 */
	public FileParser(){
	}

	/**
	 * open and parse file to sections
	 * @param filePlace adress of the file
	 * @return ArrayList of sections
	 * @throws IOException
	 */
	public ArrayList<Section> parseFile (String filePlace) throws IOException{
		lineNum=0;
		File file = new File (filePlace);
		Section sec;
		String text = null;
		
		// start reading from file
		// (the try is to close BufferReader in any case)
		try(BufferedReader reader = new BufferedReader(new FileReader (file))){
			text = reader.readLine();
			lineNum++;
			
			// start of a new section
			while (text != null){
				sec = new Section();
				//first line is FILTER
				if (!text.equals("FILTER")){
					throw new NoFilterSubSectionException();
				}
				
				// second line must be FILTER parameters
				text =reader.readLine();
				lineNum++;
				sec.addFilter(text, lineNum);
				text = reader.readLine();
				lineNum++;
				
				// third line must be ORDER
				if (text == null || !text.equals("ORDER") ){
					throw new NoOrderSubSectionException();
				}
				text =reader.readLine();
				lineNum++;
				
				// forth line may be ORDER parameters, 
				// the start of next section, or not exist
				if (text == null || text.equals("FILTER")){					
					sec.addOrder("", lineNum);
				}
				else{
					sec.addOrder(text, lineNum);
					text =reader.readLine();
					lineNum++;
				}
				// end of this section -manage the arrayList
				toReturn.add(sec);
			}
			return toReturn;
		}
	}
}
