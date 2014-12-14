package org.openstreetmap.gui.jmapviewer;
import java.io.IOException;
import java.net.ServerSocket;
import javax.swing.JOptionPane;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;


/**
 * 
 * @author Ali
 * this class will allow the user to connect between the er and and a friend. The class uses and online
 * server called jabber.org and both the user and the friend go in as clients and the servers will send 
 * the messages in between
 *
 */
public class NetworkClient{
    /**
     * 
     * connection with XMPP server
     */
    static XMPPConnection connecting;
    static int port = 0;
    static String Server= "jabber.org";
    static parse parser = new parse();
    static GPSInfo info = parser.getInfo();
    static String jabberFriend;

    protected static String message;
    protected static String user;
    
    static double latitude;
    static double longitude;
    public NetworkClient(){

    }
    @SuppressWarnings("static-access")
    public NetworkClient(String msg){
        this.message=msg;
    }
    @SuppressWarnings("static-access")
    public NetworkClient(String username,String JabberFriend){
        this.user = username;
        this.jabberFriend = JabberFriend;
        System.out.println("USER: "+user+" "+"FRIEND: "+jabberFriend);
        connectToJabber();
    }
/**
 * connects to 5222 socket number which is default for the jabber server.
 */
    public static void SocketConnection(){

        try{
            ServerSocket server= new ServerSocket(5222);
            port=server.getLocalPort();
        } catch (IOException e1) {
            System.out.println(e1.getLocalizedMessage());
        }
    }
    /**
     * after proper connection this establishes the connection with the jabber friend
     * 
     */
    public static void EstablishConnection(){
        Runnable networkingThread= new Runnable(){
            @SuppressWarnings("unused")
            public void run(){
                try {
                    if(connecting != null){
                        // start sending coordinations.
                        ChatManager manager = connecting.getChatManager();
                        boolean lat = false;
                        Chat send=null;
                        while(true){
                            String Latitude= Double.toString(info.getLat());
                            String Longitude= Double.toString(info.getLon());
                             send= manager.createChat(jabberFriend,new MessageListener(){
                                public void processMessage(Chat send, Message message) {
                                    System.out.println(message.getBody());
                                    String Latitude= message.getBody().split("#")[0];
                                    String Longitude= message.getBody().split("#")[1];
                                    latitude= Double.parseDouble(Latitude);
                                    longitude= Double.parseDouble(Longitude);                                    
                                }
                            });
                   
                            Message messageCoord= new Message();
                            messageCoord.setTo(jabberFriend);
                            //messageLong.setTo(jabberFriend);
                            messageCoord.setBody(Latitude+"#"+Longitude);
                            //messageLong.setBody(Longitude);
                            try {
                                Thread.sleep(700);
                                    send.sendMessage(messageCoord);    
                                    System.out.println("Sending: " + messageCoord.getBody());
                              //  sendLong.sendMessage(messageLong);
                                
                            } catch (Exception e) {
                              //  e.printStackTrace();
                            }

                        }

                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "wrong jabberFriend name, try again");
                    System.out.println(e.getLocalizedMessage());    
                }
            }
        };

        Thread run = new Thread(networkingThread);
        run.start();
    }
    /**
     * disconnects the connection with the jabber connection
     * 
     */
    public static void disconnect() {
        try{
        connecting.disconnect();
        System.out.println("connected?: "+ connecting.isConnected());
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    } 
    /**
     * to be able to chat with the friend.
     */
    public void chat(){
        ChatManager manager = connecting.getChatManager();
        Chat createChat= manager.createChat(jabberFriend,new MessageListener(){
            @SuppressWarnings("unused")
            public void processMessage(Chat send, Message message) {
                System.out.println(connecting.getUser()+" : "+ message.getBody());
                String messageBack= message.getBody();
            }
        });
        Message msg = new Message();
        msg.setTo("ali_nazar@jabber.org");
        msg.setBody(message);
        try {
            createChat.sendMessage(msg);
        } catch (XMPPException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getLocalizedMessage());
        }

    }
    /**
     * this method establishes the connection with the jabber server.
     */
    public static void connectToJabber() {
        try {
            // can also be done for our gmail accounts !"talk.google.com", 5222, "gmail.com"
            ConnectionConfiguration configuration= new ConnectionConfiguration(Server,port);
            configuration.setCompressionEnabled(true);
            configuration.setSASLAuthenticationEnabled(true);
            configuration.setReconnectionAllowed(true);

            connecting= new XMPPConnection(configuration);
            connecting.connect();
            connecting.login(user, "stepbystep");
            //System.out.println("Connected ? :" + connecting.isConnected());
            //System.out.println("Port :"+ connecting.getPort());
            //System.out.println("User :"+ connecting.getUser());
            EstablishConnection();
        } catch (XMPPException e) {
            System.out.println(e.getLocalizedMessage());
            JOptionPane.showMessageDialog(null, "wrong or invalid jabber username or jabber Friend username");
            user=null;
            jabberFriend=null;
        }
    }
    public static int getPort() {
        return port;
    }
    public static void setPort(int port) {
        NetworkClient.port = port;
    }
    public static String getServer() {
        return Server;
    }
    public static void setServer(String server) {
        Server = server;
    }
    public String getUser() {
        return user;
    }
    @SuppressWarnings("static-access")
    public void setUser(String user) {
        this.user = user;
    }
    public String getJabberFriend() {
        return jabberFriend;
    }
    @SuppressWarnings("static-access")
    public void setJabberFriend(String jabberFriend) {
        this.jabberFriend = jabberFriend;
    }
    public static double getLatitude() {
        return latitude;
    }
    public static double getLongitude() {
        return longitude;
    }

}
