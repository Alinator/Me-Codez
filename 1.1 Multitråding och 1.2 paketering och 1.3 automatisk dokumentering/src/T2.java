
public class T2 implements Runnable {
	
	Thread t;
	
	/***
	 * Constructor for T2.
	 */
	public T2(){
		t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		boolean alive = true;
		while(alive){
			try {
				Thread.sleep(1000);
				System.out.println("STDOUT from t2");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				alive = false;
				
			}
			
		}
	}
	/***
	 *  Stop method in order to interrupt the current ongoing thread.
	 */
	public void stop(){
		t.interrupt();
	}
}
