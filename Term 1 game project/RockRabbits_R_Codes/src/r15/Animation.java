package r15;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

//Animation.jva
//@author Kristoffer Gustavsson

/*
 * It's the animation class that takes care of switching images on player & enemies.
 * I also put some graphics-code here since so much texture coordinates are managed here.
 * If the game was bigger, i'd create a render class instead. now i don't really care. 
 * 
 */

public class Animation {
	
	int x, y, width, height, animation, frames, frames_delay, frames_counter, this_frame;
	int left,top, right,bottom,margin_x, margin_y, d_w, d_h;
	boolean flip;
	
	String str;
	
	boolean reverable, reversing, play;
	
	void check_next_frame() {
		
		
		frames_counter++;
		
		if(frames_counter>frames_delay) {
			
			frames_counter = 0;	
			
			if(reverable) {
				
				if(this_frame==frames)reversing = true;
				if(this_frame==0)reversing = false;
				
				if(reversing)this_frame--;
				else this_frame++;
	
			}
			else if(!reverable){
			this_frame++;
			if(this_frame>frames)this_frame = 0;
			}
		}
	}
	
	void clip_image()
	{	
			left = animation*width;
			right = left + width;
			top = this_frame*height;
			bottom = top+height;
			
	}
	
	void update(){
		if(play) {
		
		check_next_frame();
		}
		clip_image();
		
	}
	
	Animation(int w, int h,int f, int fps){
		
		width=w;
		height = h;
		
		d_w = w;
		d_h = h;
		
		frames = f;
		frames_delay = fps;	
		
		reverable = false; //activate in animation-user class by saying a.reverable = true; in constructor
		
		play = true;
		flip = false;
		animation = 0;
		frames_counter = 0;
		this_frame = 0;
	}
	
	//Draw sprites
	void draw(Camera ca, Graphics g, Image i, Component c)
	{
		//g.setColor(Color.red);
		//g.drawImage(image, x, y, c);
		//g.fillRect(x, y, width, height);
		
		int h= d_h;
		int w= d_w;
		
		int xt, yt;
		xt=x; yt=y;
		
		if(d_w != width) {
			yt=y-d_h+height;
		}
		
		
		//Color ambient = new Color(228,96,96);
		
		g.drawImage(i, xt-ca.x, yt-ca.y, xt+w-ca.x, yt+h-ca.y, left,top,right,bottom, c);///n 
		
		//g.drawImage(i, xt-ca.x, yt-ca.y, xt+w-ca.x, yt+h-ca.y, right,top,left,bottom, c);//flip
		
		
	
		
		if(str != null) {
			g.setColor(Color.GREEN);
			g.drawString(str, xt-ca.x, yt-ca.y - 12);
		}
	}
	
	void draw_no_krull(Camera ca, Graphics g, Image i, Component c)
	{
		//g.setColor(Color.red);
		//g.drawImage(image, x, y, c);
		//g.fillRect(x, y, width, height);
		
		int h= d_h;
		int w= d_w;
		
		if(flip) g.drawImage(i, x-ca.x, y-ca.y, x+w-ca.x, y+h-ca.y, left,top,right,bottom, c);///n 

			
		else	g.drawImage(i, x-ca.x, y-ca.y, x+w-ca.x, y+h-ca.y, right,top,left,bottom, c);///n 
		// g.drawImage(i, x-ca.x, y-ca.y, x+w-ca.x, y+h-ca.y, left,top,right,bottom, c);///n 
		
		if(str != null) {
			g.setColor(Color.RED);
			g.drawString(str, x-ca.x, y-ca.y - 12);
		}

	}
	
	void simple_line_draw(Camera ca, Graphics g, double vx, double vy)
	{	
		g.setColor(Color.yellow);
		g.drawLine(x-ca.x, y-ca.y,(x-(int)vx) -ca.x, (y-(int)vy) -ca.y);
		
	}	
	
	void draw_weapon(Camera ca, Graphics g,Image i,  Component c)
	{
		int h= d_h;
		int w= d_w;
		
		g.drawImage(i, x-ca.x, y-ca.y, x+w-ca.x, y+h-ca.y, left,top,right,bottom, c);	
		
		g.setColor(Color.GREEN);
		
		g.drawString(str, x-ca.x, y-ca.y - 12);
	}	
	//Bak sep. draw class
}

