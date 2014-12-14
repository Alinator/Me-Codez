
package server;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 
 * This class implements a Dijkstra's algorithm on a graph which is created from a database of nodes and edges.
 * 
 * @author Kirill Blazhko, Dmitry Igoshin
 * 
 * @param <type>
 *            The generic type for the Node class
 * @param <edgesType>
 *            The generic type for the Edge class
 * @param @deprecated <weightType> The generic type for the weight of Node The
 *        class implements the Dijkstra algorithm to find the shortest path from
 *        one Node to the other Node in the Graph that consists of Nodes and
 *        Edges.
 */

public class Pathfinder<type, edgesType, weightType> {

	private Node _startpoint, _currentNode, _endpoint;
	ArrayList<Node> visitedNodes = new ArrayList<Node>();
	static ArrayList<Predecessors> predecessorsList = new ArrayList<Predecessors>();
	// This HashMap holds the predecessors of one particular node:
	Map<Node, Node> paths = new HashMap<Node, Node>();
	// This HashMap holds pairs of a node and a HashMap of its predecessors
	Map<Node, Map<Node, Node>> pathsHash = new HashMap<Node, Map<Node, Node>>();
	private NodeByWeightComparator nodeByWeightComparator = new NodeByWeightComparator();
	private PriorityQueue<Node> unvisitedNodes = new PriorityQueue<Node>(1,
			nodeByWeightComparator);
	private long startTime, endTime;
	private static long mainStartTime, mainEndTime;
	private int relaxCounter, unvisitedNodesCounter, pathsCounter = 0;
	// This variable turns console output on/off (output can be very large, up
	// to 4Gb text file)
	private boolean dbgMessages = false;

	
	/**
	 * The main method which parses a database into a graph, applies Dijkstra's algorithm to _each_node_ and then writes back the results to the 'paths' database table
	 * @param args
	 * 
	 */

public static void main(String[] args) {

		Graph<String, Double> graph = new Graph<String, Double>();

		long mainStartTime = System.currentTimeMillis();
		try {

			List<Node<String>> allNodes;
			Database db = new Database("localhost", "lindholmen20000", "root",
					"1212");
			graph = db.readDBEdgesNodesToGraph(graph);
			allNodes = graph.getNodes();
			System.out
					.println("********************************************************************************************");
			System.out.println("Generated a graph from DB");

			Pathfinder pathfinder = new Pathfinder(graph);

			System.out.println("Ready to save to the DB, there are " + predecessorsList.size() + " predecessors");


			for (int i = 0; i < predecessorsList.size(); i++) {

				Predecessors currentPredecessorPair;
				currentPredecessorPair = predecessorsList.get(i);
				System.out
						.println("*Pathfinder Class: Main: Iteration: printing all shortest paths of the node "
								+ currentPredecessorPair._startNode);
				currentPredecessorPair.printPredecessors();
					ArrayList<Integer> predecessors = new ArrayList<Integer>();
					predecessors = currentPredecessorPair.getPredecessors();
				for (int j = 0; j < allNodes.size(); j++) {
					
					ArrayList<Integer> path = new ArrayList<Integer>();
					
					getPath _getPath = new getPath();
					path = _getPath.findPath(predecessors, currentPredecessorPair._startNode, allNodes.get(j).getId());
					Collections.reverse(path);

					System.out
							.println("*Pathfinder Class: Main: Iteration: ready to add path to the database: from: " + currentPredecessorPair._startNode + " to: " + allNodes.get(j).getId() + ", the path is: " + path.toString());
					pathfinder.savePathToDb(db, graph.getNodeById(currentPredecessorPair._startNode),allNodes.get(j), path);
				System.out.println("*Pathdinder Class: Main: saved a path to database for node number (numer, not id): " + i);
				}

			}

			long mainEndTime = System.currentTimeMillis();
			System.out
					.println("**Class Pathfinder: Finished saving all paths to db: "
							+ (mainEndTime - mainStartTime) + " milliseconds");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}



	
	public Pathfinder() {
	}
	
	/**
	 * 
	 * This constructor creates a Pathfinder object and does a shortest path calculation on it.
	 * The actual shortest path calculation is done in this method.
	 * @param graph - needs a Graph object to apply Dijkstra's algorithm to it
	 * 
	 */

	public Pathfinder(Graph graph) {

		startTime = System.currentTimeMillis();
		ArrayList<Node<type>> nodes = new ArrayList<Node<type>>();
		nodes = (ArrayList<Node<type>>) graph.getNodes();

		for (int i = 0; i < nodes.size(); i++) {
			Predecessors predecessors = new Predecessors(nodes.get(i).getId());
			_startpoint = nodes.get(i);
			if (dbgMessages) {
				System.out
						.println("**Class Pathfinder: Starting to search for a shortest path)");
				System.out.println("**Class Pathfinder: The startpoint is "
						+ _startpoint.getId() + " and its initial weight is "
						+ _startpoint.getWeight());
			}
			// Set startpoint's weight to 0 as a first step of Dijkstra's
			// algorithm:
			_startpoint.setWeight(0);
			unvisitedNodes.add(_startpoint);

			while (!unvisitedNodes.isEmpty()) {

				relaxCounter += 1;
				if (dbgMessages) {
					System.out
							.println("***Class Pathfinder: Smallest unvisited node is: "
									+ unvisitedNodes.peek().getId());
				}
				_currentNode = smallestUnvisitedNode();

				if (dbgMessages) {
					System.out
							.println("***Class Pathfinder: Marked smallest unvisited node as current: "
									+ _currentNode.getId());
				}
				visitedNodes.add(_currentNode);
				relaxNeighbours(_currentNode, graph, predecessors);
			} // end of while
			predecessorsList.add(predecessors);
			if (dbgMessages) {
				System.out
						.println("**Class Pathfinder: Finished searching for a shortest path, found : "
								+ predecessorsList.size()
								+ " nodes with shortest paths sets");
				System.out
						.println("**Class Pathfinder: Finished searching for a shortest path, iterated through "
								+ relaxCounter
								+ " nodes of "
								+ nodes.size()
								+ " and visited "
								+ unvisitedNodesCounter
								+ " nodes");
			}
			relaxCounter = 0;
			unvisitedNodesCounter = 0;
			unvisitedNodes.clear();
			visitedNodes.clear();
			paths.clear();
			//Set all weights of all nodes to infinity again (prepare the graph to run Dijkstra's algorithm again for the next node)
			for (int j = 0; j < nodes.size(); j++) {
				nodes.get(j).setWeight(Double.MAX_VALUE);
			}
System.out.println("*Pathfinder Class: finished searching paths for node (" + i +  ")");
		} // end of 'for' iteration through all the nodes of the graph
	

		endTime = System.currentTimeMillis();

	} // end of constructor with parameter
	
	
	/**
	 * 
	 * This method utilizes the ability of the Priority Queue object to quickly return a node with a smallest weight
	 * @return a node with a smallest weight (and deletes it from unvisitedNodes)
	 * 
	 */
	private Node smallestUnvisitedNode() {
		unvisitedNodesCounter += 1;
		return unvisitedNodes.poll();

	} // end of smallestUnvisitedNode method

	
	/**
	 * 
	 * This method is used by the Dijkstra's algorithm. It is a 'relax neighbor nodes' function.
	 * @param current The current node
	 * @param graph The Graph
	 * @param predecessors Predecessors of the current node
	 * 
	 */
	private void relaxNeighbours(Node<type> current,
			Graph<type, Integer> graph, Predecessors predecessors) {
		List<Edge<Integer>> edges = graph.getEdges();

		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i).getFrom() == current.getId()) {
				if (!visitedNodes.contains(edges.get(i).getTo())) {
					Node<type> adjacent = graph.getNodeById(edges.get(i)
							.getTo());

					if (adjacent.getWeight() > (current.getWeight() + edges
							.get(i).getWeight())) {

						adjacent.setWeight(current.getWeight()
								+ edges.get(i).getWeight());


						unvisitedNodes.add(adjacent);
						paths.put(adjacent, current);
						pathsHash.put(current, paths);

						predecessors.addToPredecessors(current.getId(),
								adjacent.getId());

						pathsCounter += 1;



					}
				}
			}
		}
		if (dbgMessages) {
			System.out.println("*Class Pathfinder: found " + paths.size()
					+ " shortest paths");
		}

	} // end of method relaxNeighbours
	
	
	/**
	 * This method is a utility which helps to debug the Dijkstra's algorithm method
	 * @param Start node - the node for which we want to print all the shortest paths
	 * 
	 */

	public void printShortestPath(int startNode) {
		if (dbgMessages) {
			System.out
					.println("Class Pathfinder: printShortestPath: Started the method--------------------------------------------");
		}
		// Iterate through all predecessors and print the predecessors of
		// startNode, provided as an argument (the 'from' node):
		for (int i = 0; i < predecessorsList.size(); i++) {

			if (predecessorsList.get(i)._startNode == startNode) {
				predecessorsList.get(i).printPredecessors();
			}

		}

		/*
		 * }
		 * 
		 * Set pathsSet = pathsHash.entrySet(); Iterator iterator =
		 * pathsSet.iterator();
		 * 
		 * System.out.print(
		 * "Class Pathfinder: printShortestPath: Printing shortest path, there are "
		 * + pathsHash.size() +
		 * " paths in the shortest paths HashMap (control counter: " +
		 * pathsCounter + ")");
		 * 
		 * while (iterator.hasNext()) { System.out.println(
		 * "*Class Pathfinder: printShortestPath: showing the paths for the node "
		 * ); Map.Entry mapEntry = (Map.Entry) iterator.next(); // Showing
		 * shortest paths in the reversed order (value -> key from // the
		 * Hashmap): Set currentPathsSet = ((Map<Node, Node>)
		 * mapEntry.getValue()).entrySet(); Iterator pathsIterator =
		 * currentPathsSet.iterator(); while (pathsIterator.hasNext()) {
		 * 
		 * Map.Entry hashMapEntry = (Map.Entry) pathsIterator.next();
		 * 
		 * System.out.println("***" + ((Node) mapEntry.getKey()).getId() + ": "
		 * + ((Node) hashMapEntry.getValue()).getId() + " -> " + ((Node)
		 * hashMapEntry.getKey()).getId()); } }
		 */
	}
	
	/**
	 * This method saves all 'predecessors', i.e. path for a taken node to the 'paths' table in the database
	 * @param db Database name
	 * @param startNode Start node
	 * @param endNode End node 
	 * @param path A string with the list of paths from the Start node 
	 */

	public void savePathToDb(Database db, Node startNode, Node endNode,
			ArrayList<Integer> path) {
		int db_counter = 1;
		if (dbgMessages) {
			System.out
					.println("Class Pathfinder: savePathToDb: Started savePathToDb method--------------------------------------------");
			System.out
					.println("Class Pathfinder: savePathToDb: started to add path to the database from "
							+ startNode.getId()
							+ " to "
							+ endNode.getId()
							+ ": " + path.toString());
		}

		/*
		 * Set pathsSet = pathsHash.entrySet();
		 * 
		 * Iterator iterator = pathsSet.iterator(); System.out.print(
		 * "**Class Pathfinder: savePathToDb: Printing shortest path, there are "
		 * + paths.size() +
		 * " paths in the shortest paths HashMap (control counter: " +
		 * pathsCounter + ")"); while (iterator.hasNext()) { Map.Entry mapEntry
		 * = (Map.Entry) iterator.next(); // Showing shortest paths in the
		 * reversed order (value -> key from // the Hashmap): Set
		 * currentPathsSet = ((Map<Node, Node>) mapEntry.getValue())
		 * .entrySet(); Iterator pathsIterator = currentPathsSet.iterator();
		 * while (pathsIterator.hasNext()) {
		 * 
		 * Map.Entry hashMapEntry = (Map.Entry) pathsIterator.next();
		 * 
		 * System.out.println("***" + ((Node) mapEntry.getKey()).getId() + ": "
		 * + ((Node) hashMapEntry.getValue()).getId() + " -> " + ((Node)
		 * hashMapEntry.getKey()).getId()); } }
		 * 
		 * Node<type> now = new Node<type>(); String path = new String(); now =
		 * startNode; Map<Node, Node> nowPaths = new HashMap<Node, Node>();
		 * nowPaths = pathsHash.get(now);
		 * System.out.println("Class Pathfinder: savePathToDb: the path is from "
		 * + now.getId()); path += now.getId() + ",";
		 * System.out.println("Class Pathfinder: savePathToDb: all paths for " +
		 * now.getId() + " are: " + nowPaths.toString());
		 * System.out.println("Class Pathfinder: savePathToDb: the path is: _" +
		 * path + "_ now");
		 * 
		 * // This loop needs to be: 1. changed to controlled iterator (if this
		 * // .get doesn't return sorted elements (it has to have a flag //
		 * "found endNode" that triggers if the next key-lopp should run); 2. //
		 * duplicated with a loop that iterates through values as well
		 * 
		 * while ((nowPaths.get(now) != endNode) && (nowPaths.get(now) != null))
		 * { System.out.println(
		 * "*Class Pathfinder: savePathToDb: still going through the path, recording from: "
		 * + now.getId() + " there are " + nowPaths.size() + " paths");
		 * System.out.println(
		 * "*Class Pathfinder: savePathToDb: the next element in the path is: "
		 * + nowPaths.get(now).getId()); path +=
		 * Integer.toString(nowPaths.get(now).getId()); path += ","; System.out
		 * .println("*Class Pathfinder: savePathToDb: the path is: _" + path +
		 * "_ now"); now = nowPaths.get(now); System.out
		 * .println("*Class Pathfinder: savePathToDb: the path is: _" + path +
		 * "_ now"); } path += Integer.toString(endNode.getId()); try {
		 * System.out
		 * .println("Class Pathfinder: savePathToDb: adding path to the database: "
		 * + path); // db.addPath(startNode.getId(), endNode.getId(), path);
		 * System.out
		 * .println("Class Pathfinder: savePathToDb: added path to the database: "
		 * + path); } catch (Exception e) { e.printStackTrace(); }
		 */

		if (path.size() > 1) {
			db.addPath(startNode.getId(), endNode.getId(), path.toString());
			db_counter++;
			if (dbgMessages) {
				System.out
						.println("Class Pathfinder: savePathToDb: Saved paths to DB: : "
								+ db_counter);
			}
		}
	}

}
