package GUI;
import java.io.File;

import GUI.Buttons.ButtonContainer;
import GUI.FileTree.FileTreeItem;
import GUI.Menu.MainMenuBar;
import javafx.scene.Scene;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class mainScene extends Scene {
	
	private Stage primarystage;
	
	
	public mainScene(Stage s)
	{
		this(s,new VBox(),GUI.DEFAULT_WIDTH,GUI.DEFAULT_HEIGHT);
	}
	
	public mainScene(Stage s,VBox root,double width,double height)
	{
		super(root,width,height);
		primarystage = s;
		setupMenu();
	}
	
	private void setupMenu()
	{
		//Add menu Bar
		((VBox)this.getRoot()).getChildren().add(new MainMenuBar(primarystage));
		((VBox)this.getRoot()).getChildren().add(new ButtonContainer(primarystage));
		
		
	}
	
}
