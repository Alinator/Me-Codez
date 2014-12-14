package server;



/**
 * A class to maintain database features. Connects to a MySQL database.
 * @author Dmitry Igoshin, Kirill Blazhko
 * 
 * 
 */
import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class Database {

	private Connection c;
	private String databasename;

	Database(String server, String dbname, String user, String password)
			throws Exception {
		c = null;
		databasename = dbname;
		LoadDriver();
		Connect(server, user, password);
		Init(server, user, password, dbname);
	}

	private void Connect(String server, String user, String password)
			throws Exception {
		try {
			c = DriverManager.getConnection("jdbc:mysql://" + server
					+ "/?user=" + user + "&password=" + password);
		} catch (Exception e) {
			Exception ex = new Exception("Wrong database data."
					+ e.getMessage());
			throw ex;
		}
		if (c == null) {
			Exception ex = new Exception("Something got wrong");
			throw ex;
		}
	}

	private void LoadDriver() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException cnfe) {
			Exception ex = new Exception("Couldn't find driver class.");
			throw ex;
		}
	}

	private void Init(String server, String user, String password, String dbname)
			throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		try {
			Statement st = c.createStatement();
			DatabaseMetaData meta = c.getMetaData();
			ResultSet rs = meta.getCatalogs();
			while (rs.next()) {
				String listofDatabases = rs.getString("TABLE_CAT");
				list.add(listofDatabases);
			}
			if (!list.contains(dbname)) {
				UpdateQuery("CREATE DATABASE `"
						+ "graph"
						+ "` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci");
				UpdateQuery("CREATE  TABLE `graph`.`nodes` ("
						+ "`id` INT PRIMARY KEY , " + "`lat` DOUBLE NULL ,"
						+ "`lon` DOUBLE NULL ," + "`tags` TEXT NULL);");
				UpdateQuery("CREATE  TABLE `graph`.`edges` ("
						+ "`from` INT NULL ," + "`to` INT NULL ,"
						+ "`weight` DOUBLE NULL ," + "`tags` TEXT NULL);");

				rs.close();
			}
		} catch (Exception se) {
			Exception ex = new Exception("Unable to create the database.");
			throw ex;
		}

	}

	private ResultSet Query(String query) throws Exception {
		ResultSet rs = null;
		Statement s = null;
		try {
			s = c.createStatement();
			rs = s.executeQuery(query);
		} catch (SQLException se) {
			System.out
					.println("We got an exception while  executing our query: "
							+ query
							+ ". that probably means our SQL is invalid");
			System.out.println(se.getMessage());
			// System.exit(1);
			throw se;
		}
		return rs;
	}

	private int UpdateQuery(String query) {
		int number = 0;
		ResultSet rs = null;
		Statement s = null;
		try {
			s = c.createStatement();
			number = s.executeUpdate(query);
		} catch (SQLException se) {
			System.out
					.println("We got an exception while  executing our query: "
							+ query
							+ ". that probably means our SQL is invalid");
			System.out.println(se.getMessage());
			// System.exit(1);
		}
		return number;
	}

	public void insertNode(int id, double lat, double lon, String tags) {
		UpdateQuery("INSERT INTO `graph`.`nodes` VALUES (" + id + ", " + lat
				+ " , " + lon + " , '" + tags + "' )");
	}

	public void insertEdge(int from, int to, double weight, String tags) {
		UpdateQuery("INSERT INTO `graph`.`edges` VALUES (" + from + ", " + to
				+ " , " + weight + " , '" + tags + "' )");
	}

	public Graph readDBEdgesNodesToGraph(Graph graph) {
		ResultSet rs = null;
		try {
			rs = Query("SELECT * FROM " + databasename + ".edges");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				try {
				Edge edge = new Edge(0, 0, 0.0);
					edge.setFrom(rs.getInt("from"));
					edge.setTo(rs.getInt("to"));
					edge.setWeight(rs.getDouble("weight"));
					graph.addEdge(edge);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
			ResultSet rs2 = null;
		try {
			rs2 = Query("SELECT * FROM " + databasename + ".nodes");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs2.next()) {
				
				Node node = new Node<String>();
				node.setId(rs2.getInt("id"));
				node.setLat(rs2.getDouble("lat"));
				node.setLon(rs2.getDouble("lon"));
				node.setValue(" ");
				graph.addNode(node);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return graph;	
	}
	
	void addPath(int id1, int id2, String path) {
		UpdateQuery("INSERT INTO " + databasename + ".paths VALUES (" + id1 + ", " + id2 + ", '" + path + "');");
	}

}
