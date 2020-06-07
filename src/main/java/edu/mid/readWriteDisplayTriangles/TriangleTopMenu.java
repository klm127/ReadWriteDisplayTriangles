//TriangleTopMenu

// extends an HBox

// will have drop down edit menus and the disappearing button LOAD TO RECORD (in a dark color)

package edu.mid.readWriteDisplayTriangles;

import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

class TriangleTopMenu extends HBox implements EventHandler<ActionEvent>{
	
	ColorPicker colorPicker;
	EventHandler<KeyEvent> keyTranslator;
	
	TriangleTopMenu(EventHandler<ActionEvent> loadHandler) {
		Button loadToRecordTable = new Button("LOAD TO RECORD TABLE");
		loadToRecordTable.setOnAction(loadHandler);
		getChildren().add(loadToRecordTable);
		
		colorPicker = new ColorPicker(Color.BLUE);
		colorPicker.setOnAction(this);
		getChildren().add(colorPicker);
		setMargin(colorPicker, new Insets(0.0,20.0,0.0,20.0));
	}
	
	public String getDumpColor() {
		Color c1 = colorPicker.getValue();
		String cs1 = c1.toString();
		if(Debug.isDebug3() == true) {System.out.println("Top Menu: Color in colorPicker is :" + cs1);}
		return cs1;
	}
		
	public void loadKeyTranslator(EventHandler<KeyEvent> eh) {
		this.keyTranslator = eh;
	}
	
	@Override
	public void handle(ActionEvent e) {
		KeyEvent keyFlipper;
		if(e.getSource() == colorPicker ) {
			keyFlipper = new KeyEvent(KeyEvent.ANY, "f","fill", KeyCode.F, false, false, false, false);
			keyTranslator.handle(keyFlipper);
		}
	}
}