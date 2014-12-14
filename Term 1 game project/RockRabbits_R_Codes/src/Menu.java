//@author: Qingju Luo , Jiaqing Li


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

import r15.User;

public class Menu {

	public JFrame f;
	public JLayeredPane contentPane;
	public MyPanel menu;
	public MyPanel creditsPanel;
	public MyPanel controlsPanel;
	public MyPanel statsPanel;
	public MyPanel inputNamePanel;
	public MyPanel selectLevelPanel;
	public MyPanel loadgame2Panel;
	public GameScreen gameScreen;
	public JButton start;
	public JButton multiBtn;
	public JButton singleBtn;
	public JButton controls;
	public JButton credits;
	public JButton stats;
	public JButton exit;
	public JButton loadgame2;
	public String playerName;
	
	r15.User user;
	public Menu(JFrame frame) {

		

		this.f = frame;
		contentPane = f.getLayeredPane();
		
		menu = new MyPanel("dsun.png");
		menu.setLayout(null);
		menu.setBounds(0,0, 800, 600);
		
		
		
		Icon imgicon1 = new ImageIcon("startps.jpg");
		start = new JButton("start", imgicon1);
		start.setBounds(80, 80, 150, 40);	

		Icon imgicon11 = new ImageIcon("multiplayer.jpg");
		multiBtn = new JButton("Multi", imgicon11);
		multiBtn.setBounds(80, 130, 150, 40);
	
		Icon imgicon2 = new ImageIcon("help.jpg");
		controls = new JButton("help", imgicon2);
		controls.setBounds(80, 230, 150, 40);

	
		
		Icon imgicon3 = new ImageIcon("resumegame.jpg");
		stats = new JButton("resumegame", imgicon3);
		stats.setBounds(80, 280, 150, 40);

		Icon imgicon5 = new ImageIcon("credits.jpg");
		credits = new JButton("credits", imgicon5);
		credits.setBounds(80, 330, 150, 40);

		Icon imgicon6 = new ImageIcon("exit.jpg");
		exit = new JButton("exit", imgicon6);
		exit.setBounds(80, 380, 150, 40);
		
		
		
		Icon imgicon4= new ImageIcon("loadgame.jpg");
		loadgame2= new JButton("loadgame2", imgicon4);
		loadgame2.setBounds(80, 180, 150, 40);
		
		
	
		

		

		
	 addListeners(start, controls, credits, stats, exit, multiBtn);
		menu.add(start);
		menu.add(multiBtn);
		menu.add(controls);
		menu.add(credits);
		menu.add(stats);
		menu.add(exit);
		
		menu.add(loadgame2);
	

		contentPane.add(menu, 1);

		creditsPanel = new MyPanel("backgroudimage.jpg");
		creditsPanel.setLayout(null);
		creditsPanel.setBounds(0, 0, 800, 560);
		creditsPanel.setVisible(false);
		contentPane.add(creditsPanel,1);

		controlsPanel = new MyPanel("backgroudimage.jpg");
		controlsPanel.setLayout(null);
		controlsPanel.setBounds(0, 0, 800, 560);
		controlsPanel.setVisible(false);
		contentPane.add(controlsPanel, 2);

		statsPanel = new MyPanel("backgroudimage.jpg");
		statsPanel.setLayout(null);
		statsPanel.setBounds(0, 0, 800, 560);
		statsPanel.setVisible(false);
		contentPane.add(statsPanel, 3);

		inputNamePanel = new MyPanel("backgroudimage.jpg");
		inputNamePanel.setLayout(null);
		inputNamePanel.setBounds(0, 0, 800, 560);
		inputNamePanel.setVisible(false);
		contentPane.add(inputNamePanel, 4);

		selectLevelPanel = new MyPanel("backgroudimage.jpg");
		selectLevelPanel.setLayout(null);
		selectLevelPanel.setBounds(0, 0, 800, 560);
		selectLevelPanel.setVisible(false);
		contentPane.add(selectLevelPanel, 5);
		
		loadgame2Panel = new MyPanel("backgroudimage.jpg");
		loadgame2Panel.setLayout(null);
		loadgame2Panel.setBounds(0, 0, 800, 560);
		loadgame2Panel.setVisible(false);
		contentPane.add(loadgame2Panel, 3);
     

	}

	public void addListeners(JButton start, JButton controls,JButton loadgame, JButton credits,
			JButton stats, JButton multiBtn) {

		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(false);
				inputName();
				
			}

		});
		
		controls.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("lala01");
				menu.setVisible(false);
				controlsPanel.setVisible(true);
				Icon imgicon6 = new ImageIcon("back.jpg");
				final JButton back = new JButton("oo", imgicon6);
				back.setBounds(10, 380, 150, 50);
				back.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						controlsPanel.setVisible(false);
						back.setVisible(false);
						menu.setVisible(true);

					}

				});
				controlsPanel.add(back);
			}

		});

		credits.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("lala02");
				menu.setVisible(false);
				creditsPanel.setVisible(true);
				Icon imgicon6 = new ImageIcon("back.jpg");
				final JButton back = new JButton("oo", imgicon6);
				back.setBounds(10, 380, 150, 50);
				back.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						creditsPanel.setVisible(false);
						back.setVisible(false);
						menu.setVisible(true);

					}

				});
				creditsPanel.add(back);
			}

		});

		loadgame2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("lala01");
				menu.setVisible(false);
				loadgame2Panel.setVisible(true);
				Icon imgicon6 = new ImageIcon("back.jpg");
				final JButton back = new JButton("oo", imgicon6);
				back.setBounds(10, 380, 150, 50);
				back.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						controlsPanel.setVisible(false);
						back.setVisible(false);
						menu.setVisible(true);

					}

				});
				loadgame2Panel.add(back);
			}

		});
		
		
		
		
		
		
		stats.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				menu.setVisible(false);
				statsPanel.setVisible(true);
				statsPanel.setLayout(null);
				JLabel label = new JLabel(" anything else");
				label.setBounds(50, 50, 200, 100);
				statsPanel.add(label);
				Icon imgicon6 = new ImageIcon("back.jpg");
				final JButton back = new JButton("oo", imgicon6);
				back.setBounds(10, 380, 150, 50);
				back.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						statsPanel.setVisible(false);
						back.setVisible(false);
						menu.setVisible(true);

					}

				});
				statsPanel.add(back);
			}

		});
		
		multiBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(false);
				r15.Main gameFrame = new r15.Main();
				
				contentPane.add(gameFrame);
				gameFrame.init();
				gameFrame.setGameUserNumber(true);
				gameFrame.start();
			}		
		});
				
		
		

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.exit(0);

			}

		});
		
				}
			

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public void inputName() {

	
		inputNamePanel.setVisible(true);
		final JTextField nameField = new JTextField();
		nameField.setBounds(220, 200, 250, 40);
		inputNamePanel.add(nameField);
		JLabel pleaseInputName = new JLabel();
		pleaseInputName.setBounds(245, 90, 300, 100);
		pleaseInputName.setForeground(Color.BLACK);
		pleaseInputName.setFont(new Font("Serif", Font.BOLD, 24));
		pleaseInputName.setText(" Enter your name!");
		inputNamePanel.add(pleaseInputName);
		Icon imgicon7 = new ImageIcon("next.jpg");
		final JButton cont = new JButton("oo", imgicon7);
		cont.setBounds(260, 300, 150, 40);
		cont.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				playerName = nameField.getText();
				r15.User user= new r15.User(playerName);
				inputNamePanel.setVisible(false);
				  
//				selectLevel();
				r15.Main gameFrame = new r15.Main();
				
				contentPane.add(gameFrame);
				gameFrame.init();
				gameFrame.start();
				
				
			}

		});
		inputNamePanel.add(cont);

	}


				

		
	
		

	}



