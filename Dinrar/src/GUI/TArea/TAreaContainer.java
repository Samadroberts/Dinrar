package GUI.TArea;

import java.util.Random;

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
			t.prefWidthProperty().bind(s.widthProperty());
			t.setEditable(false);
		}
		
	}
	
	
	public TextArea[] getTextAreas()
	{
		return TextAreas;
	}

}
