package GUI.Menu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MainMenuBar extends MenuBar {
	
	private final static Menu File = new Menu("File");
	private final static MenuItem fileitem1 = new MenuItem("Item1");
	private final static MenuItem fileitem2 = new MenuItem("Item2");
	private final static MenuItem fileitem3 = new MenuItem("Item3");
	private final static MenuItem fileitem4 = new MenuItem("Item4");
	private final static MenuItem fileitem5 = new MenuItem("Item5");
	private final static MenuItem[] Filemenuitems = {fileitem1,fileitem2,fileitem3,fileitem4,fileitem5};
	
	/*Menu 2*/
	private final static Menu Commands = new Menu("Commands");
	private final static MenuItem Commanditem1 = new MenuItem("Item1");
	private final static MenuItem Commanditem2 = new MenuItem("Item2");
	private final static MenuItem Commanditem3 = new MenuItem("Item3");
	private final static MenuItem Commanditem4 = new MenuItem("Item4");
	private final static MenuItem Commanditem5 = new MenuItem("Item5");
	private final static MenuItem[] Commandsmenuitems = {Commanditem1,Commanditem2,Commanditem3,Commanditem4,Commanditem5};
	
	/*Menu 3*/
	private final static Menu Tools = new Menu("Tools");
	private final static MenuItem Toolitem1 = new MenuItem("Item1");
	private final static MenuItem Toolitem2 = new MenuItem("Item2");
	private final static MenuItem Toolitem3 = new MenuItem("Item3");
	private final static MenuItem Toolitem4 = new MenuItem("Item4");
	private final static MenuItem Toolitem5 = new MenuItem("Item5");
	private final static MenuItem[] Toolsmenuitems = {Toolitem1,Toolitem2,Toolitem3,Toolitem4,Toolitem5};
	
	/*Menu 4*/
	private final static Menu Help = new Menu("Help");
	private final static MenuItem Helpitem1 = new MenuItem("Item1");
	private final static MenuItem Helpitem2 = new MenuItem("Item2");
	private final static MenuItem Helpitem3 = new MenuItem("Item3");
	private final static MenuItem Helpitem4 = new MenuItem("Item4");
	private final static MenuItem Helpitem5 = new MenuItem("Item5");
	
	private final static MenuItem[] Helpmenuitems = {Helpitem1,Helpitem2,Helpitem3,Helpitem4,Helpitem5};
	
	/*Menu bar menu list*/
	private static final Menu[] menulist = {
		File,
		Commands,
		Tools,
		Help
	};
	
	public MainMenuBar(Stage s)
	{
		super();
		//Binds menu to width of stage
		this.prefWidthProperty().bind(s.widthProperty());
		initializeMenus();
		this.getMenus().addAll(menulist);
	}
	
	/*Adds Menus to menubar*/
	private void initializeMenus()
	{
		File.getItems().addAll(Filemenuitems);
		Commands.getItems().addAll(Commandsmenuitems);
		Tools.getItems().addAll(Toolsmenuitems);
		Help.getItems().addAll(Helpmenuitems);
	}

}
