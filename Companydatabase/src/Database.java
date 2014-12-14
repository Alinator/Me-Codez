import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


import com.mysql.jdbc.Connection;


/**
 * bismillah ar rahman ar rahim
 * 
 * 
 * A class specifically designed for the database system. Includes methods to manipulate the different databases.
 *	will work as an engine class that communicates with the backend SQL server and manipulates its including databases.
 *
 */
public class Database {

	private String DatabaseUsername="root";
	private String DatabasePassword= "muhammad";
	private String DatabaseName= "CostumerDatabase";
	private String staffdatabase="databaseStaff";

	public String Number=null;
	public String CostumerInfo=null;
	public Connection conn= null;

	// variables for the new Costumer registration
	private String personalnumber=null;
	private String Namn=null;
	private String Fšretag= null;
	private String Telefon=null;
	private String Adress= null;
	private String postnumber= null;
	private String Ort= null;
	private String Land=null;
	private String noteringar= null;
	//---------------------------------------------
	
	//variables for the Costumer search method
	String psn=null;
	String n=null;
	String f= null;
	String tel=null;
	String adress=null;
	String pnr=null;
	String o=null;
	String land=null;
	String Noteringar=null;
	String kundnr=null;
	//----------------------------------------
	
	/**
	 * variables for product search algorithm
	 */
	String UPC=null;
	String productname=null;
	String manufacturer=null;
	String model=null;
	String price=null;
	String unitsinstock=null;
	int rownumber=0;
	//----------------------------------------
	/**
	 * variables for supplierSearch method
	 */
	String registrationnr=null;
	String incometaxnr=null;
	String website=null;
	String email=null;
	String telnr=null;
	String Faxnr=null;
	String tollfreenr=null;
	String postalAdress=null;
	String postalCode=null;
	String contactPerson=null;
	String products=null;
	//----------------------------------------

	/**
	 * variables for employeesearch method
	 */
	String user= null;
	String pass= null;
	String rights=null;
	String emphistory=null;
	//-----------------------------------------

	public Database(){}


	public Database(String pn,String n,String f, String tel, String adr,String pnum, String ort,String l,String not){

		this.personalnumber=pn ;
		this.Namn=n;
		this.Fšretag=f;
		this.Telefon=tel;
		this.Adress=adr;
		this.postnumber=pnum;
		this.Ort=ort;
		this.Land=l;
		this.noteringar=not;

		newCostumerRegistration(personalnumber,Namn,Fšretag,Telefon,Adress,postnumber,Ort,Land,noteringar);
	}
	/**
	 * Checks the database for database administration info.
	 * @param username
	 * @param password
	 * @return
	 */
	public String checkDatabaseStaff(String username,String password, String empdetails){

		connectToDatabase(this.staffdatabase);	    

		String catchdatabase="Select * from staff";
		Statement stmt= null;
		ResultSet rs=null;
		try{
			stmt= conn.createStatement();
			rs = stmt.executeQuery(catchdatabase);
			rs= stmt.getResultSet();
			
			if(!username.equalsIgnoreCase("") && !password.equalsIgnoreCase("") && empdetails.equalsIgnoreCase("")){
				while(rs.next()){
					if(rs.getString(1).equalsIgnoreCase(username) && rs.getString(2).equalsIgnoreCase(password)){
						System.out.println(rs.getString(3));
						String staffRole=rs.getString(3);
						closeConnection();
						return staffRole;

					}else{
						System.out.println("username or password not in database");
					}
				}	
			}else if(username.equalsIgnoreCase("") && password.equalsIgnoreCase("") && !empdetails.equalsIgnoreCase("")){


				while(rs.next()){
					user=rs.getString(1);

					if(user.equalsIgnoreCase(empdetails)){
						System.out.println(getRownumber());
						System.out.println(rs.getRow());
						setRownumber(rs.getRow());
						user=rs.getString(1);
						pass=rs.getString(2);
						rights=rs.getString(3);
						emphistory=rs.getString(4);

						System.out.println(user+" "+pass+" "+rights+" "+emphistory);
						return "sucessfull";
					}
				}
			}
		}catch(Exception ex){
			System.out.println(ex);
		}
		return "Fail";

	}

	/**
	 * this method updates the staffDatabase.
	 * @param string5 
	 * @param string4 
	 * @param string3 
	 * @param string2 
	 * @param string 
	 */
	public void updateStaffDatabase(String stringg, String stringg2, String stringg3, String stringg4){
		connectToDatabase("databaseStaff");

		Statement stmt;
		ResultSet st;
		try {
			stmt=conn.createStatement();      
			stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			st=stmt.executeQuery("Select * from staff");
			st.absolute(getRownumber());

			st.updateString("username",stringg);
			st.updateString("password",stringg2);
			st.updateString("staffRole",stringg3);
			st.updateString("employeeHistory",stringg4);
			System.out.println("i think its done :D");
			st.updateRow();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null,"the update was successfull");
	}
	
	public void registerNewEmployee(String string, String string2, String string3, String string4){
		connectToDatabase("databaseStaff");
		try {
			PreparedStatement st= conn.prepareStatement("INSERT INTO staff"+
					" VALUES ('"+string+"','"+string2+"','"+string3+"','"+string4+"')");
			st.executeUpdate();
			//				st.executeUpdate();
			JOptionPane.showMessageDialog(null, "The registration of "+string+" with the Universal password: "+string2+" was successfull!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			JOptionPane.showMessageDialog(null, "The registration was not successfull, please try again with other values");
		}

	}
/*
 * here ends methods for employee database-------------------------------------------------------------------------
 */
	/**
	 * 
	 * @param personalnumber2
	 * @param namn2
	 * @param fšretag2
	 * @param telefon2
	 * @param adress2
	 * @param postnumber2
	 * @param ort2
	 * @param land2
	 * @param noteringar2
	 * 
	 * Connected to the costumerdatabase.this methods make a new insertion into the database. 
	 */
	public void newCostumerRegistration(String personalnumber2, String namn2, String fšretag2, String telefon2, String adress2, String postnumber2, String ort2, String land2, String noteringar2){
		try{

			// will be used as a costumernumber
			int nummer= Namn.hashCode()*(10);
			String kundnr=null;
			String Nummer= Integer.toString(nummer);
			int Kundnr=0;
			if(Nummer.contains("-")){
				kundnr=Nummer.substring(1);
				Kundnr= Integer.parseInt(kundnr);
			}else{
				Kundnr=nummer;
			}


			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();

				conn = (Connection) DriverManager.getConnection("jdbc:mysql:///"+this.DatabaseName,this.DatabaseUsername,this.DatabasePassword);

				PreparedStatement st= conn.prepareStatement("INSERT INTO costumers"+
						" VALUES ('"+personalnumber2+"','"+namn2+"','"+fšretag2+"','"+telefon2+"','"+adress2+"','"+postnumber2+"','"+ort2+"','"+land2+"','"+noteringar2+"','"+Kundnr+"')");
				st.executeUpdate();
				//				st.executeUpdate();
				JOptionPane.showMessageDialog(null, "The registration of "+namn2+" with the personal number: "+personalnumber2+" was successfull.\n The costumers costumernumber is the following :"+Kundnr);
				closeConnection();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}catch(SQLException exc){
			System.out.println(exc.getLocalizedMessage());
			JOptionPane.showMessageDialog(null, "The registration was not successfull, please try again with other values");
		}	
	}


	public String searchCostumer(String Number) {
		// TODO Auto-generated method stub
		connectToDatabase("CostumerDatabase");
		// loop through the database and find a match with the inputted number
		String firstQuery="Select personligtnr,namn,foretag,telefon,adress,postnr,ort,land,noteringar,kundnr from costumers";
		Statement stmt= null;
		ResultSet rs=null;
		try{
			stmt= conn.createStatement();
			rs = stmt.executeQuery(firstQuery);
			rs= stmt.getResultSet();


			boolean continueFlag=false;
			while(rs.next()){

				psn= rs.getString(1);

				if(psn.equalsIgnoreCase(Number)){

					setRownumber(rs.getRow());
					psn= rs.getString(1);
					n= rs.getString(2);
					f= rs.getString(3);
					tel=rs.getString(4);
					adress=rs.getString(5);
					pnr= rs.getString(6);
					o= rs.getString(7);
					land= rs.getString(8);
					Noteringar= rs.getString(9);
					kundnr=rs.getString(10);
					closeConnection();
					return "successfull";

				}else if(!psn.equalsIgnoreCase(Number)){
					continueFlag=false;
				}	
			}

		}catch (SQLException exc){
			System.out.println(exc.getLocalizedMessage());
		}
		closeConnection();
		JOptionPane.showMessageDialog(null, "The inputted personalnumber has not been registered in the database and can therefore not be found");
		return "fail";

	}
	/**
	 * an updating method for updating a specified row in the costumer database
	 * using the same objects that was created to use the searchCostumer, we can access the variables
	 * that were initilized in that method, thus we can use the GetRownumber() to get the row number and 
	 * manipulate that row using this method.
	 */
	public void updateCostumerDatabaseRow(String one,String two, String three, String four, String five, String six, String seven, String eight, String nine,String ten){

		connectToDatabase("CostumerDatabase");

		Statement stmt;
		ResultSet st;
		try {
			stmt=conn.createStatement();      
			stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			st=stmt.executeQuery("Select * from costumers");
			st.absolute(getRownumber());

			st.updateString("personligtnr", one);
			st.updateString("namn", two);
			st.updateString("foretag", three);
			st.updateString("telefon", four);
			st.updateString("adress", five);
			st.updateString("postnr", six);
			st.updateString("ort",seven );
			st.updateString("land",eight );
			st.updateString("noteringar",nine );
			st.updateString("kundnr",ten);
			st.updateRow();

			System.out.println("i think its done :D");
			st.updateRow();
			closeConnection();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null,"the update was successfull");


	}
	/*
	 * Here ends methods for Costumer database-------------------------------------------------------------------------
	 */

	/**
	 * This method will search through the database : productDatabase, for a product. If product not in the database,
	 * it will return a message to the user that the product is not there. 
	 * 
	 * the argument passed in is the products UPC (universal product code), and the algorithm bases its search through the 
	 * product database on this UPC.
	 */
	public String searchProduct(String upc){
		connectToDatabase("productDatabase");

		String query="Select * from products";

		Statement stmt= null;
		ResultSet rs=null;

		try{

			stmt= conn.createStatement();
			rs = stmt.executeQuery(query);
			rs= stmt.getResultSet();

			while(rs.next()){

				UPC=rs.getString(1);
				if(UPC.equalsIgnoreCase(upc)){

					setRownumber(rs.getRow());
					setUPC(rs.getString(1));
					setProductname(rs.getString(2));
					setManufacturer(rs.getString(3));
					setModel(rs.getString(4));
					setPrice(rs.getString(5));
					setUnitsinstock(rs.getString(6));
					return "successfull";

				}
			}//end of while loop
		}catch(SQLException ex){
		}
		closeConnection();
		JOptionPane.showMessageDialog(null, "product was not found, please input a correct UPC code !");
		return "fail";
	}

	/**
	 * updates the product database
	 * the argument values passed in to the method will replace the column values in the database that we want.
	 * @param units 
	 * @param pricenr 
	 * @param modelnr 
	 * @param manu 
	 * @param product 
	 * @param upcnr 
	 */
	public void updateProductDatabase(String upcnr, String product, String manu, String modelnr, String pricenr, String units){
		connectToDatabase("productDatabase");

		Statement stmt;
		ResultSet st;
		try {
			stmt=conn.createStatement();      
			stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			st=stmt.executeQuery("Select * from products");

			st.absolute(getRownumber());

			st.updateString("UPC",upcnr);
			st.updateString("productName",product);
			st.updateString("mfgr",manu);
			st.updateString("model",modelnr);
			st.updateString("unitPrice",pricenr);
			st.updateString("unitsInStock",units);
			System.out.println("i think its done :D");
			st.updateRow();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null,"the update was successfull");

	}
	/**
	 * Appends a new product into the product database
	 * The parameters are the values from the form fields.
	 */
	public void registerNewProduct(String field1, String field2, String field3, String field4, String field5, String field6){
		connectToDatabase("productDatabase");
		try {
			PreparedStatement st= conn.prepareStatement("INSERT INTO products"+
					" VALUES ('"+field1+"','"+field2+"','"+field3+"','"+field4+"','"+field5+"','"+field6+"')");
			st.executeUpdate();
			//				st.executeUpdate();
			JOptionPane.showMessageDialog(null, "The registration of "+field2+" with the Universal product number: "+field1+" was successfull!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			JOptionPane.showMessageDialog(null, "The registration was not successfull, please try again with other values");
		}

	}
	/*
	 * here ends methods for product database-------------------------------------------------------------------------
	 */
	
	/**
	 * will return information regarding one supplier.
	 * parameters: regnumber: a given number on which the search will be based on.
	 */
	public String searchSupplier(String regnumber){
		connectToDatabase("SupplierDatabase");

		String query="Select * from supplier";

		Statement stmt= null;
		ResultSet rs=null;
		try{
			stmt= conn.createStatement();
			rs = stmt.executeQuery(query);
			rs= stmt.getResultSet();


			while(rs.next()){
				registrationnr=rs.getString(1);
				if(registrationnr.equalsIgnoreCase(regnumber)){

					setRownumber(rs.getRow());
					registrationnr=rs.getString(1);
					incometaxnr=rs.getString(2);
					website=rs.getString(3);
					email=rs.getString(4);
					telnr=rs.getString(5);
					Faxnr=rs.getString(6);
					tollfreenr=rs.getString(7);
					postalAdress=rs.getString(8);
					postalCode=rs.getString(9);
					contactPerson=rs.getString(10);
					products=rs.getString(11);

					System.out.println(registrationnr+" : "+incometaxnr);
					JOptionPane.showMessageDialog(null,"Search successfull!");

					return "Successfull";	
				}
			}
		}catch(SQLException ex){
		}
		closeConnection();
		JOptionPane.showMessageDialog(null, "Supplier was not found, please input a correct company registration number!");
		return "fail";

	}

	/**
	 * updates the SupplierDatabase on a specified row.
	 */
	public void updateSupplierDatabase(String reg,String tax,String web,String mail,String telenr,String fax,String toll,String padre,String pco,String conpers,String pro){
		connectToDatabase("supplierDatabase");

		Statement stmt;
		ResultSet st;
		try {
			stmt=conn.createStatement();      
			stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			st=stmt.executeQuery("Select * from supplier");
			if(getRownumber() > 0){
				st.absolute(getRownumber());
			}
			System.out.println(reg);
			st.updateString("registrationnr",reg);
			st.updateString("IncomeTaxRefnr",tax);
			st.updateString("website",web);
			st.updateString("EmailAdress",mail);
			st.updateString("Telnr",telenr);
			st.updateString("Faxnr",fax);
			st.updateString("TollFreenr",toll);
			st.updateString("postaladress",padre);
			st.updateString("postalCode",pco);
			st.updateString("contactperson",conpers);
			st.updateString("products",pro);
			System.out.println("i think its done :D");
			st.updateRow();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null,"the update was successfull");
	}
	
	
	/**
	 * this method, when called with arguments will assign a new supplier with its details into the database.
	 */
	public void registerNewSupplier(String text, String text2, String text3,
			String text4, String text5, String text6, String text7, String text8,
			String text9, String text10, String text11) {
		// TODO Auto-generated method stub
		connectToDatabase("SupplierDatabase");
		try {
			PreparedStatement st= conn.prepareStatement("INSERT INTO supplier"+
					" VALUES ('"+text+"','"+text2+"','"+text3+"','"+text4+"','"+text5+"','"+text6+"','"+text7+"','"+text8+"','"+text9+"','"+text10+"','"+text11+"')");
			st.executeUpdate();
			//				st.executeUpdate();
			JOptionPane.showMessageDialog(null, "The registration was successfull!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			closeConnection();
			JOptionPane.showMessageDialog(null, "The registration was not successfull, please try again with other values");
		}
	}
	/*
	 * here ends methods for supplier database-------------------------------------------------------------------------
	 */
	
	
	/**
	 * @param databasename
	 * a general method for connecting to a database that is specified by the programmer
	 */
	public void connectToDatabase(String databasename){

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = (Connection) DriverManager.getConnection("jdbc:mysql:///"+databasename,this.DatabaseUsername,this.DatabasePassword);

			if(!conn.isClosed())
				System.out.println("Successfully connected to " +"MySQL server using TCP/IP...");

		} catch(Exception e) {
			System.err.println("Exception: " + e.getLocalizedMessage());
		}
	}

	/**
	 * terminates the connection with the database
	 */
	public void closeConnection(){

		try {
			conn.close();
			System.out.println("database connection terminated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * methods for searchCostumer algorithm
	 * @return
	 */
	public String getUPC() {
		return UPC;
	}


	public String getProductname() {
		return productname;
	}


	public String getManufacturer() {
		return manufacturer;
	}


	public String getModel() {
		return model;
	}


	public String getPrice() {
		return price;
	}


	public String getUnitsinstock() {
		return unitsinstock;
	}


	public void setUPC(String uPC) {
		UPC = uPC;
	}


	public void setProductname(String productname) {
		this.productname = productname;
	}


	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public void setUnitsinstock(String unitsinstock) {
		this.unitsinstock = unitsinstock;
	}



	/**
	 * for productDatabase, once a search has been done, through this method you can get the ROW number from which the cursor 
	 * was positioned.
	 * @return
	 */
	private int getRownumber() {
		return rownumber;
	}

	/**
	 * 
	 * @param rownumber
	 * using this method we can set a row number, that is catch which row the cursor was pointing at and set this 
	 * to a variable that later can be used by the programmer.
	 * by giving the method a private access type/scope, nobody from outside is able to use this method
	 * 
	 */
	private void setRownumber(int rownumber) {
		this.rownumber = rownumber;
	}


}



