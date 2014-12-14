import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Communication extends Thread {
	
	DatagramSocket datagramSocket = null;
	String msg = null;
	int port=0;
	String remotehost = null;
	int remoteport=0;
	WhiteBoard whiteboard=null;
	
	public Communication(int myport, String remoteHost, int remoteport) {
		// TODO Auto-generated constructor stub
		this.port= myport;
		this.remotehost = remoteHost;
		this.remoteport = remoteport;
		whiteboard = new WhiteBoard();
		start();
	}

	public void sendCoordinates(String message){
		try {
			datagramSocket = new DatagramSocket();
			byte[] buffer = message.getBytes();
			InetAddress receiverAddress = InetAddress.getByName(this.remotehost);
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, receiverAddress,this.remoteport);
			datagramSocket.send(packet);
			System.out.println("data sent");
		      
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void run(){
		try {
			DatagramSocket datagramsocket = new DatagramSocket(this.port);
			
			while(true){
				byte[] buf = new byte[1000];
				DatagramPacket Packet = new DatagramPacket(buf, buf.length);
		        datagramsocket.receive(Packet);
		        String str = new String(Packet.getData(), 0, Packet.getLength());
		        System.out.println(str);
		        
		        String[] arr = str.split(":");
		        
		        
		        int X = Integer.parseInt(arr[0].toString());
		        int Y = Integer.parseInt(arr[1].toString());
		        
		       WhiteBoard.currentX = X;
		       WhiteBoard.currentY = Y;
		       WhiteBoard.lastX = X;
		       WhiteBoard.lastY = Y;
		       
		       WhiteBoard.frame.getContentPane().repaint();
		       
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
