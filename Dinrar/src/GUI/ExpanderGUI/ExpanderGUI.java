package GUI.ExpanderGUI;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.BorderFactory;

import FileExpander.Expander;
import GUI.GUI;

public class ExpanderGUI implements EventHandler<ActionEvent> {

	//Insets(top/right/bottom/left)

	private static Stage expanderStage;
	private Scene mainContainer;
	private VBox mainVBox;
	private BorderPane buttonBPaneBox;
	private Button browseButton;
	private GridPane filesGridPane;
	private Label filesLabel;
	private TextArea filesTextArea;

	private static final ExpanderFocusListener focusListener = new ExpanderFocusListener();

	public ExpanderGUI()
	{
		expanderStage = new Stage();
		expanderStage.setWidth(GUI.DEFAULT_WIDTH*.4);
		expanderStage.setHeight(GUI.DEFAULT_HEIGHT*.6);
		expanderStage.setResizable(false);
		createScene();
		expanderStage.setScene(mainContainer);
		/*Adds Listener to focus property so the popup window is always in focus*/
		expanderStage.focusedProperty().addListener(focusListener);
		expanderStage.show();
	}

	private void createScene()
	{
		mainVBox = new VBox();
		createFileArea();
		initfilesGridPane();
		mainVBox.getChildren().add(buttonBPaneBox);
		mainVBox.getChildren().add(filesGridPane);
		mainContainer = new Scene(mainVBox,GUI.DEFAULT_WIDTH*.75,GUI.DEFAULT_HEIGHT*.90);
	}

	private void createFileArea()
	{
		buttonBPaneBox = new BorderPane();
		initBrowseButton();
		buttonBPaneBox.prefWidthProperty().bind(expanderStage.widthProperty());
		buttonBPaneBox.setRight(browseButton);
		buttonBPaneBox.setPadding(new Insets(10,10,0,10));

	}

	private void initfilesGridPane()
	{
		filesGridPane = new GridPane();
		filesGridPane.prefWidthProperty().bind(expanderStage.widthProperty());
		initfilesLabel();
		initfilesTextArea();
		filesGridPane.add(filesLabel, 0, 0);
		filesGridPane.add(filesTextArea, 1, 0);
		ColumnConstraints column1 = new ColumnConstraints(filesLabel.getPrefWidth());
		ColumnConstraints column2 = new ColumnConstraints(100,100,Double.MAX_VALUE);
		column2.setHgrow(Priority.ALWAYS);
		filesGridPane.getColumnConstraints().addAll(column1,column2);
		filesGridPane.setHgap(10);
		filesGridPane.setPadding(new Insets(0, 10, 10, 10));

	}

	private void initBrowseButton()
	{
		browseButton = new Button("Browse...");
		Text buttonText = new Text(browseButton.getText());
		browseButton.setMinWidth(buttonText.getLayoutBounds().getWidth());
		browseButton.setPrefWidth(buttonText.getLayoutBounds().getWidth()+30);
		browseButton.setOnAction(this);
	}
	private void initfilesLabel()
	{
		filesLabel = new Label("Path:");
		Text t = new Text(filesLabel.getText());
		filesLabel.minWidth(t.getLayoutBounds().getWidth()+2);
		filesLabel.prefWidth(t.getLayoutBounds().getWidth()+2);
	}
	private void initfilesTextArea()
	{
		filesTextArea = new TextArea();
		filesTextArea.setEditable(false);
		filesTextArea.getStyleClass().add("Text-Area-File-Display");
		filesTextArea.setPrefHeight(filesLabel.getHeight());
	}


	public static Stage getExpanderStage() {
		return expanderStage;
	}
	

	@Override
	public void handle(ActionEvent e) {
		Object source = e.getSource();
		FileChooser fileChooser = new FileChooser();
		if(source.equals(browseButton))
		{
			ExpanderGUI.getExpanderStage().focusedProperty().removeListener(focusListener);
			fileChooser.setTitle("Open File to Expand");
			Expander expander = GUI.getExpander();
			try
			{
				expander.addFile(fileChooser.showOpenMultipleDialog(new Stage()));
			}catch(NullPointerException npe)
			{
				ExpanderGUI.getExpanderStage().focusedProperty().addListener(focusListener);
				return;
			}
			ExpanderGUI.getExpanderStage().focusedProperty().addListener(focusListener);
		}
	}

}
