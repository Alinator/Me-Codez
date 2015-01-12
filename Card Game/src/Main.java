import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import View.View;
import Controller.Controller;
public class Main {

	
	public static void main(String [] args){
	Controller controller = new Controller();
	View newView= new View(controller);
	
	newView.RunSim();
		
	}
}
