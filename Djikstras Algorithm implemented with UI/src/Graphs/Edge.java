package Graphs;

public class Edge<N>{
	
	private String name=null;
	private int weight=0;
	private N destination;
	
	//constructor
	public Edge(N dest,String namn,int vikt){
		this.destination=dest;
		this.name=namn;
		this.weight=vikt;
	}
	//metoder
	
	public int getWeight(){
		return this.weight;
	}
	public N getDestination(){
		return this.destination;
	}
	public void setWeight(int newWeight){
		
		if(newWeight < 0){
			throw new IllegalArgumentException();
		}else{
			this.weight=newWeight;
		}
	}
	public String getName(){
		return this.name;
	}
	// returns a String with some information regarding this edge.
	public String toString(){
		return "till "+this.destination+" med "+this.name+" : "+this.weight+" km";
	}
}