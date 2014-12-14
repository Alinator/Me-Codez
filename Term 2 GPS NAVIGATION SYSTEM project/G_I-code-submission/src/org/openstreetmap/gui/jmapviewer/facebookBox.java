
package org.openstreetmap.gui.jmapviewer;
import java.awt.Color;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
/**
 * 
 * @author Ali Nazar
 * this class would e the gui class for the facebook requirement, however it was left out because of 
 * to little time.
 *
 */
@SuppressWarnings("serial")
public class facebookBox extends JFrame {
    public facebookBox(){
        super("Send A Message");        
        setSize(600,200);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JLayeredPane pane= new JLayeredPane();
        Container con = super.getContentPane();
        JTextArea messageArea = new JTextArea();
        messageArea.setBounds(50,30,500,100);
        messageArea.setBorder(BorderFactory.createLineBorder(Color.CYAN));       
        JLabel updateLocation= new JLabel("Update where you are");
        updateLocation.setBounds(30, -80, 200, 200);
        String update=messageArea.getText();
        JButton send = new JButton(" Update !");
        send.setBounds(400, 130, 100,20 );
        new facebookAuthentication(update);        
        pane.add(messageArea);
        pane.add(updateLocation);
        pane.add(send);
        con.add(pane);
    }
    
    
}
