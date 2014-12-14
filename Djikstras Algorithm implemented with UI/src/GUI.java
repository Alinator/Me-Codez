import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Graphs.Graph;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * @author alinazar
 */
public class GUI extends JFrame{

	GraphPoint point=null;
	Point pointClicked=null;
	Cursor Dcursor= new Cursor(Cursor.DEFAULT_CURSOR);
	static ArrayList <Object> objectCount= new ArrayList<Object>();
	karta nyKarta=null;
	String city="";
	public JLabel citylabel=null;
	
	public GUI(){
		super("find you way ");
		setLayout(null);
		
		setLocation(100,100);
		setSize(600,600);
		
		JMenuBar menubar= new JMenuBar();
		setJMenuBar(menubar);
		
		JMenu ArkivMenu = new JMenu("Arkiv");
		JMenu OperationerMenu = new JMenu("Operationer");
		menubar.add(ArkivMenu);
		menubar.add(OperationerMenu);

		JMenuItem Ny = new JMenuItem("Ny");
		JMenuItem avsluta = new JMenuItem("Avsluta");
		ArkivMenu.add(Ny);
		ArkivMenu.add(avsluta);
		
		Ny.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Dcursor= new Cursor(Cursor.CROSSHAIR_CURSOR);
				setCursor(Dcursor);
			}
		});
		setVisible(true);
	}
}

