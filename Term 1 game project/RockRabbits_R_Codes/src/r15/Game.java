package r15;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

// javax temporary, should not be used, stick to applet
import javax.swing.ImageIcon;

//Game.java
//@author: Kristoffer Gustavsson

public class Game {

	User u;
	
		private static int native_width, native_height;
		
		static int physics_multi_sampling;
		static double factor;
		
		Camera cam;
		Map tiles;

		Players players;
		
		Enemies enemies;
		Weapons weapons;
		Background background;
				
		//
		Image textures, background_image,background_image_2,enemy_sprite,player_sprite,gun_sprite,exp_sprite, flash_sprite;

		void pause_menu(){}
		void exit(){}
		
		
		void mp_init()
		{
			
			//game speed & colission check runs
			physics_multi_sampling = Main.physics_multi_sampling; //how many times the physics loop will loop
			factor = Main.factor;
			native_width = Main.native_width; native_height = Main.native_height;
			
			//fill classes
			tiles = new Map(1);
			
			players = new Players();
			
			players.p[0] = new Player(640, 360);
			players.p[1] = new Player(480, 360);
			
			cam = new Camera(players.p[0].ix, players.p[0].iy); //00 should be player pos from map.. or camera start.
			background = new Background(native_width, native_height, 1); 
			weapons = new Weapons(10);
			
			//load images//does not return until fully loaded 
			textures		 	= new ImageIcon("maggots32.png").getImage(); 
			background_image 	= new ImageIcon("dsun.png").getImage(); 
			background_image_2 	= new ImageIcon("002.png").getImage(); 
			player_sprite 		= new ImageIcon("no_ears64t.png").getImage();
			gun_sprite 			= new ImageIcon("gun.png").getImage();
			flash_sprite		= new ImageIcon("fireflash32alt.png").getImage();
			exp_sprite			= new ImageIcon("wx2m.png").getImage();

			
		}
		void sp_init(){

			//game speed & colission check runs
			physics_multi_sampling = Main.physics_multi_sampling; //how many times the physics loop will loop
			factor = Main.factor;
			native_width = Main.native_width; native_height = Main.native_height;
			
			//fill classes
			tiles = new Map(0);
			
			players = new Players();
			players.p[0] = new Player(1024, 360);
			players.p[1] = null;
			
			cam = new Camera(players.p[0].ix, players.p[0].iy); //00 should be player pos from map.. or camera start.

			background = new Background(native_width, native_height, 1); 

			weapons = new Weapons(10);
			enemies = new Enemies(25, tiles);
		
			//load images//does not return until fully loaded 
			textures		 	= new ImageIcon("maggots32.png").getImage(); 
			background_image 	= new ImageIcon("dsun.png").getImage(); 
			background_image_2 	= new ImageIcon("002.png").getImage(); 
			enemy_sprite 		= new ImageIcon("rawah64.png").getImage();  
			player_sprite 		= new ImageIcon("no_ears64t.png").getImage();
			gun_sprite 			= new ImageIcon("gun.png").getImage();
			flash_sprite		= new ImageIcon("fireflash32alt.png").getImage();
			exp_sprite			= new ImageIcon("wx2m.png").getImage();
			
					
		}
		
		void sp_loop(){		//GAME LOOP

				
				//MULTI SAMPLE PHYSICS LOOP
				for(int n=0; n<physics_multi_sampling; n++) {
	
					players.p[0].physics_multi_sample(tiles.tiles, enemies);
					enemies.enemy_multi_sample_update(tiles.tiles, players.p[0]); 
					weapons.multi_sample_update(tiles.tiles, enemies.enemy);
					tiles.multi_sample_update();
				}

				//regular frame updates
				
				players.p[0].update();
				weapons.update(players.p);
				enemies.update(players.p[0].x, players.p[0].y); //player pos for AI... 
				tiles.update();
				cam.soft_update(players.p[0].ix,players.p[0].iy);

		}
	void mp_loop(){		//GAME LOOP

			
			//MULTI SAMPLE PHYSICS LOOP
			for(int n=0; n<physics_multi_sampling; n++) {

				players.p[0].physics_multi_sample_mp(tiles.tiles);
				players.p[1].physics_multi_sample_mp(tiles.tiles);
				
				
				
				weapons.multi_sample_update_mp(tiles.tiles, players.p);
				tiles.multi_sample_update();
			}

			//regular frame updates
			players.p[0].update();
			players.p[1].update();
			
			weapons.update(players.p);
			
			tiles.update();
			cam.soft_update_mp(players.p[0].ix,players.p[0].iy, players.p[1].ix, players.p[1].iy);
		
		
	}	
		
	public void sp_render(Graphics g, Component c) { //mobe to game
	        
			background.draw(cam, g, background_image_2, c);
			enemies.draw(cam, g, enemy_sprite, c);
			players.p[0].a.draw_no_krull(cam, g, player_sprite, c);
			weapons.draw_w_p(cam, g, exp_sprite, c);
			weapons.draw(cam, g, gun_sprite, flash_sprite, c);
			tiles.draw(cam, g, textures, c);
			
			String pos = Integer.toString(players.p[0].ix)+ "  " + Integer.toString(players.p[0].iy);
			
			//hud
			g.setColor(Color.green);
			g.drawString(pos, 8, 468);
	}
		
	public void mp_render(Graphics g, Component c) { //mobe to game
	        
			background.draw(cam, g, background_image, c);
			
			players.p[0].a.draw_no_krull(cam, g, player_sprite, c);
			players.p[1].a.draw_no_krull(cam, g, player_sprite, c);
			
			weapons.draw_w_p(cam, g, exp_sprite, c);
			weapons.draw(cam, g, gun_sprite, flash_sprite, c);
			
			tiles.draw(cam, g, textures, c);
	}
		
		
	Game(){}
			
	
}
