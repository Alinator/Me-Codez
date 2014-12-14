
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int myport;
		String remoteHost;
		int remoteport;
			
			try{
			myport = Integer.parseInt(args[0]);
			remoteHost = args[1];
			remoteport = Integer.parseInt(args[2]);
			
		}catch(ArrayIndexOutOfBoundsException ex){
			myport = 2000;
			remoteHost = "localhost";
			remoteport = 2001;
		}
		
		WhiteBoard whiteboard =  new WhiteBoard(myport,remoteHost,remoteport);
	}

}
