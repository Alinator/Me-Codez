//@author: Qingju Luo , Jiaqing Li

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
@SuppressWarnings("serial")
public class GameScreen extends MyPanel implements KeyListener {

	
	public GameScreen(String imagePath) {

		super(imagePath);
		this.setBounds(10, 73, w, h);
		this.setVisible(false);

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
