
package server;
import java.io.FileInputStream;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.*;

/**
 * XML stream parsing class
 * @author Dmitry Igoshin
 *
 */
public class XMLParser {
	private XMLInputFactory factory = XMLInputFactory.newInstance();
	private boolean insideWay;
	private boolean insideNode;
	private boolean haveNode;
	private int node;
	private int nodeId;
	private int wayId;
	private Graph<String, Double> g;
	private Node<String> newNode;
	private Edge<Double> newEdge;
	
	/**
	 * Actual parsing method
	 * @param filename - xml file name
	 * @return - graph filled with xml information
	 */
   public Graph<String, Double> Parse(String filename) {
	   g = new Graph<String, Double>();
	   try {
		   FileInputStream in = new FileInputStream(filename);
		   XMLEventReader eventReader = factory.createXMLEventReader(in);
		   while(eventReader.hasNext())
		    {
			   System.out.println("next element");
			//   System.out.println(g.toString());
		      XMLEvent xmlEvent = eventReader.nextEvent();
		      switch(xmlEvent.getEventType())
		      {
		      case XMLStreamConstants.START_DOCUMENT:
		          init();
		          break;
		        case XMLStreamConstants.START_ELEMENT:
		          processStartElement(xmlEvent.asStartElement());
		          break;
		        case XMLStreamConstants.CHARACTERS:
		          processCharacters(xmlEvent.asCharacters());
		          break;
		      }
		      
		      
		    }
		   
	   } catch (Exception e) {
		   System.out.println("Parsing error: " + e.getLocalizedMessage());
	   }
	   return g;
   }
   
   /**
    * Init the variables
    */
   private void init() {
	   g = new Graph<String, Double>();
	   insideWay = false;
	   insideNode = false;
	   haveNode = true;
	   nodeId = 0;
	   wayId = 0;
   }
   
   /**
    * Process opening element
    */
   private void processStartElement(StartElement element)
   {
	   if (element.getName().toString().equals("node")) {
		   nodeId++;
		   System.out.println("Node #" + nodeId);
		   insideWay = false;
		   if (insideNode) {
			   g.addNode(newNode);
			   newNode = new Node<String>();
			   newNode.setValue("");
			   newNode.setId(Integer.parseInt(element.getAttributeByName(new QName("id")).getValue().toString()));
			   newNode.setLat(Double.parseDouble(element.getAttributeByName(new QName("lat")).getValue().toString()));
			   newNode.setLon(Double.parseDouble(element.getAttributeByName(new QName("lon")).getValue().toString()));	   
		   } else {
			   insideNode = true;
			   newNode = new Node<String>();
			   newNode.setValue("");
			   newNode.setId(Integer.parseInt(element.getAttributeByName(new QName("id")).getValue().toString()));
			   newNode.setLat(Double.parseDouble(element.getAttributeByName(new QName("lat")).getValue().toString()));
			   newNode.setLon(Double.parseDouble(element.getAttributeByName(new QName("lon")).getValue().toString()));	     
		   }
	   }
	   if (element.getName().toString().equals("way")) {
		   wayId++;
		   System.out.println("Way #" + wayId);
		
		   haveNode = false;
		   insideWay = true;
		   insideNode  = false;
	   }

	   if (element.getName().toString().equals("tag")) {
		   if (insideNode) {
			   newNode.setValue(newNode.getValue() + element.getAttributeByName(new QName("k")).getValue().toString() + "=" + element.getAttributeByName(new QName("v")).getValue().toString() + ",");
		   }
		   if (insideWay) {
			   newEdge.setTags(newEdge.getTags() + element.getAttributeByName(new QName("k")).getValue().toString() + "=" + element.getAttributeByName(new QName("v")).getValue().toString() + ",");
		   } 
	   
	   }
	   
	   if (element.getName().toString().equals("nd")) {
		    if (haveNode) {
		    	newEdge = new Edge<Double>(node, Integer.parseInt(element.getAttributeByName(new QName("ref")).getValue().toString()), 0);
		    	g.addEdge(newEdge);
		    	node = Integer.parseInt(element.getAttributeByName(new QName("ref")).getValue().toString()); 
		    	haveNode = true;
		    } else {
		    	haveNode = true;
		    	node = Integer.parseInt(element.getAttributeByName(new QName("ref")).getValue().toString());
		    }
	   }
   }
   
   /**
    * Process characters (element's content)
    * @param characters - characters to work with
    */
   private void processCharacters(Characters characters)
   {
	   /*
     if ((insideNode) && (insideNodes)) {
    	 if (!(characters.getData().isEmpty())) {
    		 newNode.setValue(characters.getData());
    		 g.addNode(newNode);
    		 insideNode = false;
    	 } 
     }
     */
   }

   
   
}
