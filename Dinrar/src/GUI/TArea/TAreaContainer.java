package GUI.TArea;

import java.util.Random;

import javafx.scene.control.TextArea;

public class TAreaContainer {
	
	private static final TextArea TextArea1 = new TextArea();
	private static final TextArea TextArea2 = new TextArea();
	
	private static final TextArea[] TextAreas = {TextArea1,TextArea2};
	
	private final static double WIDTH = 100;
	private final static double HEIGHT = 400;
	
	public TAreaContainer()
	{
		for(TextArea t:TextAreas)
		{
			t.setPrefSize(WIDTH, HEIGHT);
			t.setEditable(false);
		}
		
	}
	
	public void fillBinary()
	{
		System.out.println("Gothere");
		Random n = new Random();
		Integer i = n.nextInt(2);
		for(TextArea ta:TextAreas)
		{
			ta.appendText(String.valueOf(i));
		}
	}
	
	public TextArea[] getTextAreas()
	{
		return TextAreas;
	}

}
