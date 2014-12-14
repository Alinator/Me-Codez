package r15;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

public class Projectiles {
	
	Projectile bullets[];
	public boolean fire; 
	
	void release(double fx, double fy, boolean fl) {
		
		for(int n=0; n<bullets.length-1; n++) { 
			if(!bullets[n].live) {
			
				//if(!fl)bullets[n].Fire(fx,fy, fl);
				//else 
				
				bullets[n].Fire(fx,fy, fl);
				return;
				
			}
		}
	}

	void update() {
		
		for(int n=0; n<bullets.length-1; n++) { 
			if(bullets[n].live) {
			
				bullets[n].update();
				
			}
		}
	}
	
	void multi_sample_update(WorldObject[] m, WorldObject[] e) {
		
		if(!bullets.equals(null)) {	
		for(int n=0; n<bullets.length-1; n++) { 
			
			if(bullets[n].live) {
			
				bullets[n].multi_sample_update(m, e);
				
			}
		}
		}
	}
	
	void multi_sample_update_mp(WorldObject[] m, WorldObject[] p) {
		
		if(!bullets.equals(null)) {	
		for(int n=0; n<bullets.length-1; n++) { 
			
			if(bullets[n].live) {
			
				bullets[n].multi_sample_update_mp(m, p);
				
			}
		}
		}
	}
	
	Projectiles(int n){
		bullets = new Projectile[n];
		
		for(int b=0; b<n; b++) { 
			
			bullets[b] = new Projectile(0,0);
			bullets[b].live=false;
		}	
	}
	
	//draw all bullets to screen
	void draw(Camera cam,  Graphics g,Image f, Component c)
	{
		for(int n=0; n < bullets.length-1; n++) {
		
			if(bullets[n].live) {
				if(cam.on_camera(bullets[n].ix, bullets[n].iy, bullets[n].w)) {
					
					if(!bullets[n].target_hit)bullets[n].a.simple_line_draw(cam, g,bullets[n].vx, bullets[n].vy);
				
					bullets[n].fx.a.draw_no_krull(cam, g, f, c);
				}
				else bullets[n].live = false;
				
			}
		}
	}
	
}
