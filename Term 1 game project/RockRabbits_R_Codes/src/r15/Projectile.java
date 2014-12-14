package r15;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Projectile extends WorldObject {

	Random r;
	FX fx;
	
	boolean live = false;
	int time, time_limit;
	double speed;
	
	void on_target_hit() {
		
		if(!fx.flash)fx.flash(ix-fx.a.d_w/2,iy-fx.a.d_h/2, flip);
		
		//target_hit = false;
	
	}
	
	void on_hit() {
	
			
			//uneven repel
			vx += 2 * r.nextDouble() - 1; 
			vy += 2 * r.nextDouble() - 1; 
			
			if(!fx.flash)fx.flash(ix-fx.a.d_w/2,iy-fx.a.d_h/2 , flip);
			
			//friction
			vx*=f; vy*=f;
			
			//hit=false;
			
	
	}
	
	void multi_sample_update(WorldObject m[], WorldObject e[]){
		
		if(!target_hit) {
			
		c.check_b_vs_e(this, e);
		if(target_hit)on_target_hit();
	
		c.check_bo_vs_map(this, m); 
		if(hit)on_hit();
		
	
		x+=	vx*factor;
		y+=	vy*factor;
		//wo_ms_update(m);
		}
	}

	void multi_sample_update_mp(WorldObject[] m,  WorldObject p[]){
		
		if(!target_hit) {
		

			c.check_b_vs_e(this, p);
		if(target_hit)on_target_hit();
	
		c.check_bo_vs_map(this, m); 
		if(hit)on_hit();
		
	
		x+=	vx*factor;
		y+=	vy*factor;
		//wo_ms_update(m);
		}
	}
	
	void update(){
		
		timer();
		fx.update();
		
		if(!target_hit) {
		wo_update();
		
		vx*=0.99;
		vy+=g;
		
		if(vy<0)vy*=f;		
		}
		
	}
	
	void timer() {
		time++;
		if(time > time_limit)kill();
		
	}
	void kill() {
		live = false;
	}
	
	Projectile(double sx,double sy){
		
		live = false;
		x=sx;
		y=sy;
		vx = 0;
		vy= 0;
		
		w=4;
		h=4;  
		
		a = new Animation(w,h,0,0);
		
		
		fx = new FX(16,16, 9, 2);
		r = new Random();
		

		fx.a.d_w = 32;
		fx.a.d_h = 32;
		
		time = 0;
		time_limit = 120; // /60
		
		speed = 10;
		
		g=0.05;
		f=0.98;
		
	}
	
	void Fire(double sx,double sy,boolean fl){
		
		target_hit = false;
		hit = false;
		live = true;
		
		x=sx;
		y=sy;
		
		if(fl) {vx = -speed; x -= 24;}
		else {vx = speed; x += 24;}
		
		vy= 0;
		
		//uneven repel
		vx += 2 * r.nextDouble() - 1; 
		vy += 2 * r.nextDouble() - 1.4; 
		
		
		time = 0;
		
	}

	
	//DONT USwe
	void draw(Camera ca, Graphics g){
			
			g.setColor(Color.yellow);
			g.drawLine(ix-ca.x, iy-ca.y,(ix-(int)vx) -ca.x, (iy-(int)vy) -ca.y);
			//g.fillOval(ix-ca.x, iy-ca.y, (ix-(int)vx), (iy-(int)vy));
			
			//if(fx.flash)a.draw(ca, g, i, c);

		}	

}
