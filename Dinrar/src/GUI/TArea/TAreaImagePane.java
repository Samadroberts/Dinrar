package GUI.TArea;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TAreaImagePane extends GridPane {
	private static TAreaContainer TAC;
	public TAreaImagePane(Stage s)
	{
		super();
		TAC = new TAreaContainer(s);
		//Top,right,bottom,left
		this.setPadding(new Insets(5,0,3,0));
		this.prefWidthProperty().bind(s.widthProperty());
		this.setAlignment(Pos.CENTER);
		/*Temp picture*/
		String imageUrl = "https://cdn1.lockerdome.com/uploads/b1cae0eb288f1bf12d72face89bb879ab36d1b14d824b08f7230b7b7309a4188_large";
		Image image = new Image(imageUrl);
		ImageView imageView = new ImageView(image);
		/*Set to same height as textboxes*/
		imageView.setFitHeight(400);
		
		HBox hbox = new HBox(5);
		hbox.getChildren().add(imageView);
		
		this.add(TAC.getTextAreas()[0], 0, 0);
		this.add(hbox, 1, 0);
		this.add(TAC.getTextAreas()[1], 2, 0);
		
		
	}

}
