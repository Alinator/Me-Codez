package org.openstreetmap.gui.jmapviewer;

import java.io.*;

/**
 * Class that creates and writes a locfile
 * @author Johan
 *
 */
public class LocFileWriter {
    
    /**
     * method for writing locfiles
     * @param lat
     *          given latitude
     * @param lon
     *          given longitutde
     * @param id
     *          given id of coordinate
     */
    public void writeLocFile (double lat, double lon, String id) {
        try {
            FileWriter fileWriter = new FileWriter("toUpload.loc");
            BufferedWriter out = new BufferedWriter(fileWriter);
            out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            out.write("<loc version=\"1.0\" src=\"Step By Step\">\n"); 
            out.write("<waypoint>\n");
            out.write("<name id=\"" + id + "\"><![CDATA[Koppartrans II by Hedstrom]]>\n");
            out.write("</name>\n");
            out.write("<coord lat=\"" + lat + "\" lon=\"" + lon + "\"/>\n");
            out.write("<type>Geocache</type>\n");
            out.write("</waypoint>\n");
            out.write("</loc>");
            out.close();
            System.out.println("Wrote file");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
