//@author: Qingju Luo , Jiaqing Li

import java.awt.GridLayout;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Vengeful Rabbit");
		frame.setVisible(true);
		frame.setSize(800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(200, 100);
		frame.setResizable(false);System.out.print("start6");
		frame.setLayout(new GridLayout(1,1));
		
		System.out.print("start0");

		new Menu (frame);
		System.out.print("menu end");
	}

}
