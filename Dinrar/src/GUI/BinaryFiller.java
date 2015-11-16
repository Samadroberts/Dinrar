package GUI;

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
			for(TextArea t:ta)
			{
				t.appendText("123");
			}
			try{
				sleep(500);
			}catch(Exception e)
			{
				running = false;
			}
			
		}
	}

}
