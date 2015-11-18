package GUI;

import java.util.Random;

import javafx.scene.control.TextArea;

public class BinaryFiller extends Thread {

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
				/*Append to Text Area*/
				t.appendText(String.valueOf(i));
			}
			try{
				/*Check for kill signal*/
				/*Slow down if resize happens because computer cannot keep up*/
				sleep(125);
			}catch(Exception e)
			{
				running = false;
			}
		}
		try{
		}catch(Exception e)
		{
			
		}
	}

}
