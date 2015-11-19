package GUI;
import java.util.Random;

import FileExpander.Deflater;
import FileExpander.Expander;
import ThreadList.ThreadList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application {
	
	public final static ThreadList Threadlist = new ThreadList();
	
	private final static String APPNAME = "Dinrar";
	private final static String[] APPDESC = {"Bigger is Always Better","Computer Load Up Celery Man","Beep Boop Computer Please","Winrar Winrar Chicken Dinrar"};
	
	public final static double DEFAULT_WIDTH = 900;
	public final static double DEFAULT_HEIGHT = 700;

	
	private Scene primaryscene;
	private static Expander E;
	private static Deflater D;
	
	@Override
	public void init()
	{
		E = new Expander();
		D = new Deflater();
	}
	
	@Override
	public void start(Stage primarystage)
	{

		this.primaryscene = new mainScene(primarystage);
		primarystage.setTitle(APPNAME + " : " + APPDESC[(new Random()).nextInt(APPDESC.length)]);
		primarystage.setScene(primaryscene);
		primarystage.show();
	}
	public static Expander getExpander()
	{
		return E;
	}
	public static Deflater getDeflater()
	{
		return D;
	}

	public static void main(String[] args) {
		launch();
	}
	
	

}
