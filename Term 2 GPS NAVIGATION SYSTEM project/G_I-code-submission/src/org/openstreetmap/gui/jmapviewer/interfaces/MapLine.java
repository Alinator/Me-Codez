package org.openstreetmap.gui.jmapviewer.interfaces;


import java.awt.Graphics2D;
import java.awt.Point;


/**
 * Interface to be implemented by all two dimensional elements that can be displayed on the map.
 *
 * @author Johan
 */
public interface MapLine {

    /**
     * @return Latitude of the map marker position
     */
    public double getLat1();
    
    /**
     * @return Longitude of the map marker position
     */
    public double getLon1();
    
    /**
     * @return Latitude of the map marker position
     */
    public double getLat2();
    
    /**
     * @return Longitude of the map marker position
     */
    public double getLon2();

    /**
     * paints this line
     * @param g
     * @param position1
     * @param position2
     */
    public void paint(Graphics2D g, Point position1, Point position2);
}
