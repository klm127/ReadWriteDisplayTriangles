package edu.mid.readWriteDisplayTriangles;

/*RecordTable wraps the array of records, overwrites it with new records when files are loaded, and (for now), parses
 Strings into record objects when they are read from a file.
*/

//for generating random valued records 
import java.util.Random;
//needed to pass Polygon along apparently?
import javafx.scene.shape.Polygon;

class RecordTable {
	
	private Record recordArray[];
	private String name;
	
	//constructor method
	RecordTable(int size) {
		recordArray = new Record[size];
	}
	
	//returns index size of recordArray
	public int getSize() {
		return recordArray.length;
	}
	public void setName(String s) {
		this.name = s;
		if(Debug.isDebug2() == true) {
			System.out.println("setName called in record table, name set to :" + s);
		}
	}
	public String getName() {
		return this.name;
	}
	
	public void setRecordColor(int index, String color) {
		recordArray[index].setColor(color);
	}
	public String getRecordColor(int index) {
		return recordArray[index].getColor();
	}
	//this is called before a new RecordArray is built
	public void setNewRecordArray(int size) {
		this.recordArray = new Record[size];
		if(Debug.isDebug2() == true) { System.out.println("New Record loaded with size: " + recordArray.length); }
	}
	
	//This method parses a string then passes the values along to a Record object constructor to build the table. Could move this to the Record class and have a Record(string) constructor. 
	public void fillRecordFromString(int index, String text) {
		if(Debug.isDebug2() == true ) { 
			System.out.println("fillRecordFromString called with index :" + index);
		}
		String delim = CONST.DELIM;
		String[] tokens = text.split(delim);
		if(Debug.isDebug2() == true ) {
			for(int i = 0; i < tokens.length; i++) {
				System.out.println("Token + " + i + " is " + tokens[i]); }
		}
		//parse String into other types
		int id = Integer.parseInt(tokens[0]);
		double x = Double.parseDouble(tokens[1]);
		double y = Double.parseDouble(tokens[2]);
		char direction = tokens[3].charAt(0);
		double angle = Double.parseDouble(tokens[4]);
		double adjacent = Double.parseDouble(tokens[5]);
		String colorString = tokens[6];
		String name = tokens[7];
		
		recordArray[index] = new Record(id, x, y, direction, angle, adjacent, name, colorString);
	}
 	public void setRecord(int index, Record record) {
		recordArray[index] = record;
	}
	//returns output from record.String()
	public String getRecordString(int index) {
		return recordArray[index].fileString();
	}	
	
	//returns the Polygon (Triangle) at a given index - for use with Pane/Scene construction in JavaFx
	public Triangle getTrianglePolygon(int index) {
		return recordArray[index].getTriangle();
	}
	
	//fills recordArray with Records based on random number generation. 
	public void generateRandomTable(int seed) {
		Random rand = new Random(seed);
		double x;
		double y;
		char dir;
		double angle;
		double adjacent;	
	
		for(int i = 0; i < recordArray.length; i++) {
			x = rand.nextDouble() * Options.randXrange + Options.randXoffset;
			y = rand.nextDouble() * Options.randYrange + Options.randYoffset;
			dir = __debug__GenerateDirection(rand.nextInt(3));
			angle = rand.nextDouble() * Options.randMaxAngle; 
			adjacent = rand.nextDouble() * Options.randMaxAdj; // how to evaluate min adjacent
			recordArray[i] = new Record(i, x, y, dir, angle, adjacent); //no string arg constructor auto generates names
			if(Debug.isDebug1() == true) {
				System.out.println("Rec:" + i + "-ang:" + Math.round(angle) + "-adj:" + Math.round(adjacent) + "-x:" + Math.round(x) + "-y:" + Math.round(y) + "-dir:"+dir);
			}
		}
		this.name = "Random-Seed:" + Integer.toString(seed) + " Size:" +Integer.toString(recordArray.length);
	}
	
	//debug method which calls each records fileString() method to console 
	public void _debug_printRecordStrings() {
		System.out.println("_DEBUG_ FUNCTION: String output from Records");
		String s;
		for(int i = 0; i < recordArray.length; i++) {
			s = recordArray[i].fileString();
			System.out.println(s);
		}
	}
	
	//debug method - calls a Record method to generate an RGBA value string based on Sin, Cos, and Tan, passes it along until I come up with something better for colors 
	public String generateSinColorString(int index) {
		return recordArray[index].generateSinColorString();
	}
	
	//debug method - generates a direction for use with _debug_GenerateRandomTable()
	public char __debug__GenerateDirection(int n) {
		switch(n) {
			case 0: return 'u';
			case 1: return 'd';
			case 2: return 'l';
			case 3: return 'r';
		}
		return 'd';
	}
	
	//debug method - prints names of all records in table to terminal
	public void _debug_printAllRecordNames() {
		System.out.println("Record names called. Size of record array is " + recordArray.length);
		System.out.println(recordArray[0].toString());
		for(int i = 0; i < recordArray.length; i++) {
			System.out.println(recordArray[i].toString());
		}
	}
	public void _debug_printName() {
		System.out.println("This record table is named: " + this.name);
	}
}