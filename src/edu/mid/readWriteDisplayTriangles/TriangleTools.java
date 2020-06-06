//left menu in pane with fill, selector, etc. button 

package edu.mid.readWriteDisplayTriangles;

import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.EventType;
import javafx.geometry.Insets;

class TriangleTools extends VBox implements EventHandler<ActionEvent>{
	
	EventHandler<KeyEvent> keyTranslator;
	Button transButton;
	Button fillButton;
	Button pickerButton;
	
	TriangleTools() {
		super();
		
		try {
			Image trans = new Image("/Icons/translate.png");
			ImageView transIcon = new ImageView(trans);
			transButton = new Button(null, transIcon);
		}
		catch(Exception e) {
			if(Debug.isDebug6())
				System.out.println("Image load error. Placeholder button");
			transButton = new Button("translate");
		}
		getChildren().add(transButton);
		setMargin(transButton, new Insets(20.0,0,0,0.0));
		transButton.setOnAction(this);
		if(Debug.isDebug6() == true) { System.out.println("Preferred width of trans button is : " + transButton.getWidth()); }
		
		try{
			Image fill = new Image("/Icons/fill.png");
			ImageView fillIcon = new ImageView(fill);
			fillButton = new Button(null, fillIcon);
		}catch(Exception e) {
			if(Debug.isDebug6())
				System.out.println("Image load error. Placeholder button");
			fillButton = new Button("fill");
		}
		getChildren().add(fillButton);
		fillButton.setOnAction(this);
		
		try{
			Image picker = new Image("/Icons/picker.png");
			ImageView pickerIcon = new ImageView(picker);
			pickerButton = new Button(null, pickerIcon);
		}catch(Exception e) {
			if(Debug.isDebug6())
				System.out.println("Image load error. Placeholder button");
			pickerButton = new Button("pick");
		}
		
		getChildren().add(pickerButton);
		pickerButton.setOnAction(this);
	}
	
	public void loadKeyTranslator(EventHandler<KeyEvent> eh) {
		keyTranslator = eh;
	}
	
	@Override
	public void handle(ActionEvent e) {
		KeyEvent keyFlipper;
		if(e.getSource() == transButton ) {
			keyFlipper = new KeyEvent(KeyEvent.ANY, "t","translate", KeyCode.T, false, false, false, false);
			keyTranslator.handle(keyFlipper);
		}
		if(e.getSource() == fillButton ) {
			keyFlipper = new KeyEvent(KeyEvent.ANY, "f","fill", KeyCode.F, false, false, false, false);
			keyTranslator.handle(keyFlipper);
		}
		if(e.getSource() == pickerButton ) {
			keyFlipper = new KeyEvent(KeyEvent.ANY, "p","pick", KeyCode.P, false, false, false, false);
			keyTranslator.handle(keyFlipper);
		}
	}
}