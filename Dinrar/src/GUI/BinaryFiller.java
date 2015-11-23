package GUI;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

public class BinaryFiller extends AnimationTimer implements ChangeListener<Number>{
	
	private static TextArea[] ta;
	
	private static final int MAX_CHARACTERS = 2852;
	
	public BinaryFiller(TextArea[] t)
	{
		ta = t;
		for(TextArea tarea:t)
		{
			//tarea.applyCss();
			tarea.scrollTopProperty().addListener(this);
		}
	}

	public void fillTextArea()
	{
		/*Select Either 1 or 0*/
		Random ran = new Random();
		int i = ran.nextInt(2);
		for(TextArea t:ta)
		{
			if(t.getText().length()<MAX_CHARACTERS)
			{
				t.replaceText(0,0, String.valueOf(i));
			}
			else
			{
				t.deleteText(t.getText().length()-2, t.getText().length()-1);
			}
		}
	}

	@Override
	public void handle(long now) {
		fillTextArea();
	}

	@Override
	public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
		for(TextArea t:ta)
		{
			t.setScrollTop(Double.MIN_VALUE);
		}
	}
}
