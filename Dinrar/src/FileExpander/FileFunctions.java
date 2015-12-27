package FileExpander;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public abstract class FileFunctions extends Thread {
	
	private File cur_file;
	HashMap<File,Integer> filemap;
	private byte[] filedata;
	
	

	protected static final int BYTES_TO_READ_DEFAULT = 8192;//Some website said 8kb was most efficient read value lol
	/*Min size a file needs to be to get default read size else the file is read in increments of 1/20 with the last read being 1/20+remainder*/
	protected static final int MIN_DEFAULT_READ_FILE_SIZE = 163840;
	protected static final int FILE_DIV_RATIO = 20;
	private static final int MAX_BYTES_TO_WRITE = 4096;
	protected boolean appendfirstfile = true;
	
	/*Change*/
	private static final String TEMPFNAME = "test.dinrar";
	
	
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
	
	/*Gets the number of bytes that will be read given a file size
	 * if the file is less then MIN_DEFAULT_READ_FILE_SIZE then the function
	 * returns a value which will result in 20-21 reads depending on if the size
	 * of the file can be divided evenly by 20*/
	
	protected int calculate_bytes_per_read(File f)
	{
		if(f.length()<MIN_DEFAULT_READ_FILE_SIZE)
		{
			return ((int)f.length())/FILE_DIV_RATIO;
		}
		return BYTES_TO_READ_DEFAULT;
	}
	
	/*This function determine the number of file reads that will occur based on the amount
	 * of bytes that was calculated to be read in the function calculate_bytes_per_read()*/
	protected int calculate_number_of_reads(File f)
	{
		int rval;
		int fsize = (int)f.length();
		if(fsize<MIN_DEFAULT_READ_FILE_SIZE)
		{
			rval = FILE_DIV_RATIO;
			if(fsize%FILE_DIV_RATIO>0)
			{
				rval++;
			}
			return rval;
		}
		int bytes_per_read = calculate_bytes_per_read(f);
		rval = fsize/bytes_per_read;
		if(fsize%bytes_per_read>0)
		{
			rval++;
		}
		return rval;
	}
	/*This function determines how many bytes of the calculated bytes to be added for the given
	 * file will be added per write*/
	protected long calculate_bytes_per_write(long num_of_bytes_to_write,int num_reads)
	{
		return num_of_bytes_to_write/num_reads;
	}
	/*Returns the remainder which will need to be added for the last write of random data*/
	protected long calculate_remainder_bytes_for_last_write(long num_of_bytes_to_write,int num_reads)
	{
		return num_of_bytes_to_write%num_reads;
	}
	
	
	
	public abstract void modify_bytes(byte[] data);
	
	public int write(File f,byte[] data,boolean append)
	{
		
		try (
			FileOutputStream output = new FileOutputStream(TEMPFNAME, append)) {
			DataOutputStream dos = new DataOutputStream(output);
			dos.write(data);
			return dos.size();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int write(File f,ByteBuffer b,boolean append)
	{
		try (
				FileOutputStream output = new FileOutputStream(TEMPFNAME, append)) {
				FileChannel channel = output.getChannel();
				return channel.write(b);
			}
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		
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
