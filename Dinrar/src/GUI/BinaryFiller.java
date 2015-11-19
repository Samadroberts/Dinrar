package GUI;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;

public class BinaryFiller extends AnimationTimer{
	
	private static TextArea[] ta;
	public BinaryFiller(TextArea[] t)
	{
		ta = t;
	}

	public void fillTextArea()
	{
		/*Select Either 1 or 0*/
		Random ran = new Random();

		int i = ran.nextInt(2);
		for(TextArea t:ta)
		{
			t.appendText(String.valueOf(i));
			ScrollBar scrollBarv = (ScrollBar)t.lookup(".scroll-bar");
			if(scrollBarv!=null)
			{
				scrollBarv.setDisable(true);
			}
		}
	}

	@Override
	public void handle(long now) {
		fillTextArea();
	}
}
