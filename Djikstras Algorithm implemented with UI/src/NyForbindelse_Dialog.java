import java.awt.*;  
import java.io.StringWriter;

import javax.swing.*;

import Graphs.Edge;
import Graphs.Stad;


public class NyForbindelse_Dialog{
	String n = null;
	String t = null;
	int Time= 0;
	public NyForbindelse_Dialog(Boolean change, Edge<Stad> edge){  

		JTextField name = new JTextField(5);
		if(change== true){
			if(edge.getName() == null){
				System.out.println("aborted");
			}else{
					name.setText(edge.getName());
					name.setEditable(false);
			}
		}
		JTextField time = new JTextField(5);
		Object[] dialogs = { "Fšrbindelse mellan $stad och $stad" ,"Namn:", name, "Tid", time};

		JOptionPane op = new JOptionPane(
				dialogs,
				JOptionPane.QUESTION_MESSAGE,
				JOptionPane.OK_CANCEL_OPTION,
				null,
				null);

		JDialog dialog = op.createDialog("Ny Fšrbindelse");
		dialog.setVisible(true);
		int selection = (Integer) op.getValue();
		
		if(selection == JOptionPane.CANCEL_OPTION){
			System.out.println("nothing");
		}else{
			try{
				Integer.parseInt(name.getText());
				JOptionPane.showMessageDialog(null, "The Namn field must be a String, not a number! please try again");
				return;
			}catch(NumberFormatException ex){
				setN(name.getText());
			}
			t=time.getText();
			if(t != ""){
				try{
					Time= Integer.parseInt(t);
					setT(Time);
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "The input must be a number!");
				}
			}
		}
	}
	public String getN() {
		return n;
	}
	public void setN(String n) {
		this.n = n;
	}
	public int getTime() {
		return Time;
	}
	public void setT(int t) {
		this.Time = t;
	}  


}