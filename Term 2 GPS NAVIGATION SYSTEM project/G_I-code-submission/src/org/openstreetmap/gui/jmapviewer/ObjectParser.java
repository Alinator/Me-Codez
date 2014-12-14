package org.openstreetmap.gui.jmapviewer;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.*;

	/**
	 * XML stream parsing class
	 * @author Dmitry Igoshin
	 *
	 */

public class ObjectParser {
		private XMLInputFactory factory = XMLInputFactory.newInstance();
		ArrayList<MapObject> res;
		/**
		 * Actual parsing method
		 * @param filename - xml file name
		 * @return - graph filled with xml information
		 */
	   public ArrayList<MapObject> Parse(String XML) {
		   res = new ArrayList<MapObject>();
		   try {
			   StringReader s = new StringReader(XML);
			   XMLEventReader eventReader = factory.createXMLEventReader(s);
			   while(eventReader.hasNext())
			    {
			      XMLEvent xmlEvent = eventReader.nextEvent();
			      switch(xmlEvent.getEventType())
			      {
			        case XMLStreamConstants.START_ELEMENT:
			          processStartElement(xmlEvent.asStartElement());
			          break;
			      }
			    }
		   } catch (Exception e) {
			   System.out.println("Parsing error: " + e.getLocalizedMessage());
		   }
		   return res;
	   }
	   
   
	   /**
	    * Process opening element
	    */
	   private void processStartElement(StartElement element)
	   {
		   if (element.getName().toString().equals("object")) {
			   	  MapObject newObject = new MapObject();
			      newObject.setId(Integer.parseInt(element.getAttributeByName(new QName("id")).getValue().toString()));
			      newObject.setLat(Double.parseDouble(element.getAttributeByName(new QName("lat")).getValue().toString()));
			      newObject.setLon(Double.parseDouble(element.getAttributeByName(new QName("lon")).getValue().toString()));
			      newObject.setTags(element.getAttributeByName(new QName("tags")).getValue().toString());
			      res.add(newObject);	
		   }
	   }
	}


