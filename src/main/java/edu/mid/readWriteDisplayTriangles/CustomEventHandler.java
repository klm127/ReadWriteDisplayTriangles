//Custom Event Handler
package edu.mid.readWriteDisplayTriangles;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

//this class should only be used by App. App passes itself on CEH construction. CEH parses Events into strings and calls appropriate App methods.
class CustomEventHandler implements EventHandler<ActionEvent> {
	
	private App caller;
	
	//When app calls this event handler, it passes itself to it so handler can access all methods
	CustomEventHandler(App app){
		super();
		this.caller = app;
		if(Debug.isDebug4() == true) { System.out.println("EventHandler constructor called."); }
	}
	
	@Override
	public void handle(ActionEvent e){
		String parsedEvent = parseEvent( e.getSource().toString() );
		if(Debug.isDebug4() == true) {
			System.out.println("Event registered.");
			System.out.println("Source = " + e.getSource());
			System.out.println("Source to string = " + e.getSource().toString() );
			System.out.println("Parsed string is = " + parsedEvent);
		}
		if(parsedEvent.equals("Show")) {
			if(Debug.isDebug4() == true) {
				System.out.println("SHOW button detected.");
			}
			caller.makeTriangleStage();
		}
		if(parsedEvent.equals("Generate Random Triangles")) {
			caller.loadRandomTriangles();
		}
		if(parsedEvent.equals("Save")) {
			caller.saveFile();
		}
		if(parsedEvent.equals("Load")) {
			caller.loadFile();
		}
		if(parsedEvent.equals("close-All")) {
			caller.closeAllTriangleStages();
		}
		if(parsedEvent.equals("closeNotLast")) {
			caller.closeNotLast();
		}
		if(parsedEvent.equals("Colorize")) {
			caller.colorize();
		}
		if(parsedEvent.equals("LOAD TO RECORD TABLE")) {
			caller.loadRecordsFromTrianglePaneForSaving(e.getTarget());
		}
		if(parsedEvent.equals("Rename")) {
			caller.rename();
		}
	}
	
	public static String parseEvent(String s) {
		String delim = "'";
		String[] tokens = s.split(delim);
		if(Debug.isDebug4() == true ) {
			System.out.println("Debug for Event Handler parsing");
			for(int i = 0; i < tokens.length; i++) {
				System.out.println("Token + " + i + " is " + tokens[i]); }
		}
		return tokens[1];
	}
}



