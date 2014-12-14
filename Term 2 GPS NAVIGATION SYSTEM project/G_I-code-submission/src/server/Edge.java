
package server;

/**
 * generic class Edge - represents edge in graph contains two id's (from and to)
 * and weight
 * 
 * @author Dmitry Igoshin
 * 
 * @param <double> - weight field double
 */
public class Edge<type> {
	private int _from;
	private int _to;
	private double _weight;
	String _tags;

	/**
	 * Constructor.
	 * 
	 * @param from
	 *            - id of `from` node
	 * @param to
s	 *            - id of `to` node
	 * @param weight
	 *            - weight of the edge
	 */
	public Edge(int from, int to, double weight) {
		_from = from;
		_to = to;
		_weight = weight;
		_tags = new String();
	}

	/*
	 * This constructor creates an edge with precalculated weight, which is
	 * calculated from geo coordinates of two nodes
	 * 
	 * @param Takes two nodes with geo coordinates
	 */

	public Edge(Node<type> from, Node<type> to) {
		
		System.out.println("*Class Edge: Generating new egde with weight defined in meters. The edge is between " + from.getId() + " and " + to.getId());
		_from = from.getId();
		_to = to.getId();

		//Calculate longitude cathetus:
		System.out.println("*Class Edge: Calculating longitude cathetus");
		double cathetusLat = Math.abs(from.getLat() - to.getLat());
		//Calculate latitude cathetus:
		System.out.println("*Class Edge: Calculating latitude cathetus");
		double cathetusLon = Math.abs(from.getLon() - to.getLon());

		//Calculate the square of hypotenuse:
		System.out.println("*Class Edge: Calculating the square of hypotenuse");
		double hypotenuseSquare = (cathetusLat * cathetusLat) + (cathetusLon * cathetusLon);
		//Calculate the hypotenuse (the actual weight) and convert it from degrees to meters:
		System.out.println("*Class Edge: Calculating the weight and converting it to meters");
		//One degree is equal to 111.2 kilometers:
		_weight = Math.sqrt(hypotenuseSquare) * 111200;
		System.out.println("*Class Edge: Created an edge from " + this.getFrom() + " to " + this._to + " with the weight of " + this._weight + " meters");
		

	}


	/**
	 * Sets `from` node's id.
	 * 
	 * @param from
	 *            - `from` node's id to set
	 */
	public void setFrom(int from) {
		_from = from;
	}

	/**
	 * Sets `to` node's id.
	 * 
	 * @param to
	 *            - `to` node's id to set
	 */
	public void setTo(int to) {
		_to = to;
	}

	/**
	 * Sets the tags
	 * 
	 * @param tags
	 */
	public void setTags(String tags) {
		_tags = tags;
	}

	/**
	 * Returns tags
	 * 
	 * @return
	 */
	public String getTags() {
		return _tags;
	}

	/**
	 * Sets weight
	 * 
	 * @param weight
	 *            - new weight
	 */
	public void setWeight(double weight) {
		_weight = weight;
	}

	/**
	 * gets `from` node's id
	 * 
	 * @return `from` node's id
	 */
	public int getFrom() {
		return _from;
	}

	/**
	 * gets `to` node's id
	 * 
	 * @return `to` node's id
	 */
	public int getTo() {
		return _to;
	}

	/**
	 * gets weight
	 * 
	 * @return edge's weight
	 */
	public double getWeight() {
		return _weight;
	}

	/**
	 * Determines if the edge is between two specified nodes. Ignores direction.
	 * 
	 * @param id1
	 *            - id of the first node
	 * @param id2
	 *            - id of the second node
	 * @return - true if the current edge is between nodes with ids id1 and id2,
	 *         false otherwise
	 */
	public boolean isBetween(int id1, int id2) {
		if (((_from == id1) && (_to == id2))
				|| ((_from == id2) && (_to == id1))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Determines if the current edge starts in node with specified id
	 * 
	 * @param id
	 *            - id of starting node
	 * @return - true if the edge starts in this node, false otherwise
	 */
	public boolean isFrom(int id) {
		if (_from == id) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Determines if the current edge ends in node with specified id
	 * 
	 * @param id
	 *            - id of ending node
	 * @return - true if the edge ends in this node, false otherwise
	 */
	public boolean isTo(int id) {
		if (_to == id) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Determines if two edges are equal
	 * 
	 * @param object
	 *            - edge to compare with
	 * @return - true if edges are equal, false otherwise
	 */
	public boolean equals(Edge<type> object) {
		if ((object.getFrom() == _from) && (object.getTo() == _to)
				&& (object.getWeight() == (_weight))) {
			return true;
		} else {
			return false;
		}
	}
}
