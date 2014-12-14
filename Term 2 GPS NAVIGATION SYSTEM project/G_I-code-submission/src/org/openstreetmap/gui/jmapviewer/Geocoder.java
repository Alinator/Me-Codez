package org.openstreetmap.gui.jmapviewer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Class for Geocoding(Search a lat and long by address).
 * Uses Googles geocoder api.
 * @author Sebastian
 * @param <update>
 *
 */

public class Geocoder {
private final static String ENCODING = "UTF-8";
private final static String APIKEY = "ABQIAAAAuKqWBsPsG2evmxvlxE_V3BR6N23j2c1WZX8Wi3JnMd2xtTa-yBQMpiXLQyEdI-e_PhpM3wqIjXucRA";

public static class Location {
public String lon, lat;
/**
 * Constructor
 * @param lat
 *          the given latitude
 * @param lon
 *          the given longitude
 */
public Location (String lat, String lon) {
this.lon = lon;
this.lat = lat;
}

public String toString () {
    return "Lat: "+lat+", Lon: "+lon;
    }

}

/**
 * Gets the location of the given address.
 * Send a request to the Google geocoding api with the address.
 * Parses the returned lines into coordinates, used to construct a location.
 * @param address
 *              the given adress
 * @return
 *              returns a Location attribute
 * @throws IOException
 */
public static Location getLocation (String address) throws IOException {
BufferedReader input = new BufferedReader(new InputStreamReader (new URL ("http://maps.google.com/maps/geo?q=" +URLEncoder.encode (address, ENCODING)+"&output=csv&key="+APIKEY).openStream ()));
String inputLine;
Location location = null;
int codeStatus = -1;
    while ((inputLine = input.readLine ()) != null) {
        codeStatus = Integer.parseInt (inputLine.substring (0, 3));
    if (codeStatus == 200)
        location = new Location (
        inputLine.substring ("200,6,".length (), inputLine.indexOf (',', "200,6,".length ())),
        inputLine.substring (inputLine.indexOf (',', "200,6,".length ())+1, inputLine.length ()));
}
    if (location == null) {
        switch (codeStatus) {
        case 400: throw new IOException ("Bad Request");
        case 500: throw new IOException ("Unknown error from Google Encoder");
        case 601: throw new IOException ("Missing query");
        case 602: return null;
        case 603: throw new IOException ("Legal problem");
        case 604: throw new IOException ("No route");
        case 610: throw new IOException ("Bad key");
        case 620: throw new IOException ("Too many queries");
        }
    }
        return location;
    }
}
