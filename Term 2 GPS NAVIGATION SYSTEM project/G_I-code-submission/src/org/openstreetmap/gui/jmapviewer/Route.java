package org.openstreetmap.gui.jmapviewer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class for handling wayPoints
 * @author Johan
 */

public class Route {
    ArrayList<WayPoint> wayPoints;
    String name;
    
    /**
     * Default constructor (use add after this!)
     */
    public Route() {
        this.wayPoints = new ArrayList<WayPoint>();
    }
    
    /**
     * Constructs an arraylist of waypoints from the given arraylist
     * @param waypoints
     *          given arraylist of waypoints
     */
    public Route(ArrayList<WayPoint> waypoints) {
        this.wayPoints = waypoints;
    }
    
    /**
     * Constructs an arraylist of waypoints from the given array
     * @param waypoints
     *          given array of waypoints
     */
    public Route(WayPoint[] waypoints) {
        this.wayPoints = new ArrayList<WayPoint>(Arrays.asList(waypoints));
    }
    
    /**
     * adds a point at the end of the list
     * @param point
     */
    public void addPoint(WayPoint point) {
        this.wayPoints.add(point);
    }
    
    /**
     * adds a point at the given index in the list
     * @param point
     *          given point
     * @param index
     *          given index
     */
    public void addPoint(WayPoint point, int index) {
        this.wayPoints.add(index, point);
    }
    
    /**
     * adds a point at index 0
     * @param point
     *          given point
     */
    public void addHead(WayPoint point) {
        this.wayPoints.add(0, point);
        for (int i = 1; i < this.wayPoints.size(); i++) {
            this.wayPoints.get(i).setId(i);
        }
    }
    
    /**
     * @return
     *      returns the first value
     */
    public WayPoint getHead() {
        return this.wayPoints.get(0);
    }
    
    /**
     * @param index
     *          the given index
     * @return
     *          returns the point after the given index
     */
    public WayPoint getNext(int index) {
        return this.wayPoints.get(index + 1);
    }
    
    /**
     * @param index
     *          the given index
     * @return
     *          returns the point from the given index
     */
    public WayPoint getPoint(int index) {
        return this.wayPoints.get(index);
    }
    /**
     * Removes a single waypoint at given index
     * @param index
     *          the given index
     * @return
     *          returns the removed point (for safety)
     */
    public WayPoint removePoint(int index) {
        WayPoint toReturn = this.wayPoints.get(index);
        this.wayPoints.remove(index);
        return toReturn;
    }
    
    /**
     * Removes a single specific point
     * @param point
     *          the point to be removed
     * @return
     *          returns the removed point (for safety)
     */
    public WayPoint removePoint(WayPoint point) {
        for (int i = 0; i < this.wayPoints.size(); i++) {
            if (this.wayPoints.get(i).equals(point)) {
                this.wayPoints.remove(i);
                break;
            }
        }
        return point;
    }
    
    /**
     * @return
     *      returns the list of waypoints
     */
    public ArrayList<WayPoint> getWayPointList () {
        return this.wayPoints;
    }
    
    /**
     * creates and saves the route
     * @param name
     *          the name the route should be save under
     */
    public void createAndSaveRoute(String name) {
        try {
            // Create file
            FileWriter fstream = new FileWriter("routes/" + name + ".route");
            BufferedWriter out = new BufferedWriter(fstream);
            // writes out the route to
            out.write("<ROUTE>");
            out.write("<name id = \"" + name + "\"></name>");
            for (int i = 0; i < this.wayPoints.size(); i++) {
                out.write("<WAYPOINT>");
                out.write("<id id = \"" + this.wayPoints.get(i).getId() + "\"></id>");
                out.write("<coord lat = \"" + this.wayPoints.get(i).getLat() + "\" long = \"" + this.wayPoints.get(i).getLong() + "\"/>");
                out.write("</WAYPOINT>");
            }
            out.write("</ROUTE>");
            out.close();
        } catch (FileNotFoundException e) {
            new File("routes").mkdir();
            this.createAndSaveRoute(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * adds a route to the end of this route
     * @param route
     *          the given route
     * @param head
     *          the waypoint that connects the route
     * @return
     *          returns the new route
     */
    public Route appendRouteWithRoute(Route route, WayPoint head) {
        this.addPoint(head);
        for (int i = 0; i < route.wayPoints.size(); i++) {
            this.addPoint(route.wayPoints.get(i));
        }
        return this;
    }
    
    public String toString() {
        return this.name;
    }
}
