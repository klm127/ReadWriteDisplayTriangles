//debug class
package edu.mid.readWriteDisplayTriangles;

//this class is mostly just a wrapper for boolean flags which is compiled first so all other classes can check the static booleans
class Debug {
	//array/triangle logic
	private static boolean debugMode1 = false;
	//files
	private static boolean debugMode2 = false;
	//colors
	private static boolean debugMode3 = false;
	//FileGeneratorUI
	private static boolean debugMode4 = false;
	//Loading Triangles from Pane to Record
	private static boolean debugMode5 = false;
	//TrianglePane controls
	private static boolean debugMode6 = false;
	
	
	
	//getters
	public static boolean isDebug1() {
		return debugMode1;
	}
	
	public static boolean isDebug2() {
		return debugMode2;
	}
	
	public static boolean isDebug3() {
		return debugMode3;
	}
	
	public static boolean isDebug4() {
		return debugMode4;
	}
	public static boolean isDebug5() {
		return debugMode5;
	}
	public static boolean isDebug6() {
		return debugMode6;
	}
	
	//and setters
	public static void setDebug1(boolean val) {
		debugMode1 = val;
	}
	
	public static void setDebug2(boolean val) {
		debugMode2 = val;
	}
	
	public static void setDebug3(boolean val) {
		debugMode3 = val;
	}
	
	public static void setDebug4(boolean val) {
		debugMode4 = val;
	}
	public static void setDebug5(boolean val) {
		debugMode5 = val;
	}
	public static void setDebug6(boolean val) {
		debugMode6 = val;
	}
	
	public static String debug1Info() {
		return "--Debug 1 is used for checking Triangle logic.";
	}
	
	public static String debug2Info() {
		return "--Debug 2 is used for checking File reading and writing logic.";
	}
	
	public static String debug3Info() {
		return "--Debug 3 is used for checking Color logic.";
	}
	
	public static String debug4Info() {
		return "--Debug 4 is used for checking FileGeneratorUI stuff.";
	}
	public static String debug5Info() {
		return "--Debug 5 is used for checking Triangle UI stuff.";
	}
	public static String debug6Info() {
		return "--Debug 6 is used for checking things, especially events, having to do with the TrianglePaneUI";
	}
	
}
