package GUI.TArea;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import GUI.GUI;

public class TAreaContainer {
	
	private final static TextArea TextArea1 = new TextArea();
	private final static TextArea TextArea2 = new TextArea();
	
	private final static TextArea[] TextAreas = {TextArea1,TextArea2};
	
	/*Change width to size of screen*/
	private final static double HEIGHT = 400;
	
	public TAreaContainer(Stage s)
	{
		for(TextArea t:TextAreas)
		{
			/*Set Height and then bind TextAreas to edge of Screen*/
			t.setMaxHeight(HEIGHT);
			t.setPrefWidth(GUI.DEFAULT_WIDTH/3);
			t.prefWidthProperty().bind(s.widthProperty());
			t.prefColumnCountProperty().bind(s.widthProperty());
			/*Sets id to Text-Area-Binary in TextArea.css file*/
			t.getStyleClass().add("Text-Area-Binary");
			t.setEditable(false);
			t.setWrapText(true);
		}
		
	}
	
	public static TextArea[] getTextAreas()
	{
		return TextAreas;
	}
	

}
