import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Graphs.Edge;
import Graphs.Stad;


public class HittaVag_Dialog {
	
	 public HittaVag_Dialog(Stad st1,Stad st2,List<Edge<Stad>> route,int total){  
	    	
	    	final JTextArea textArea = new JTextArea();;
			textArea.setEditable(false);
			JScrollPane scrollPane = new JScrollPane(textArea);		
			scrollPane.setPreferredSize(new Dimension(350, 150));
			
			// filling the list
			textArea.append("Från "+st1.getNamn()+" till "+st2.getNamn()+"\n");
			for(Edge<Stad> me : route){
				textArea.append(me.toString()+"\n");
			}
			textArea.append("Total km's: ("+total+")");
			
			
			
			
			JOptionPane.showMessageDialog(null, scrollPane, "Snabbaste Vägen", JOptionPane.INFORMATION_MESSAGE); 
	         
	         
	    }  
	
	
	
	

}
