package GUI.ExpanderGUI;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.input.MouseEvent;

public class SelectedListener implements EventHandler<MouseEvent> {
	private static ArrayList<String> stringList = new ArrayList<String>();
	private static ArrayList<Integer> indexs = new ArrayList<Integer>();
	
	private static ListView<String> list;
	
	public SelectedListener(ListView<String> l)
	{
		list = l;
	}
	
	/*return String list of selected items*/
	public static ArrayList<String> getindexList()
	{
		return stringList;
	}

	public static ArrayList<Integer> getIndexs() {
		return indexs;
	}

	@Override
	/*When mouse is clicked get selected items*/
	public void handle(MouseEvent arg0) {
		ObservableList<String> selectedItems =  list.getSelectionModel().getSelectedItems();
		ObservableList<Integer> selectedindexs = list.getSelectionModel().getSelectedIndices();
		for(String s:selectedItems)
		{
			stringList.add(s);
		}
		for(Integer i:selectedindexs)
		{
			indexs.add(i);
		}
	}

}