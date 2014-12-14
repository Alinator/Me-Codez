package org.openstreetmap.gui.jmapviewer;

/**
 * Class to handle info from the GPS. Use: make instances of this class
 * see http://www.gpsinformation.org/dale/nmea.htm for information about tags
 * @author Johan and Sebastian
 *
 */
public class GPSInfo {

    protected double lat;
    protected double lon;
    protected float track;
    protected String time;
    protected float altitude;
    protected int satNum;
    protected String date;
    protected float speed;
    protected int update;
    protected String SignalStrength;

    /**
     * Default constructor
     */
    public GPSInfo() {
        this.lat = 0.0;
        this.lon = 0.0;
        this.track = 0;
        this.time = "00:00:00";
        this.altitude = 0;
        this.satNum = 0;
        this.date = "00/00/00";
        this.speed = 0;
        this.update = 0;
        this.SignalStrength = "noSignal";
    }

    /**
     * Constructor with GGA tag
     * @param longIn
     *          given longitude
     * @param latIn
     *          given latitude
     * @param timeIn
     *          given time
     * @param altitudeIn
     *          given altitude
     * @param satNumIn
     *          given number of satellites 
     */
    public GPSInfo(double longIn, double latIn, String timeIn, float altitudeIn, int satNumIn) {
        //GGA
        this.lon = longIn;
        this.lat = latIn;
        this.time = timeIn;
        this.altitude = altitudeIn;
        this.satNum = satNumIn;
        this.SignalStrength = "noSignal";
    }

    /**
     * Constructor with RMC tag
     * @param longIn
     *          the given longitude
     * @param latIn
     *          the given latitude
     * @param trackIn
     *          the given track
     * @param timeIn
     *          the given time
     * @param dateIn
     *          the given date
     * @param speedIn
     *          the given speed
     */
    public GPSInfo(double longIn, double latIn, float trackIn, String timeIn, String dateIn, float speedIn) {
        //RMC
        this.lon = longIn;
        this.lat = latIn;
        this.track = trackIn;
        this.time = timeIn;
        this.date = dateIn;
        this.speed = speedIn;
        this.SignalStrength = "noSignal";
    }

    /**
     * Constructor with GSV tag
     * @param satNumIn
     *          the given number of satellites 
     */
    public GPSInfo(int satNumIn) {
        //GSV
        this.satNum = satNumIn;
    }

    /**
     * Constructor for all values
     * @param latIn
     *          the given latitude
     * @param lonIn
     *          the given longitude
     * @param trackIn
     *          the given track
     * @param timeIn
     *          the given time
     * @param altIn
     *          the given altitude
     * @param satNumIn
     *          the given number os satellites
     * @param dateIn
     *          the given date
     * @param speedIn
     *          the given speed
     * @param updateIn
     *          the given update
     */
    public GPSInfo(double latIn, double lonIn, int trackIn, String timeIn, float altIn,
            int satNumIn, String dateIn, float speedIn, int updateIn) {
        this.lat = latIn;
        this.lon = lonIn;
        this.track = trackIn;
        this.time = timeIn;
        this.altitude = altIn;
        this.satNum = satNumIn;
        this.date = dateIn;
        this.speed = speedIn;
        this.update = updateIn;
        this.SignalStrength = "noSignal";
    }

    /**
     * gets the latitude
     * @return
     *        latitude
     */
    public double getLat() {
        return this.lat;
    }

    /**
     * sets the latitude
     * @param latIn
     *         latitude
     */
    public void setLat(double latIn) {
        this.lat = latIn;
    }

    /**
     * gets the longitude
     * @return
     *         longitude
     */
    public double getLon() {
        return this.lon;
    }

    /**
     * sets the longitude
     * @param lonIn
     *         longitude
     */
    public void setLon(double lonIn) {
        this.lon = lonIn;
    }

    /**
     * gets the track
     * @return
     *         track
     */
    public float getTrack() {
        return this.track;
    }

    /**
     * sets the track
     * @param trackIn
     *         track
     */
    public void setTrack(float trackIn) {
        this.track = trackIn;
    }

    /**
     * gets the time
     * @return
     *         time
     */
    public String getTime(){
        return this.time;
    }

    /**
     * sets the time
     * @param timeIn
     *         time
     */
    public void setTime(String timeIn) {
        this.time = timeIn;
    }

    /**
     * gets the altitude
     * @return
     *         altitude
     */
    public float getAltitude() {
        return this.altitude;
    }

    /**
     * sets the altitude
     * @param altIn
     *         altitude
     */
    public void setAltitude(float altIn) {
        this.altitude = altIn;
    }

    /**
     * gets the number of satellites
     * @return
     *         number of satellites
     */
    public int getSatNum() {
        return this.satNum;
    }

    /**
     * sets the number of satellites
     * @param satNumIn
     *         number of satellites
     */
    public void setSatNum(int satNumIn) {
        this.satNum = satNumIn;
    }

    /**
     * gets the date
     * @return
     *         date
     */
    public String getDate() {
        return this.date;
    }

    /**
     * sets the date
     * @param dateIn
     *         date
     */
    public void setDate(String dateIn) {
        this.date = dateIn;
    }

    /**
     * gets the speed
     * @return
     *         speed
     */
    public float getSpeed() {
        return this.speed;
    }

    /**
     * sets the speed
     * @param speedIn
     *         speed
     */
    public void setSpeed(float speedIn) {
        this.speed = speedIn;
    }

    /**
     * gets the update
     * @return
     *          the update
     */
    public int getUpdate() {
        return this.update;
    }

    /**
     * sets the update
     * @param updateIn
     *          update
     */
    public void setUpdate(int updateIn) {
        this.update = updateIn;
    }

    /**
     * checks if this objects contains information
     * @return
     *         true or false
     */
    public boolean hasInfo() {
        return true;
    }
    
    /**
     * gets the signal strength
     * @return
     *          the signalstrength
     */
    public String getSignalStrength() {
        return SignalStrength;
    }

    /**
     * sets the signalstrength
     * @param signalStrength2
     *          the signalstrength
     */
    public void setSignalStrength(String signalStrength2) {
        SignalStrength = signalStrength2;
    }
}