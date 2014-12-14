import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 * 
 * 
 * bismillah ar rahman ar rahim
 *
 */
public class Main {

	/**
	 *  Authentication variables
	 */
	public static String username=null;
	public static String password=null;
	public static Database dbobject;
	public static String databaseadmin=null;
	
	public static void main(String [] args){

		username=JOptionPane.showInputDialog("Enter Username");
		password=JOptionPane.showInputDialog(null,"Enter password");	
		
		databaseadmin=username;
		dbobject=new Database();
		
		/**
		 *  this will grant the access to the databases accordingly to what rights a staff has been given
		 */
		if(dbobject.checkDatabaseStaff(username, password,"").equalsIgnoreCase("costumerdatabase")){
			new CostumerDatabase();
		}else if(dbobject.checkDatabaseStaff(username, password,"").equalsIgnoreCase("productdatabase")){
			new Productdatabase();
		}else if(dbobject.checkDatabaseStaff(username, password,"").equalsIgnoreCase("supplierdatabase")){
			new SupplierDatabase();
		}else if(dbobject.checkDatabaseStaff(username, password,"").equalsIgnoreCase("Employeedatabase")){
			new EmployeeDatabase();
		}else if(dbobject.checkDatabaseStaff(username, password,"").equalsIgnoreCase("Chief")){
				new Controller();
		}else{
			JOptionPane.showMessageDialog(null, "There is no such staff/employee registered in the database. Please register and then try again");
		}
		/**
		 * 
		 */
		
	}
	
	public static String getCurrentAdmin(){
		return databaseadmin;
	}
}
