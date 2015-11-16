package ThreadList;
import GUI.BinaryFiller;
import javafx.scene.control.TextArea;


/*Used to keep track of all the threads*/
public class ThreadList {
	
	
	private  BinaryFiller BinaryFillerThread;//pos 0
	private  final Thread[] Threadlist = {BinaryFillerThread};
	
	
	public Thread[] getthreadList()
	{
		return Threadlist;
	}
	
	public void initializeBinaryFiller(TextArea[] t)
	{
		BinaryFillerThread = new BinaryFiller(t);
	}
}
