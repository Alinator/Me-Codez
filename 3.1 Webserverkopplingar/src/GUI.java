import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;


public class GUI  {

	JFrame frame = null;
	JTextField urlfield = null;
	JTextArea htmlarea =  null;

	public GUI(){
		frame = new JFrame();

		frame.setLayout(new FlowLayout());

		urlfield = new JTextField(50);
		frame.add(urlfield);

		htmlarea = new JTextArea(50,50);
		htmlarea.setEditable(false);
		htmlarea.setPreferredSize(new Dimension(50,50));
		frame.add(htmlarea);

		urlfield.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == 10){
					readURL();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		frame.setSize(700,500);

		frame.setVisible(true);
	}

	public String getURLText(){
		return urlfield.getText();
	}

	public void appendHTMLToTextArea(String html){
		htmlarea.append(html);
	}

	public void readURL(){
		URL oracle;
		BufferedReader in = null;
		try {
			oracle = new URL(getURLText());

			try {
				in = new BufferedReader(new InputStreamReader(oracle.openStream()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String inputLine;
			try {
				while ((inputLine = in.readLine()) != null)
					appendHTMLToTextArea(inputLine+"\n");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				in.close();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
