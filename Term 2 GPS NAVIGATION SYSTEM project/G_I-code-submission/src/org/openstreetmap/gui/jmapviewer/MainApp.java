package org.openstreetmap.gui.jmapviewer;
/**
 * 
 * The main class that will run the application
 *
 */
public class MainApp {
    
    /**
     * Application entry point
     * @param args - command line arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI();
        Gps GPS= new Gps(gui);
        GPS.GpsIgnition();
    }
}
 