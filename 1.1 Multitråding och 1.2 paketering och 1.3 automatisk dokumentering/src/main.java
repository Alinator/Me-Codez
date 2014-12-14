
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			T1 t1 = new T1();
			Thread.sleep(5000);
			System.out.println("now here");
			T2 t2 = new T2();
			Thread.sleep(5000);
			t1.interrupt();
			Thread.sleep(5000);
			t2.stop();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
