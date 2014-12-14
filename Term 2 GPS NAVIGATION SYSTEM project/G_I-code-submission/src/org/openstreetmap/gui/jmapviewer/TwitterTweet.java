package org.openstreetmap.gui.jmapviewer;
/**
 * 
 * @author Alinator
 *  Razr91 = username
 *
 *
 */

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * 
 * @author Ali
 *  this class will update your twitter status.
 *  intended to make the application more personal
 *  you can for example tell where you are at the moment, only thing that requires is a internet connection
 *  and access to the application.
 *  
 *  this class will also show you current location on google maps, by sending in the coordinates to google maps.
 *  
 */
public class TwitterTweet{
    
    String messageUpdate;
    String username;
    String password;
    public TwitterTweet(String msg){
        
        this.messageUpdate=msg;
        update(messageUpdate);
    }
    /**
     *  @param update
     *  this method will access my twitter account and let me update my status.
     *  
     */
    public void update(String update){
        
        String oauth_token="281945802-khIs9GEUTnpjzFE4UgH0Z491tp9CmYSohAzGpv4Q";
        
        String oauth_token_secret="4FUBlYs8Gsrb3UOWtFMNVYk6pHmZmGf3G80VMIHfjrU";
        // configure OAUTH settings.
        
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey("yCwaaXCXLkd1uexTJxfaeA")
          .setOAuthConsumerSecret("CqVNHe4PcI1qkcbvu7fwql8e2DNJqUBwCG7yv17WiE")
          .setOAuthAccessToken(oauth_token)
          .setOAuthAccessTokenSecret(oauth_token_secret);
        
        TwitterFactory tf = new TwitterFactory(cb.build());
        
        twitter4j.Twitter twitter = tf.getInstance();
                Status status;
        try {
            parse parser= new parse();
            GPSInfo info= parser.getInfo();
//            status = twitter.updateStatus(update+" (User location is: Latitude:"+info.getLat()+" Longitude :"+info.getLon()+" put on Google maps to check location )");
            status = twitter.updateStatus(update+"UserLocation: http://maps.google.com/maps?q="+info.getLat()+",+"+info.getLon()+"&iwloc=A&hl=en");
            System.out.println("Successfully updated the status to [" + status.getText() + "].");
            System.out.println(status.getGeoLocation());
            
            
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        
 
    }
    }
  
        
            
           
           

 
    

      
    

    
    



