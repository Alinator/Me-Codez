package r15;//Physics.java
//@author: Kristoffer Gustavsson

/*
 * Class with physics & collision functions to use in other classes 
 * (planning to inherit i world object class, which is inherited by player, enemy etc...)//K
 */

public class Collision {

	//check if one box touches another
	boolean hit_detection
		(double x1, double y1,int w1, int h1, double x2, double y2, int w2, int h2){			
		
		//this chunk returns false if - 
		if((x1+w1)<x2)return false;	// - the right edge of box1 is to the left of the left edge of box2
		if(x1>(x2+w2))return false; // and so on with all edges 
		if((y1+h1)<y2)return false;
		if(y1>(y2+h2))return false;
		
		return true;

	}
	
	boolean under_box_hit_detection
		(double x1, double y1,int w1, int h1, double x2, double y2, int w2, int h2){			
		
		double yt=y1+h1; 
		double yb=y1+h1+2;
		
		if(x1+w1<x2)return false;
		if(x1>x2+w2)return false;
		
		if(yb<y2)return false;
		if(yt>y2+h2)return false;
		
		return true;

	}
	
	//center comparsion 

	int hit_direction(double x1, double y1,int w1, int h1, double x2, double y2, int w2, int h2){			
		
		//gen. center pos box1
		double cx1 = x1+(w1/2); 
		double cy1 = y1+(h1/2); 
		//box2
		double cx2 = x2+(w2/2); 
		double cy2 = y2+(h2/2); 
	
		//distances
		double dx, dy;	

		if(cx1>cx2)dx=cx1-cx2;//right coli
		else dx=cx2-cx1;//left coli
		
		if(cy1>cy2)dy=cy1-cy2;//bot coli
		else dy=cy2-cy1;//top coli
			
		//so... if xd > yd collision is probably sideways not from above 
		if(dx < dy) {
			if(cy1>cy2)return 2;
			else return 0;
		}
		
		else {
			if(cx1>cx2)return 1;
			else return 3;
		}
		//0=T, 1=RIGHT, 2 = BOT, 3 = LEFT
	}
	
	void check_feet_vs_map(WorldObject o, WorldObject []m) { //convert to world_object instead
		
		for(int n = 0; n<m.length; n++){
			
				
					
					double tx = m[n].x;
					double ty = m[n].y;
					int tw 	= m[n].h;
					int th 	= m[n].w;						
					
					if(under_box_hit_detection(o.x, o.y, o.w, o.h,tx, ty, tw, th)) {
								
						o.ground = true; 
						o.x += m[n].vx*o.factor; 		
						o.y += m[n].vy*o.factor; 	
						return;
					}
				else o.ground = false;
				}
	}

	//Does not bounce up falling and colliding world_objects players & enemies 	
	void check_wo_vs_map(WorldObject o, WorldObject m[]) { //convert to world_object instead //Funny how you don't need a pointer for this in Java.//K
		
		for(int n = 0; n<m.length; n++){ //checks emty now remember
					
					double tx = m[n].x;
					double ty = m[n].y;
					int tw 	= m[n].h;
					int th 	= m[n].w;			
					
					if(hit_detection(o.x,o.y, o.w, o.h, tx, ty, tw, th)) {
						
						o.hit = true;	
						o.hit_direction=hit_direction(o.x,o.y, o.w, o.h, tx, ty, tw, th);
						
						//directly effects the players position, force out of box
						if(o.hit_direction==0){o.y=	  ty-o.h-1;		o.vy*=0; 		return;}
						if(o.hit_direction==1){o.x=   tx+tw+1;		o.vx*=-0.5;		return;}
						if(o.hit_direction==2){o.y=   ty+th+1;		o.vy*=-1;		return;}
						if(o.hit_direction==3){o.x=	  tx-o.w-1;		o.vx*=-0.5;		return;}
						//
						
						return;
					}
					
					else o.hit = false;
				}
			
	}

//Does bounce falling and colliding world objects in any direction 
void check_bo_vs_map(WorldObject o, WorldObject []m) { 
		
		for(int n = 0; n<m.length; n++){
			
				
					
					double tx = m[n].x;
					double ty = m[n].y;
					int tw 	= m[n].h;
					int th 	= m[n].w;					
					
					if(hit_detection(o.x,o.y, o.w, o.h, tx, ty, tw, th)) {
						
						o.hit = true;	
						o.hit_direction=hit_direction(o.x,o.y, o.w, o.h, tx, ty, tw, th);
						
						//directly effects the players position, force out of box
						if(o.hit_direction==0){o.y=	  ty-o.h-1;		o.vy*=-1;return;}
						if(o.hit_direction==1){o.x=   tx+tw+1;		o.vx*=-1;return;}
						if(o.hit_direction==2){o.y=   ty+th+1;		o.vy*=-1;return;}
						if(o.hit_direction==3){o.x=	  tx-o.w-1;		o.vx*=-1;return;}
						//
						
						return;
					}
					
					else o.hit = false;
				}
			
	}


void check_b_vs_e(WorldObject o, WorldObject e[]) { //convert to world_object instead
		
		for(int n = 0; n<e.length; n++){
			
			if(!e[n].live)continue;
					
					double tx = e[n].x;
					double ty = e[n].y;
					int tw 	= e[n].h;
					int th 	= e[n].w;					
					
					if(hit_detection(o.x,o.y, o.w, o.h, tx, ty, tw, th)) {
						
						o.target_hit = true;	
						e[n].target_hit = true;
						
						return;
					}

				}
			
	}
void check_e_vs_p(WorldObject o, WorldObject p) { //convert to world_object instead

				
				double tx = p.x;
				double ty = p.y;
				int tw 	= p.h;
				int th 	= p.w;					
				
				if(hit_detection(o.x,o.y, o.w, o.h, tx, ty, tw, th)) {
					
					p.target_hit = true;
					
					return;
				}

			}

}


