
package server;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic graph class
 * can be used for directed and undirected graphs
 * @author Dmitry Igoshin
 *
 * @param <type> - type of nodes value (values are tags!)
 * @param <Double> - type of edges weight value 
 */
public class Graph<type, Double> {
	private List<Node<type>> nodes;
	private List<Edge<Double>> edges; 
	
	/**
	 * Default constructor.
	 */
	public Graph() {
		nodes = new ArrayList<Node<type>>();
		edges = new ArrayList<Edge<Double>>();
	}
	
	/**
	 * Adds a node to the graph
	 * @param node - node to add
	 */
	public void addNode(Node<type> node) {
		nodes.add(node);
	}
	
	/**
	 * Creates a new node in the graph
	 * @param id - node's id
	 * @param value - node's value
	 */
	public void addNode(int id, type value) {
		Node<type> node = new Node<type>(id, value);
		nodes.add(node);
	}
	
	/**
	 * Removes specific node from graph
	 * @param node - node to remove
	 */
	public void removeNode(Node<type> node) {
		nodes.remove(node);
	}
	
	/**
	 * Removes specific node from graph
	 * @param id - node's id
	 */
	public void removeNode(int id) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getId() == id) {
				nodes.remove(i);
			}
		}
	}
	
	/**
	 * Removes all the nodes from the graph
	 */
	public void clearNodes() {
		nodes.clear();
	}
	
	/**
	 * Adds an edge to the graph 
	 * @param node1 - starting node (must be present in the graph)
	 * @param node2 - ending node (must be present in the graph)
	 * @param weight - weight of the edge
	 */
	public void addEdge(Node<type> node1, Node<type> node2, double weight) {
		if ((nodes.contains(node1)) && (nodes.contains(node2))) {
			addEdge(nodes.get(nodes.indexOf(node1)).getId(), nodes.get(nodes.indexOf(node2)).getId(), weight);
		}
	}
	
	/**
	 * Adds an edge to the graph
	 * @param from - id of the starting node (must be present in the graph)
	 * @param to - id of ending node (must be present in the graph)
	 * @param weight - weight of the edge
	 */
	public void addEdge(int from, int to, double weight) {
		if ((getNodeById(from) != null) && (getNodeById(to) != null)) {
			Edge<Double> edge = new Edge<Double>(from, to, weight);
			edges.add(edge);
		}
	}
	
	/**
	 * Adds an edge to the graph
	 * @param edge - edge to add
	 */
	@Deprecated public void addEdge(Edge<Double> edge) {
		edges.add(edge);
	}
	
	/**
	 * Determines if the graph contains specified node
	 * @param node - node to search for
	 * @return - true if the graph contains specified node, false otherwise
	 */
	public boolean contains(Node<type> node) {
		return nodes.contains(node);
	}
	
	/**
	 * Gets node by its id
	 * @param id - id to look for
	 * @return - node with specified id or null
	 */
	public Node<type> getNodeById(int id) {
		for(int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getId() == id) {
				return nodes.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Removes an edge
	 * @param edge - edge to remove
	 */
	public void removeEdge(Edge<Double> edge) {
			edges.remove(edge);
	}
	
	/**
	 * Removes an edge
	 * @param from - starting node's id 
	 * @param to - ending node's id
	 */
	public void removeEdge(int from, int to) {
		for (int i = 0; i < edges.size(); i++) {
			if ((edges.get(i).getFrom() == from) && (edges.get(i).getTo() == to)) {
				edges.remove(i);
			}
		}
	}
	
	/**
	 * Removes an edge
	 * @param from - starting node's id 
	 * @param to - ending node's id
	 * @param ignoreDirection - `true` to remove both `from`-`to` and `to`-`from` edges
	 */	
	public void removeEdge(int from, int to, boolean ignoreDirection) {
		for (int i = 0; i < edges.size(); i++) {
			if ((edges.get(i).getFrom() == from) && (edges.get(i).getTo() == to)) {
				edges.remove(i);
			}
			if (ignoreDirection) {
				if ((edges.get(i).getFrom() == to) && (edges.get(i).getTo() == from)) {
					edges.remove(i);
				}
			}
		}
	}
	
	/**
	 * Deletes all the graph's edges
	 */
	public void clearEdges() {
		edges.clear();
	}
	
	/** 
	 * Determines if there is an edge between from and to 
	 * @param from - starting node's id
	 * @param to - ending node's id
	 * @return - true if edge exists; false otherwise
	 */
	public boolean isEdgeBetween(int from, int to) {
		for (int i = 0; i < edges.size(); i++) {
			if ((edges.get(i).getFrom() == from) && (edges.get(i).getTo() == to)) {
				return true;
			}
		}
		return false;
	}

	/** 
	 * Determines if there is an edge between from and to 
	 * @param from - starting node's id
	 * @param to - ending node's id
	 * @param ignoreDirection - `true` to look for both `from`-`to` and `to`-`from` edges
	 * @return - true if edge exists; false otherwise
	 */
	public boolean isEdgeBetween(int from, int to, boolean ignoreDirection) {
		for (int i = 0; i < edges.size(); i++) {
			if ((edges.get(i).getFrom() == from) && (edges.get(i).getTo() == to)) {
				return true;
			}
			if (ignoreDirection) {
				if ((edges.get(i).getFrom() == to) && (edges.get(i).getTo() == from)) {
					return true;
				}	
			}
		}
		return false;
	}

	/**
	 * Returns an edge between two nodes
	 * @param from - starting node's id
	 * @param to - ending node's id
	 * @return - true if edge exists, false otherwise
	 */
	public Edge<Double> getEdgeBetween(int from, int to) {
		for (int i = 0; i < edges.size(); i++) {
			if ((edges.get(i).getFrom() == from) && (edges.get(i).getTo() == to)) {
				return edges.get(i);
			}
		}
		return null;	
	}

	/**
	 * Returns an edge between two nodes
	 * @param from - starting node's id
	 * @param to - ending node's id
	 * @param ignoreDirection - true to get `from`-`to` edge or `to`-`from` edge
	 * @return - true if edge exists, false otherwise
	 */
	public Edge<Double> getEdgeBetween(int from, int to, boolean ignoreDirection) {
		for (int i = 0; i < edges.size(); i++) {
			if ((edges.get(i).getFrom() == from) && (edges.get(i).getTo() == to)) {
				return edges.get(i);
			}
			if (ignoreDirection) {
				if ((edges.get(i).getFrom() == to) && (edges.get(i).getTo() == from)) {
					return edges.get(i);
				}	
			}
		}
		return null;
	}
	
	/**
	 * Returns weight between two nodes
	 * @param from - starting node's id
	 * @param to - ending node's id
	 * @return - weight between two nodes or null
	 */
	public double getWeightBetween(int from, int to) {
		for (int i = 0; i < edges.size(); i++) {
			if ((edges.get(i).getFrom() == from) && (edges.get(i).getTo() == to)) {
				return edges.get(i).getWeight();
			}
		}
		return 0;	
	}
	
	/**
	 * Returns weight between two nodes
	 * @param from - starting node's id
	 * @param to - ending node's id
	 * @param ignoreDirection - true to get weight of `from`-`to` edge or `to`-`from` edge
	 * @return - weight between two nodes or null
	 */	
	public double getWeightBetween(int from, int to, boolean ignoreDirection) {
		for (int i = 0; i < edges.size(); i++) {
			if ((edges.get(i).getFrom() == from) && (edges.get(i).getTo() == to)) {
				return edges.get(i).getWeight();
			}
			if (ignoreDirection) {
				if ((edges.get(i).getFrom() == to) && (edges.get(i).getTo() == from)) {
					return edges.get(i).getWeight();
				}	
			}
		}
		return 0;
		
	}
	
	/** 
	 * Gets all the graph's nodes
	 * @return - List of nodes
	 */
	public List<Node<type>> getNodes() {
		return nodes;
	}

	/** 
	 * Gets all the graph's edges
	 * @return - List of edges
	 */
	public List<Edge<Double>> getEdges() {
		return edges;
	}
	
	/**
	 * Determines if two edges are equal
	 * @param object - edge to compare with
	 * @return - true if edges are equal, false otherwise
	 */
	public boolean equals(Graph<type, Double> object) {
		if ((object.edges.equals(edges)) && (object.nodes.equals(nodes))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Creates a String graph summary.
	 * Useful for debugging
	 */
	public String toString() {
			String result = new String();
		result = "**************************\n";
		result += "Graph summary: \n";
		result += "Nodes: \n";
		result += "Id        Value \n";
		for (int i = 0; i < nodes.size(); i++) {
			result += nodes.get(i).getId() + "           "
					+ nodes.get(i).getValue().toString() + ", weight: "
					+ nodes.get(i).getWeight();
			result += "\n";
		}
		result += "Edges: \n";
		result += "From      To       Weight \n";
		for (int i = 0; i < edges.size(); i++) {
			result += edges.get(i).getFrom() + "          "
					+ edges.get(i).getTo() + "         "
					+ edges.get(i).getWeight();
			result += "\n";
		}
		result += "Total nodes: " + nodes.size() + ", total edges: " + edges.size() + "\n";
		result += "----------------------------------------";
		return result;

	}
	
	public void saveToDb(String server, String dbname, String user, String password) {
		try {
		Database db = new Database(server, dbname, user, password);
		
		for (int i = 0; i < nodes.size(); i++) {
			System.out.println("Processing node " + i + " of " + nodes.size());
			db.insertNode(nodes.get(i).getId(), nodes.get(i).getLat(), nodes.get(i).getLon(), nodes.get(i).getValue().toString());
		}
		for (int i = 0; i < edges.size(); i++) {
			System.out.println("Processing edge " + i + " of " + edges.size());
			db.insertEdge(edges.get(i).getFrom(), edges.get(i).getTo(), edges.get(i).getWeight(), edges.get(i).getTags());
		}	
		} catch (Exception ex) {
			System.out.println("Database failure! " + ex.getLocalizedMessage());
		}
	
	}
	
}
