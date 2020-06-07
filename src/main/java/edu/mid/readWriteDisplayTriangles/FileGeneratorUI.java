/* This is the UI for the File Generator 
Will spawn in a seperate pane
Clicking Show will display a triangle pane
Could make the pane more responsive to resizes by choosing
a different layout but I'm in too deep now.

*/

package edu.mid.readWriteDisplayTriangles;


import javafx.scene.layout.GridPane;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.paint.Paint;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;
import javafx.scene.control.ColorPicker;

public class FileGeneratorUI extends GridPane{
	
	private Label nameLabel;
	private TextField fileNameInput;
	private Button loadButton;
	private Button saveButton;
	private Button renameButton;
	private Button generateRandomButton;
	private Label seedLabel;
	private TextField seedInput;
	private Label sizeLabel;
	private TextField sizeInput;
	private Button showButton;
	private VBox closeVbox;
	private Button closeAll;
	private Button closeNotLast;
	private ColorPicker colorPicker1;
	private ColorPicker colorPicker2;
	private ColorPicker colorPicker3;
	private Button colorize;
	
	public EventHandler<ActionEvent> customEventHandler;

	FileGeneratorUI(EventHandler<ActionEvent> c){
		super();
		this.customEventHandler = c;
	}
	
	private void paint() {
		
		//setGridLinesVisible(true); //for debug
		
		//set up the UI and make it look pretty
		nameLabel = new Label("Loaded:");
		setConstraints(nameLabel, 0, 1);
		setMargin(nameLabel, new Insets(0,0,0,40));
		
		fileNameInput = new TextField(" ");
		fileNameInput.setPrefColumnCount(7);
		setConstraints(fileNameInput, 1, 1);
		
		renameButton = new Button("Rename");
		setConstraints(renameButton, 2,1);
				
		loadButton = new Button("Load");
		setConstraints(loadButton, 0, 0); 
		//insets - top, right, bottom, left
		setMargin(loadButton, new Insets(0,0,20,40));
		
		saveButton = new Button("Save");
		setConstraints(saveButton, 1, 0);
		setMargin(saveButton, new Insets(0,0,20,0));
		
		generateRandomButton = new Button("Generate Random Triangles");
		setConstraints(generateRandomButton, 0, 3);
		setColumnSpan(generateRandomButton, 3);
		setMargin(generateRandomButton, new Insets(20.0,0,0,30));
				
		seedLabel = new Label("Seed");
		setConstraints(seedLabel, 0, 4);
		setMargin(seedLabel, new Insets(0.0,0,0,40));
		
		seedInput = new TextField(Integer.toString(Options.randSeed));
		seedInput.setPrefColumnCount(4);
		setConstraints(seedInput, 1, 4);
		
		sizeLabel = new Label("Quantity");
		setConstraints(sizeLabel, 0, 5);
		setMargin(sizeLabel, new Insets(0.0,0,0,40));
		
		sizeInput = new TextField(Integer.toString(Options.randQty));
		sizeInput.setPrefColumnCount(4);
		setConstraints(sizeInput, 1, 5);
		
		showButton = new Button("Show");
		setConstraints(showButton, 1, 6);
		setMargin(showButton, new Insets(20.0,0,0,0));
		showButton.setTextFill(Paint.valueOf("#0059b3"));
		showButton.setFont(new Font(16.5));
		
		closeVbox = new VBox();
		setConstraints(closeVbox, 2, 6);
		
		closeAll = new Button("close-All");
		closeNotLast = new Button("closeNotLast");
		closeVbox.getChildren().add(closeAll);
		closeVbox.getChildren().add(closeNotLast);
		
		//insets - Insets(double top, double right, double bottom, double left)
		colorPicker1 = new ColorPicker(Color.RED);
		setConstraints(colorPicker1, 1, 7);
		setMargin(colorPicker1, new Insets(20.0, 0, 0, 0));
		colorPicker2 = new ColorPicker(Color.BLUE);
		setConstraints(colorPicker2, 1, 8);
		colorPicker3 = new ColorPicker(Color.GREEN);
		setConstraints(colorPicker3, 1, 9);
		Button colorize = new Button("Colorize");
		setConstraints(colorize, 2, 8);
		setMargin(colorize, new Insets(0.0, 0.0,0.0, 10.0));
		
		Text aboutText = new Text();
		aboutText.setText(CONST.ABOUT_TEXT);
		setConstraints(aboutText, 0,10);
		setColumnSpan(aboutText, 3);
		setMargin(aboutText, new Insets(20.0, 0.0,0.0, 20.0));
		
		Text commandsText = new Text();
		commandsText.setText(CONST.ABOUT_CMDS);
		setConstraints(commandsText,0,11);
		setColumnSpan(commandsText,3);
		//insets - top, right, bottom, left
		setMargin(commandsText, new Insets(20.0, 0.0,0.0, 20.0));
		
		getChildren().add(colorPicker1);
		getChildren().add(colorPicker2);
		getChildren().add(colorPicker3);
		getChildren().add(colorize);
		
		//add all the nodes to this pane
		getChildren().addAll(nameLabel, fileNameInput, renameButton, loadButton, saveButton, generateRandomButton, seedLabel, seedInput, sizeLabel, sizeInput, showButton, closeVbox, aboutText, commandsText);
		
		//register the eventHandler with the relevent nodes
		showButton.setOnAction(customEventHandler);
		generateRandomButton.setOnAction(customEventHandler);
		saveButton.setOnAction(customEventHandler);
		loadButton.setOnAction(customEventHandler);
		renameButton.setOnAction(customEventHandler);
		closeAll.setOnAction(customEventHandler);
		closeNotLast.setOnAction(customEventHandler);
		colorize.setOnAction(customEventHandler);
		
	}
	
	//returns what's in the Seed 
	public int getSeed() {
		return Integer.parseInt(seedInput.getText());
	}
	
	//returns what's in the Quanity text field
	public int getSize() {
		return Integer.parseInt(sizeInput.getText());
	}
	
	//returns what's in the File Name field
	public String getName() {
		return fileNameInput.getText();
	}
	public void setName(String s) {
		fileNameInput.setText(s);
	}
	
	//returns stringArray of colors
	public String[] getColors() {
		if(Debug.isDebug3() == true) { System.out.println("getColors() called in fileGeneratorUI.");}
		Color c1 = colorPicker1.getValue();
		Color c2 = colorPicker2.getValue();
		Color c3 = colorPicker3.getValue();
		if(Debug.isDebug3() == true) { System.out.println("Colors gotten succesfully.");}
		String color1 = c1.toString();
		String color2 = c2.toString();
		String color3 = c3.toString();
		if(Debug.isDebug3() == true) { System.out.println("Strings gotten succesfully.");}
		String[] stringArray = new String[] {color1, color2, color3};
		if(Debug.isDebug3() == true) {
			System.out.println("Color values in UI getColors are, " + color1 + ", " + color2 + ", " + color3 + ".");
		}
		return stringArray;
	}
	
	public void publicPaint() {
		paint();
	}
	
}

