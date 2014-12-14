import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import Graphs.Edge;
import Graphs.Stad;


public class AndraForbindelse_Dialog {

	 static ArrayList<Edge<Stad>> edgeobj=null;
	 
	
	public AndraForbindelse_Dialog(ArrayList<Edge<Stad>> edges){
		
		Object [] list = edges.toArray();
			
		Object andra = JOptionPane.showInputDialog(null, "VŠlj en fšrbindelse", "VŠlj en fšrbindelse", JOptionPane.QUESTION_MESSAGE,
	        null, list, "Titan");
		
	     
		 Edge<Stad> edge= (Edge<Stad>)andra;
		  
		 edgeobj= new ArrayList<Edge<Stad>>();
		 edgeobj.add(edge); 

	
	}
	public static ArrayList<Edge<Stad>> list(){
		return edgeobj;
	}
	
	
}
