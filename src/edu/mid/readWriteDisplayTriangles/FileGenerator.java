//File Generator

/* A (may be static in the future) class which only handles the direct interactions with the files. RecordTable handles parsing
*/

//producing an error message that this in an auxillary class, maybe because there are no properties? 
package edu.mid.readWriteDisplayTriangles;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.FXCollections;

class FileGenerator {
	
	public void writeRecordTableToTextFile(RecordTable recordTable, Path filePath) throws Exception{
		
		//Files.write() takes an iterable object, such as an Arraylist. Elements in the list must implement the charSequence interface, which Strings do.
		
		Path directory = Paths.get(CONST.SUBDIRECTORY);
		if(!(Files.isDirectory(directory))) {
			Files.createDirectory(directory);
		}
		if(!(Files.exists(filePath))) {
			Files.createFile(filePath);
		}
		
		int recSize = recordTable.getSize();
		
		if(Debug.isDebug2() == true) {
			System.out.println("recordTable size is = " + recordTable.getSize());}
		ArrayList<String> stringArray = new ArrayList<String>(recSize + 1); //one extra for file properties
		
		stringArray.add(0, recordTable.getName());			
		
		// have to offset to account for first line file name
		for(int i = 1; i < (recSize + 1); i++) {
			if(Debug.isDebug2() == true) { 
				System.out.println("i is = to: " + i);
			}
			stringArray.add(i, recordTable.getRecordString(i - 1));
		}
		try {
			Files.write(filePath, stringArray);
		}
		catch(Exception e) {
			e.printStackTrace();
			if(Debug.isDebug2() == true) { System.out.println("Exception in fileGenerator writeRecordTable."); }
			throw e;
		}
			
		
	}
	
	//takes a string representing the file name as a parameter, recordTable parses
	public void readTextFileToRecordTable(RecordTable recordTable, Path filePath) throws Exception{
		
		List<String> stringArray = new ArrayList<String>();
		/* List<String> stringArray = new ArrayList<String>(recordTable.getSize()+1); */

		try{
			stringArray = Files.readAllLines(filePath);
		}
		catch(Exception e) {
			if(Debug.isDebug2() == true ) {System.out.println("FileGenerator - Error reading file.");};
			throw e;
		}
		/* if(Debug.isDebug2() == true ) {
			System.out.println("Debug 2 - print from read String ArrayList");
			for(String each : stringArray){
				System.out.println(each);
			}
		} */
		
		int RTsize = stringArray.size() - 1; //first line is file name
		recordTable.setNewRecordArray(RTsize);
		if(Debug.isDebug2() == true) { System.out.println("stringArray(0) = " + stringArray.get(0) + "\nStringArray(1) = " + stringArray.get(1)); }
		recordTable.setName(stringArray.get(0));
		
		for(int i = 1; i < stringArray.size(); i++) {
			recordTable.fillRecordFromString((i-1),stringArray.get(i));
		}
		
	}
		
}