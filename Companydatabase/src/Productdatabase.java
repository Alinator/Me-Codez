import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

/**
 * Bismillahirahmanirrahim
 * @author alinazar
 *	uses the same frame as the class CostumerDatabase, but using inheritance and Polymorphism, this class manipulates and 
 *	changes variable and methods accordingly in the costumerDatabase using polymorphism.
 *
 */
public class Productdatabase extends CostumerDatabase{
	
	//class variables
	JButton searchProductButton=null;
	JButton updateButton=null;
	JButton registerProduct=null;
	Database dbobj=null;
	JFrame productreg=null;
	//--------
	
	// Objects that are used in generateRows method
	final JTextField Field=new JTextField(50);
	final JTextField Field2=new JTextField(50);
	final JTextField Field3=new JTextField(50);
	final JTextField Field4=new JTextField(50);
	final JTextField Field5=new JTextField(50);
	final JTextField Field6=new JTextField(50);
	//-------
	
	public Productdatabase(){
		
	this.setTitle("Currently logged in user: "+Main.getCurrentAdmin()+"    Product Database");
	this.header.setText("Product Database");
	this.searchLabel.setText("Search for a product by entering its UPC");
	
	this.searchField.setText("Enter a correct universal product code(UPC)");
	this.searchButton.setVisible(false);
	this.searchButton=null;
	
	
	this.updateDatabaseButton.setVisible(false);
	this.updateDatabaseButton=null;
	
	//the button connected to the updateproductdatabase function in the database class
	updateButton= new JButton("Update Database");
	updateButton.setBounds(500,450,150,20);
	updateButton.setEnabled(false);
	updateButton.setVisible(true);
	lpane.add(updateButton);
	
	// search button
	searchProductButton= new JButton("Search Product");
	searchProductButton.setBounds(500,135,150,20);
	searchProductButton.setVisible(true);
	this.lpane.add(searchProductButton);
	
	searchProductButton.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String upc= searchField.getText();
			dbobj= new Database();
			if(dbobj.searchProduct(upc).equalsIgnoreCase("successfull")){
				generateRows();
				updateButton.setEnabled(true);
			}
		}
	});
	
	updateButton.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String upc= Field.getText();
			String product= Field2.getText();
			String manu=Field3.getText();
			String model=Field4.getText();
			String price=Field5.getText();
			String units=Field6.getText();
			
			dbobj.updateProductDatabase(upc,product,manu,model,price,units);
		}
		
	});
	
	this.addNewCostumerButton.setVisible(false);
	this.addNewCostumerButton=null;
	
	
	//button to enable new product registration
	registerProduct= new JButton("Register new Product");
	registerProduct.setBounds(500,500,150,20);
	registerProduct.setVisible(true);
	this.lpane.add(registerProduct);
	
	
	
	registerProduct.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			productreg= new JFrame();
			
			JLayeredPane pane2= new JLayeredPane();
			Container conn2 = productreg.getContentPane();
			
			productreg.setTitle("Register a new product");
			productreg.setBounds(700,0,400,400);
			
			JLabel registrationLabel= new JLabel("New Registration");
			registrationLabel.setFont(new Font("Birth of a hero",10,20));
			registrationLabel.setBounds(100,-10,200,100);
			registrationLabel.setVisible(true);
			
			pane2.add(registrationLabel,1);
			
			
			//textFields and corresponding labels to them, A form.
			
			JLabel UPCLabel= new JLabel("UPC:");
			UPCLabel.setBounds(30,50,200,100);
			UPCLabel.setVisible(true);
			pane2.add(UPCLabel,0);
			
			final JTextField UPCField = new JTextField(50);
			UPCField.setBounds(120,90,200,20);	
			UPCField.setVisible(true);
			pane2.add(UPCField,0);
			
			JLabel productLabel= new JLabel("Product:");
			productLabel.setBounds(30,80,200,100);
			productLabel.setVisible(true);
			pane2.add(productLabel,0);
			
			final JTextField productField = new JTextField(50);
			productField.setBounds(120,120,200,20);	
			productField.setVisible(true);
			pane2.add(productField,0);
			
			JLabel mfgrLabel= new JLabel("Manufacturer:");
			mfgrLabel.setBounds(30,110,200,100);
			mfgrLabel.setVisible(true);
			pane2.add(mfgrLabel,0);
			
			final JTextField mfgrField = new JTextField(50);
			mfgrField.setBounds(120,150,200,20);	
			mfgrField.setVisible(true);
			pane2.add(mfgrField,0);
			
			
			JLabel modelLabel= new JLabel("Modelnr:");
			modelLabel.setBounds(30,140,200,100);
			modelLabel.setVisible(true);
			pane2.add(modelLabel,0);
			
			final JTextField modelField = new JTextField(50);
			modelField.setBounds(120,180,200,20);	
			modelField.setVisible(true);
			pane2.add(modelField,0);
			
			JLabel priceLabel= new JLabel("Price:");
			priceLabel.setBounds(30,170,200,100);
			priceLabel.setVisible(true);
			pane2.add(priceLabel,0);
			
			final JTextField priceField = new JTextField(50);
			priceField.setBounds(120,210,200,20);	
			priceField.setVisible(true);
			pane2.add(priceField,0);
			
			JLabel unitsLabel= new JLabel("Units:");
			unitsLabel.setBounds(30,200,200,100);
			unitsLabel.setVisible(true);
			pane2.add(unitsLabel,0);
			
			final JTextField unitsField = new JTextField(50);
			unitsField.setBounds(120,240,200,20);	
			unitsField.setVisible(true);
			pane2.add(unitsField,0);
			
			final JButton registerButton= new JButton("Submit");
			registerButton.setBounds(150,280,100,20);
			registerButton.setVisible(true);
			pane2.add(registerButton,0);
			
			
			registerButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				Database db= new Database();
				db.registerNewProduct(UPCField.getText(),productField.getText(),mfgrField.getText(),modelField.getText(),priceField.getText(),unitsField.getText());
				productreg.setVisible(false);
				productreg=null;
				}
				
			}); 
			
			productreg.setVisible(true);
			conn2.add(pane2);
		}
		
	});
	
	}
	/**
	 * Note for myself: First you created an object of the database class, dbobj, and then in the method generateRows, you 
	 * created a new object in order to take get variabels created, like getUPC(). However when you created the
	 * new obj of the database class, the variables where not initiated in that obj in the memory, therefore they were null.
	 * so we need to use the obj first created, with which you called the method searchProduct(), and use the same obj to 
	 * then take the variables that were initiated in the searchProduct() method.
	 */
	public void generateRows(){
	
		int heightpos=200;
		

				//Field 1 and label 1 			
				JLabel label= new JLabel("UPC:");
				this.lpane.remove(label);
				this.lpane.remove(Field);
				label.setBounds(100,heightpos,300,20);
				Field.setText(dbobj.getUPC());
				System.out.println(dbobj.getUPC());
				Field.setBounds(200,200,300,20);
				
				this.lpane.add(label);
				label.setVisible(true);
				this.lpane.add(Field,0);
				
				//Field 2 and label 2 			
				JLabel label2= new JLabel("Product:");
				this.lpane.remove(label2);
				this.lpane.remove(Field2);
				label2.setBounds(100,230,300,20);
				Field2.setText(dbobj.getProductname());
				Field2.setBounds(200,230,300,20);
				
				this.lpane.add(label2);
				label2.setVisible(true);
				this.lpane.add(Field2,0);
			
				
				//Field 3 and label 3 			
				JLabel label3= new JLabel("Manufacturer:");
				this.lpane.remove(label3);
				this.lpane.remove(Field3);
				label3.setBounds(100,260,300,20);
				Field3.setText(dbobj.getManufacturer());
				Field3.setBounds(200,260,300,20);
				
				this.lpane.add(label3);
				label3.setVisible(true);
				this.lpane.add(Field3,0);
				
				
				//Field 4 and label 4 			
				JLabel label4= new JLabel("Model:");
				this.lpane.remove(label4);
				this.lpane.remove(Field4);
				label4.setBounds(100,290,300,20);
				Field4.setText(dbobj.getModel());
				Field4.setBounds(200,290,300,20);
				
				this.lpane.add(label4);
				label4.setVisible(true);
				this.lpane.add(Field4,0);
				

				//Field 5 and label 5 			
				JLabel label5= new JLabel("Price:");
				this.lpane.remove(label5);
				this.lpane.remove(Field5);
				label5.setBounds(100,320,300,20);
				Field5.setText(dbobj.getPrice());
				Field5.setBounds(200,320,300,20);
				
				this.lpane.add(label5);
				label5.setVisible(true);
				this.lpane.add(Field5,0);
				
				
				//Field 6 and label 6 			
				JLabel label6= new JLabel("Units in Stock:");
				this.lpane.remove(label6);
				this.lpane.remove(Field6);
				label6.setBounds(100,350,300,20);
				Field6.setText(dbobj.getUnitsinstock());
				Field6.setBounds(200,350,300,20);
				
				this.lpane.add(label6);
				label6.setVisible(true);
				this.lpane.add(Field6,0);
				
		}
	
	
	}

