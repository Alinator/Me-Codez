package r15;//Tile.java
//@author Kristoffer  Gustavsson

/*
 * Store a bunch of those to make a map.
 */


public class Tile extends WorldObject {
	
	//move points
	int d1, d2;
	
	double cp, cv, speed = 1;
	
	int hold, type; boolean forward;
	
	void direction() {
		
		if(forward && cp > d2)forward = false; 
		else if(!forward && cp < d1)forward = true; 
		
	}
	
	double move() {

		if(forward)cv = speed;
		else cv = -speed; 
		
		cp += cv;
		
		return cv;

	}

	
	void update() {
		
		switch (type) {
		case 2:
			direction();
			vx = move();
		break;
		
		case 3:
			direction();
			vy = move();
		break;
		
		case 4: 
			direction();
			vy = move();
			vx = move();
		break;
		}

		wo_update();
		a.update();
		
	}

	
	Tile(int tx, int ty, int ts, int des, int tt){
		
		x = tx;
		y = ty;
		
		w =	ts;
		h =	ts;
		
		type = tt;
		forward = true;
		cp=0;
		d1=0;
		d2 = des;
		

		vx = 0; 
		vy = 0;		
		
		a = new Animation(32,32,7,10);
		a.reverable = true;
		
		a.d_h = ts;
		a.d_w = ts;		
	}
	
	Tile(int tx, int ty, int ts, int tt){
		
		x = tx;
		y = ty;
		
		w =	ts;
		h =	ts;
		
		type = tt;
		
		vx = 0; //
		vy = 0;		
		
		a = new Animation(32,32,7,10);
		a.reverable = true;
		
		a.d_h = ts;
		a.d_w = ts;
		
		
		
	}
	
}
