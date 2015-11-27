package GUI.ExpanderGUI;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableArrayBase;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import FileExpander.Deflater;
import FileExpander.Expander;
import FileExpander.FileFunctions;
import GUI.GUI;

public class ExpanderGUI implements EventHandler<ActionEvent> {

	//Insets(top/right/bottom/left)

	private static Stage expanderStage;
	private Scene mainContainer;
	private VBox mainVBox;
	private Button browseButton;
	private ListView<String> filesListView;
	private HBox lbHBox = new HBox();
	private TitledPane filestp;
	private ArrayList<String> filesListArray;
	
	/*Size GUI items*/
	private VBox sizeVBox;
	private TextField sizeTField;
	private GridPane expanderGPane;
	private TitledPane sizetp;
	private GridPane sizeGPane;
	private ToggleGroup memorySelectionButtons;
	private RadioButton mbButton;
	private RadioButton gbButton;
	private RadioButton tbButton;
	private Label sizeTOBIG;
	
	private GridPane expandBGPane;
	private Button expandAll;
	private Button expandSelected;
	private Button Cancel;
	
	private boolean isExpander = false;
	private boolean isDelfater = false;
	
	private ObservableList<String> filesList;

	private static final ExpanderFocusListener focusListener = new ExpanderFocusListener();

	public ExpanderGUI(FileFunctions ff)
	{
		expanderStage = new Stage();
		expanderStage.setWidth(GUI.DEFAULT_WIDTH*.4);
		expanderStage.setHeight(GUI.DEFAULT_HEIGHT*.6);
		if(ff instanceof Expander)
		{
			expanderStage.setTitle("Choose Files to Expand");
			isExpander=true;
		}
		if(ff instanceof Deflater)
		{
			expanderStage.setTitle("Choose Files to Deflate");
			isDelfater=true;
		}
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
		mainContainer = new Scene(mainVBox,GUI.DEFAULT_WIDTH*.75,GUI.DEFAULT_HEIGHT*.90);
		initfilesListView();
		initlbHBox();
		mainVBox.getChildren().add(lbHBox);
		mainVBox.getChildren().add(filestp);
		if(isExpander)
		{
			expanderGPane = new GridPane();
			expanderGPane.minWidthProperty().bind(mainContainer.widthProperty());
			initRadioButtons();
			initexpanderButtons();
			expanderGPane.add(sizetp, 0, 0);
			expanderGPane.add(expandBGPane,1,0);
			expanderGPane.setPadding(new Insets(10,0,0,0));
			expanderGPane.setHgap(40);
			mainVBox.getChildren().add(expanderGPane);
			
		}
		mainVBox.setPadding(new Insets(10,10,10,10));
	}
	
	private void initlbHBox()
	{
		lbHBox.prefWidthProperty().bind(expanderStage.widthProperty());
		
		initBrowseButton();
		lbHBox.setPadding(new Insets(0,0,10,0));
		lbHBox.getChildren().addAll(browseButton);
		lbHBox.setAlignment(Pos.BASELINE_RIGHT);
	}

	private void initBrowseButton()
	{
		browseButton = new Button("Browse...");
		Text buttonText = new Text(browseButton.getText());
		browseButton.setMinWidth(buttonText.getLayoutBounds().getWidth());
		browseButton.setPrefWidth(buttonText.getLayoutBounds().getWidth()+30);
		//Add listener as this
		browseButton.setOnAction(this);
	}
	
	
	private void initfilesListView()
	{
		filesListView = new ListView<String>();
		filesList = FXCollections.observableList(new ArrayList<String>());
		filesListView.setItems(filesList);
		filesListView.setEditable(false);
		//Set selection mode
		filesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		//Add listener for selected files
		filesListView.setOnMouseClicked(new SelectedListener(filesListView));
		//Add listener for delete key pressed
		filesListView.setOnKeyPressed(new DeletedKeyListener(this));
		//Add to Title Pane
		filestp = new TitledPane("Paths",filesListView);
		filestp.setMaxHeight(expanderStage.getHeight()*.5);
		filestp.setCollapsible(false);
	}
	
	private void initRadioButtons()
	{
		/*Add Buttons to ToggleGroup*/
		memorySelectionButtons = new ToggleGroup();
		mbButton = new RadioButton("MB");
		gbButton = new RadioButton("GB");
		tbButton = new RadioButton("TB");
		mbButton.setToggleGroup(memorySelectionButtons);
		gbButton.setToggleGroup(memorySelectionButtons);
		tbButton.setToggleGroup(memorySelectionButtons);
		/*Add Buttons to GridPane*/
		sizeGPane = new GridPane();
		sizeGPane.setPadding(new Insets(10,0,0,0));
		sizeGPane.add(mbButton, 0, 0);
		sizeGPane.add(gbButton, 1, 0);
		sizeGPane.add(tbButton, 0, 1);
		sizeGPane.setHgap(5);
		sizeGPane.setVgap(5);
		sizeTField = new TextField();
		sizeTField.prefWidthProperty().bind(sizeGPane.widthProperty());
		sizeVBox = new VBox();
		/*Label Used to tell user if the value they entered in the tb is>1TB*/
		sizeTOBIG = new Label("File size is greater then 1TB!");
		sizeTOBIG.setTextFill(Color.web("#ff0000"));
		sizeTOBIG.setVisible(false);
		sizeVBox.getChildren().add(sizeTField);
		sizeVBox.getChildren().add(sizeGPane);
		sizeVBox.getChildren().add(sizeTOBIG);
		sizetp = new TitledPane("Size Options",sizeVBox);
		sizetp.setCollapsible(false);
	}
	
	private void initexpanderButtons()
	{
		expandBGPane = new GridPane();
		
		expandAll = new Button("Expand All");
		expandAll.setOnAction(this);
		
		expandSelected = new Button("Expand Selected");
		expandSelected.setOnAction(this);
		
		Cancel = new Button("Cancel");
		Cancel.setOnAction(this);
		
		expandAll.minWidthProperty().bind(expandBGPane.widthProperty());
		expandSelected.minWidthProperty().bind(expandBGPane.widthProperty());
		Cancel.minWidthProperty().bind(expandBGPane.widthProperty());
		
		
		expandBGPane.add(expandAll, 0, 0);
		expandBGPane.add(expandSelected, 0, 1);
		expandBGPane.add(Cancel, 0, 2);
		expandBGPane.setVgap(10);
		expandBGPane.setAlignment(Pos.CENTER_RIGHT);
		
	}
	
	public static Stage getExpanderStage() {
		return expanderStage;
	}
	

	@Override
	public void handle(ActionEvent e) {
		Object source = e.getSource();
		if(source.equals(browseButton))
		{
			browseButtonHandler();
		}
		if(source.equals(expandAll))
		{
			expandAllButtonHandler();
		}
		
		if(source.equals(expandSelected))
		{
			expandSelectedButtonHandler();
		}
		
		if(source.equals(Cancel))
		{
			expanderStage.close();
		}
		
		
	}
	
	private void expandAllButtonHandler()
	{
		Expander e = GUI.getExpander();
		if(e.getFiles().size()==0)
		{
			return;
		}
		expanderStage.close();
		e.run();
	}
	
	private void expandSelectedButtonHandler()
	{
		Expander e = GUI.getExpander();
		if(SelectedListener.getIndexs().size()==0)
		{
			return;
		}
		ArrayList<Integer> toremove = new ArrayList<Integer>();
		for(int i = 0;i<e.getFiles().size();i++)
		{
			if(!(SelectedListener.getIndexs().contains(i)))
			{
				toremove.add(i);
			}
		}
		e.removeFiles(toremove);
		expanderStage.close();
		e.run();
	}
	
	private void browseButtonHandler()
	{
		FileChooser fileChooser = new FileChooser();
		ExpanderGUI.getExpanderStage().focusedProperty().removeListener(focusListener);
		fileChooser.setTitle("Open File to Expand");
		Expander expander = GUI.getExpander();
		/*If the action is cancel theses are used to restore*/
		filesListArray = new ArrayList<String>();
		filesListArray.addAll(filesList);
		try
		{
			expander.removeallFiles();
			filesList.clear(); 
			expander.addFile(fileChooser.showOpenMultipleDialog(new Stage()));
			if(!expander.getFiles().isEmpty()){
				int num_of_files = expander.getFiles().size();
				for(int i =0;i<num_of_files;i++)
				{
					filesList.add(expander.getFiles().get(i).getAbsolutePath());
				}
			}
		}catch(NullPointerException npe)
		{
			ExpanderGUI.getExpanderStage().focusedProperty().addListener(focusListener);
			restorefilesList();
			return;
		}
		ExpanderGUI.getExpanderStage().focusedProperty().addListener(focusListener);
		
	}
	
	
	/*Restore items from list*/
	public void restorefilesList()
	{
		for(String s:filesListArray)
		{
			filesList.add(s);
		}
	}
	
	/*Remove items from list*/
	public void  remove_files(ArrayList<String> indexs)
	{
		for(String s:indexs)
		{
			filesList.remove(s);
		}
	}

}
