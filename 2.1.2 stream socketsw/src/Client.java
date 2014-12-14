import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;


public class Client {
	
	static String host = "Ali-PC";
	static int port = 2000;	
	
	public static void main(String[] args) {
//			if(args.length == 0){
//				host = "127.0.0.1";
//				port = Integer.parseInt("2000");
//			}else if(args.length == 1){
//				host = args[0].toString();
//				port = Integer.parseInt("2000");
//			}else if(args.length == 2){
//				host = args[0].toString();
//				port = Integer.parseInt(args[1].toString());
//			}
		ConnectToChatServer();
	}

	private static void ConnectToChatServer() {
		GUI chatframe=null;
		PrintWriter bos= null;
		BufferedReader bis=null;
		Socket connection=null;
		try {
			connection = new Socket(host,port);
			
			if(connection.isConnected()){
				chatframe = new GUI(host,port);
			}else{
				System.exit(0);
			}
			while(connection.isConnected()){
				
				bos = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()),true);
				String msg = JOptionPane.showInputDialog("Enter a message to the server");
				if(msg.equalsIgnoreCase("exit")){
					bos.close();
				}
				String composedMsg= connection.getInetAddress().getHostName()+": "+msg;
				bos.write(composedMsg+"\n");
				System.out.println(connection.getInetAddress().getHostName()+": "+msg);
				chatframe.myChatFrame("Me: "+msg);
				bos.flush();
				
				bis = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					String msgfromserver = bis.readLine();
					chatframe.myChatFrame(msgfromserver);
				
			}
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
