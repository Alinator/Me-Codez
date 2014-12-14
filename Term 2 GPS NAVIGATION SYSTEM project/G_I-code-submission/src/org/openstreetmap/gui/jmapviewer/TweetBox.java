package org.openstreetmap.gui.jmapviewer;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TweetBox extends JFrame{

    /**
     * the tweetBox provide a tweetbox in which the user writes his tweet and sneds it to step by step
     * twitter account
     */
    private static final long serialVersionUID = 1L;

    public TweetBox(){
        setSize(600,200);
        setVisible(true);
       
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLayeredPane pane= new JLayeredPane();
        Container conn= super.getContentPane();
        
        final JLabel whereareyou= new JLabel("Tweet your location!");
        whereareyou.setBounds(30, -80, 200, 200);
        
        final JTextArea textbox= new JTextArea(0,0);
        textbox.setBounds(50,30,500,100);
        textbox.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        
        final JLabel username= new JLabel("Username");
        username.setBounds(130,-60 , 200, 200);
        
        final JTextField user= new JTextField(10);
        user.setBounds(200, 30, 150, 30);
        
        final JLabel password= new JLabel("password");
        password.setBounds(130,-30 , 200, 200);
     
        final JPasswordField pass= new JPasswordField(30);
        pass.setBounds(200, 60, 150, 30);
        
        
        JButton send = new JButton(" Tweet !");
        send.setBounds(400, 130, 100,20 );
        
        send.addActionListener(new ActionListener() {
            
            @SuppressWarnings("unused")
            public void actionPerformed(ActionEvent e) {
                String update=textbox.getText();
                TwitterTweet updating= new TwitterTweet(update);
            }
        });
        
        pane.add(textbox,0);
        pane.add(send,0);
        pane.add(whereareyou,0);
        conn.add(pane);

           
    }
}
