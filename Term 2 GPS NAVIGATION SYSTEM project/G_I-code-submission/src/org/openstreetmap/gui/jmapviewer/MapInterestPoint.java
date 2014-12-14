package org.openstreetmap.gui.jmapviewer;

import java.awt.Graphics;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

/**
 * A simple implementation of the {@link MapMarker} interface. Each map interest point
 * is displayed as a indication image. Works just like {@link MapMarkerDot}
 *
 * @author Sebastian & Johan W-Schützer
 *
 */
public class MapInterestPoint implements MapMarker {

    ImageIcon interestMarker;
    static JLabel[] interestLabelList;
    int iterator = 0;
    double lat;
    double lon;
    org.openstreetmap.gui.jmapviewer.GUI.Interest interest;

    /**
     * Constructor
     * @param lat
     *          latitude
     * @param lon
     *          longitude
     * @param interest
     *          the type of interest point (see enum variable {@link GUI.Interest} in the GUI class)
     */
    public MapInterestPoint(double lat, double lon, org.openstreetmap.gui.jmapviewer.GUI.Interest interest) {
        this.lat = lat;
        this.lon = lon;
        this.interest = interest;
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
     * paints the interest point to be painted
     * Makes sure that the points not to be painted are taken out of the memory 
     * @param g
     *          graphics object
     * @param position
     *          the given position
     * @param i
     *          the index of the point to be painted
     */
    public void paint(Graphics g, Point position, int i) {
        if (interestLabelList == null) 
            interestLabelList = new JLabel[GUI.map.getInterestPointList().size()];
        if (interestLabelList[i] == null) {
            switch (this.interest) {
                case PARKING: interestMarker = new ImageIcon(getClass().getResource("/parkingicon.png")); 
                    break;
                case ATM: interestMarker = new ImageIcon(getClass().getResource("/dollaricon.png")); 
                break;
                case DINERS: interestMarker = new ImageIcon(getClass().getResource("/restauranticon.png")); 
                break;
                case TRANSPORT: interestMarker = new ImageIcon(getClass().getResource("/busicon.png")); 
                break;
            }
            interestLabelList[i] = new JLabel(interestMarker);
            interestLabelList[i].setVisible(true);
            GUI.map.add(interestLabelList[i], i);
            interestLabelList[i].setBounds(position.x, position.y-interestMarker.getIconHeight(), interestMarker.getIconWidth(), interestMarker.getIconHeight());
            interestMarker = null;
        }
        else {
            if (position == null) {
                interestLabelList[i].setVisible(false);
            }
            else {
                interestLabelList[i].setVisible(true);
                interestLabelList[i].setLocation(position.x, position.y-27);
            }
        } 
    }
    public void paint(Graphics g, Point position) {       
    }
}
