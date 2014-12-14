import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



/**
 * bissmillahirahmanirahim
 * 
 * This class is connected to the database: SupplierDatabase
 */
public class SupplierDatabase extends CostumerDatabase{
	
	JButton supplierSearchButton=null;
	Database dbsobject=null;
	JTextField Field10= new JTextField();
	JButton updatingbutton=null;
	JButton adder=null;
	JButton submitter=null;
	
	public SupplierDatabase(){
		super();
		
		this.setTitle("Currently logged in user: "+Main.getCurrentAdmin()+" in Supplier Database");
		this.header.setText("Supplier Database");
		this.searchLabel.setText("Search for a supplier:");
		this.searchField.setText("Enter a correct company registration number");
		
		this.searchButton.setVisible(false);
		this.searchButton=null;
		
		this.updateDatabaseButton.setVisible(false);
		this.updateDatabaseButton= null;
		
		
		supplierSearchButton = new JButton("Search");
		supplierSearchButton.setBounds(500,135,100,20);
		supplierSearchButton.setVisible(true);
		
		this.supplierSearchButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String regnumber= searchField.getText();
				dbsObject2=new Database();
				if(dbsObject2.searchSupplier(regnumber).equalsIgnoreCase("successfull")){
					generateResultRows("further");
					updatingbutton.setEnabled(true);
					submitter.setVisible(false);
					adder.setEnabled(true);
				}
			}
		});
		this.lpane.add(supplierSearchButton);
		
		//update database button
		updatingbutton= new JButton("Update Database");
		updatingbutton.setBounds(500,450,150,20);
		updatingbutton.setEnabled(false);
		
		updatingbutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stud
				dbsObject2.updateSupplierDatabase(kundField.getText(), Field.getText(), Field2.getText(), Field3.getText(), Field4.getText(), Field5.getText(),Field6.getText(), Field7.getText(), Field8.getText(), Field10.getText(),Field9.getText());
			}
		});
		this.lpane.add(updatingbutton);
		
		
		this.addNewCostumerButton.setVisible(false);
		this.addNewCostumerButton=null;
		
		// add button
		adder= new JButton("Register");
		adder.setBounds(550, 500,100, 20);
		adder.setVisible(true);
		this.lpane.add(adder);
		
		adder.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				lpane.remove(kundField);
				kundField.setVisible(false);
				lpane.remove(Field);
				Field.setVisible(false);
				lpane.remove(Field2);
				Field2.setVisible(false);
				lpane.remove(Field3);
				Field3.setVisible(false);
				lpane.remove(Field4);
				Field4.setVisible(false);
				lpane.remove(Field5);
				Field5.setVisible(false);
				lpane.remove(Field6);
				Field6.setVisible(false);
				lpane.remove(Field7);
				Field7.setVisible(false);
				lpane.remove(Field8);
				Field8.setVisible(false);
				lpane.remove(Field9);
				Field9.setVisible(false);
				lpane.remove(Field10);
				Field10.setVisible(false);
				kundField.setText(null);
				Field.setText(null);
				Field2.setText(null);
				Field3.setText(null);
				Field4.setText(null);
				Field5.setText(null);
				Field6.setText(null);
				Field7.setText(null);
				Field8.setText(null);
				Field9.setText(null);
				Field10.setText(null);
				generateResultRows("notfurther");
				JOptionPane.showMessageDialog(null, "Fill in the empty fields and hit the submit button");
				updatingbutton.setEnabled(false);
				adder.setEnabled(false);
			}
		});
		
		submitter= new JButton("submit");
		submitter.setBounds(550, 550,100, 20);
		submitter.setVisible(false);
		
		submitter.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				adder.setEnabled(true);
				dbsObject2.registerNewSupplier(kundField.getText(), Field.getText(), Field2.getText(), Field3.getText(), Field4.getText(), Field5.getText(),Field6.getText(), Field7.getText(), Field8.getText(), Field10.getText(),Field9.getText());
			}
		});
		this.lpane.add(submitter);

		}
	
	public void generateResultRows(String further){
	
		
		kundField.setBounds(200,160,300,20);
		kundField.setVisible(true);

		JLabel kundLabel= new JLabel("Registrationnr#:");
		kundLabel.setBounds(100,120,300,100);
		kundLabel.setVisible(true);
		this.lpane.add(kundLabel);
		this.lpane.add(kundField,0);

		JLabel label= new JLabel("Income tax nr#:");
		this.lpane.remove(label);
		this.lpane.remove(Field);

		label.setBounds(100,200,300,20);
		Field.setBounds(200,200,300,20);

		this.lpane.add(label);
		label.setVisible(true);
		Field.setVisible(true);
		this.lpane.add(Field,0);

		//Field 2 and label 2 			
		JLabel label2= new JLabel("website:");
		this.lpane.remove(label2);
		this.lpane.remove(Field2);
		label2.setBounds(100,230,300,20);
		Field2.setBounds(200,230,300,20);

		this.lpane.add(label2);
		label2.setVisible(true);
		Field2.setVisible(true);
		this.lpane.add(Field2,0);


		//Field 3 and label 3 			
		JLabel label3= new JLabel("Email:");
		this.lpane.remove(label3);
		this.lpane.remove(Field3);
		label3.setBounds(100,260,300,20);
		Field3.setBounds(200,260,300,20);

		this.lpane.add(label3);
		label3.setVisible(true);
		Field3.setVisible(true);
		this.lpane.add(Field3,0);


		//Field 4 and label 4 			
		JLabel label4= new JLabel("Telephone:");
		this.lpane.remove(label4);
		this.lpane.remove(Field4);
		label4.setBounds(100,290,300,20);
		Field4.setBounds(200,290,300,20);

		this.lpane.add(label4);
		label4.setVisible(true);
		Field4.setVisible(true);
		this.lpane.add(Field4,0);


		//Field 5 and label 5 			
		JLabel label5= new JLabel("Faxnr:");
		this.lpane.remove(label5);
		this.lpane.remove(Field5);
		label5.setBounds(100,320,300,20);
		Field5.setBounds(200,320,300,20);

		this.lpane.add(label5);
		label5.setVisible(true);
		Field5.setVisible(true);
		this.lpane.add(Field5,0);


		//Field 6 and label 6 			
		JLabel label6= new JLabel("Toll free nr:");
		this.lpane.remove(label6);
		this.lpane.remove(Field6);
		label6.setBounds(100,350,300,20);
		Field6.setBounds(200,350,300,20);

		this.lpane.add(label6);
		label6.setVisible(true);
		Field6.setVisible(true);
		this.lpane.add(Field6,0);

		//Field 7 and label 7 			
		JLabel label7= new JLabel("Postal adress");
		this.lpane.remove(label7);
		this.lpane.remove(Field7);
		label7.setBounds(100,380,300,20);
		Field7.setBounds(200,380,300,20);

		this.lpane.add(label7);
		label7.setVisible(true);
		Field7.setVisible(true);
		this.lpane.add(Field7,0);

		//Field 8 and label 8 			
		JLabel label8= new JLabel("Postal Code:");
		this.lpane.remove(label8);
		this.lpane.remove(Field8);
		label8.setBounds(100,420,300,20);
		Field8.setBounds(200,420,300,20);

		this.lpane.add(label8);
		label8.setVisible(true);
		Field8.setVisible(true);
		this.lpane.add(Field8,0);

		
		//FIELD FOR CONTACT PERSON
		JLabel label10= new JLabel("Contact person:");
		this.lpane.remove(label10);
		this.lpane.remove(Field10);
		label10.setBounds(100,460,300,20);
		Field10.setBounds(200,460,300,20);

		this.lpane.add(label10);
		label10.setVisible(true);
		Field10.setVisible(true);
		this.lpane.add(Field10,0);
		
		
		//areaField 9 and label 9 			
		JLabel label9= new JLabel("Products:");
		this.lpane.remove(label9);
		this.lpane.remove(Field9);
		label9.setBounds(100,500,300,20);
		Field9.setBounds(200,500,290,100);

		this.lpane.add(label9);
		label9.setVisible(true);
		Field9.setVisible(true);
		this.lpane.add(Field9,0);	
		
		if(further.equalsIgnoreCase("further")){
			
		kundField.setText(dbsObject2.registrationnr);
		Field.setText(dbsObject2.incometaxnr);
		Field2.setText(dbsObject2.website);
		Field3.setText(dbsObject2.email);
		Field4.setText(dbsObject2.telnr);
		Field5.setText(dbsObject2.Faxnr);
		Field6.setText(dbsObject2.tollfreenr);
		Field7.setText(dbsObject2.postalAdress);
		Field8.setText(dbsObject2.postalCode);
		Field9.setText(dbsObject2.products);
		Field10.setText(dbsObject2.contactPerson);
		
		}else{
			submitter.setVisible(true);
		}
	}
}
