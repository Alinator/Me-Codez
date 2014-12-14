
public class T1 extends Thread {

	/***
	 * Constructor for T1
	 */
	public T1(){
		super("T1");
		start(); 
	}
	
	/***
	 * Inherited run method from Thread class
	 */
	
	public void run(){
		
		boolean alive = true;
		
		while(alive){
			try {
				System.out.println("STDOUT");
				this.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				alive = false;
			}
			
		}

	}
	
}
