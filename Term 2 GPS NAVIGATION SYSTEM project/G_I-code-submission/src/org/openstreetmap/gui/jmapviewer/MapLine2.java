package org.openstreetmap.gui.jmapviewer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import org.openstreetmap.gui.jmapviewer.interfaces.MapLine;

/**
 * A simple implementation of the {@link MapLine} interface. Each map marker
 * is painted as a circle with a black border line and filled with a specified
 * color. See {@link MapLine} for usage information
 *
 * @author Johan
 *
 */
public class MapLine2 implements MapLine {

    double lat1;
    double lon1;
    double lat2;
    double lon2;
    Color color;

    /**
     * Constructor
     * @param lat1
     *          the first latitude
     * @param lon1
     *          the first longitude
     * @param lat2
     *          the second latitude
     * @param lon2
     *          the second longitude
     */
    public MapLine2(double lat1, double lon1, double lat2, double lon2) {
        this(Color.GREEN, lat1, lon1, lat2, lon2);
    }

    /**
     * Constructor
     * @param color
     *          the given color
     * @param lat1
     *          the first latitude
     * @param lon1
     *          the first longitude
     * @param lat2
     *          the second latitude
     * @param lon2
     *          the second longitude
     */
    public MapLine2(Color color, double lat1, double lon1, double lat2, double lon2) {
        super();
        this.color = color;
        this.lat1 = lat1;
        this.lon1 = lon1;
        this.lat2 = lat2;
        this.lon2 = lon2;
    }
    
    /**
     * Constructor
     * @param point1
     *          the first point
     * @param point2
     *          the second point
     */
    public MapLine2(WayPoint point1, WayPoint point2) {
        this(Color.GREEN, point1.getLat(), point1.getLong(), point2.getLat(), point2.getLong());
    }

    /**
     * returns the latitude of the first point
     */
    public double getLat1() {
        return lat1;
    }

    /**
     * returns the longitude of the first point
     */
    public double getLon1() {
        return lon1;
    }
    
    /**
     * returns the latitude of the second point
     */
    public double getLat2() {
        return lat2;
    }

    /**
     * returns the longitude of the second point
     */
    public double getLon2() {
        return lon2;
    }

    /**
     * paints the line
     * @param g
     *          graphics object
     * @param position1
     *          the first position
     * @param position2
     *          the second position
     */
    public void paint(Graphics2D g, Point position1, Point position2) {
        g.setColor(color);
        g.setStroke(new BasicStroke(5));
        g.drawLine(position1.x, position1.y, position2.x, position2.y);
    }

    @Override
    public String toString() {
        return "Line from " + lat1 + " " + lon1 + " to " + lat2 + " " + lon2;
    }
}
