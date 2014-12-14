package org.openstreetmap.gui.jmapviewer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Gets the information about map objects (parkings etc.) from the API
 * @author Dmitry
 */
public class Explorer {
    /**
     * gets the XML from the API
     * @param lat1 - starting latitude
     * @param lon1 - starting longitude
     * @param lat2 - ending latitude
     * @param lon2 - ending longitude
     * @param tags - tags to look for
     * @return - a String containing XML
     * @throws Exception
     */
	String getXML(double lat1, double lon1, double lat2, double lon2, String tags) throws Exception {
		String codePage = new String("UTF-8");
		StringBuilder sb = new StringBuilder();
        URL pageURL = new URL("http://api.geoproject.se/getplaces.php?lat1=" + Double.toString(lat1) + "&lon1="
        		+ Double.toString(lon1) + "&lat2=" 
        		+ Double.toString(lat2) + "&lon2=" + Double.toString(lon2) + "&tag=" + tags );
        System.out.println(pageURL.toString());
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
