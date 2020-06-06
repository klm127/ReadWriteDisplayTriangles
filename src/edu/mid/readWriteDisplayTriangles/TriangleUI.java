//TriangleUI

//extends BorderPane

//will contain the TriangleTopMenu instance

//will contain a TrianglePane instance

//will contain edit tools on the left size

//will contain tooltips on the bottom

//will perform event handling for editing the trianglePane 


package edu.mid.readWriteDisplayTriangles;

import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;

//import EventHandler 


class TriangleUI extends BorderPane implements EventHandler<KeyEvent>{
	
	
	private TrianglePane trianglePane;
	private RecordTable recordTable;
	private TriangleTopMenu triangleTopMenu;
	private TriangleTools triangleTools;
	private EventHandler<ActionEvent> loadHandler;
	
	//place holder so I can pass basically un-initialized versions
	TriangleUI() {
	}
	
	TriangleUI(RecordTable recordTable, EventHandler<ActionEvent> loadHandler) {
		super();
		this.recordTable = recordTable;
		this.loadHandler = loadHandler;
		assemble();
	}
	
	@Override
	public void handle(KeyEvent e) {
		if(Debug.isDebug6() == true) { 
			System.out.println("Key Event detected in TriUI. + getText() : " + e.getText());
			System.out.println("getCharacter() : "+  e.getCharacter() );
			System.out.println("e.getCode().getName() : " + e.getCode().getName());
			//remember Keycode.KEYNAME constants
			
		}
		
		//get some properties of the key event, set move modifer
		double moveModifier;
		if(e.isShiftDown() == true ) {
			moveModifier = 50.0;
		} else if(e.isAltDown() == true ) {
			moveModifier = 1.0;
		} else {
			moveModifier = 10.0;
		}
		KeyCode keycode = e.getCode();
		String keyname = keycode.getName();
		String keychar = e.getCharacter();
		
		//move triangles cartesian
		switch( keycode ) {
			case UP:
				trianglePane.focusMove(0.0, (-1.0 * moveModifier) );
				break;
			case DOWN:
				trianglePane.focusMove(0.0,moveModifier);
				break;
			case LEFT:
				trianglePane.focusMove( (-1.0 * moveModifier) , 0.0);
				break;
			case RIGHT: 
				trianglePane.focusMove(moveModifier, 0.0);
				break;
		}
		//move triangles z and (ultimately) record order 
		if( keyname == "Minus" || keychar == "-" || keyname == "Subtract") {
			trianglePane.focusChange(-1);
		}
		if( keyname == "Add" || keyname == "Equals") {
			trianglePane.focusChange(1);
		}
		//rotate the triangles 
		if( keyname == "W" ) {
			trianglePane.focusRotate('u');
		}
		if( keyname == "A" ) {
			trianglePane.focusRotate('l');
		}
		if( keyname == "S" ) {
			trianglePane.focusRotate('d');
		}
		if( keyname == "D" ) {
			trianglePane.focusRotate('r');
		}
		if( keyname == "Open Bracket" ) {
			trianglePane.focusRotate(1);
		}
		if( keyname == "Close Bracket" ) {
			trianglePane.focusRotate(3);
		}
		if( keyname == "T" ) {
			trianglePane.setTool("translate");
		}
		if( keyname == "F" ) {
			if(Debug.isDebug3() == true) { System.out.println("Fill tool selected."); }
			String dumpColor = triangleTopMenu.getDumpColor();
			trianglePane.setFillColor(dumpColor);
			trianglePane.setTool("fill");
		}
		if( keyname == "P" ) {
			trianglePane.setTool("picker");
		}
	}
	
	
	public void assemble() {
		
		triangleTopMenu = new TriangleTopMenu(loadHandler);
		triangleTopMenu.loadKeyTranslator(this);
		triangleTools = new TriangleTools();
		triangleTools.loadKeyTranslator(this);
		trianglePane = new TrianglePane();
		trianglePane.loadRecordTable(recordTable);
		trianglePane.publicPaint();
		
		setCenter(trianglePane);
		setTop(triangleTopMenu);
		setLeft(triangleTools);
		//insets - top, right, bottom, left
		
		trianglePane.setOnKeyPressed(this);
		setOnKeyPressed(this);
		setOnKeyTyped(this);
	}
	public void loadRecordTable(RecordTable recordTable) {
		this.recordTable = recordTable;
		trianglePane.loadRecordTable(recordTable);
	}
	public RecordTable getRecordTable() {
		return recordTable;
	}
	
	public void setToRecordTable() {
		if(Debug.isDebug5()==true) { System.out.println("setToRecordTable called in TriangleUI."); }
		trianglePane.setToRecordTable();
	}

}