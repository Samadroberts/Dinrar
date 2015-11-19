package GUI;

import java.util.Random;import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;

public class BinaryFiller extends Thread {
	
	private double cur_width;
	ScrollBar scrollBarv;

	private TextArea[] ta;

	public BinaryFiller(TextArea[] t)
	{
		this.ta = t;

	}

	@Override
	public void run()
	{
		boolean running = true;
		while(running)
		{
			/*Select Either 1 or 0*/
			Random ran = new Random();
			int i = ran.nextInt(2);
			
			for(TextArea t:ta)
			{
				t.appendText(String.valueOf(i));
				cur_width = t.getWidth();
			}
			
			try{
				/*Check for kill signal*/
				/*Slow down if resize happens because computer cannot keep up*/
				if(cur_width>300)
				{
					sleep(125);
				}
				else
				{
					sleep(50);
				}
			}catch(Exception e)
			{
				running = false;
			}
		}
	}

}
