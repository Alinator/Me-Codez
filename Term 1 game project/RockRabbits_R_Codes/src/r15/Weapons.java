package r15;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;


public class Weapons {

	Weapon[] weapon;
	int amount;
	
	//functions
	
	void multi_sample_update(WorldObject [] m, WorldObject []e){
		for(int n=0; n<amount; n++) {
			weapon[n].multi_sample_update(m, e);
		}
	}
	void multi_sample_update_mp(WorldObject [] m, WorldObject [] p){
		for(int n=0; n<amount; n++) {
			weapon[n].multi_sample_update_mp(m, p);
		}
	}
	void update(Player []p) {
		for(int n=0; n<amount; n++) {
		 	weapon[n].update(p);
		 	
		} 
	}
	
	//CONSTRUCT
	Weapons(int a){
		
		
		amount = a;
		weapon = new Weapon[amount];
		
		for(int n=0; n<amount; n++) {
			
			weapon [n] = new Weapon(128*n, 128);//read startpos from map...
		
		}
		
		
	}
	//DRAW
	void draw(Camera cam, Graphics g, Image i, Image f, Component c)
	{
		
		for(int n=0; n<amount; n++) {
			
			if(cam.on_camera(weapon[n].ix, weapon[n].iy, weapon[n].w)) {
				
				if(weapon[n].fx.flash)weapon[n].fx.a.draw_no_krull(cam, g, f, c);
				weapon[n].a.draw_weapon(cam, g, i, c);
				
				
			}
		}
	}
	
	void draw_w_p(Camera cam, Graphics g, Image i, Component c) {
		
		for(int n=0; n<amount; n++) {
			weapon[n].projectiles.draw(cam, g, i, c);
		}
		
	}
	
}
