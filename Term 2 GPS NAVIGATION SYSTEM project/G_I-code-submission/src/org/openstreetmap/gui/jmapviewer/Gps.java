package org.openstreetmap.gui.jmapviewer;
import java.util.Enumeration;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;

/**
 * 
 * @author Ali
 *  this class loops uses a method called gps ignition and this class is called from the main 
 *  class in order to read the gps.
 */
public class Gps {
    GUI gui;
    
    /**
     * @param GUI object
     */
    public Gps(GUI gui) {
        this.gui = gui;
    }
   static GPSInfo info;
   /**
    * Ali Nazar
    * this method loops through all of the ports in the computer and checks which 
    * ones it receives data from and catches that port, later it calls the commchecker 
    * constructor in order to connect the gps. 
    */
@SuppressWarnings({ "rawtypes", "unused" })
public void GpsIgnition(){
		Enumeration ports= CommPortIdentifier.getPortIdentifiers();
		// this will enable the user to enter the gpsdevice in any port and the program will execute and start
		//reading the data from the gps aswell as parsing, without the user having to manipulate the code 
		// and changing the String value of the CommPortIdentifier method, that is specifying a comport. this works
		//in linux as well, as it catches the USBport being in use in the Linux operating system.
		while(ports.hasMoreElements()){
			gpsdevice.port= (CommPortIdentifier) ports.nextElement();
		try{
			gpsdevice.port= CommPortIdentifier.getPortIdentifier(gpsdevice.port.getName());
			commChecker check = new commChecker();
		}catch(NoSuchPortException np){
			System.out.println(np.getLocalizedMessage());
		}catch (Exception e){
			System.out.println(gpsdevice.port+" gps connection failed. " );
		}
	}
		gui.dot();	
}
}

