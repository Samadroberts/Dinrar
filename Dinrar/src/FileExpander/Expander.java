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
		
		try {
			Files.write(Paths.get("test.dinrar"), data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
