package org.openstreetmap.gui.jmapviewer;

import java.io.File;
import java.util.LinkedList;
import java.util.List;



import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {
static double coordLon;
static double coordLat;
static File path;
static List<Double> geocacheList;
static WayPoint[] geoList;
/**
 * author: ali nazar 
 * this method allows you to choose the .loc file from anywhere in the system the file may be.
 */
public static int fileChoose(){

    
    final JFileChooser choose= new JFileChooser();
        int toReturn = -1; 
            int open= choose.showOpenDialog(null);
            if( open == JFileChooser.CANCEL_OPTION ){
                System.out.println("closed");
                return -1;
            }else if(open== JFileChooser.APPROVE_OPTION && choose.getSelectedFile().toString().endsWith(".loc")) {
                toReturn = 0;
            path= choose.getSelectedFile();
            geocacheFileParse();
        }else{
         JOptionPane.showMessageDialog(null, "The selected file is not a .loc file, Please choose the\n right file and try again");
         fileChoose();
        }

    return toReturn;
}
/**
 * author: Mahsa	
 * parses the .loc file.
 */
public static void geocacheFileParse(){   
		try {
		
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(path);
			doc.getDocumentElement().normalize();
			NodeList waypointList = doc.getElementsByTagName("waypoint");

            geocacheList= new LinkedList<Double>();
            geoList = new WayPoint[waypointList.getLength()];
			for (int s = 0; s < waypointList.getLength(); s++) {

				Node waypointNode = waypointList.item(s);

				if (waypointNode.getNodeType() == Node.ELEMENT_NODE) {

					Element elment = (Element) waypointNode;

					NodeList nameList = elment.getElementsByTagName("name");
					Element nameElement = (Element) nameList.item(0);
					NodeList nameChildList = nameElement.getChildNodes();
					String nameId = ((Node) nameChildList.item(0)).getNodeValue();

					NodeList coordList = elment.getElementsByTagName("coord");
					Element coordElement = (Element) coordList.item(0);
					coordLon = Double.parseDouble(coordElement.getAttribute("lon"));
					coordLat = Double.parseDouble(coordElement.getAttribute("lat"));

		            geocacheList.add(coordLat);
		            geocacheList.add(coordLon);
		            geoList[s] = new WayPoint(coordLat, coordLon, s, nameId);
		            
				}
			}
		} catch (Exception e) {
		}
	}
public static double getCoordLon() {
    return coordLon;
}
public static void setCoordLon(double coordLon) {
    XMLReader.coordLon = coordLon;
}
public static double getCoordLat() {
    return coordLat;
}
public static void setCoordLat(double coordLat) {
    XMLReader.coordLat = coordLat;
}


}