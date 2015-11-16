package ThreadList;
import FileExpander.Deflater;
import FileExpander.Expander;
import GUI.BinaryFiller;
import javafx.scene.control.TextArea;


/*Used to keep track of all the threads*/
public class ThreadList {
	
	
	private  BinaryFiller BinaryFillerThread;//pos 0
	private Deflater DeflaterThread;//pos 1
	private Expander ExpanderThread;//pos2
	private  final Thread[] Threadlist = {BinaryFillerThread,DeflaterThread,ExpanderThread};
	
	
	public Thread[] getthreadList()
	{
		return Threadlist;
	}
	
	public void initializeBinaryFiller(TextArea[] t)
	{
		BinaryFillerThread = new BinaryFiller(t);
	}
}
