//this code uses javafx to display triangles from a recordTable
package edu.mid.readWriteDisplayTriangles;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import javafx.scene.Cursor;

class TrianglePane extends Pane implements EventHandler<MouseEvent>{
	
	private RecordTable recordTable;
	private ArrayList<Triangle> pList = new ArrayList<Triangle>();
	private Triangle focusTri;
	private String tool;
	private String dumpColor;
	
	private double mouseDragX;
	private double mouseDragY;
	
	//Record Table must be loaded prior to painting
	private void paint() {
		if(Debug.isDebug2()){
			System.out.println("Triangle pane paint called, recordTable index size is " + recordTable.getSize() );
		}
		Triangle tri;
		//Color c;
		getChildren().clear();
		for(int i = 0; i < recordTable.getSize(); i++) {
			tri = recordTable.getTrianglePolygon(i);
			//if(Debug.isDebug3()) {System.out.println("color generated is :" + recordTable.generateSinColorString(i)); }
			//c = Color.web(recordTable.generateSinColorString(i));
			//tri.setStroke(c);
			//tri.setFill(c);
			tri.setOnMousePressed(this);
			tri.setOnMouseEntered(this);
			tri.setOnMouseExited(this);
			tri.setOnMouseDragged(this);
			getChildren().add(tri);
			pList.add(tri);
			setTool("translate");
		}
	}
	
	public void publicPaint() {
		paint();
	}
	
/* 	public void addTriangle() {
		tri.setOnAction(self);
	} */
	public void focusChange(int change) {
		int i = pList.indexOf(focusTri);
		int newI = i + change;
		if(Debug.isDebug6() == true) { System.out.println(" TrianglePane: focus change of " + change + " applied, focusTri index is " + i);}
		if(newI > (-1) && newI < (pList.size()) )
		{
			pList.remove(focusTri);
			getChildren().remove(focusTri);
			pList.add(newI, focusTri);
			getChildren().add(newI, focusTri);
			if(Debug.isDebug6() == true) { System.out.println("                        newI is " + newI + " succesful change.");
			}
		}
	}

	public void focusMove(double x0, double y0) {
		if(focusTri!=null) {
			double oldX = focusTri.getX();
			double oldY = focusTri.getY();
			double newX = oldX + x0;
			double newY = oldY + y0;
			focusTri.setX(newX);
			focusTri.setY(newY);
			focusTri.relocate(newX, newY);
		}
	}
	
	public void focusRotate(char newdir) {
		focusTri.setDirection(newdir);
	}
	public void focusRotate(int rotations) {
		focusTri.rotate90Left(rotations);
	}
	public void setTool(String toolname) {
		if(toolname == "translate" ) {
			this.tool = toolname;
			setCursor(Cursor.OPEN_HAND);
		}
		else if(toolname == "fill" ) {
			this.tool = toolname;
			setCursor(Cursor.CROSSHAIR);
		}
		else if(toolname == "picker") {
			this.tool = toolname;
			setCursor(Cursor.N_RESIZE);
		}
	}
	public void setFillColor(String fillColor) {
		dumpColor = fillColor;
	}
	
	public void setToRecordTable() {
		int size = getChildren().size();
		if(Debug.isDebug5() == true) { System.out.println("setToRecordTable() called in TrianglePane. Size is " + size); }
 		try{			
			recordTable.setNewRecordArray(size);
			System.out.println("RecordTable size is now " + recordTable.getSize());
			for(int i = 0; i < size; i++) {
				if(Debug.isDebug5() == true) {System.out.println("Observable list " + i + " is " + getChildren().get(i).toString() ); }
				recordTable.setRecord(i, pList.get(i).makeRecord());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadRecordTable(RecordTable recordTable) {
		this.recordTable = recordTable;
	}
	
	public void newFocus(Triangle tri) {
		if(focusTri != null) {
			focusTri.setStroke(null);
		}
		if(focusTri == tri) {
			focusTri = null;
		}
		else {
			focusTri = tri;
			focusTri.setStroke(Color.web(Options.highlightColor));
			focusTri.setStrokeWidth(3.0);
		}
	}
	
	
	@Override
	public void handle(MouseEvent e) {
		/* get the event name and use the ArrayList to find the right triangle. */
		String eName = e.getEventType().getName();
		Object triSrc = e.getSource();
		int trindex = getChildren().indexOf(triSrc);
		Triangle tri = pList.get(trindex);
		
		if(Debug.isDebug6() == true) {
			System.out.println("Event name: " + eName);
			System.out.println("Triangle debug String " + tri._debugString_());
			tri._debug_printInfo();
		}
		switch(eName) {
			case "MOUSE_PRESSED":
				newFocus(tri);
				if(Debug.isDebug1() == true) { System.out.println("Direction is: " + tri.getDirection() + " x:" + tri.getX() + " y:" + tri.getY());}
				mouseDragX = e.getSceneX();
				mouseDragY = e.getSceneY();
				if(tool == "fill") {
					if(Debug.isDebug3() == true) { System.out.println("Mouse pressed with fill tool selected."); }
					tri.setColor(dumpColor);
				}
				break;
			case "MOUSE_ENTERED":
				break;
			case "MOUSE_EXITED":
				break;
			case "MOUSE_MOVED":
				mouseDragX = e.getSceneX();
				mouseDragY = e.getSceneY();
				break;
			case "MOUSE_DRAGGED":
				if(Debug.isDebug6() == true) {
					System.out.println("Mouse getSceneX() = " + e.getSceneX() + " Mouse getSceneY() = " + e.getSceneY());
					System.out.println("Mouse getScreenX() = " + e.getScreenX());
					System.out.println("Mouse getX() = " + e.getX());
					System.out.println("Triangle getLayoutX() = " + tri.getLayoutX() + " Triangle getLayoutY() = " + tri.getLayoutY());
					System.out.println("Triangle getTranslateX() = " + tri.getTranslateX());
					System.out.println("Triangle getX() = " + tri.getX() + " Triangle getY() = " + tri.getY() );
				}
				if(tool == "translate") {
					double xOffset = 0.0;
					double yOffset = 0.0;
					xOffset = e.getSceneX() - mouseDragX;
					yOffset = e.getSceneY() - mouseDragY;
					mouseDragX = e.getSceneX();
					mouseDragY = e.getSceneY();
					tri.mouseMove(xOffset, yOffset);
				}
				break;
		}
	}
	
/* 	public void handle(Event e) {
		if(Debug.isDebug6() == true) { System.out.println("Event detected in TrianglePane! Info: " + e.toString() ); }
	}
	public void handle(ActionEvent e) {
		if(Debug.isDebug6() == true) { System.out.println("Event detected in TrianglePane! Info: " + e.toString() ); }
	}
	
	public void handle(KeyEvent e) {
		if(Debug.isDebug6() == true) { System.out.println("Key Event detected in TrianglePane! Info: " + e.toString() ); }
	} */

}