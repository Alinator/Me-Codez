package r15;

	import java.awt.event.KeyListener;
	import java.awt.event.KeyEvent;

	public class Keyboard implements KeyListener  {

		private static final int KEY_COUNT = 256;

		private enum KeyStatus{
			Released,Pressed,Once;
		}
		// here we can see what state the keyboard is in at the moment

		private boolean [] keyRightNow= null;

		//polled? keyboard state
		private KeyStatus [] key = null;

		public  Keyboard(){

			keyRightNow= new boolean[ KEY_COUNT ];
			key= new KeyStatus[ KEY_COUNT];
			for ( int i=0; i< KEY_COUNT;i++){
				key[i]=KeyStatus.Released;
			}
			
		}
		public synchronized void pollkey(){
			for ( int i= 0; i< KEY_COUNT; i++){
				if (keyRightNow[i]){
					// the condition now will set the key to once if the key was down last frame, 
					// otherwise will set it to pressed
					if (key[i]==KeyStatus.Released )
						key[i]= KeyStatus.Once;
					else 
						key[i]=KeyStatus.Pressed;	
					}else{
						key[i]=KeyStatus.Released;
					}
				}
			}
		


		@Override
		public synchronized void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
	int keyCode= e.getKeyCode();
	if (keyCode >=0 && keyCode< KEY_COUNT ){
		keyRightNow[keyCode]=true;
	}
		}

		@Override
		public synchronized void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
	int keyCode= e.getKeyCode();
	// check if larger than or equal to Key_count
	if(keyCode >=0 && keyCode < KEY_COUNT){
		keyRightNow[keyCode]=false;
	}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}
		  public boolean keyDown( int keyCode ) {
			    return key[ keyCode ] == KeyStatus.Once ||
			           key[ keyCode ] == KeyStatus.Pressed;
			  }
				
			  public boolean keyDownOnce( int keyCode ) {
			    return key[ keyCode ] == KeyStatus.Once;
			  }

	}
