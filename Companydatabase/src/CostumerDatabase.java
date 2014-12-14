import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Graphics;
/**
 * 
 * bismillah ar rahman ar rahim
 *
 */
public class CostumerDatabase extends JFrame{

	//class variabels and objects------
	JButton addNewCostumerButton=null;
	JLabel header=new JLabel("Costumer Database");
	JTextField searchField = null;
	JButton searchButton=null;
	JButton updateDatabaseButton=null;
	JLabel developedByLabel=null;
	static String costumerInfo=null;
	static JLabel printcostumerInformation=null;
	static JFrame costumerDetailsFrame=null;
	public static JLayeredPane lpane=null;
	public static Container conn=null;
	public JLabel searchLabel=null;
	public static Database dbsObject2=null;
	//----------------------------------

	// objects used in the generateResultRows() method
	final JTextField Field=new JTextField(50);
	final JTextField Field2=new JTextField(50);
	final JTextField Field3=new JTextField(50);
	final JTextField Field4=new JTextField(50);
	final JTextField Field5=new JTextField(50);
	final JTextField Field6=new JTextField(50);
	final JTextField Field7=new JTextField(50);
	final JTextField Field8=new JTextField(50);
	final JTextArea Field9=new JTextArea(50,50);
	final JTextField kundField=new JTextField(50);
	//--------

	public CostumerDatabase(){
		super("Currently logged in user: "+Main.getCurrentAdmin()+"    Costumer Database");
		lpane= new JLayeredPane();	
		conn= super.getContentPane();
//		setSize(800,700);
		
		// a little animation

		//header
		getHeader();
		header.setFont(new Font("Birth Of a Hero",20,30));
		header.setBounds(180,20,480,50);
		header.setVisible(true);
		lpane.add(header);

		// add button
		addNewCostumerButton= new JButton("Register");
		addNewCostumerButton.setBounds(550, 500,100, 20);
		addNewCostumerButton.setVisible(true);

		// Label : Search for costumer
		searchLabel= new JLabel("Search for a costumer:");
		searchLabel.setBounds(170,70,400,30);
		lpane.add(searchLabel);

		// search bar for searching some costumer details.

		searchField= new JTextField(50);
		searchField.setBounds(170, 100, 400, 30);
		searchField.setText("Enter costumers personalnumber");
		searchField.setVisible(true);
		lpane.add(searchField);

		//update database button
		updateDatabaseButton= new JButton("Update Database");
		updateDatabaseButton.setBounds(500,450,150,20);
		updateDatabaseButton.setEnabled(false);
		updateDatabaseButton.setVisible(true);
		this.lpane.add(updateDatabaseButton);

		// search button
		searchButton= new JButton("Search");
		searchButton.setBounds(500,135,100,20);
		searchButton.setVisible(true);


		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				final String inputtedPersonalnr= searchField.getText();
				dbsObject2= new Database();
				if(dbsObject2.searchCostumer(inputtedPersonalnr).equalsIgnoreCase("successfull")){
					generateResultRows();
					updateDatabaseButton.setEnabled(true);
				}
			}
		});

		lpane.add(searchButton);

		// developed by label
		developedByLabel= new JLabel("Welcome "+Main.getCurrentAdmin());
		developedByLabel.setFont(new Font("Arial",20,15));
		developedByLabel.setBounds(300, -10, 300, 50);
		developedByLabel.setVisible(true);
		lpane.add(developedByLabel);



		addNewCostumerButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				costumerDetailsFrame= new JFrame();

				JLayeredPane pane2= new JLayeredPane();
				Container conn2 = costumerDetailsFrame.getContentPane();


				costumerDetailsFrame.setTitle("Register a new costumer");
				costumerDetailsFrame.setSize(450, 600);
				costumerDetailsFrame.setBounds(700,0,400,600);


				JLabel registrationLabel= new JLabel("New Registration");
				registrationLabel.setFont(new Font("Birth of a hero",10,20));
				registrationLabel.setBounds(100,-10,200,100);
				registrationLabel.setVisible(true);

				pane2.add(registrationLabel,1);


				//textFields and corresponding labels to them, A form.

				JLabel nameLabel= new JLabel("Personligt#:");
				nameLabel.setBounds(30,50,200,100);
				nameLabel.setVisible(true);
				pane2.add(nameLabel);


				final JTextField nameField = new JTextField(50);
				nameField.setBounds(100,90,200,20);	
				nameField.setVisible(true);

				pane2.add(nameField);


				JLabel personalNumberLabel= new JLabel("Namn:");
				personalNumberLabel.setBounds(30,80,200,100);
				personalNumberLabel.setVisible(true);
				pane2.add(personalNumberLabel);

				final JTextField personalNumberField= new JTextField(50);
				personalNumberField.setBounds(100,120,200,20);	
				personalNumberField.setVisible(true);


				pane2.add(personalNumberField);

				JLabel companyLabel= new JLabel("Fšretag:");
				companyLabel.setBounds(30,110,200,100);
				companyLabel.setVisible(true);
				pane2.add(companyLabel);

				final JTextField companyField= new JTextField(50);
				companyField.setBounds(100,150,200,20);	
				companyField.setVisible(true);

				pane2.add(companyField);


				JLabel phoneLabel= new JLabel("Telefon:");
				phoneLabel.setBounds(30,140,200,100);
				phoneLabel.setVisible(true);
				pane2.add(phoneLabel);

				final JTextField phoneField= new JTextField(50);
				phoneField.setBounds(100,180,200,20);	
				phoneField.setVisible(true);

				pane2.add(phoneField);

				JLabel adressLabel= new JLabel("Adress:");
				adressLabel.setBounds(30,170,200,100);
				adressLabel.setVisible(true);
				pane2.add(adressLabel);

				final JTextField adressField= new JTextField(50);
				adressField.setBounds(100,210,200,20);	
				adressField.setVisible(true);
				final String adress= adressField.getText();
				pane2.add(adressField);

				JLabel postnrLabel= new JLabel("post#:");
				postnrLabel.setBounds(30,200,200,100);
				postnrLabel.setVisible(true);
				pane2.add(postnrLabel);

				final JTextField postnrField= new JTextField(50);
				postnrField.setBounds(100,240,200,20);	
				postnrField.setVisible(true);

				pane2.add(postnrField);

				JLabel ortLabel= new JLabel("Ort:");
				ortLabel.setBounds(30,230,200,100);
				ortLabel.setVisible(true);
				pane2.add(ortLabel);

				final JTextField ortField= new JTextField(50);
				ortField.setBounds(100,270,200,20);	
				ortField.setVisible(true);

				pane2.add(ortField);

				JLabel countryLabel= new JLabel("Land:");
				countryLabel.setBounds(30,260,200,100);
				countryLabel.setVisible(true);
				pane2.add(countryLabel);

				final JTextField countryField= new JTextField(50);
				countryField.setBounds(100,300,200,20);	
				countryField.setVisible(true);

				pane2.add(countryField);

				JLabel notificationLabel= new JLabel("Noteringar:");
				notificationLabel.setBounds(30,290,200,100);
				notificationLabel.setVisible(true);
				pane2.add(notificationLabel);

				final JTextArea notificationArea= new JTextArea(50,50);
				notificationArea.setBounds(100,330,200,200);
				notificationArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
				notificationArea.setVisible(true);
				pane2.add(notificationArea);

				JButton registerButton= new JButton("Submit");
				registerButton.setBounds(150,535,100,20);
				registerButton.setVisible(true);

				costumerDetailsFrame.setVisible(true);
				registerButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub

						//values past on to database class constructor from the registration form.
						// Should include form validation


						String personalnr= nameField.getText();
						String name= personalNumberField.getText();
						String company= companyField.getText();
						String telefon= phoneField.getText();
						String adress= adressField.getText();
						String postnr= postnrField.getText();
						String ort= ortField.getText();
						String land= countryField.getText();
						String notification= notificationArea.getText();

						// send for form validation before registration
						if(formValidation(personalnr,name,company,telefon,adress,postnr,ort,land,notification)==false){

							System.out.println("The costumer will be re submitting the form with corrections made.");

						}else{

							Database dbaseobj= new Database();
							dbaseobj.connectToDatabase("CostumerDatabase");
							new Database(personalnr,name,company,telefon,adress, postnr,ort,land,notification);

							costumerDetailsFrame.setVisible(false);
							costumerDetailsFrame= null;
						}
					}
				});

				pane2.add(registerButton);

				conn2.add(pane2);
			}
		});

		lpane.add(addNewCostumerButton);

		updateDatabaseButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dbsObject2.updateCostumerDatabaseRow(Field.getText(),Field2.getText(),Field3.getText(),Field4.getText(),Field5.getText(),Field6.getText(),Field7.getText(),Field8.getText(),Field9.getText(),kundField.getText());
			}

		});
		
		// this will create a simple opening animation
		int x;
		int y;
		for(x=0; x < 800; x+=7){
			setSize(x,0);
			setVisible(true);
		}
		for(y=0; y < 700; y+=7){
			setSize(x,y);
			setVisible(true);
		}
		conn.add(lpane);

	}
	/**
	 * 
	 * @param psn
	 * @param n
	 * @param comp
	 * @param tel
	 * @param adr
	 * @param pnr
	 * @param country
	 * @param notice
	 * @return
	 * 
	 * this method will validate the form so that the user has inputted the correct information before the actual
	 * registration into the database
	 */

	public boolean formValidation (String psn, String n,String comp,String tel,String adr,String pnr,String o,String country,String notice){


		if(psn.equalsIgnoreCase("") || psn.length() != 10 || psn.isEmpty()){

			JOptionPane.showMessageDialog(null, "Personal number field empty or invalid personalnumber inputted. please try again");
			return false;

		}else if(n.equalsIgnoreCase("") || n.length() == 40 || n.isEmpty()){

			JOptionPane.showMessageDialog(null, "Name field is empty!");
			return false;
		}else if(comp.equalsIgnoreCase("") || comp.isEmpty()){

			JOptionPane.showMessageDialog(null, "fšretags field is empty!");
			return false;

		}else if (tel.isEmpty() || tel.length()== 40){

			JOptionPane.showMessageDialog(null, "Telephone field is either empty or number sequence is to long,please try again");
			return false;
		}else if(adr.equalsIgnoreCase("") || adr.isEmpty()){

			JOptionPane.showMessageDialog(null, "Adress field is empty, please try again");
			return false;
		}else if(pnr.isEmpty() || pnr.length()!= 5){

			JOptionPane.showMessageDialog(null, "Invalid postnumber, enter the correct postnumber");
			return false;
		}else if(o.equalsIgnoreCase("") || o.isEmpty()){

			JOptionPane.showMessageDialog(null, "Ort field is empty!");
			return false;

		}else if(country.equalsIgnoreCase("") || country.isEmpty()){

			JOptionPane.showMessageDialog(null, "Fill in the land field properly.");
			return false;
		}else if(notice.equalsIgnoreCase("") || notice.isEmpty()){

			JOptionPane.showMessageDialog(null, "Notification field is empty. OBS: you have to enter some notification to the new costumer,\n" +
					"If no notification is needed, then please write the following: 'No notification added to costumer'");
			return false;
		}



		return true;

	}
	public void generateResultRows(){

		int heightpos=200;
		
		//Field 1 and label 1 
		kundField.setBounds(200,160,300,20);
		kundField.setText(dbsObject2.kundnr);
		kundField.setVisible(true);

		JLabel kundLabel= new JLabel("Costumer#:");
		kundLabel.setBounds(100,120,300,100);
		kundLabel.setVisible(true);
		this.lpane.add(kundLabel);
		this.lpane.add(kundField,0);

		JLabel label= new JLabel("personligt#:");
		this.lpane.remove(label);
		this.lpane.remove(Field);

		label.setBounds(100,heightpos,300,20);
		Field.setText(dbsObject2.psn);
		Field.setBounds(200,200,300,20);

		this.lpane.add(label);
		label.setVisible(true);
		this.lpane.add(Field,0);

		//Field 2 and label 2 			
		JLabel label2= new JLabel("Namn:");
		this.lpane.remove(label2);
		this.lpane.remove(Field2);
		label2.setBounds(100,230,300,20);
		Field2.setText(dbsObject2.n);
		Field2.setBounds(200,230,300,20);

		this.lpane.add(label2);
		label2.setVisible(true);
		this.lpane.add(Field2,0);


		//Field 3 and label 3 			
		JLabel label3= new JLabel("Fšretag:");
		this.lpane.remove(label3);
		this.lpane.remove(Field3);
		label3.setBounds(100,260,300,20);
		Field3.setText(dbsObject2.f);
		Field3.setBounds(200,260,300,20);

		this.lpane.add(label3);
		label3.setVisible(true);
		this.lpane.add(Field3,0);


		//Field 4 and label 4 			
		JLabel label4= new JLabel("telefon:");
		this.lpane.remove(label4);
		this.lpane.remove(Field4);
		label4.setBounds(100,290,300,20);
		Field4.setText(dbsObject2.tel);
		Field4.setBounds(200,290,300,20);

		this.lpane.add(label4);
		label4.setVisible(true);
		this.lpane.add(Field4,0);


		//Field 5 and label 5 			
		JLabel label5= new JLabel("Adress:");
		this.lpane.remove(label5);
		this.lpane.remove(Field5);
		label5.setBounds(100,320,300,20);
		Field5.setText(dbsObject2.adress);
		Field5.setBounds(200,320,300,20);

		this.lpane.add(label5);
		label5.setVisible(true);
		this.lpane.add(Field5,0);


		//Field 6 and label 6 			
		JLabel label6= new JLabel("postnr:");
		this.lpane.remove(label6);
		this.lpane.remove(Field6);
		label6.setBounds(100,350,300,20);
		Field6.setText(dbsObject2.pnr);
		Field6.setBounds(200,350,300,20);

		this.lpane.add(label6);
		label6.setVisible(true);
		this.lpane.add(Field6,0);

		//Field 7 and label 7 			
		JLabel label7= new JLabel("Ort:");
		this.lpane.remove(label7);
		this.lpane.remove(Field7);
		label7.setBounds(100,380,300,20);
		Field7.setText(dbsObject2.o);
		Field7.setBounds(200,380,300,20);

		this.lpane.add(label7);
		label7.setVisible(true);
		this.lpane.add(Field7,0);

		//Field 8 and label 8 			
		JLabel label8= new JLabel("Land:");
		this.lpane.remove(label8);
		this.lpane.remove(Field8);
		label8.setBounds(100,420,300,20);
		Field8.setText(dbsObject2.land);
		Field8.setBounds(200,420,300,20);

		this.lpane.add(label8);
		label8.setVisible(true);
		this.lpane.add(Field8,0);

		//areaField 9 and label 9 			
		JLabel label9= new JLabel("Notifications:");
		this.lpane.remove(label9);
		this.lpane.remove(Field9);
		label9.setBounds(100,460,300,20);
		Field9.setText(dbsObject2.Noteringar);
		Field9.setBounds(200,460,290,100);

		this.lpane.add(label9);
		label9.setVisible(true);
		this.lpane.add(Field9,0);
		
	}
	public JLabel getHeader(){
		return header;
	}
	public void setHeader(JLabel newheader){
		header=newheader;
	}
}
