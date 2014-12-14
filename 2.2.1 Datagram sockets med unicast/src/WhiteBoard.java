import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class WhiteBoard extends JPanel{

	
	protected static int lastX = 0;
	protected static int lastY = 0;
	protected static int currentX = 0;
	protected static int currentY = 0;
	
	public String Coordinates=null;
	public static JFrame frame=null;
	Communication communication=null;
	JFrame frame2=null;
	
	public WhiteBoard(){
		frame2 = WhiteBoard.frame;
	}
	
	public WhiteBoard(int myport, String remoteHost, int remoteport){
		frame = new JFrame();
		frame.setSize(400, 400);
		
		communication = new Communication(myport,remoteHost,remoteport);
		
		frame.getContentPane().add(this);
		frame.getContentPane().setBackground(Color.white);
		frame.getContentPane().addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getX()+" : "+e.getY());
				saveCoordinates(e.getX(),e.getY());
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				saveCoordinates(e.getX(),e.getY());
				frame.getContentPane().repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		frame.getContentPane().addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getX()+" : "+e.getY());
			    saveCoordinates(e.getX(),e.getY());
			    frame.getContentPane().repaint();
			    communication.sendCoordinates(Coordinates);
			}
		});
		
		frame.setVisible(true);
	}

	public void saveCoordinates(int x, int y){
		this.lastX = x;
		this.lastY = y;
		this.currentX = x;
		this.currentY = y;
		
		Coordinates = Integer.toString(this.lastX)+":"+Integer.toString(this.lastY);
		
	}
	
	@Override
	protected void paintComponent(Graphics g){
		Graphics2D gr = (Graphics2D) g;
		gr.drawLine(this.lastX,this.lastY,this.currentX,this.currentY);
	}
	
}
