package r15;
//@author Kristoffer Gustavsoson

public class Weapon extends WorldObject {

	Projectiles projectiles;
	FX fx;
	
	int ammunition, ignore_pickup,frames_counter, fire_delay, carrier = 0;
	boolean dropped, carried;

	
	void fire_delay() {
	
	if(frames_counter>0)frames_counter--;
		
	}
////////UNCARRIED DROPPED
	void dropped(Player[] p) {
		
		if(ignore_pickup>0)ignore_pickup--;
		else {
			if(c.hit_detection(x, y, w, h, p[0].x, p[0].y, p[0].w, p[0].h)) {dropped = false; carrier = 0; carried = true;}
		
			if(p[1] != null) {
				if(c.hit_detection(x, y, w, h, p[1].x, p[1].y, p[1].w, p[1].h)) {dropped = false; carrier = 1; carried = true;}
			}
		}
		
		vx*=0.99;
		
		vy+=g;	
		
		
	}
//WORN ////////////
	void carried(Player[] p) {

		flip = p[carrier].flip;
		
		hand_pos(p[carrier].gx, p[carrier].gy);
		fx.update();
		fire_delay();
		
		if(p[carrier].fire && ammunition>0 && frames_counter==0) {
			
			frames_counter = fire_delay; 
			projectiles.release(x+16, y, p[carrier].flip);
			ammunition--;
			
			//if(!fx.flash) {
				
				if(flip)fx.flash(ix-fx.a.d_w,iy-fx.a.d_h/2, flip);
				else fx.flash(ix+w,iy-fx.a.d_h/2, flip);
			//}
		}
		if(p[carrier].drop) {

			frames_counter = 0;
			carried = false;
			dropped = true;
			ignore_pickup = 60;//1sec
			p[carrier].drop = false;
			
		}
		

	}
	
	void hand_pos(int pgx, int pgy){
		
		if(flip) {
			x= pgx -16;
		}
		else {
			x = pgx-8;
		}
		
		y=	pgy - h;
		
	}
	
	
	////4EVA
	void all_time(){
		projectiles.update();
		wo_update();	
		check_ammo();
		a.clip_image();
		a.str = Integer.toString(ammunition);
	}
	
	void multi_sample_update(WorldObject []m, WorldObject [] e){
		
		//alltime
		projectiles.multi_sample_update(m, e);
		
		if(!carried)wo_ms_update(m);
	}
	
	
	void multi_sample_update_mp(WorldObject []m, WorldObject [] p){
		
		//alltime
		projectiles.multi_sample_update_mp(m, p);
		
		if(!carried)wo_ms_update(m);
	}	
	
	void update(Player []p) {
		
		if(dropped) {
			dropped(p);
			
		}
		else carried(p);
		all_time();
		
	}
	
	void check_ammo(){
		
		if(ammunition==0) {
			if(flip)a.animation = 2;
			else a.animation = 3;

			return;
		}
		
		if(flip)a.animation = 0;
		else a.animation = 1;
		
		
		switch(ammunition/24) {
		
		case 0: //0-100 left
		
			a.this_frame=6;
			break;
		
		case 1: //100-200 left

			a.this_frame=5;
			break;
		case 2: //200+

			a.this_frame=4;
			break;
		case 3: //300 left
			
			a.this_frame=3;
			break;
		case 4: //400 left
			
			a.this_frame=3;
			break;
		case 5: //500 left
			
			a.this_frame=2;
			break;
		case 6: //600 left
			
			a.this_frame=2;
			break;
		case 7: //700 left
			
			a.this_frame=1;
			break;
		case 8: //800+ left

			a.this_frame=1;
			break;
		case 9: //900+ left

			a.this_frame=0;
			break;
		}
	}
	
	
	
	Weapon(int sx, int sy){
		carried = false;
		dropped = true;
		
		x=sx; y=sy;
		
		w	=	32;
		h	=	32;
		
		vx 	= 0;
		vy	= 0;
		
		fire_delay = 4;
		
		ammunition = 256;
		
		fx = new FX(32,32, 6, 0);
		projectiles = new Projectiles(25);		
		a = new Animation(w,h,0,1);		a.str = Integer.toString(ammunition);
		
		a.d_w=32;
		a.d_h=32;
		
		fx.a.d_w=48;
		fx.a.d_h=48;
		
		a.margin_y=-24;
		//a.margin_x=16;
		g=0.2;
		f=1;
		
	}

	
}

