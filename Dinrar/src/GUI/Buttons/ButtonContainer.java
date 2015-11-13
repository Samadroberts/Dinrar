package GUI.Buttons;

import GUI.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ButtonContainer extends HBox implements EventHandler<ActionEvent> {
	
	private final static Button Expand = new Button("Expand");
	private final static Button Deflate = new Button("Deflate");
	
	private final static Button[] ButtonOptions = {Expand,Deflate};
	
	public ButtonContainer(Stage s)
	{
		super();
		this.setPrefHeight(50);
		this.setSpacing(10);
		//Top,right,bottom,left
		this.setPadding(new Insets(3,0,3,10));
		this.prefWidthProperty().bind(s.widthProperty());
		initButtons();
		this.getChildren().addAll(ButtonOptions);
	}
	
	private void initButtons()
	{
		for(Button b:ButtonOptions)
		{
			b.setPrefHeight(50);
			b.setOnAction(this);
			
		}
	}

	@Override
	public void handle(ActionEvent e) {
		Object source = e.getSource();
		FileChooser fileChooser = new FileChooser();
		
		
		if(source.equals(Expand))
		{
			fileChooser.setTitle("Open File to Expand");
			try
			{
				GUI.getExpander().addFile(fileChooser.showOpenMultipleDialog(new Stage()));
			}catch(NullPointerException npe){};
		}
		if(source.equals(Deflate))
		{
			fileChooser.setTitle("Open File to Deflate");
			try
			{
				GUI.getDeflater().addFile(fileChooser.showOpenMultipleDialog(new Stage()));
			}catch(NullPointerException npe){};
			
		}
		
	}
	
}
