package org.openstreetmap.gui.jmapviewer;

/**
 * 
 * @author ali nazar
 * 
 * this class would be the code for the domain for the facebook authentication, howver this reuirement
 * was not implemented because of to little time
 *
 */

public class facebookAuthentication {

    public String api_key;
    public String consumer;
    public String secret;
    public String update;
    
   public facebookAuthentication(String msg){
      
       this.update=msg;
       
       updateWall(update);
       
   }
   
   public static void updateWall(String update){
      
   }
   
   
   
   
}
