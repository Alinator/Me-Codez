package r15;
//Player.java
//@author: Kristoffer Gustavsson


public class Player extends WorldObject {
	
	//variables
	double move_speed, jump_speed, max_speed, max_fall_speed;
	int motion, gx, gy;
	
	int health;
	
	public boolean LEFT,RIGHT,UP,DOWN,SPACE, drop;
	protected boolean fire;
	
    Player player;
	
    
	void gun_swinger() {
		
		switch(a.this_frame) {
		case 0:
			gx = ix + 11;
			gy = iy + 32;
			break;
		case 1:
			gx = ix + 11;
			gy = iy + 32;
			break;
		case 2:
			gx = ix + 12;
			gy = iy + 31;
			break;
		case 3:
			gx = ix + 12;
			gy = iy + 30;
			break;
		case 4:
			gx = ix + 12;
			gy = iy + 29;
			break;
		case 5:
			gx = ix + 12;
			gy = iy + 28;
			break;
		case 6:
			gx = ix + 11;
			gy = iy + 30;
			break;
		case 7:
			gx = ix + 10;
			break;
		}
	}
	
	//on_self_hit
	void take_damage() {
		if(target_hit)health-=1;target_hit=false;
		
	}
	
	private void movement()
	{
		//zero it
		fire=false;
		
		if(SPACE)fire=true;
		
		if(LEFT) {
			
			flip = true;//rem
			a.flip=true;
			vx -= move_speed;
			//if(vx>0)vx = vx -= move_speed;
			
			LEFT = false;
		}//
		if(RIGHT){
			
			flip = false;
			a.flip=false;

			vx += move_speed;
		
		} 

		if(UP && ground){
			if(vx<0)vy += (vx/4 -jump_speed);
			else vy += -(vx/4 +jump_speed);

			UP = false;
		} //
		
		//action taken, zero it.
		UP = false;	
		RIGHT = false;
		LEFT = false; //add follow p in e
		SPACE = false;

	}
	
	//things that needs to be checked more than one time per frame
	void physics_multi_sample(WorldObject [] m, Enemies e)
	{	
		//soo.. this update is for all WorldObjects
		wo_ms_update(m);
		
		//but this one is unique for the player class. They use the same initiation of Collision though, in WC (inherited by Player) 
		c.check_feet_vs_map(this, m); //feet check is smarter to have in normal physics func... but for simplicity, I put it here.
		//c.check_feet_vs_map(this, m.mmap); 
	}
	//things that needs to be checked more than once per frame, multiplayer
	void physics_multi_sample_mp(WorldObject [] m)
	{	
		//soo.. this update is for all WorldObjects
		wo_ms_update(m);

		//but this one is unique for the player class. They use the same initiation of Collision though, in WC (inherited by Player) 
		c.check_feet_vs_map(this, m); //feet check is smarter to have in normal physics func... but for simplicity, I put it here.
		//c.check_feet_vs_map(this, m.mmap); 
	}
	
	void physics(){
		
		//speed limit
		if(vx > max_speed)vx = max_speed;//replace with MAX_SPEED or whatava 
		if(vx < -max_speed)vx = -max_speed;
		if(vy > max_fall_speed)vy = max_fall_speed;//replace with MAX_SPEED or whatava 
		if(vy < -max_fall_speed)vy = -max_fall_speed;
		
		
		vx*=f;
		
		
		if(ground) {
		
		}
		else {
		//gravity
		vy += g;
		
		if(vy<0)vy *= f;
		}		

	}
	
	void gameplay(){
		if (x > 1000)health = 0;
	}
	
	//runs in run
	void update()
	{	

		movement();
		physics();
		wo_update();
		gun_swinger();
		gameplay();
		take_damage();
		
		a.update();
		a.str = Integer.toString(health);
		live = true;

	}


	Player(int cx, int cy){ //+string path

		x = cx; 
		y = cy;	
		
		ix = cx; 
		iy = cy;	
		
		vx = 0;
		vy = 0;
		
		w = 32;
		h = 32;
		 
		a = new Animation(64, 64, 7, 2);
		//a.reverable = true;
		a.margin_y = -32;
		a.margin_x = -16;
		
		a.d_h = 64;
		a.d_w = 64;		
		//a.str = "Kristoffer"; //player name ftw
		
		
		move_speed = 0.2;
		jump_speed = 8.4;
		max_speed = 3;//Main.physics_multi_sampling;
		max_fall_speed = 12;//Main.physics_multi_sampling;
		
		f = 0.98;
		g = 0.2;
		
		//copy to user for feedback & db hightscore 
		health = 500; 
	}
	
	}

