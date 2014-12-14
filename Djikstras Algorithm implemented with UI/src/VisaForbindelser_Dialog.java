import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Graphs.Edge;
import Graphs.Stad;


public class VisaForbindelser_Dialog {

	public VisaForbindelser_Dialog(ArrayList<Edge<Stad>> edges){
		
		final JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
	
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		for(int x=0; x < edges.size(); x++){
		textArea.append(edges.get(x).toString()+"\n");
		}
		scrollPane.setPreferredSize(new Dimension(350, 150));
		JOptionPane.showMessageDialog(null, scrollPane, "Visa Fšrbindelser", JOptionPane.INFORMATION_MESSAGE); 
		
	}
	
	
}
