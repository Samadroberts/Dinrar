package FileExpander;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import GUI.GUI;

public class Expander extends FileFunctions {
	public Expander()
	{
		super();
		//filemap = new HashMap<File,Integer>();
	}

	@Override
	public void run() {
		GUI.getBinaryFiller();
		for(File f:this.getFiles()){
			System.out.println(f.getName());
			this.setCur_file(f);
			//DO Stuff
		}
		GUI.getBinaryFiller().stop();
		return;
	}
}
