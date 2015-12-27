package FileExpander;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import GUI.GUI;
import GUI.ExpanderGUI.ExpanderGUI;
import sun.misc.IOUtils;

public class Expander extends FileFunctions implements Observer {

	private int bytes_written=0;
	/*Used to calculate the number of bytes needed to be written to the file*/
	private BigInteger total_bytes_to_write; //in bytes
	private HashMap<File,BigInteger> bytes_to_write_per_file = new HashMap<File,BigInteger>();
	private final static String START_TAG = "_start#";
	private BigInteger cur_file_size;

	/*Change*/
	private String output_file = "test.dinrar";

	public Expander()
	{
		super();
	}

	@Override
	public void run() {
		GUI.getBinaryFiller().start();
		appendfirstfile = false;
		calculate_bytes_to_add();
		calculate_bytes_per_file();
		long start;
		long stop;
		
		start = System.currentTimeMillis();
		for(File f:this.getFiles()){
			System.out.println(f.getName());
			setCur_file(f);
			getFileBytes(calculate_bytes_per_read(f));
		}
		stop = System.currentTimeMillis();
		System.out.println("Elapsed time: " + (stop-start)/1000+" sec\n");
		System.out.println("Elapsed time: " + (stop-start)+" ms\n");
		System.out.println("DONE\n");
		//GUI.getBinaryFiller().stop();
		return;
	}


	/*Calculates the total number of bytes that need to be added to make the new file the desired size*/
	private void calculate_bytes_to_add()
	{
		for(File f:this.getFiles())
		{
			total_bytes_to_write=total_bytes_to_write.subtract(new BigInteger(String.valueOf(f.getName().getBytes().length)));
			total_bytes_to_write=total_bytes_to_write.subtract(new BigInteger(String.valueOf(START_TAG.getBytes().length)));
			total_bytes_to_write=total_bytes_to_write.subtract(new BigInteger(String.valueOf(f.length())));
		}
	}

	/*Calculates the number of bytes that will be added to each file*/
	private void calculate_bytes_per_file()
	{
		int num_of_files = getFiles().size();
		int remainder = total_bytes_to_write.remainder(new BigInteger(String.valueOf(num_of_files))).intValue();
		total_bytes_to_write = total_bytes_to_write.subtract(new BigInteger(String.valueOf(remainder)));
		for(int i = 0;i<num_of_files;i++)
		{
			bytes_to_write_per_file.put(getFiles().get(i),
					total_bytes_to_write.divide(new BigInteger(String.valueOf(num_of_files))));
			if(i==(num_of_files-1))
			{
				bytes_to_write_per_file.remove(getFiles().get(i));
				BigInteger temp = total_bytes_to_write.divide(new BigInteger(String.valueOf(num_of_files)));
				temp=temp.add(new BigInteger(String.valueOf(remainder)));
				bytes_to_write_per_file.put(getFiles().get(i),temp);
			}
		}
	}

	/*Get all the file info in an array of bytes*/
	public void getFileBytes(int num_of_bytes)
	{
		try{

			FileInputStream f=new FileInputStream( getCur_file() );
			FileChannel ch = f.getChannel( );
			ByteBuffer bb = ByteBuffer.allocate(num_of_bytes);
			cur_file_size = new BigInteger((String.valueOf(this.getCur_file().length())));
			initwrite();
			int num_of_reads = super.calculate_number_of_reads(getCur_file());
			long bytesforfile = bytes_to_write_per_file.get(getCur_file()).longValue();
			long bwrite = calculate_bytes_per_write(bytesforfile,num_of_reads);
			
			//if the amount of bytes per write is greater then the max integer value
			if(bwrite>Integer.MAX_VALUE)
			{
				bwrite = Integer.MAX_VALUE;
			}
			
			for(int i = 0;i<num_of_reads;i++)
			{
				ch.read(bb);
				bb.position(0);
				write(getCur_file(),bb,true);//change so not getCur_file() but actual output file name
				bb.clear();
				addHashedData(getCur_file(),(int)bwrite);
				bytesforfile-=bwrite;
			}
			while(bytesforfile>=bwrite)
			{
				addHashedData(getCur_file(),(int)bwrite);
				bytesforfile-=bwrite;
			}
			if(bytesforfile>0)
			{
				addHashedData(getCur_file(),(int)bytesforfile);
			}
			f.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	protected void addHashedData(File f,int num_of_bytes)
	{
		/*Make random number generator based off the seed of the hash of the file */
		Random ran = new Random((long)filemap.get(f));
		byte[] ranarr = new byte[num_of_bytes];
		ran.nextBytes(ranarr);	//fills ranarr with 1024 bytes of random values
		write(f, ranarr, true);//change so its not f
	}

	@Override
	public void modify_bytes(byte[] data) {
		File f = this.getCur_file();
		this.write(f,data,true);
	}

	public void initwrite() {
		File f = this.getCur_file();
		byte filename[] = new byte[f.getName().length()+START_TAG.length()+String.valueOf(f.length()).length()+1];
		for(int i = 0;i<f.getName().length();i++)
		{
			filename[i] = (byte)f.getName().charAt(i);
		}
		for(int i = 0;i<START_TAG.length();i++)
		{
			filename[f.getName().length()+i] = (byte)START_TAG.charAt(i);
		}
		String fsize = String.valueOf(f.length());
		fsize+="#";
		for(int i = 0;i<fsize.length();i++)
		{
			filename[f.getName().length()+START_TAG.length()+i] = (byte)fsize.charAt(i);
		}

		write(super.getCur_file(),filename,appendfirstfile);
		appendfirstfile = true;
	}

	public BigInteger getCur_file_size() {
		return cur_file_size;
	}
	@Override
	public void update(Observable expandergui, Object arg1) {
		/*Size in bytes*/
		total_bytes_to_write = new BigInteger(String.valueOf(((ExpanderGUI)expandergui).getsize()*1024*1024));
	}
}
