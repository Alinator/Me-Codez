import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.Control;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 *	Bismillahirahmanirahim
 *	this class will work as a controller class, from which a person can access any database.
 *	
 *
 */
public class Controller extends JFrame{
	
	public enum controller{
		costumerDatabase,productDatabase,supplierDatabase,employeeDatabase,controller
	}
	
	JButton button1=null;
	JButton button2=null;
	JButton button3=null;
	JButton button4=null;
	
	CostumerDatabase cdatabase= null;
	Productdatabase pdatabase= null;
	SupplierDatabase sdatabase= null;
	EmployeeDatabase edatabase=null;

	
	public controller control;
	
	public Controller(){
		
		JLayeredPane pane= new JLayeredPane();
		Container conn=this.getContentPane();
		
		setBounds(1000,0,200,300);
		
		button1= new JButton("Costumer Database");
		button1.setBounds(0,50, 150, 20);
		button1.setVisible(true);
		button1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				openDatabase(controller.controller);
				openDatabase(controller.costumerDatabase);
			}
		});
		pane.add(button1);
		
		button2= new JButton("Product Database");
		button2.setBounds(0,100, 150, 20);
		button2.setVisible(true);
		button2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openDatabase(controller.controller);
				openDatabase(controller.productDatabase);
			}
		});
		pane.add(button2);
		
		button3= new JButton("Supplier Database");
		button3.setBounds(0,150, 150, 20);
		button3.setVisible(true);
		button3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openDatabase(controller.controller);
				openDatabase(controller.supplierDatabase);
			}
			
		});
		pane.add(button3);
		
		
		button4= new JButton("Employee Database");
		button4.setBounds(0,200, 150, 20);
		button4.setVisible(true);
		button4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openDatabase(controller.controller);
				openDatabase(controller.employeeDatabase);
			}
			
		});
		pane.add(button4);
		
		conn.add(pane);
		
		
		setVisible(true);
	}
	
	public void openDatabase(controller control){
		
		
		switch(control){
		case costumerDatabase: 
			
			cdatabase= new CostumerDatabase();
			break;
		case productDatabase:
			pdatabase= new Productdatabase();
			break;
		case supplierDatabase:	
			sdatabase= new SupplierDatabase();
			break;
		case employeeDatabase:
			edatabase= new EmployeeDatabase();
		case controller:
			
			if( cdatabase != null ){
				cdatabase.setVisible(false);
			}else if( pdatabase != null ){
				pdatabase.setVisible(false);
			}else if( sdatabase != null ){
				sdatabase.setVisible(false);
			}else{
				System.out.println("All null");
			}
			
			break;
		}
		
	}
	
	
	
	
	
	
}
