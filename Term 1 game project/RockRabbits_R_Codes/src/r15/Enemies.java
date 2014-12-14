package r15;
//Enemies.java
//@author Kristoffer Gustavsson

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

public class Enemies {

	public Enemy enemy[];//[] = new Enemy[amount];
	
	
	//update all enemies in list
	void update(double player_x, double player_y)
	{
		for(int n=0; n<enemy.length; n++) {
			if(enemy[n].live) {
				enemy[n].update(player_x, player_y);
			}
		}
	}
	
	//update all enemies in list
	void enemy_multi_sample_update(WorldObject[] m, WorldObject p)
	{
		for(int n=0; n<enemy.length; n++) {
			if(enemy[n].live) {
				enemy[n].enemy_multi_sample_update(m,p);
			}
		}
	}
	
	

	//CONSTRUCTOR															//CONSTRUCTOR
	Enemies(int i, Map m){
		
		enemy = new Enemy[i];

		Random random = new Random();
		
		int rx;
		int ry;
		
			//the random stuff here is just left from when the enemies were flies... I don't bother to move since it's useful for spreading enemies around still
		
		for(int n=0; n<i; n++) {
			while(true) {
				
				 rx = 32*random.nextInt(76)+2; 
				 ry = 32*random.nextInt(5)+5;
				
			//construct
			enemy[n] = new Enemy(rx, ry);
			
			//randomize starting frame
			enemy[n].a.this_frame = random.nextInt(enemy[n].a.frames);
			
			//check if collide
			enemy[n].c.check_wo_vs_map(enemy[n], m.tiles);
			
			//PPOOOPPP
			if(!enemy[n].hit)break;//re - spawn if collide
			
			}

		}
	}
	//draw all enemies to screen
	void draw(Camera cam, Graphics g, Image i, Component c)
	{
		for(int n=0; n<enemy.length; n++) {
		
			if(cam.on_camera(enemy[n].ix, enemy[n].iy, enemy[n].w)) {
				
				if(enemy[n].live) {
					enemy[n].a.draw_no_krull(cam,g,i,c);
				}
			}
		}
	}
	Enemies(){}

	
}