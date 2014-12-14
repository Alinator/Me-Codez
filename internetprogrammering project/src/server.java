import java.io.IOException;
import java.net.ServerSocket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * @author Ali
 */
public class server {
    
    public static void main(String [] args) throws InterruptedException{
        
    
        int portnumber = 80;
        boolean listeningForIncomingConnections=true;
       
        try (ServerSocket hangmanserversocket = new ServerSocket(portnumber);){
            
            while(listeningForIncomingConnections){
                HangManGameThread hangManGameThread = new HangManGameThread(hangmanserversocket.accept());
                Thread newGameThread = new Thread(hangManGameThread);
                newGameThread.start();
            }   
            
            
        } catch (IOException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    

}

