package r15;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

// author Ali Nazar and Li Jiaqing
public class User extends AbstractTableModel implements Serializable{

	int health, score, time;
	String name;
	
	int player_x;
	int player_y;
	
	
	Keyboard keyboard;
	User user;
	Player player;
	
	public User(String n){
		name = n;
		
	}
	public User(int s,int h){
		
		
		health = h;
		score = s;
		
//		player_x= px;
//		player_y=py;
		
//		load_save(player_x,player_y);
		storeHighscore(name,score, health);
	}
	
	
		
	
    void storeHighscore(String Name,int Score , int Health) {
//   
    	Connection connection= null;
    	try{
    		// database connection
    		
    		connection= DriverManager.getConnection("jdbc:mysql://localhost/highscore","root","muhammad");
    		Statement statement= connection.createStatement();
    		statement.execute(String.format("INSERT INTO scoreboard(name, Score, health ) VALUES('%s','%d','%d');",Name,Score,Health));
    		
    	}catch(SQLException e){
    		JOptionPane.showMessageDialog(null,e.getLocalizedMessage());
    		
    	}
    	try{
    		connection.close();
    	}catch(Exception e){
    		System.out.println(e.getLocalizedMessage());
    	}
    }
    
    void loadhighscore (){
    Connection load= null;
    Statement statement;
    ResultSet results;
    int row=0;

    try{
    		
    		load= DriverManager.getConnection("jdbc:mysql://localhost/highscore","root","muhammad");
    		statement= load.createStatement();
    		results= statement.executeQuery("SELECT * FROM scoreboard ORDER BY score DESC LIMIT "+ROWS);
    		while(results.next()){
    			table[row][0]=results.getString("name");
    			table[row][1]=results.getString("Score");
    			table[row][2]=results.getString("Time");
    			table[row][3]=results.getString("health");
    			row++;
    		}
    		load.close();
    		
    		fireTableDataChanged();
    	}catch(SQLException ex){
    		JOptionPane.showMessageDialog(null,"DURING LOAD PROCESS"+"/n"+ex.getLocalizedMessage());
    	}
    	try{
    	
    
    }catch(Exception e){
    	JOptionPane.showMessageDialog(null,"could not close database highscore after loading highscore");
    }
    }


	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return COLUMNS;
	}




	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return ROWS;
	}




	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		return table[row][column];
	}
    String[][] table = new String[ROWS][COLUMNS];
    
    static final int ROWS = 5;
    static final int COLUMNS= 4;

    

void save(){
	
try{
	
	player.update();
	 FileOutputStream file = new FileOutputStream("Save.txt");
     ObjectOutputStream output = new ObjectOutputStream(file);
     output.writeObject(player);
     output.close();
}catch (IOException iox){
		JOptionPane.showMessageDialog(null,iox.getLocalizedMessage());
}
}
void load() throws ClassNotFoundException{

	try{
		 FileInputStream FileIn = new FileInputStream("save.txt");
         ObjectInputStream input = new ObjectInputStream(FileIn);
         Player player =(Player)input.readObject();
         player.update();
         input.close();
	}catch(IOException iox){
		JOptionPane.showMessageDialog(null,iox.getLocalizedMessage()); 
	}
}
	
	
}

		
		
		
		
		
	

