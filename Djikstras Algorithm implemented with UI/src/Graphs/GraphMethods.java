package Graphs;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class GraphMethods<N>{

	private static <N> void depthFirstSearch(Graph<N> graph,N from, Set<N> visited){
		visited.add(from);
		for(Edge<N> e : graph.getGraphList().get(from)){
			if(!visited.contains(e.getDestination())){
				depthFirstSearch(graph,e.getDestination(),visited);
			}
		}
	}

	public static <N> boolean pathExist(Graph<N> graph,N from, N to){
		if(graph.getGraphList().containsKey(from)){

			Set<N> visited= new HashSet<N>();
			depthFirstSearch(graph,from, visited);
			return visited.contains(to);

		}
		return false;
	}
	/**
	 * @param g
	 * @param from
	 * @param to
	 * @return 
	 * Method will find the shortest path between two nodes, Using the DJIKTRAS Algorithm.
	 *  FYI THIS IS CRAZY STUFF
	 */
	public static <N> List<Edge<N>> shortestPath(Graph<N> g, N from, N to){		

		ArrayList<Edge<N>> edgelist= new ArrayList<Edge<N>>();
		ArrayList<Edge<N>> Route = new ArrayList<Edge<N>>();
		N arrivedNode = null;

		if(pathExist(g,from,to) == true){

			Map<N,Integer> tidmap = new HashMap <N,Integer>();		
			Map<N,Boolean> decidedmap = new HashMap <N,Boolean>();
			Map<N,N> nodemap= new HashMap<N,N>();

			for(N entry : g.getNodes()){
				tidmap.put(entry, Integer.MAX_VALUE);
				decidedmap.put(entry, false);
				nodemap.put(entry, null);
			}
			nodemap.put(from, null);

			tidmap.put(from, 0);
			decidedmap.put(from, true);

			N startNode=from;

			while(decidedmap.get(to).equals(false)){

				for(Edge<N> e : g.getEdgesFrom(startNode)){


					int nodeweight  = tidmap.get(startNode);
					N neighbourNode=e.getDestination();


					int neighbourWeight = tidmap.get(neighbourNode);

					int edgeWeight = e.getWeight();
					int newWeight=0;
					int startnodeweight=0;
					int neighbournodeweight=0;

					startnodeweight=nodeweight + edgeWeight;

					neighbournodeweight=neighbourWeight;

					if(startnodeweight < neighbournodeweight){

						newWeight= startnodeweight;
						tidmap.put(neighbourNode, newWeight);
						nodemap.put(neighbourNode,startNode);
					}
				}
				arrivedNode=null;
				int vikt=0;
				for(Entry<N,Integer> e : tidmap.entrySet()){
					if(decidedmap.get(e.getKey()).equals(false)){
						if(arrivedNode == null){

							arrivedNode=e.getKey();
							vikt=e.getValue();
						}else{
							if(vikt > e.getValue()){
								arrivedNode=e.getKey();
							}
						}
					}
				}
				decidedmap.put(arrivedNode, true);
				System.out.println(arrivedNode);
				startNode=arrivedNode;
			}
			System.out.println("---------------");

			N destNod=to;
			while(destNod != null){

				N xNode= nodemap.get(destNod);
				if(xNode != null){
					for(Edge<N> edges : g.getEdgesBetween(xNode, destNod)){
						edgelist.add(edges);
						destNod=xNode;
					}
				}else{
					destNod=null;
				}
			}
			for(int x = edgelist.size()-1; x >= 0; x--){
				Route.add(edgelist.get(x));
			}
		}
		return Route;
	}

}