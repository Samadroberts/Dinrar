package GUI.TArea;

import java.net.URL;

import javax.imageio.ImageIO;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TAreaImagePane extends GridPane {
	private static final TAreaContainer TAC = new TAreaContainer();
	public TAreaImagePane(Stage s)
	{
		super();
		//this.prefWidthProperty().bind(s.widthProperty());
		this.setAlignment(Pos.CENTER);
		/*Temp picture*/
		String imageUrl = "https://cdn1.lockerdome.com/uploads/b1cae0eb288f1bf12d72face89bb879ab36d1b14d824b08f7230b7b7309a4188_large";
		Image image = new Image(imageUrl);
		ImageView imageView = new ImageView(image);
		HBox hbox = new HBox(5);
		hbox.getChildren().add(imageView);
		this.add(TAC.getTextAreas()[0], 0, 0);
		this.add(hbox, 1, 0);
		this.add(TAC.getTextAreas()[1], 2, 0);
	}

}
