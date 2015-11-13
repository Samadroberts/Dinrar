package GUI;
import java.util.Random;

import FileExpander.Deflater;
import FileExpander.Expander;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application {
	
	private final static String APPNAME = "Dinrar";
	private final static String[] APPDESC = {"Bigger is Always Better","Computer Load Up Celery Man","Beep Boop Computer Please"};
	
	public final static double DEFAULT_WIDTH = 900;
	public final static double DEFAULT_HEIGHT = 700;

	
	private Scene primaryscene;
	private static Expander E;
	private static Deflater D;
	
	@Override
	public void start(Stage primarystage)
	{
		E = new Expander();
		D = new Deflater();
		this.primaryscene = new mainScene(primarystage);
		primarystage.setTitle(APPNAME + " : " + APPDESC[(new Random()).nextInt(APPDESC.length)]);
		primarystage.setScene(primaryscene);
		primarystage.show();
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	public static Expander getExpander()
	{
		return E;
	}
	public static Deflater getDeflater()
	{
		return D;
	}
	
	

}
