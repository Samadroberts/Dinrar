package GUI.ExpanderGUI;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ExpanderFocusListener implements ChangeListener<Boolean> {

	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				ExpanderGUI.getExpanderStage().requestFocus();
			}
		});
	}

}
