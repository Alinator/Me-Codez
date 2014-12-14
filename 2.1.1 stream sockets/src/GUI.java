import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class GUI{
	
	JTextArea textAreaone = null;
	
	public GUI(String host, int port){
		JFrame newframe = new JFrame("Host: "+host+" : Port: "+port);
		newframe.setSize(new Dimension(500,500));
		
		newframe.setLayout(new FlowLayout());
		
		   textAreaone = new JTextArea(5, 10);
		    textAreaone.setPreferredSize(new Dimension(400, 400));
		    textAreaone.setLineWrap(true);
		    textAreaone.setEditable(false);
		    newframe.add(textAreaone);
		
		newframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newframe.setVisible(true);
	}
	
	public void myChatFrame(String message){
		textAreaone.append(message+"\n");
	}
	
	
}
