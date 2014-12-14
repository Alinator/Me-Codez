package org.openstreetmap.gui.jmapviewer;

import java.awt.Graphics;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

/**
 * A simple implementation of the {@link MapMarker} interface. Each map geocache dot
 * is displayed as a indication image. Works just like {@link MapMarkerDot}
 *
 * @author Johan W-Schützer
 *
 */
public class MapGeocacheDot implements MapMarker {

    ImageIcon geoMarker;
    static JLabel geoLabel;
    int iterator = 0;
    double lat;
    double lon;

    /**
     * Constructor
     * @param lat
     *          given latitude
     * @param lon
     *          given longitude
     */
    public MapGeocacheDot(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    /**
     * returns the latitude
     */
    public double getLat() {
        return lat;
    }

    /**
     * returns the longitude
     */
    public double getLon() {
        return lon;
    }

    /**
     * Paints the MapGeocacheDot to be painted
     * makes sure the not visible dots are taken away from the memory
     * @param g
     *          graphics object
     * @param position
     *          the position of the dot
     * @param i
     *          the index of the dot to be painted
     */
    public void paint(Graphics g, Point position, int i) {
        if (geoLabel == null) {
            geoMarker = new ImageIcon(getClass().getResource("/geopoint.png"));
            geoLabel = new JLabel(geoMarker);
            geoLabel.setVisible(true);
            GUI.map.add(geoLabel, i);
            geoLabel.setBounds(position.x, position.y-geoMarker.getIconHeight(), geoMarker.getIconWidth(), geoMarker.getIconHeight());
            geoMarker = null;
        }
        else {
            if (position == null) {
                geoLabel.setVisible(false);
            }
            else {
                geoLabel.setVisible(true);
                geoLabel.setLocation(position.x, position.y-27);
            }
        } 
    }

    @Override
    public String toString() {
        return "MapMarker at " + lat + " " + lon;
    }

    public void paint(Graphics g, Point position) {       
    }
}
