package GUI.TArea;




import java.io.File;

import GUI.BinaryFiller;
import ThreadList.ThreadList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class TAreaImagePane extends GridPane {
	private static TAreaContainer TAC;
	private static final String filename = "DW.jpg";
	private static Image i;
	
	
	public TAreaImagePane(Stage s)
	{
		super();
		TAC = new TAreaContainer(s);
		//Top,right,bottom,left
		this.setPadding(new Insets(5,0,3,0));
		this.prefWidthProperty().bind(s.widthProperty());
		this.setAlignment(Pos.CENTER);
		
		i = new Image(this.getClass().getResource(filename).toExternalForm());
		ImageView imageView = new ImageView(i);
		/*Set to same height as textboxes*/
		imageView.setFitHeight(400);
		
		HBox hbox = new HBox(10);
		this.add(TAC.getTextAreas()[0], 0, 0);
		this.setStyle("-fx-focus-color: transparent;");
		hbox.getChildren().add(imageView);
		this.add(hbox, 1, 0);
		this.add(TAC.getTextAreas()[1], 2, 0);
		BinaryFiller bf = new BinaryFiller(TAC.getTextAreas());
		bf.start();
		
		
	}

}
