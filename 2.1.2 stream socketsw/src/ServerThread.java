import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class ServerThread extends Thread {
	private Socket socket = null;

	public ServerThread(Socket sock){
		this.socket= sock;

		System.out.println("Connected: ");
	}
	public void run(){
		
		System.out.println("in serverthread");

		try {
			BufferedReader in = null;
			Server.getConnectionList().add(this.socket);
			Server.updateframe();
			
			while(true){
				System.out.println("im hererererer");
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String msgfromclient = in.readLine();
				
				if(msgfromclient == null){
					DeleteMe(this.socket);
				}else{
					System.out.println(msgfromclient);
					Server.serverChatFrame(msgfromclient);
					broadcastMsg("Server: "+msgfromclient);
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public synchronized void broadcastMsg(String msg){
		PrintWriter bos=null;
		for(int y=0; y < Server.getConnectionList().size(); y++){
			try {
				bos = new PrintWriter(new OutputStreamWriter(Server.getConnectionList().get(y).getOutputStream()),true);
				bos.write(msg+"\n");
				bos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized void DeleteMe(Socket thissocket){
		
		for(int x =0; x < Server.getConnectionList().size();  x++){
			
			if(Server.getConnectionList().get(x).equals(thissocket)){
				Server.getConnectionList().remove(x);
				try {
					Server.updateframe();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}


}
