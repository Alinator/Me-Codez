package org.openstreetmap.gui.jmapviewer;

import org.openstreetmap.gui.jmapviewer.GUI.Measure;
/**
 * 
 * @author Sebastian
 *
 */
public class CalculateDistance {

    /**
     * Calculates the distance from one coordinate to an other in meters
     * @param lat1
     *          latitude of coordinate 1
     * @param lng1
     *          longitude of coordinate 1
     * @param lat2
     *          latitude of coordinate 2
     * @param lng2
     *          longitude of coordinate 2
     * @return
     *          returns the distance in meters as a double
     */
    public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 3958.75;
        double diffLat = Math.toRadians(lat2-lat1);
        double diffLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(diffLat/2) * Math.sin(diffLat/2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(diffLng/2) * Math.sin(diffLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = earthRadius * c;

        int conversionMeter = 1609;

        return new Double(dist * conversionMeter).doubleValue();
        }
    
    /**
     * calculates the length of a given route in meters or kilometers
     * @param route
     *          the given route
     * @return
     *          returns the length of the route in meters or kilometers,
     *          depending on the enum Measure value of measure in the {@link GUI} class
     */
    public String calculate (Route route) {
        double result = 0;
        double finalResultMeters;
        double finalResultKm;
        for (int i = 0; i < route.wayPoints.size() - 1; i++) {
            result = result+ distFrom(route.getPoint(i).getLat(), route.getPoint(i).getLong(), route.getPoint(i+1).getLat(), route.getPoint(i+1).getLong());
        }
        finalResultMeters = Math.round(result);
        finalResultKm = Math.round(result);
        finalResultKm = finalResultKm / 1000;

        if (GUI.measure == Measure.METERS) {
        return Double.toString(finalResultMeters);
        } else {
           return Double.toString(finalResultKm);
        }
    }
}
