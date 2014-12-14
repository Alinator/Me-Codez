package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.swing.JOptionPane;

public class ListGraph<N> implements Graph<N>{

	Map<N, List<Edge<N>>> graph = new HashMap <N,List<Edge<N>>>();

	public ListGraph(){
	}

	@Override
	public void add(N Node) {
		// TODO Auto-generatwd method stub
		if(!graph.containsKey(Node)){
			graph.put(Node, new ArrayList<Edge<N>>());
			System.out.println("added");
		}
	}
	@Override
	public void Connect(N from, N to, String name, int weight) {
		// TODO Auto-generated method stub
		if((graph.containsKey(from)) || (graph.containsKey(to))){
			
		List<Edge<N>> fromList= graph.get(from);
		List<Edge<N>> toList= graph.get(to);

		if(fromList == null || toList == null){
			throw new NoSuchElementException("Connect");
		}

		Edge<N> edge1= new Edge<N>(to,name,weight);
		fromList.add(edge1);
		Edge<N> edge2 = new Edge<N>(from,name,weight);
		toList.add(edge2);	
		
		}else{
			throw new NoSuchElementException();
		}
	}//Connect

	@Override
	public Set<N> getNodes(){
		// TODO Auto-generated method stub
		return graph.keySet();
	}

	@Override
	public ArrayList<Edge<N>> getEdgesFrom(N Node) {
		// TODO Auto-generated method stub
		return new ArrayList<Edge<N>>(graph.get(Node));
	}

	@Override
	public ArrayList<Edge<N>> getEdgesBetween(N Node1, N Node2) {
		// TODO Auto-generated method stub
		ArrayList<Edge<N>> edges= new ArrayList<Edge<N>>();
		
		if(graph.containsKey(Node1) && graph.containsKey(Node2)){
			for(Edge<N> e : getEdgesFrom(Node1)){
				if(e.getDestination() == Node2){	
					edges.add(e);
				}
			}
			return edges;	
		}
		return null;
	}

	public String toString(){
		String str="";
		for(Map.Entry<N, List<Edge<N>>> me : graph.entrySet()){
			str += me.getKey() + ": "; for(Edge<N> e : me.getValue())
				str += e.toString() + " ";
					str += "\n";
		} // yttre for
		return str;
	}

	@Override
	public Map<N, List<Edge<N>>> getGraphList() {
		// TODO Auto-generated method stub
		return this.graph;
	}

}
