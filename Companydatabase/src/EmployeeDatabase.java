import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/** 
 *
 * This class is connected to the database: databaseStaff
 *
 */
public class EmployeeDatabase extends CostumerDatabase {

	JButton employeesearchbutton=null;
	JButton updaterbutton=null;
	JButton registerer=null;
	JButton submiterer=null;
	
	
	final JTextField Field=new JTextField(50);
	final JTextField Field2=new JTextField(50);
	final JTextField Field3=new JTextField(50);
	final JTextArea Field4=new JTextArea(50,50);

	
	public EmployeeDatabase(){
	super();
	
	this.setTitle("Currently logged in user: "+Main.getCurrentAdmin()+"    Employee Database");
	this.header.setText("Employee Database");
	this.searchLabel.setText("Search for an Employee: ");
	this.searchField.setText("Search by entering an employee name");
	
	this.searchButton.setVisible(false);
	this.searchButton=null;
	
	this.updateDatabaseButton.setVisible(false);
	this.updateDatabaseButton=null;
	
	this.addNewCostumerButton.setVisible(false);
	this.addNewCostumerButton=null;
	
	employeesearchbutton = new JButton("Search");
	employeesearchbutton.setBounds(500,135,100,20);
	employeesearchbutton.setVisible(true);
	this.lpane.add(employeesearchbutton);
	
	employeesearchbutton.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String details=searchField.getText();
			dbsObject2=new Database();
			dbsObject2.checkDatabaseStaff("","",details);
			generateResult("further");
			updaterbutton.setEnabled(true);
		}
	});
			//update database button
			updaterbutton= new JButton("Update Database");
			updaterbutton.setBounds(500,450,150,20);
			updaterbutton.setEnabled(false);
			
			updaterbutton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stud
					System.out.println(kundField.getText());
					System.out.println(Field.getText());
					System.out.println(Field2.getText());
					System.out.println(Field3.getText());
					System.out.println(Field4.getText());
					dbsObject2.updateStaffDatabase(Field.getText(), Field2.getText(), Field3.getText(), Field4.getText());
				}
			});
			this.lpane.add(updaterbutton);
	
			// register button
			registerer= new JButton("Register");
			registerer.setBounds(550, 500,100, 20);
			registerer.setVisible(true);
			this.lpane.add(registerer);
			
			registerer.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					Field.setText("");
					Field2.setText("");
					Field3.setText("");
					Field4.setText("");
					generateResult("");
					updaterbutton.setEnabled(false);
					registerer.setEnabled(false);
				}
				
			});
			
			
			submiterer= new JButton("submit");
			submiterer.setBounds(550, 550,100, 20);
			submiterer.setVisible(false);
			
			submiterer.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					registerer.setEnabled(true);
					dbsObject2=new Database();
					dbsObject2.registerNewEmployee(Field.getText(), Field2.getText(), Field3.getText(), Field4.getText());
				}
			});
			this.lpane.add(submiterer);
	}
	/**
	 *generates the results.
	 */
	public void generateResult(String further){
		Field.setBounds(200,160,300,20);
		Field.setVisible(true);

		JLabel Label= new JLabel("Name:");
		Label.setBounds(100,120,300,100);
		Label.setVisible(true);
		this.lpane.add(Label);
		this.lpane.add(Field,0);

		JLabel label2= new JLabel("Password:");
		this.lpane.remove(label2);
		this.lpane.remove(Field2);

		label2.setBounds(100,200,300,20);
		Field2.setBounds(200,200,300,20);

		this.lpane.add(label2);
		label2.setVisible(true);
		Field2.setVisible(true);
		this.lpane.add(Field2,0);

		//Field 2 and label 2 			
		JLabel label3= new JLabel("System Rights:");
		this.lpane.remove(label3);
		this.lpane.remove(Field3);
		label3.setBounds(100,230,300,20);
		Field3.setBounds(200,230,300,20);

		this.lpane.add(label3);
		label3.setVisible(true);
		Field3.setVisible(true);
		this.lpane.add(Field3,0);


		//Field 3 and label 3 			
		JLabel label4= new JLabel("History:");
		this.lpane.remove(label4);
		this.lpane.remove(Field4);
		label4.setBounds(100,260,300,20);
		Field4.setBounds(200,260,300,300);

		this.lpane.add(label4);
		label4.setVisible(true);
		Field4.setVisible(true);
		this.lpane.add(Field4,0);
		
		
		if(further.equalsIgnoreCase("further")){
			Field.setText(dbsObject2.user);
			Field2.setText(dbsObject2.pass);
			Field3.setText(dbsObject2.rights);
			Field4.setText(dbsObject2.emphistory);
		}else{
			this.submiterer.setVisible(true);
		}
	}
	
}
