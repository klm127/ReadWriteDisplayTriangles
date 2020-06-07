//Triangle Stage
package edu.mid.readWriteDisplayTriangles;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

//extending EventHandler a big breakthrough
class TriangleStage extends Stage implements EventHandler<ActionEvent>{
	
	TriangleUI triangleUI;
	RecordTable recordTable;
	EventHandler<ActionEvent> loadHandler;
	
	public void assemble(RecordTable recordTable) {
		if(Debug.isDebug5() == true) {
			System.out.println("Assembling a TriangleStage.");
		}
		
		triangleUI = new TriangleUI(recordTable, this);
		triangleUI.assemble();
		
		//UI placed in scene in constructor
		Scene triangleScene = new Scene(triangleUI, CONST.TRI_WIDTH, CONST.TRI_HEIGHT);
		setScene(triangleScene);
	}
	
	public void loadRecordTable(RecordTable recordTable) {
		triangleUI.loadRecordTable(recordTable);
	}
	public RecordTable getRecordTable() {
		return recordTable;
	}
	public void setRecordTable(RecordTable recordTable) {
		this.recordTable = recordTable;
		triangleUI.loadRecordTable(recordTable);
	}
	
	public void setUpLoadHandler(EventHandler<ActionEvent> customEventHandler) {
		this.loadHandler = customEventHandler;
	}
	
	public void unloadClear() {
		recordTable = null;
		loadHandler = null;
	}
	
	//when the customEventHandler in App, which is registered to this instance, picks up this fired event, the target will be this triangleStage and the source (for the string parsing) will be the button
	//might wind up using some better parsing here 
	public void handle(ActionEvent e) {
		ActionEvent targetMe = e.copyFor(e.getSource(), this);
		if(Debug.isDebug5() == true) {
			System.out.println("Event caught in TriangleStage extending EventHandler.");
			System.out.println("Event getSource.toString() == " + e.getSource().toString());
			System.out.println("Event target is == " + e.getTarget() );
			System.out.println("New target is == " + targetMe.getTarget() );
		}
		//calling this pass through method here is tempro
		triangleUI.setToRecordTable();
		recordTable = triangleUI.getRecordTable();
		if(recordTable.getName() == null) {
			recordTable.setName("Untitled.");
		}
		loadHandler.handle(targetMe);
		if(Debug.isDebug4() == true) { System.out.println("Triangle Stage firing ActionEvent."); };
	}
}