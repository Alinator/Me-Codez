
package server;
public class ParseMain {
	public static void main(String[] args) {
		XMLParser parser = new XMLParser();
		Graph<String, Double> g = new Graph<String, Double>();
		g = parser.Parse("map.osm");
		System.out.println(g.toString());
	//	g.saveToDb("localhost", "graph", "root", "41681");
		
	}
}
