package FileExpander;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public class Deflater extends Thread {
	HashMap<File,Integer> filemap;
	
	
	public Deflater()
	{
		filemap = new HashMap<File,Integer>();
	}
	
	public void addFile(File f)
	{
		filemap.put(f, f.toString().hashCode());
	}
	
	public void addFile(List<File> filelist)
	{
		for(File f:filelist)
		{
			addFile(f);
		}
		/*For testing*/
		printfiles();
	}
	
	@Override
	public void run()
	{
		/*Deflate File*/
	}
	
	
	/*For Testing*/
	private void printfiles()
	{
		String s = new String();
		for(File f:filemap.keySet())
		{
			s+= f.toString()+ " : " + filemap.get(f).toString() + "\n";
		}
		System.out.println(s);
	}
}
