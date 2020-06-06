// comment
package edu.mid.readWriteDisplayTriangles;

public class MainEntry {	
		
	public static void main(String args[]) {
		for(int i = 0; i < args.length; i++) {
			System.out.println("args["+i+"]="+args[i]);
			if(args[i].equals("DEBUG1")) {
				Debug.setDebug1(true);
				System.out.println("Debug Mode 1 activated : " + Debug.isDebug1());
				System.out.println(Debug.debug1Info());
			}
			if(args[i].equals("DEBUG2")) {
				Debug.setDebug2(true);
				System.out.println("Debug Mode 2 activated : " + Debug.isDebug2());
				System.out.println(Debug.debug2Info());
			}
			if(args[i].equals("DEBUG3")) {
				Debug.setDebug3(true);
				System.out.println("Debug Mode 3 activated : " + Debug.isDebug3());
				System.out.println(Debug.debug3Info());
			}
			if(args[i].equals("DEBUG4")) {
				Debug.setDebug4(true);
				System.out.println("Debug Mode 4 activated : " + Debug.isDebug4());
				System.out.println(Debug.debug4Info());
			}
			if(args[i].equals("DEBUG5")) {
				Debug.setDebug5(true);
				System.out.println("Debug Mode 5 activated : " + Debug.isDebug5());
				System.out.println(Debug.debug5Info());
			}
			if(args[i].equals("DEBUG6")) {
				Debug.setDebug6(true);
				System.out.println("Debug Mode 6 activated : " + Debug.isDebug6());
				System.out.println(Debug.debug6Info());
			}
		}
		App app = new App();
		CustomEventHandler c = new CustomEventHandler(app);
		//nothing like a little circular dependencies to put a fork in your code
		app.loadLoadHandler(c);
		app.execute(args);
	}

}

