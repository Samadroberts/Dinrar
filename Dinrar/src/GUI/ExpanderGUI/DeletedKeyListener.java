package GUI.ExpanderGUI;

import FileExpander.Expander;
import GUI.GUI;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class DeletedKeyListener implements EventHandler<KeyEvent> {
	
	private FileFunctionsGUI e;
	private Expander expander = GUI.getExpander();
	
	public DeletedKeyListener(FileFunctionsGUI gui)
	{
		this.e = gui;
	}

	/*Remove files from list if they press delete*/
	@Override
	public void handle(KeyEvent arg0) {
		
		if(arg0.getCode()==KeyCode.DELETE)
		{
			e.remove_files(SelectedListener.getindexList());
			expander.removeFiles(SelectedListener.getfiles());
		}
	}

}
