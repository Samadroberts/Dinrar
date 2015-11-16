package GUI.TArea;

import java.util.Random;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class TAreaContainer {
	
	private final TextArea TextArea1 = new TextArea();
	private final TextArea TextArea2 = new TextArea();
	
	private final TextArea[] TextAreas = {TextArea1,TextArea2};
	
	/*Change width to size of screen*/
	private final static double HEIGHT = 400;
	
	public TAreaContainer(Stage s)
	{
		for(TextArea t:TextAreas)
		{
			/*Set Height and then bind TextAreas to edge of Screen*/
			t.setMaxHeight(HEIGHT);
			t.prefWidthProperty().bind(s.widthProperty());
			t.setEditable(false);
			t.maxWidthProperty().bind(s.widthProperty());
			t.setPrefColumnCount(100);
			t.setWrapText(true);
			int i = 0;
		}
		
	}
	
	
	public TextArea[] getTextAreas()
	{
		return TextAreas;
	}

}
