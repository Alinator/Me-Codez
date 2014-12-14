
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ali
 */
public class GUI extends Thread{
    // labels
    JLabel scoreLabel=null;
    JLabel statusofwordLabel=null;
    JLabel gameCounterLabel=null;
    
    JLabel score=null;
    JLabel statusofword=null;
    JLabel gameCounter=null;
    
    
    JLabel Message =null;
    
    int x=0;
    int y=0;
    int z=0;
    
    JLayeredPane pane = null;
    JLabel piclabel = null;
    JFrame frame = null;
     public GUI(){ 
     }
     
     public void run(){
         frame = new JFrame("Hangman game board");  
         pane = new JLayeredPane();
         
         scoreLabel = new JLabel("Score:");
         statusofwordLabel = new JLabel("status of word:");
         gameCounterLabel = new JLabel("Tries left:");
         
         score =  new JLabel("0");
         
         frame.setSize(400,600);
         frame.add(pane);
         //score
         scoreLabel.setBounds(10,10,100,100);
         score.setBounds(15, 30,100,100);
         pane.add(scoreLabel);
         pane.add(score);

         //status
         statusofwordLabel.setBounds(100,10,100,100);
         pane.add(statusofwordLabel);
         
         //status
         gameCounterLabel.setBounds(200,10,100,100);
         pane.add(gameCounterLabel);
         piclabel = new JLabel();
         try {
			hangmanpic(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         
         
         frame.setVisible(true);
     }
     public void updateScoreLabel(int Score){
         String myScore = String.valueOf(Score);
         score.setText(myScore);
         frame.repaint();
     }
     
    public void updatestatusWord(String word){
        
        if(x == 0){
        statusofword = new JLabel(word);
        statusofword.setBounds(100,30,100,100);
        pane.add(statusofword); 
        x++;
        }else{
            statusofword.setText(word);
            frame.repaint();
        }
    }
    
    public void gameCounterLabel(String counter){
        if(y == 0){
        gameCounter = new JLabel(counter);
        gameCounter.setBounds(200, 30, 100, 100);
        pane.add(gameCounter);
        y++;
        }else{
            gameCounter.setText(counter);
            frame.repaint();
        }
    }
    public void setMessageLabel(String message){
        if(z == 0){
            
        
        Message=new JLabel(message);
        Message.setBounds(120, 60, 300, 100);
        pane.add(Message);
        frame.repaint();
        z++;
        }else{
            Message.setText(message);
            frame.repaint();
        }
  
    }
    
    /***
     * 
     * @param number
     * @throws IOException
     * 
     * will be used to change the hangman picture in the JFrame.
     * The number variable vill used to call a pic the specified number.
     *
     */
    
    public void hangmanpic(int number) throws IOException{
    	System.out.println(number);
		if(number == 0){
			piclabel.setIcon(new ImageIcon(getImage(0)));
			piclabel.setBounds(0, 200,373,249);
			piclabel.setVisible(true);
			pane.add(piclabel);
			piclabel.repaint();
			pane.repaint();
			frame.getContentPane().repaint();
		}else{
			piclabel.setIcon(new ImageIcon(getImage(number)));
			piclabel.setBounds(0, 200,373,249);
			piclabel.setVisible(true);
			piclabel.repaint();
			pane.repaint();
			frame.getContentPane().repaint();
		}
				
	
    }
    public Image getImage(int number) throws IOException{
    	Image image = ImageIO.read(new File(number+".png"));
    	return image;
    }
    public void destroyGameFrame(){
        frame.setVisible(false);
        frame=null;
    }
}
