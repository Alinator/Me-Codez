package View;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import Controller.Controller;
import Model.CardStack;

public class View {
	private Controller controller;
	public JFrame frame=null;
	public JLayeredPane pane= null;
	public ArrayList<JCheckBox> checkboxarray= new ArrayList<>();
	public int number=0;
	public int ycoordinate=0;
	
	JLabel firstCard;
	JLabel DeckBackSide;
	public View(Controller controller){
		this.controller = controller;
	}
	
	public void RunSim(){
		
		frame = new JFrame("Kortspel IStone");
		pane = new JLayeredPane();
		pane.setPreferredSize(new Dimension(1000,700));
		
		controller.callForAddingDeck();
		// sortera knappen
		JButton sorteraknappen = new JButton("Sortera kortlek");
		sorteraknappen.setBounds(30, 600, 200, 50);
		pane.add(sorteraknappen);
		
		sorteraknappen.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				CardStack.cardstack.clear();
				CardStack.Allavaldakort.clear();
				for(int x=0; x < checkboxarray.size();x++){
					if(checkboxarray.get(x).isSelected()){
						System.out.println(checkboxarray.get(x).getText());
						controller.callForBuildDeck(checkboxarray.get(x).getText());
					}
				}
				
				CardStack.sorteraAllavaldakort();
			}
		});
		//
		// drakort knappen
		JButton drakortknappen = new JButton("Dra kort");
		drakortknappen.setBounds(280, 600, 200, 50);
		pane.add(drakortknappen);
		drakortknappen.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String dragetkort = null;
				try{
					dragetkort = CardStack.cardstack.pop().getCardpathname();
					DrawTheFirstCardInDeck(dragetkort);
					System.out.println(dragetkort);
					
				}catch(EmptyStackException ex){
					JOptionPane.showMessageDialog(null,"Alla kort dragna ! blanda eller sortera om!");
					DeckBackSide.setVisible(true);
				}
			}
		});
		//
		//blanda korten knappen
		JButton blandaknappen = new JButton("blanda korten");
		blandaknappen.setBounds(500,600, 200, 50);
		pane.add(blandaknappen);
		blandaknappen.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CardStack.cardstack.clear();
				CardStack.Allavaldakort.clear();
				for(int x=0; x < checkboxarray.size();x++){
					if(checkboxarray.get(x).isSelected()){
						System.out.println(checkboxarray.get(x).getText());
						controller.callForBuildDeck(checkboxarray.get(x).getText());
					}
				}
				CardStack.blandaKortlek();
			}
		});
		
		// add new Deck button
		JButton addNewDeckKnappen = new JButton("lägg till ny kortlek");
		addNewDeckKnappen.setBounds(750,600, 200, 50);
		pane.add(addNewDeckKnappen);
		addNewDeckKnappen.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controller.callForAddingDeck();
				JOptionPane.showMessageDialog(null, "Ny kortlek tillagd i spelet!");
				CreatecheckBox();
			}
		});
		drawBacksideOfDeck();
		CreatecheckBox();
		frame.add(pane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public void CreatecheckBox(){
		number++;
		ycoordinate+=20;
		JCheckBox checkbox = new JCheckBox("kortlek nr "+number);
		checkboxarray.add(checkbox);
		checkbox.setBounds(600,ycoordinate , 100, 20);
		pane.add(checkbox);
	}
	
	public void drawBacksideOfDeck(){
		if(firstCard != null){
			firstCard.setVisible(false);
			firstCard = null;
		}
			DeckBackSide = new JLabel(new ImageIcon("src/blandar.gif"));
			
			DeckBackSide.setBounds(50, 50, 500, 333);
			pane.add(DeckBackSide);
			pane.repaint();
	}
	
	public void DrawTheFirstCardInDeck(String pathToCardPic){
		if(DeckBackSide !=null ){
			DeckBackSide.setVisible(false);
		}
		if(firstCard != null){
			firstCard.setVisible(false);
		}
		
		if(pathToCardPic.equalsIgnoreCase("Alla kort dragna ! blanda om!")){
			DeckBackSide.setVisible(true);
			JOptionPane.showMessageDialog(null, "Alla kort dragna ! blanda om!");
		}else{
			BufferedImage image;
			try {
				image = ImageIO.read(new File("src/images/"+pathToCardPic));
				firstCard = new JLabel(new ImageIcon(image));
				firstCard.setBounds(50, 50, 400, 400);
				firstCard.setVisible(true);
				pane.add(firstCard);
				pane.repaint();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
	}
}
