package r15;
import java.awt.Color;
import java.awt.Graphics;

//WO.java
//@author Kristoffer Gustavsson

//WorldObjectClass, is inherited by enemies, tiles, player. A class with attributes that will be used by everything cordinated, animated and collision detected.//K
public class WorldObject {
	
	Collision c;
	Animation a;
	
	
	double x,y, vx,vy, f, g, factor; //x,y,speed, friction, gravity pull
	int ix, iy, w,h;//width,height
	int hit_direction;
	boolean hit, flip, ground, target_hit, live;
	Player player;
	
	//MULTI SAMPLE LOOP
	void wo_ms_update(WorldObject[] m) {
		
		x+=	vx*factor;
		y+=	vy*factor;

		
		c.check_wo_vs_map(this, m);	
		

	}
	
	void ms_pos_update() {
		
		x+=	vx*factor;
		y+=	vy*factor;
		
	}
	
	void wo_update() {
		
		ix=(int)x;
		iy=(int)y;
		
		//animation pos
		a.x=ix+a.margin_x; a.y=iy+a.margin_y;
	}
	
	
	WorldObject(){
		factor = Main.factor;
		c=new Collision();	
		
		flip = true;
		hit = false; 
		
		ground = false;
		
		ix = (int)x;
		iy = (int)y;
	}
	
	public void dead(){
	
	}
			
		
	
	
	//just used for error checking...
	void draw(Graphics g){
		
		g.setColor(Color.red);
		g.fillRect(ix, iy, w, h);
		
	}
}
