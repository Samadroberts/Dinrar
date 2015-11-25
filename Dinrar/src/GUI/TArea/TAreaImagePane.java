package GUI.TArea;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPaneBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TAreaImagePane extends GridPane {
	private static final String filename = "Images/DW.jpg";
	private static Image i;
	
	
	public TAreaImagePane(Stage s)
	{
		super();
		new TAreaContainer(s);
		//Top,right,bottom,left
		this.setPadding(new Insets(5,0,3,0));
		this.prefWidthProperty().bind(s.widthProperty());
		this.setAlignment(Pos.CENTER);
		i = new Image(this.getClass().getResource(filename).toExternalForm());
		ImageView imageView = new ImageView(i);
		/*Set to same height as textboxes*/
		imageView.setFitHeight(400);
		HBox hbox = new HBox();
		this.add(TAreaContainer.getTextAreas()[0], 0, 0);
		hbox.getChildren().add(imageView);
		this.add(hbox, 1, 0);
		this.add(TAreaContainer.getTextAreas()[1], 2, 0);
	}

}
