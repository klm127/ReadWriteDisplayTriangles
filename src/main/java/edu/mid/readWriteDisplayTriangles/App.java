package edu.mid.readWriteDisplayTriangles;
//this is the main app
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.nio.file.Path;

public class App extends Application {
	
	private Stage primaryStage;
	
	private RecordTable recordTable;	
	private FileGenerator fileGenerator;
	private FileGeneratorUI fileGeneratorUI;
	
	private ArrayList<TriangleStage> triangleStageArrayList;
	private TriangleStage loadedStage;

	private FileChooser fileChooser = new FileChooser();
	private File initialDirectory = new File(CONST.SUBDIRECTORY);
	
	private EventHandler<ActionEvent> customEventHandler;
	

	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		//create the customEventHandler object, defined in this file 
		customEventHandler = new CustomEventHandler(this);
		
		//a starting table so users can click Show right away, size 30, seed 25
		recordTable = new RecordTable(CONST.DEFAULT_QTY);
		recordTable.generateRandomTable(CONST.DEFAULT_SEED);
		
		fileGenerator = new FileGenerator();
		fileChooser.setInitialDirectory(initialDirectory);
		ExtensionFilter extensionFilter = new ExtensionFilter("TXT files (*.txt)","*.txt");
		fileChooser.getExtensionFilters().add(extensionFilter);
		//eventHandler passed to fileGenerator for event handling
		fileGeneratorUI = new FileGeneratorUI(customEventHandler);
		fileGeneratorUI.publicPaint();
		
		//triangleStageArray list is used for close and close all buttons - too many windows before
		triangleStageArrayList = new ArrayList<TriangleStage>();
		
		//directoryViewer used for Load button 
		
		//create the UI and place it on the stage
		Scene scene = new Scene(fileGeneratorUI, CONST.UI_WIDTH, CONST.UI_HEIGHT);
		primaryStage.setTitle("Read and display Triangles from a file");
		primaryStage.setX(CONST.UI_X);
		primaryStage.setY(CONST.UI_Y);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public void loadLoadHandler(EventHandler<ActionEvent> e) {
		customEventHandler = e;
	}
	public void colorize() {
		String[] colors = fileGeneratorUI.getColors();
		if(Debug.isDebug3() == true && Debug.isDebug4() == true) {
			System.out.println("ColorizeTriangles called in App, object: " + colors);
		}
		int cl = colors.length;
		for(int i = 0; i < recordTable.getSize(); i++) {
			recordTable.setRecordColor(i, colors[i%cl]);
			if(Debug.isDebug3() == true) System.out.println("recordTable.getColor("+i+") = " + recordTable.getRecordColor(i) + " name " + recordTable.getName());
			
		}
		if(loadedStage != null )
		loadedStage.setRecordTable(recordTable);
		// need to add something to update the triangleStage here, like triangleStage.updateColors();
		//even better, just include it in recordTable.setRecordColor - it should reColor the polygons 
	}
	
	public void loadFile() {
		
		if(!initialDirectory.exists()) {
			initialDirectory.mkdir();
		}
		
		File selectedFile = fileChooser.showOpenDialog(primaryStage);
		if(selectedFile != null ) {
			Path filePath = selectedFile.toPath();
			try {
				fileGenerator.readTextFileToRecordTable(recordTable, filePath);
				makeTriangleStage();
				updateUIName();
			}
			catch(Exception e) {
				if(Debug.isDebug4() == true ) {
					System.out.println("App - exception caught in loadFile().");
				}
				makeAlert("Error Loading File - check data compatability", e);
			}
		}
	}
	public void saveFile() {

		try{
			File selectedFile = fileChooser.showSaveDialog(primaryStage);
			Path filePath = selectedFile.toPath();
			fileGenerator.writeRecordTableToTextFile(recordTable, filePath);
		}
		catch(Exception e) {
			if(Debug.isDebug4() == true) {
				System.out.println("App - exception caught in saveFile()");
			}
			makeAlert("Error saving File",e);
		}
	}
	public void makeAlert(String s, Exception e) {
					 
		 String text = ">>>" + s + "<<<\n" + e.getStackTrace();
		 Alert alert = new Alert(AlertType.ERROR, text);
		 alert.setTitle("Exception!");
		 alert.setHeaderText(e.getMessage());
		 
		 VBox dialogPaneContent = new VBox();
		 
		 Label label = new Label("Stack Trace:");
		 
		 String stackTrace = this.getStackTrace(e);
		 TextArea textArea = new TextArea();
		 textArea.setText(stackTrace);
		 
		 dialogPaneContent.getChildren().addAll(label, textArea);
		 
		 alert.getDialogPane().setContent(dialogPaneContent);
		 alert.showAndWait(); 
	}
	private String getStackTrace(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String s = sw.toString();
		return s;
	}
	
	public void rename() {
		String newName = fileGeneratorUI.getName();
		if( newName == null ) { newName = "untitled"; }
		
		char[] validator = newName.toCharArray();
		boolean invalid = false;
		char delimeter = CONST.DELIM.charAt(0);
		for(int i = 0; i < validator.length; i++) {
			if (validator[i] == delimeter) 
				invalid = true;
			if(Debug.isDebug4()) {
				System.out.println("Validator[" + i + "] = " + validator[i]);
				System.out.println("invalid = " + invalid);
			}
		}
		
		if(invalid == false) {
			recordTable.setName(newName);
			Alert alert = new Alert(AlertType.INFORMATION, "New name: " + newName);
			alert.setHeaderText("The active RecordTable has been renamed.");
			alert.setTitle("Renamed!");
			alert.showAndWait();
		}
		else {
			Alert alert = new Alert(AlertType.WARNING, "Record names may not contain the delimeter - " + CONST.DELIM);
			alert.setHeaderText("Invalid input - use of delimeter in record name.");
			alert.setTitle("Invalid Name!");
			alert.showAndWait();
		}
		
	}
	public void updateUIName() {
		String name = recordTable.getName();
		fileGeneratorUI.setName(name);
	}
	//the event handler calls this method to open a new window with triangles 
	public void makeTriangleStage() {
		TriangleStage triangleStage = new TriangleStage();
		triangleStage.assemble(recordTable);
		triangleStage.setTitle("Triangles! > " + recordTable.getName() );
		triangleStageArrayList.add(triangleStage);
		triangleStage.setUpLoadHandler(customEventHandler);
		loadedStage = triangleStage;
		loadedStage.show();
		
	}
	public void closeAllTriangleStages() {
		if(Debug.isDebug4() == true) { System.out.println("close All Triangle Stages called in App"); }
		for(int i = 0; i < triangleStageArrayList.size(); i++ ) {
			triangleStageArrayList.get(i).close();
			triangleStageArrayList.get(i).unloadClear();
		}
		//clear for better garbage collecting 
		triangleStageArrayList.clear();
	}
	
	public void loadRecordsFromTrianglePaneForSaving(Object callingStageEventTarget) { //needs to be type event target
		int stageArrayIndex = triangleStageArrayList.indexOf(callingStageEventTarget);
		loadedStage = triangleStageArrayList.get(stageArrayIndex);
		recordTable = loadedStage.getRecordTable();
		if(Debug.isDebug2() == true) { System.out.println("In App, triangleStage recordTable: " + recordTable.getName() +" was retrieved."); }
		
	}
	public void closeNotLast() {
		if(Debug.isDebug4() == true) { System.out.println("close All Triangle Stages called in App"); }
		for(TriangleStage i : triangleStageArrayList) {
			if( i != loadedStage) {
				i.close();
				i.unloadClear();
			}
		}
		ArrayList<TriangleStage> triangleStageArrayList2 = new ArrayList<TriangleStage>(0);
		triangleStageArrayList2.add(loadedStage);
		triangleStageArrayList.clear();
		triangleStageArrayList = triangleStageArrayList2;
	}
	public void closeAll() {
		for(int i =0; i < ( triangleStageArrayList.size() - 1 ); i++ ) {
			triangleStageArrayList.get(i).close();
			triangleStageArrayList.get(i).unloadClear();
		}
		triangleStageArrayList.clear();
	}
	
	public void loadRandomTriangles() {
		int seed = fileGeneratorUI.getSeed();
		int size = fileGeneratorUI.getSize();
		recordTable.setNewRecordArray(size);
		recordTable.generateRandomTable(seed);
		colorize();
		if(Debug.isDebug4() == true) { System.out.println("Random table generated."); }
		makeTriangleStage();
		updateUIName();
	}

	public void execute(String args[]) {
		App.launch(args);
	}
}







