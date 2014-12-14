package org.openstreetmap.gui.jmapviewer;
/**
 * 
 * @author Dmitry Igoshin
 * Object class. Used to store information about an object
 *
 */
public class MapObject {
	double lat;
	double lon;
	int id;
	String tags;
	
	/**
	 * Set object's latitude
	 * @param _lat - new latitude
	 */
	void setLat(double _lat) {
		lat = _lat;
	}
	
	/**
	 * Set object's longitude
	 * @param _lon - new longitude
	 */
	void setLon(double _lon) {
		lon = _lon;
	}
	
	/**
	 * Set object's id
	 * @param _id - new id
	 */
	void setId(int _id) {
		id = _id;
	}
	
	/**
	 * Set object's tags
	 * @param _tags - new tags
	 */
	void setTags(String _tags) {
		tags = _tags;
	}

	/**
	 * Get object's latitude
	 * @return latitude
	 */
	double getLat() {
		return lat;
	}
	
	/**
	 * Get object's longitude
	 * @return longitude
	 */
	double getLon() {
		return lon;
	}
	
	/**
	 * Get object's id
	 * @return id
	 */
	int getId() {
		return id;
	}
	
	/**
	 * Get object's tags
	 * @return tags
	 */
	String getTags() {
		return tags;
	}
	
}
