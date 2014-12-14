package Graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Graph<N> {

	
	void add(N Node);
	void Connect(N from, N to, String name, int weight);
	Set<N> getNodes();
	ArrayList<Edge<N>> getEdgesFrom(N Node);
	ArrayList<Edge<N>> getEdgesBetween(N Node1, N Node2);
	String toString();
	Map<N, List<Edge<N>>> getGraphList();

}
