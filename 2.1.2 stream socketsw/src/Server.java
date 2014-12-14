import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextArea;


public class Server {
	public static int DEFAULT_PORT =0;
	public static ArrayList<Socket> connectionList = new ArrayList<>();	
	public static JTextArea textAreaone = null;
	public static JFrame serverFrame=null;
	public static String address=null;
	public static void main(String [] args) throws IOException{
		
		try{
			DEFAULT_PORT = Integer.parseInt(args[0]);
		}catch(ArrayIndexOutOfBoundsException ex){
			DEFAULT_PORT = 2000;
		}
		
		boolean listening =  true;
		
		try(ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT)){
			
			iniateGUI(serverSocket.getInetAddress(),serverSocket.getLocalPort());
			address = serverSocket.getInetAddress().getLocalHost().getHostName();
			
			while(listening){
				new ServerThread(serverSocket.accept()).run();
			}
			
		} catch (IOException e) {
            System.err.println("Could not listen on port " + DEFAULT_PORT);
            System.exit(-1);
        }
	}
	
	public static ArrayList<Socket> getConnectionList(){
		return connectionList;
	}
	public static int getAmountOfConnections(){
		int connections=0;
		
		for(int x=0; x < connectionList.size(); x++){
			connections++;
		}
		return connections;
	}
	public static void serverChatFrame(String message){
		textAreaone.append(message+"\n");
	}
	public static void iniateGUI(InetAddress adress, int port) throws HeadlessException, UnknownHostException{
		serverFrame= new JFrame(adress.getLocalHost().getHostName()+" port: "+port+" Members connected: "+getAmountOfConnections());
		serverFrame.setSize(new Dimension(500,500));
		
		serverFrame.setLayout(new FlowLayout());
		
		   	textAreaone = new JTextArea(5, 10);
		    textAreaone.setPreferredSize(new Dimension(400, 400));
		    textAreaone.setLineWrap(true);
		    textAreaone.setEditable(false);
		    serverFrame.add(textAreaone);
		
		serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		serverFrame.setVisible(true);
	}
	public static void updateframe() throws UnknownHostException{
		
		serverFrame.setTitle(address+" port: "+DEFAULT_PORT+" Members connected: "+getAmountOfConnections());
		
	}
}
