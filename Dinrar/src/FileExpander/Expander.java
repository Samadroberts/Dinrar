package FileExpander;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import GUI.GUI;
import sun.misc.IOUtils;

public class Expander extends FileFunctions {
	
	private int bytes_written=0;
	
	public Expander()
	{
		super();
	}

	@Override
	public void run() {
		GUI.getBinaryFiller();
		for(File f:this.getFiles()){
			System.out.println(f.getName());
			this.setCur_file(f);
			this.getFileBytes();
		}
		GUI.getBinaryFiller().stop();
		return;
	}

	@Override
	public void modify_bytes(byte[] data) {
		File f = this.getCur_file();
		this.write(f,data,true);
	}

	@Override
	public void initwrite() {
		File f = this.getCur_file();
		byte filename[] = new byte[f.getName().length()+1];
		for(int i = 0;i<f.getName().length();i++)
		{
			filename[i] = (byte)f.getName().charAt(i);
		}
		filename[f.getName().length()] = (byte)'#';
		bytes_written+=write(f,filename,false);
	}
}
