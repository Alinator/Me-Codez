import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * this class will draw a point on the screen.
 * @author alinazar
 *
 */
public class GraphPoint extends JComponent{

	private boolean klickad=false;
	public musLyss lyss=null;
	
	int counter=0;
	public Object  city=null;
	public GraphPoint(int x, int y, int sida ,Object city){
		setBounds(x,y,sida,sida);
		lyss= new musLyss();
		this.city=city;
		addMouseListener(lyss);

	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (klickad)
			g.setColor(Color.RED);
		else
			g.setColor(Color.BLUE);
		g.fillOval(0, 0, getWidth(), getHeight());
	}
	public void klick(){
		klickad = ! klickad;
		repaint(); 
	}
	class musLyss extends MouseAdapter{
		public void mouseClicked(MouseEvent event){
			Object nodobj= event.getSource()+""+city;
			
			if(Frame.objectCount.size() < 2 && !Frame.objectCount.contains(nodobj)){
				Frame.objectCount.add(nodobj);
				System.out.println(Frame.objectCount.size());
				System.out.println(city);
				klick();
			}else if(Frame.objectCount.contains(nodobj)){
				Frame.objectCount.remove(nodobj);
				System.out.println(Frame.objectCount.size());
				klick();
			}
		}
	}
}
