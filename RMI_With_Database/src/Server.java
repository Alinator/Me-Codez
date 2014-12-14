/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 *
 * @author Ali
 */
public class Server extends UnicastRemoteObject implements MarketPlace {
    static Map<CallBack,Object[]> WishedItems= new HashMap<>();
    static Map<String, String> ItemsInMarketPlace=new HashMap<>();
    static Map<String,CallBack> objects = new HashMap<>();
    static MarketPlace stub=null;
    static int clients =0;
    static Connection connect;
    
    public Server() throws RemoteException{
    }
    
      public static void main(String args[]) {
        
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            Server obj = new Server();
            Bank bankobj =  new BankImpl("Nordea") {};
            //stub = (MarketPlace) UnicastRemoteObject.exportObject(obj, 0);
            // Bind the remote object's stub in the registry
           // Registry registry = LocateRegistry.getRegistry();
            //registry.bind("marketplace", stub);
            Naming.rebind("marketplace", obj);
            Naming.rebind("Nordea",bankobj);
            
            // database
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/MarketPlace","root","root");

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
    @Override
    public void buyItem(CallBack buyerref,Account myAccount,String mywisheditem) throws RemoteException {
    	try {
			Statement getitemsinmarketplacestatement = connect.createStatement();
			ResultSet rs1 = getitemsinmarketplacestatement.executeQuery("SELECT name,price,referenceToSeller FROM itemsinmarketplace WHERE name='"+mywisheditem+"'");
			
			if(rs1.next()){
				
				int theprice = Integer.parseInt(rs1.getString("price"));
				if(myAccount.getBalance() > theprice){
					
					try {
						//withdraw and deposit
						myAccount.withdraw(theprice);
						CallBack reff = objects.get(rs1.getString("referenceToSeller"));
						reff.getTraderAccount().deposit(theprice);
						
						//send the bought and sold msgs:
						buyerref.boughtItem(rs1.getString("name"));
						reff.itemSoled(rs1.getString("name"));
						
						//deletion process
						
						Statement deletestatement = connect.createStatement();
						deletestatement.executeUpdate("DELETE FROM itemsinmarketplace WHERE name='"+rs1.getString("name")+"'");
						
						
						// items sold and bought process.
						Statement updatestatement = connect.createStatement();
						ResultSet rs2 = updatestatement.executeQuery("SELECT itemssold FROM RMI_Login WHERE username='"+reff.getTraderName()+"'");
						
						if(rs2.next()){
							if(rs2.getString("itemssold").equalsIgnoreCase("-")){
								updatestatement.executeUpdate("UPDATE RMI_Login SET itemssold="+1+" WHERE username='"+reff.getTraderName()+"'");
								
							}else{
								int count = Integer.parseInt(rs2.getString("itemssold"));
								count++;
								updatestatement.executeUpdate("UPDATE RMI_Login SET itemssold="+count+" WHERE username='"+reff.getTraderName()+"'");
							}
						}
						
						Statement updatestatement2 = connect.createStatement();
						ResultSet rs3 = updatestatement.executeQuery("SELECT itemsbought FROM RMI_Login WHERE username='"+buyerref.getTraderName()+"'");
						
						if(rs3.next()){
							if(rs3.getString("itemsbought").equalsIgnoreCase("-")){
								updatestatement.executeUpdate("UPDATE RMI_Login SET itemsbought ="+1+" WHERE username='"+buyerref.getTraderName()+"'");
								
							}else{
								int count = Integer.parseInt(rs3.getString("itemsbought"));
								count++;
								updatestatement.executeUpdate("UPDATE RMI_Login SET itemsbought ="+count+" WHERE username='"+buyerref.getTraderName()+"'");
							}
						}
						
					} catch (RejectedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					buyerref.cannotBuy();
				}
				
			}else{
				buyerref.callback("No such item in the market! ");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    @Override
    public void sellItem(CallBack ref,String name, String price) throws RemoteException {
        
        try {
			Statement statement = connect.createStatement();
			statement.execute("INSERT INTO itemsinmarketplace (name,price,referenceToSeller) VALUES('"+name+"','"+price+"','"+ref.getTraderName()+"')");
			System.out.println("item stored into database!");
			objects.put(ref.getTraderName(), ref);
			int mp = Integer.parseInt(price);
			checkIfMatch(name,mp,ref);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    @Override
    public void checkIfMatch(String n,int mp,CallBack sellerRef) throws RemoteException {
 
			try{
				Statement statement2 = connect.createStatement();
				ResultSet rs = statement2.executeQuery("SELECT name,price,buyerName FROM wishlist WHERE name='"+n+"'");
			if(rs.next()){
				String na = rs.getString("name");
				int wp = Integer.parseInt(rs.getString("price"));
				String referenceToBuyer = rs.getString("buyerName");
				CallBack reff = objects.get(referenceToBuyer);
				
				if(na.equalsIgnoreCase(n) && mp < wp){
					reff.callback("There is now a "+na+" in the marketplace!");
				}
				
			}else{
				System.out.println("NOP!");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
    }
    
    @Override
    public void wishForItem(CallBack traderef,String wishedname, String price, String wisher) throws RemoteException {
        
      	 try {
      		
  			Statement statement = connect.createStatement();
  			statement.execute("INSERT INTO wishlist (name,price,buyerName) VALUES('"+wishedname+"','"+price+"','"+traderef.getTraderName()+"')");
  			System.out.println("wish stored into database!");
  			
  			objects.put(traderef.getTraderName(), traderef);
  			Statement statement2 = connect.createStatement();
  			ResultSet rs = statement2.executeQuery("SELECT name,price,referenceToSeller FROM itemsinmarketplace WHERE name='"+wishedname+"'");
  			
  			if(rs.next()){
  				String n = rs.getString("name");
  				int p = Integer.parseInt(rs.getString("price"));
  				System.out.println(n);
  				int pr= Integer.parseInt(price);
  				System.out.println(pr);
  			
  				
  				if(n.equalsIgnoreCase(wishedname) && p < pr){
  					traderef.callback("There is now a "+n+" in the marketplace!");
  				}
  				
  			}else{
  				System.out.println("NOP!");
  			}
  			
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
        
    }
       @Override
    public void showItems(CallBack ref) throws RemoteException {

    	   try {
    		   
        	   connect.close();
        	   connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/MarketPlace","root","root");
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery("SELECT name,price FROM itemsinmarketplace");
			
			ItemsInMarketPlace.clear();
			while(rs.next()){
				ItemsInMarketPlace.put(rs.getString("name"), rs.getString("price"));
			}
			ref.viewItems(ItemsInMarketPlace);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   
    }
    
    @Override
    public void register(CallBack ref,String username,String password) throws RemoteException {
        try {
			Statement statement = connect.createStatement();
			ResultSet rs=statement.executeQuery("Select username From RMI_Login where username='"+username+"'");
			
			if(rs.next()){
				ref.register("Username Already exists! Please try again.");
			}else{
				if(password.length() >= 8){
					connect.createStatement();
					statement.execute("INSERT INTO RMI_Login (username,password,itemssold,itemsbought) VALUES('"+username+"','"+password+"','-','-')");
					System.out.println("inserted/ registered");
					
					// add object 
					objects.put(username, ref);
				}else{
					ref.register("password does not fullfill the right length, try again.");
				}
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void unregister(CallBack ref) throws RemoteException{  
        
    	try {
			Statement statement = connect.createStatement();
			statement.executeUpdate("DELETE FROM RMI_Login WHERE username='"+ref.getTraderName()+"'");
			statement.executeUpdate("DELETE FROM itemsinmarketplace WHERE name='"+ref.getTraderName()+"'");
			statement.executeUpdate("DELETE FROM wishlist WHERE username='"+ref.getTraderName()+"'");
			ref.unregister();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    

    @Override
    public void welcome() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public void login(CallBack ref,String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			Statement statement = connect.createStatement();
			ResultSet rs=statement.executeQuery("Select username,password From RMI_Login where username='"+username+"' AND password ='"+password+"'");
			
			if(rs.next()){
				String user = rs.getString("username");
				String pass = rs.getString("password");
				if(user.equalsIgnoreCase(username) && pass.equalsIgnoreCase(password)){
					ref.welcome("Welcome to the MarketPlace!");
					objects.put(username, ref);
				}else{
					ref.logIn("The login credentials are not correct!");
				}
				
			}else{
				ref.logIn("The Login credentials where Wrong!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void showMyAccountStatus(CallBack ref) throws RemoteException {
		// TODO Auto-generated method stub
		Map<String,String> mylist = new HashMap<>();
		
		try {
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery("SELECT itemssold,itemsbought FROM RMI_Login WHERE username='"+ref.getTraderName()+"'");
				rs.next();
				String itemssold = rs.getString("itemssold");
				String itemsbought = rs.getString("itemsbought");
				mylist.put(itemssold,itemsbought);
				
				ref.showMyAccountStatus(mylist);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}
    
}