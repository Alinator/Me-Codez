import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import Graphs.*;
public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public GraphPoint point=null;
	public Point pointClicked=null;
	public Cursor Dcursor= new Cursor(Cursor.DEFAULT_CURSOR);
	static ArrayList <Object> objectCount= new ArrayList<Object>();
	public ArrayList <String> stadnamn = new ArrayList<String>();
	public karta nyKarta=null;
	public String city="";
	public JLabel citylabel=null;
	public JFileChooser jfc =null;
	public JPanel Buttons=null;
	public Stad stad1=null;
	public Stad stad2=null;

	protected JButton hittaVag = null;
	protected JButton visaForbindelse = null;
	protected JButton nyPlats = null;
	protected JButton nyForbindelse =null;
	protected JButton andraForbindelse =null;
	protected ArrayList<Edge<Stad>> c=null;
	protected ArrayList<Edge<Stad>> forbindelser=null;
	public Stad CITY=null;
	//the graph
	protected Graph<Stad> graph= new ListGraph<Stad>();
	public Frame(){



		//------------------- DEFAULT --------------------//
		super("Pathfinder - Michel Nickbon & Ali Nazar ");
		setSize(700,120);
		//		setLayout(new GridLayout(1,5));
		//-----------------------------------------------//
		//-------------------------- MENY -------------------------------//
		JMenuBar mainMenu = new JMenuBar();
		setJMenuBar(mainMenu);
		JMenu Arkiv = new JMenu("Arkiv");
		mainMenu.add(Arkiv);
		addMouseListener(new musLyss());

		JMenuItem oppna = new JMenuItem("Ny");
		Arkiv.add(oppna);

		oppna.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				jfc = new JFileChooser();
				int svar=jfc.showDialog(Frame.this, "Select");
				if (svar == JFileChooser.APPROVE_OPTION){
					File fil = jfc.getSelectedFile();
					String namn = fil.getAbsolutePath();

					if(nyKarta != null){
						getContentPane().removeAll();
						objectCount.clear();
						add(Buttons);
					}

					nyKarta = new karta(namn);
					//empty the graph
					graph.getNodes().clear();
					//empty the frame
					add(nyKarta,BorderLayout.CENTER);

					hittaVag.setEnabled(true);
					visaForbindelse.setEnabled(true);
					nyPlats.setEnabled(true);
					nyForbindelse.setEnabled(true);
					andraForbindelse.setEnabled(true);

					pack();
				}
			}
		});


		JMenuItem avsluta = new JMenuItem("Avsluta");
		Arkiv.add(avsluta);

		avsluta.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});


		JMenu Operationer = new JMenu("Operationer");
		mainMenu.add(Operationer);


		JMenuItem operationsVal1 = new JMenuItem ("Hitta vŠg");
		Operationer.add(operationsVal1);

		operationsVal1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				if(objectCount.size() == 2){
					int total=0;
					Forbindelse();

					List<Edge<Stad>> route=GraphMethods.shortestPath(graph, stad1, stad2);

					for(Edge<Stad> t: route){
						total += t.getWeight();
					}
					new HittaVag_Dialog(stad1, stad2,route,total);
				}else{
					JOptionPane.showMessageDialog(null,"Två platser måste vara valda!","Felmeddelande",JOptionPane.ERROR_MESSAGE);				}
			}
		});

		JMenuItem operationsVal2 = new JMenuItem ("Visa fšrbindelse");
		Operationer.add(operationsVal2);

		operationsVal2.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {

				if(objectCount.size() == 2){

					new VisaForbindelser_Dialog(Forbindelse());
				}else{
					JOptionPane.showMessageDialog(null,"Två platser måste vara valda!","Felmeddelande",JOptionPane.ERROR_MESSAGE);				

				}
			}
		});


		JMenuItem operationsVal3 = new JMenuItem ("Ny plats");
		Operationer.add(operationsVal3);


		operationsVal3.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0){
				Dcursor= new Cursor(Cursor.CROSSHAIR_CURSOR);
				setCursor(Dcursor);
			}
		});


		JMenuItem operationsVal4 = new JMenuItem ("Ny fšrbindelse");
		Operationer.add(operationsVal4);

		operationsVal4.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {

				nyforbindelse();

			}
		});


		JMenuItem operationsVal5 = new JMenuItem ("Šndra fšrbindelser");
		Operationer.add(operationsVal5);

		operationsVal5.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				andraforbindelse();
			}
		});
		//-------------------------------------------------------------------------//

		//---------------------- PANEL FšR BUTTONS ------------------------//
		Buttons = new JPanel();
		Buttons.setBorder(BorderFactory.createTitledBorder("Options"));
		add(Buttons);

		hittaVag = new JButton("Hitta vŠg");
		hittaVag.setEnabled(false);
		Buttons.add(hittaVag);

		hittaVag.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(objectCount.size() == 2){
					int total=0;
					Forbindelse();

					List<Edge<Stad>> route=GraphMethods.shortestPath(graph, stad1, stad2);

					for(Edge<Stad> t: route){
						total += t.getWeight();
					}
					new HittaVag_Dialog(stad1, stad2,route,total);
				}else{
					JOptionPane.showMessageDialog(null,"Två platser måste vara valda!","Felmeddelande",JOptionPane.ERROR_MESSAGE);				}
			}
		});

		visaForbindelse = new JButton("Visa fšrbindelse");
		Buttons.add(visaForbindelse);
		visaForbindelse.setEnabled(false);
		visaForbindelse.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {

				if(objectCount.size() == 2){

					new VisaForbindelser_Dialog(Forbindelse());
				}else{
					JOptionPane.showMessageDialog(null,"Två platser måste vara valda!","Felmeddelande",JOptionPane.ERROR_MESSAGE);				}
			}
		});


		nyPlats = new JButton("Ny Plats");
		nyPlats.setEnabled(false);
		Buttons.add(nyPlats);
		nyPlats.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {

				Dcursor= new Cursor(Cursor.CROSSHAIR_CURSOR);
				setCursor(Dcursor);
			}
		});



		nyForbindelse = new JButton("Ny fšrbindelse");
		Buttons.add(nyForbindelse);
		nyForbindelse.setEnabled(false);
		nyForbindelse.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				nyforbindelse();
			}
		});



		andraForbindelse = new JButton("Šndra fšrbindelse");
		Buttons.add(andraForbindelse);
		andraForbindelse.setEnabled(false);
		andraForbindelse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				andraforbindelse();
			}
		});
		// ----------------------------------------------------------- //
		//------------------- OTHER --------------------//
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		// --------------------------------------------//
	}
	public void nyforbindelse(){
		if(objectCount.size() == 2){
			stadnamn.clear();
			for(Object e : objectCount){
				String referens= e.toString();
				String stad= referens.substring(121, referens.length());
				stadnamn.add(stad);
			}

			for(Stad e : graph.getNodes()){
				if(e.getNamn().equalsIgnoreCase(stadnamn.get(0))){
					stad1=e;
				}else if(e.getNamn().equalsIgnoreCase(stadnamn.get(1))){
					stad2=e;
				}
			}

			NyForbindelse_Dialog forbindelse=new NyForbindelse_Dialog(false,null);

			System.out.println(forbindelse.getN()+" "+forbindelse.getTime());
			System.out.println(graph.getEdgesBetween(stad1, stad2));
			if(forbindelse.getN() != "" && forbindelse.getTime() != 0){
				graph.Connect(stad1, stad2, forbindelse.getN(), forbindelse.getTime());

				stad1=null;
				stad2=null;
			}
		}else{
			JOptionPane.showMessageDialog(null,"Två platser måste vara valda!","Felmeddelande",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void andraforbindelse(){
		if(objectCount.size() == 2){
			c= new ArrayList<Edge<Stad>>();
			for(Edge<Stad> me : Forbindelse()){
				System.out.println(me);
				c.add(me);
			}
			if(c.size() == 1){

				NyForbindelse_Dialog f=new NyForbindelse_Dialog(true,c.get(0));

				for(Edge<Stad> st : forbindelser){
					if(st.getName().equalsIgnoreCase(c.get(0).getName())){
						if(f.getTime() != 0){
							st.setWeight(f.getTime());
						}
					}
				}
			}else{
				new AndraForbindelse_Dialog(c);
				try{	
					NyForbindelse_Dialog fo=new NyForbindelse_Dialog(true,AndraForbindelse_Dialog.list().get(0));
					for(Edge<Stad> st : forbindelser){
						if(st.getName().equalsIgnoreCase(AndraForbindelse_Dialog.list().get(0).getName())){
							if(fo.getTime() != 0){
								st.setWeight(fo.getTime());
							}
						}
					}
				}catch(Exception ex){
					System.out.println("aborted");
				}
			}
		}else{
			JOptionPane.showMessageDialog(null,"Två platser måste vara valda!","Felmeddelande",JOptionPane.ERROR_MESSAGE);
		}
	}
	class musLyss extends MouseAdapter{
		public void mouseClicked(MouseEvent event){

			if(Dcursor.getName().equalsIgnoreCase("HŒrkorsmarkšr")){

				JOptionPane dialogue=new JOptionPane();
				String city =dialogue.showInputDialog("Enter city name");
				
				if(city == null){
					System.out.println("aborted");
				}else{
				try{
					Integer.parseInt(city);
					JOptionPane.showMessageDialog(null, "The name must be a String not a number, try again!");
				}catch(NumberFormatException ex){
					
				citylabel = new JLabel(city);
				point=new GraphPoint(event.getX()-30,event.getY()-60,30,city);
				CITY= new Stad(city);
				graph.add(CITY);
				citylabel.setBounds(event.getX()-20, event.getY()-40, 100,20);
				citylabel.setVisible(true);
				add(point,1);
				add(citylabel,1);
				repaint();
				Dcursor= new Cursor(Cursor.DEFAULT_CURSOR);
				setCursor(Dcursor);
				}
				}
			}else{
				System.out.println("inte hŒrkorsmarkšr");
			}
		}
	}
	public ArrayList<Edge<Stad>> Forbindelse(){
		stadnamn.clear();
		for(Object me : objectCount){
			String referens= me.toString();
			String stad= referens.substring(121, referens.length());
			stadnamn.add(stad);
		}

		for(Stad x : graph.getNodes()){
			if(x.getNamn().equalsIgnoreCase(stadnamn.get(0))){
				stad1=x;
			}else if(x.getNamn().equalsIgnoreCase(stadnamn.get(1))){
				stad2=x;
			}
		}
		if(stad1 ==null && stad2 == null){
			JOptionPane.showMessageDialog(null, "inga fšrbindelser mellan dessa!");
		}else{
			ArrayList<Edge<Stad>> blo=graph.getEdgesBetween(stad1, stad2);
			ArrayList<Edge<Stad>> blob=graph.getEdgesBetween(stad2, stad1);
			forbindelser= new ArrayList<Edge<Stad>>();
			forbindelser.addAll(blo);
			forbindelser.addAll(blob);
		}
		ArrayList<Edge<Stad>> listedge=graph.getEdgesBetween(stad1, stad2);
		return listedge;
	}
}