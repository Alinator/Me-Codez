import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ali
 * 
 * this class implements runnable so each client that will connect to the server gets a unique game thread.
 */
class HangManGameThread implements Runnable {

    Socket newsocket = null;
    String chosenword;
    int gameCounter;
    
     PrintWriter send = null;
     BufferedReader read = null;
     String fromClient=null;
     String TheWord = "";
    
    String [] wordarray;
    
    public HangManGameThread(Socket accept) {
        this.newsocket = accept;
        this.initializeData(false);
           
        try {
            send = new PrintWriter(newsocket.getOutputStream(), true);
            read = new BufferedReader(
                    new InputStreamReader(newsocket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(HangManGameThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *  The main game loop.
     *  Sends messages to the specified client as well as recieves messages from the client.
     */
    public void startGame(boolean anotherround){
       
        try {
            if(anotherround == false){
                if(read.readLine().equalsIgnoreCase("startgame")){
                    for(int y = 0; y < wordarray.length; y++){
                    
                        TheWord += wordarray[y];
                    
                    }
                send.println(TheWord);
                send.println(gameCounter);
                }
            }else{
                
                send.println(TheWord);
                }
            
            while((fromClient = read.readLine()) != null){
                System.out.println(fromClient);
                
                TheWord ="";
                if(fromClient.equalsIgnoreCase(chosenword)){
                    
                    send.println("Congratulations! it was the right word!");
                    send.println(chosenword);
                    startNewGame();
                }else if(chosenword.contains(fromClient.toLowerCase()) || chosenword.contains(fromClient.toUpperCase())){
                    String tmp="";
                    System.out.println(fromClient+" exists in the word!");
                    String [] chosenwordintoarray = chosenword.split("");
                    
                    for(int z=0; z < chosenwordintoarray.length; z++){
                        if(chosenwordintoarray[z].equalsIgnoreCase(fromClient)){
                            wordarray[z-1] = chosenwordintoarray[z];
                            
                        }
                        
                    }
                    
                    for(int a=0; a < wordarray.length; a++){
                        tmp += wordarray[a];
                    }
                    
                    if(tmp.equalsIgnoreCase(chosenword)){
                       send.println("won");
                       send.println(tmp);
                       startNewGame();
                    }else{
                        send.println("right"); 
                    }
    
                }else{
                    gameCounter--;
                    send.println("wrong");
                    send.println(gameCounter);
                    
                }
               
                for(int y = 0; y < wordarray.length; y++){
                    TheWord += wordarray[y];
                }
                
                send.println(TheWord);
            }
            read.close();
            send.flush();
            send.close();
            newsocket.close();
            
            
        } catch (IOException ex) {
            Logger.getLogger(HangManGameThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            send.close();
        }
        
    }
    
    public void startNewGame(){
        send.flush();
        send.println("startnewgame");
        initializeData(true);
    }
    /**
     * @param newround
     * 
     * this method will initialize the variables needed for the hangman game.
     * 
     */
    public void initializeData(boolean newround){
        String [] words ={"Monkey","Cake","Computer","Mobile","Gladiators","superman","microsoft","Apple"};
        
        Random random = new Random();
        int index = random.nextInt(8);
           
        this.chosenword= words[index];
        
        
        System.out.println(chosenword);
        System.out.println(chosenword.length());
        wordarray=null;
        wordarray = new String [chosenword.length()];
        Arrays.fill(wordarray, "");
        
        for(int x = 0; x < chosenword.length(); x++){
            wordarray[x]+="-";
        }
     
        if(newround== true){
           for(int w = 0; w < wordarray.length; w++){
                    TheWord += wordarray[w];
                }
            startGame(true);
        }else{
            this.gameCounter=12;
        }
        
    }
    
    @Override
    public void run() {
       startGame(false);
    }
}
