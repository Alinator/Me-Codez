package r15;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;


public class Background {

	double x, y, move_factor;
	int ix, iy, w, h, scale, map_width = 1088, map_height= 640; //make cam stop move instead
	
	void update(double nx, double ny){
	
		x = nx*move_factor; 
		y = ny*move_factor;	
		
		ix = (int) x; iy =(int) y;
	}
	
	Background(int iw,int ih, int scl){
		
		move_factor = 0.25;
		w = iw;
		h = ih;
		x=0;
		y=0;
		
		scale = scl;
	}	
	
	//void move(){}
	void draw_x(Camera cam, Graphics g, Image i, Component c)
	{	
		x=cam.x*move_factor;
		y=cam.y*move_factor;
		
		ix=(int) x;
		iy=(int) y;	
		
		//g.drawImage(i,0, 0, w, h,		ix, 0, ix+w/2, h,  c);
		
		g.drawImage(i,0, 0, w, h, 		ix, iy, ix+(w), iy+(h),  c);
		//g.drawImage(i,0, 0, 680, 480,640+ix, 0, 640+ix+640/2, 480/2,  c);
	}
	void draw(Camera cam, Graphics g, Image i, Component c)
	{
		
		x=cam.x*move_factor;
		y=cam.y*move_factor;
		
		ix=(int) x;
		iy=(int) y;	
		
		int l = 0;
		int t = 0; 
		int r = w;
		int b = h;
		
		scale = 2;
		
		if(cam.x<0)l-=cam.x;
		//if(cam.y<0)t-=cam.y; 
		if(cam.x+w>map_width)r -= (cam.x+w)-map_width;//mapw
		if(cam.y+h>map_height)b-=(cam.y+h)-map_height;//+=(map_height -(cam.y+h)); //(1.5 d or 2d...)
		
		int wr = r-w;
		int hb = b-h;
		
		//g.drawImage(i,0, 0, w, h,		ix, 0, ix+w/2, h,  c);
		
		g.drawImage(i,l, t, r, b, 			ix+l, iy+t, wr+ix+w, hb+iy+h,  c);
		//g.drawImage(i,0, 0, 680, 480,640+ix, 0, 640+ix+640/2, 480/2,  c);
	}

}
