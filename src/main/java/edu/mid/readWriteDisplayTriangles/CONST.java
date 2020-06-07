package edu.mid.readWriteDisplayTriangles;

public final class CONST {
	
	public static final String SUBDIRECTORY = "User-Files";
	public static final String DELIM = ";";
	
	public static final int UI_X = 100;
	public static final int UI_Y = 100;
	public static final int UI_WIDTH = 330;
	public static final int UI_HEIGHT = 550;
	
	public static final int TRI_HEIGHT = 600;
	public static final int TRI_WIDTH = 600;
	
	//fileGenerator,Random Table filler
	public static final int DEFAULT_SEED = 77;
	public static final int DEFAULT_QTY = 55;
	
	public static final double DEFAULT_randXoffset = 0;
	public static final double DEFAULT_randYoffset = 0;
	public static final double DEFAULT_randXrange = TRI_WIDTH;
	public static final double DEFAULT_randYrange = TRI_HEIGHT;
	public static final double DEFAULT_randMaxAngle = 60.9999;
	public static final double DEFAULT_randMaxAdj = TRI_HEIGHT * 0.5;
	public static final double DEFAULT_randMinAdj = TRI_HEIGHT * 0.05;
	
	public static final String STARTING_HIGHLIGHT_COLOR = "#18bbc7";
	
	
	public static final String RED = "#c75048";
	public static final String ORANGE = "#cc9c5e";
	public static final String YELLOW = "#cc9c5e";
	public static final String GREEN = "#1ecc18";
	public static final String LIGHT_BLUE = "#18bbc7";
	public static final String DARK_BLUE = "#1c4b87";
	public static final String PURPLE = "#c73dd9";
	public static final String VIOLET = "#e6196b";
	
	public static final String ABOUT_TEXT = 
	    " ReadWriteDisplayTriangles, by Karl Miller. \n"+
	    "---------------------------------------------\n"+
	    "This program can generate a view of triangles\n"+
	    "based on text file data. Created March 2020\n"+
	    "for professor Haley's Java class.\n";
		
	public static final String ABOUT_CMDS = 
		"Controls\n" +
		" + / -\t\t\t\traise and lower triangles\n" +
		" arrow keys\t\t\tmove selected triangle\n" +
		" shift+direction\t\tmove 100\n" +
		" alt+direction\t\t\tmove 1\n";
		
}

/*
	int UI_WIDTH
	int UI_HEIGHT
	
	int TRI_HEIGHT
	int TRI_WIDTH
	
	String RED 
	String ORANGE 
	String YELLOW 
	String GREEN 
	String LIGHT_BLUE 
	String DARK_BLUE 
	String PURPLE 
	String VIOLET 
}
*/