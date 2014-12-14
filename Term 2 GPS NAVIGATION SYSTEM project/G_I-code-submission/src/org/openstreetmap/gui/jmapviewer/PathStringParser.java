package org.openstreetmap.gui.jmapviewer;

/**
 * Class to parse path of format string to format Route
 * @author Johan
 *
 */
public class PathStringParser {
    
    /**
     * Parses a given string to a Route object
     * @param path
     *          the given path
     * @return
     *          returns the path as a Route object
     */
    public Route parsePathString(String path) {
        Route route = new Route();
        
        String withoutParantheses = "";
        
        for (int i = 0; i < path.length(); i ++) {
           if (path.charAt(i) != '(' && path.charAt(i) != ')') withoutParantheses += path.charAt(i);
        }

        String[] pathArray = withoutParantheses.split(",");
        for (int i = 0; i < pathArray.length - 1; i+=2) {
            int j = 0;
            double lat = Double.parseDouble(pathArray[i]);
            double lon = Double.parseDouble(pathArray[i+1]);
            route.addPoint(new WayPoint(lat, lon, j));
            j++;
        }
        return route;
    }
}
