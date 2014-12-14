package r15;
//Enemy.java
//@author: Kristoffer Gustavsson


public class Enemy  extends WorldObject{
	
	//variables
	double max_speed,max_fall_speed, move_speed, jump_speed;
	
	boolean LEFT,RIGHT,UP, BITE;
	int health;
	AI ai;

	//functions
	void kill() {
		
		live = false;
	}
	
	private void movement()
	{
		if(ai.jump && ground) {vy = -move_speed; ai.jump=false;} //
		
		if(ai.go_left) {vx -= move_speed; a.flip = true;} //
		if(ai.go_right) {vx += move_speed; a.flip = false;}//

	}
	
	void enemy_multi_sample_update(WorldObject[] m,  WorldObject p){
			
			if(!target_hit) {
			
				c.check_e_vs_p(this, p);
			}
			wo_ms_update(m);
			c.check_feet_vs_map(this, m); 
		
	}
	
	void physics() {
		
		//speed limit
		if(vx > max_speed)vx = max_speed;//replace with MAX_SPEED or whatava 
		if(vx < -max_speed)vx = -max_speed;
		if(vy > max_fall_speed)vy = max_fall_speed;//replace with MAX_SPEED or whatava 
		if(vy < -max_fall_speed)vy = -max_fall_speed;
		
		
		if(ground) {
			
		}
		else {
		//gravity
		vy += g;
		
		if(vy<0)vy *= f;
		}		

	}
	void deal_damage(){
		if (target_hit)health-=1;

	}

	void update(double player_x, double player_y){
		
		deal_damage();
		
		if(target_hit && health==0)
			kill();
			target_hit = false;

		
		
		if(ai.alerted)a.play = true;
		
		
		movement();
		
		physics();
		wo_update();
		a.update();
		
		a.str = Integer.toString(health);
		
		
		ai.x	=	x; 
		ai.y	=	y;
		ai.update(player_x, player_y);
		
	}
	
	Enemy(){}

	Enemy(int cx, int cy){
		
		x = (double)cx;
		y = (double)cy;

		vx = 0;
		vy = 0;

		w = 64;
		h = 64;
		
		g = 0.05;
		
		a = new Animation(64, 64, 7, 4); 
//		a = new Animation (350, 200,7, 4);
		
		
		a.margin_y = -32; 
		a.margin_x = -32; 
		a.d_w=96;
		a.d_h=96;
		
		a.play = false;
		a.flip = true;
		
		ai = new AI(x,y);
		ai.turned_left = true;
		
		live=true;
		
		move_speed = 0.2;
		jump_speed = 8.6;
		max_speed = 10;
		max_fall_speed = 12;
		
		ground = false;
		
		health= 100;
		
		
	}
	

}