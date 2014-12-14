
package server;
/**
 * 
 * generic class Node
 * represents graph's node
 * @author Dmitry Igoshin
 * @param <type> - type of node's value (values are tags!)
 */
public class Node<type> {
	private int _id;
	private type _value;
	private double _weight;
	private double _lat;
	private double _lon;
	
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}



	private String tags;
	
	
	/**
	 * Constructor
	 * @param id - node's id
	 * @param value - node's value
	 */
	public Node(int id, type value) {
		_id = id;
		_value = value;
		_weight = Double.longBitsToDouble(0x7fefffffffffffffL);
	}
	
	/**
	 * Default constructor
	 */
	public Node() {
		_weight = Double.longBitsToDouble(0x7fefffffffffffffL);
	}

	/**
	 * Sets node's id
	 * @param id - new id
	 */
	public void setId(int id) {
		_id = id;
	}
	
	/**
	 * Sets node's value
	 * @param value - new value
	 */
	public void setValue(type value) {
		_value = value;
	}
	
	/**
	 * Sets longitude
	 * @param lon
	 */
	public void setLon(double lon) {
		_lon = lon;
	}
	
	/**
	 * Gets longitude
	 * @return
	 */
	public double getLon() {
		return _lon;
	}
	
	/**
	 * Sets latitude
	 * @param lat
	 */
	public void setLat(double lat) {
		_lat = lat;
	}
	
	/**
	 * Gets latitude
	 * @return
	 */
	public double getLat() {
		return _lat;
	}
	
	
	
	
	
	/**
	 * Sets node's value
	 * @param value - new value
	 */
	public void setWeight(double weight) {
		_weight = weight;
	}
	
	/**
	 * Returns node's id
	 * @return - node's id
	 */
	public int getId() {
		return _id;
	}

	/**
	 * Returns node's value
	 * @return - node's value
	 */
	public type getValue() {
		return _value;
	}
	
		/**
	 * Returns node's weight
	 * * @return - node's weight
	 * */
	public double getWeight() {
		return _weight;
	}
	
	
	
	/**
	 * Determines if two nodes are equal
	 * @param object - node to compare with
	 * @return - true if nodes are equal, false otherwise
	 */
	public boolean equals(Node<type> node) {
		if ((node.getValue().equals(_value)) && (node.getId() == _id)) {
			return true;
		} else {
			return false;
		}
 	}
	
	
}
