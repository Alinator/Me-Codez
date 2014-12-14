import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;


public class karta extends JPanel{

	ImageIcon karta=null;

	public karta(String filpath){
		setLayout(null);
		karta = new ImageIcon(filpath);	
	
		setPreferredSize(new Dimension(800,karta.getIconHeight()));
	}
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		System.out.println("i do get here");
		g.drawImage(karta.getImage(), 0,54, 800,karta.getIconHeight(), this);
	}
}
