package org.openstreetmap.gui.jmapviewer;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.*;

/**
 * XML stream parsing class
 * @author Dmitry Igoshin
 *
 */
public class RouteParser {
	private XMLInputFactory factory = XMLInputFactory.newInstance();
	private boolean insideRoute;
	private boolean insideWayPoint;
	private Route r;
	private WayPoint w;
	
	/**
	 * Actual parsing method
	 * @param filename - xml file name
	 * @return - graph filled with xml information
	 */
   public Route Parse(String filename) {
	  r = new Route();
	   try {
		   FileInputStream in = new FileInputStream(filename);
		   XMLEventReader eventReader = factory.createXMLEventReader(in);
		   while(eventReader.hasNext())
		    {
		      XMLEvent xmlEvent = eventReader.nextEvent();
		      switch(xmlEvent.getEventType())
		      {
		      case XMLStreamConstants.START_DOCUMENT:
		          init();
		          break;
		        case XMLStreamConstants.START_ELEMENT:
		          processStartElement(xmlEvent.asStartElement());
		          break;
		      }
		      
		      
		    }
		   
	   } catch (Exception e) {
		   System.out.println("Parsing error: " + e.getLocalizedMessage() + e.getMessage() + e.getStackTrace());
	   }
	   return r;
   }
   
   /**
    * Init the variables
    */
   private void init() {
	   insideRoute = false;
	   insideWayPoint = false;
	 
   }
   
   /**
    * Process opening element
    */
   private void processStartElement(StartElement element)
   {
	   if (element.getName().toString().equals("ROUTE")) {
		   insideRoute = true;
	   }
	   if (element.getName().toString().equals("name")) {
  
	       r.name = element.getAttributeByName(new QName("id")).getValue().toString();
       }
	   if (element.getName().toString().equals("WAYPOINT")) {
		   insideWayPoint = true;
		   w = new WayPoint();
	   } 
	   if (element.getName().toString().equals("id")) {
		   if (insideWayPoint && insideRoute) {
			   w.setId(Integer.parseInt(element.getAttributeByName(new QName("id")).getValue().toString()));
		   } 
	   }
       if (element.getName().toString().equals("coord")) {
           if (insideWayPoint && insideRoute) {
               w.setLat(Double.parseDouble(element.getAttributeByName(new QName("lat")).getValue().toString()));
               w.setLong(Double.parseDouble(element.getAttributeByName(new QName("long")).getValue().toString()));
               r.addPoint(w);
           } 
       }
   }
   
   public Route[] getRoutes() {
       try {
           File file = new File("routes");
           File[] files = file.listFiles();
           int numFiles = files.length;
           Route[] toReturn = new Route[numFiles];
           for (int i = 0; i < numFiles; i++) {
               toReturn[i] = Parse("routes/" + files[i].getName());
           }
           
           return toReturn;
       } catch (Exception e){
           e.printStackTrace();
       }
       
       return null;
   }
}
