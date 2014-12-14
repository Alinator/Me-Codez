package org.openstreetmap.gui.jmapviewer;

import java.io.*;
import java.net.*;

/**
 * 
 * @author Dmitry Igoshin
 * Pathfinder class. Returns a path from point A to point B.
 *
 */
public class PathFinder {
	
	/**
	 * The actual pathfinding method.
	 * @return - the path in (lat,lon)(lat,lon), format
	 * @throws Exception
	 */
	static String getPath(double lat1, double lon1, double lat2, double lon2, boolean newApi) throws Exception {
		String codePage = new String("UTF-8");
		StringBuilder sb = new StringBuilder();
		URL pageURL;
		if (newApi) {
             pageURL = new URL("http://api.geoproject.se/getpath.php?lat1=" + Double.toString(lat1) + "&lon1="
                    + Double.toString(lon1) + "&lat2=" 
                    + Double.toString(lat2) + "&lon2=" + Double.toString(lon2) );
		} else {
		     pageURL = new URL("http://api.geoproject.se/getpath2.php?lat1=" + Double.toString(lat1) + "&lon1="
	                + Double.toString(lon1) + "&lat2=" 
	                + Double.toString(lat2) + "&lon2=" + Double.toString(lon2) );    
		}
        
        URLConnection uc = pageURL.openConnection();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        uc.getInputStream(), codePage));
        try {
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }         
        } finally {
            br.close();
        }
        return sb.toString();
	}
}
