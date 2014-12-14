package r15;
public class Marker {

	int x,y,w,h,marking, max, dtime, itime;
	boolean push, down, up;
	
	Marker (Button [] b){
		
		max = b.length-1;
		
		x = b[0].x-4;
		y = b[0].y-4;
		
		w = b[0].w+8;
		h = b[0].h+8;
		
		push = false;
		up = false;
		down = false;
		
		marking = 0;
		
	}
	
	void update(Button [] b){
	if(b != null) {
	
	if(down)incr();else itime 	=	 0;	
	if(up)decr();else dtime		=		 0;
	
	up = false;
	down = false;
	
	if(marking > max)marking = 0;
	if(marking < 0)marking = max;

	x = b[marking].x-4;
	y = b[marking].y-4;
	w = b[marking].w+8;
	h = b[marking].h+8;
	
	if(push)b[marking].pushed = true;
	push=false;
		}
	}

	void decr() {
		//fulkod ignore key repeat //emergency sollution
		dtime++;
		if(dtime > 10) {
			marking--;
			dtime = 0;
		}
	}
	
	void incr() {
		//fulkod ignore key repeat //emergency sollution
		itime++;
		if(itime > 10) {
			marking++;
			itime = 0;
		}
	}
}
