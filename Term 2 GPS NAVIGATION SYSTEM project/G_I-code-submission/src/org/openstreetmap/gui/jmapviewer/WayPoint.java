package org.openstreetmap.gui.jmapviewer;

/**
 * WayPoint class with constructors, set and get methods.
 * @author Sebastian Hansson
 *
 */

public class WayPoint {

    protected double latitude;
    protected double longitude;
    protected int id;
    protected String name;
        
    /**
     * default constructor
     */
        public WayPoint() {
            
        }
    
        /**
         * Constructor
         * @param latitude
         *          given latitude
         * @param longitude
         *          given longitude
         * @param id
         *          given id
         */
        public WayPoint(double latitude, double longitude, int id) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.id = id;
        }
        
        /**
         * Constructor
         * @param latitude
         *          given latitude
         * @param longitude
         *          given longitude
         * @param id
         *          given id
         * @param name
         *          given name (used by the toString method)
         */
        public WayPoint(double latitude, double longitude, int id, String name) {
            this(latitude, longitude, id);
            this.name = name;
        }
        
        /**
         * @return
         *      returns the latitude
         */
        public double getLat() {
            return this.latitude;
        }
        
        /**
         * @return
         *      returns the longitude
         */
        public double getLong() {
            return this.longitude;
        }
        
        /**
         * @return
         *      returns the id
         */
        public int getId() {
            return this.id;
        }
        
        /**
         * sets the latitude
         * @param latitude
         *          the given latitude
         */
        public void setLat(double latitude) {
            this.latitude = latitude;
        }
        
        /**
         * sets the longitude
         * @param longitude
         *          the given latitude
         */
        public void setLong(double longitude) {
            this.longitude = longitude;
        }
        
        /**
         * sets the id
         * @param id
         *          the given id
         */
        public void setId(int id) {
            this.id = id;
        }
        
        public String toString() {
            return this.name;
        }
        
}
