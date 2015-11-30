package FileExpander;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import GUI.GUI;

public abstract class FileFunctions implements Runnable {
	
	private File cur_file;
	HashMap<File,Integer> filemap;
	private byte[] filedata;
	
	protected static final long MEGABYTE = 1000000;
	protected static final long GIGABYTE = 1000000000;
	protected static final BigInteger TERABYTE = new BigInteger("1000000000000");
	private static final int BYTES_TO_READ = 3096; //leaves 1000 characters to be written assuming each character is 1 byte
	private static final int MAX_BYTES_TO_WRITE = 4096;
	
	
	public FileFunctions()
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
	
	
	/*Get all the file info in an array of bytes*/
	public void getFileBytes()
	{
		RandomAccessFile filereader = null;
		byte[] Fileinfo = null;
		
		try {
			/*Set to read only*/
			filereader = new RandomAccessFile(this.cur_file,"r");
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Fileinfo = new byte[BYTES_TO_READ];
		try {
			filereader.read(Fileinfo);
			{
				initwrite();
			}
			while(filereader.read(Fileinfo)!=-1)
			{
				write(this.getCur_file(),Fileinfo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done");
	}
	
	public abstract void modify_bytes(byte[] data);
	public abstract void initwrite();
	
	public void write(File f,byte[] data)
	{
		try {
			Files.write(Paths.get("test.dinrar"), data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public File getCur_file() {
		return cur_file;
	}

	public void setCur_file(File cur_file) {
		this.cur_file = cur_file;
	}
	
	public ArrayList<File> getFiles() {
		ArrayList<File> r = new ArrayList<File>();
		for(File f:filemap.keySet())
		{
			r.add(f);
		}
		return r;
	}
	
	public void removeFiles(ArrayList<File> files)
	{
		ArrayList<File> toremove = new ArrayList<File>();
		for(File f:files)
		{
			for(File f1:filemap.keySet())
			{
				if(f1.getName().equals(f.getName()))
				{
					toremove.add(f1);
				}
			}
		}
		for(File  f:toremove)
		{
			filemap.remove(f);
		}
	}
	
	public void removeallFiles()
	{
		filemap.clear();
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
	
	public abstract void run();

}
