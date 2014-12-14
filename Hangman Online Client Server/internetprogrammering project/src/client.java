/*
* ali nazar
*/



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Ali
 * 
 * This class will act as a client that can connect to a server.
 */
public class client {
    public static String message = "";
    public static void main(String []args) {
        
        GUI newgameframe= new GUI();
        newgameframe.start();
        
        String ip = JOptionPane.showInputDialog("Enter IP");
        
        String port = JOptionPane.showInputDialog("Enter port");
        ArrayList<String> alphabetsUsed = new ArrayList<String>();
        
        int portnumber= Integer.parseInt(port);
       
        Socket clientSocket;
        PrintWriter sendToServer = null;
        BufferedReader fromServer = null;
        String counter="";
        String currentword="";
        int scoreCounter=0;
        String gameCounter="";
        try {
            clientSocket = new Socket(ip,portnumber);
            
            sendToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            fromServer =  new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            sendToServer.println("startgame");
            
            String recieved = fromServer.readLine();
            newgameframe.updatestatusWord(recieved);
            gameCounter = fromServer.readLine();
            newgameframe.gameCounterLabel(gameCounter);
            System.out.println(recieved);
      
            while(true){
               
                 String message = writeMessage();
                 
                 alphabetsUsed.add(message);
                 sendToServer.println(message);
                 recieved = fromServer.readLine();
                if (recieved.equalsIgnoreCase("Congratulations! it was the right word!")){
                    
                    
                    scoreCounter+=120;
                    newgameframe.updateScoreLabel(scoreCounter);
                    currentword = fromServer.readLine();
                    newgameframe.updatestatusWord(currentword);
                    JOptionPane.showMessageDialog(null, "BullsEYE! you guessed the right word");
                    
                }
              
                if(recieved.equalsIgnoreCase("won")){
      
                    scoreCounter+=20;
                    newgameframe.updateScoreLabel(scoreCounter);
                    currentword = fromServer.readLine();
                    newgameframe.updatestatusWord(currentword);
                    JOptionPane.showMessageDialog(null, "You have finally guessed the right word! Congratulations!");
                    
                }   
                if(recieved.equalsIgnoreCase("right")){
                    newgameframe.setMessageLabel("YOU GUESSED A RIGHT LETTER, Keep going!");
                    newgameframe.updateScoreLabel(scoreCounter);
                    
                }
                
                if(recieved.equalsIgnoreCase("wrong")){
                    counter= fromServer.readLine();
                   newgameframe.setMessageLabel("WRONG! no such letter exists in the word!");
                    newgameframe.gameCounterLabel(counter);
                    int picnumber = Integer.parseInt(counter);
                    newgameframe.hangmanpic(picnumber);
                    
                }
                
                 if(counter.equals("0")){ 
                    newgameframe.setMessageLabel("GAME OVER MISTER!");
                     try {
                         Thread.sleep(2000);
                         newgameframe.destroyGameFrame();
                         newgameframe.hangmanpic(0);
                         break;
                     } catch (InterruptedException ex) {
                         Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
                     }
                    
                }
                currentword =fromServer.readLine();
                
                 if(currentword.equalsIgnoreCase("startnewgame")){
                    newgameframe.updatestatusWord(fromServer.readLine());
                    newgameframe.setMessageLabel("LETS START A NEW ROUND!");
                }else{
                     newgameframe.updatestatusWord(currentword);
                 }
                
            }
        } catch (IOException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
        sendToServer.flush();
        sendToServer.close();
        
    }
    public static String writeMessage(){
             message = JOptionPane.showInputDialog("guess a letter!");
             return message;
        }
    
}