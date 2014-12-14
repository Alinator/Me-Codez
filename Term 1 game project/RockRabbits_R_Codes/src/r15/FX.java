package r15;
//FX.java
//author: Kristoffer Gustavsson
public class FX {

	Animation a;
	
	
	int w, h, flash_time, frame_counter,frames;
	int direction;
	boolean flash;
	
	void flash(int ix, int iy, boolean bf) {
		
		a.x=ix;		a.y=iy;
		a.flip=bf; 
	
	flash = true;
	frame_counter = flash_time;
	a.this_frame=0;
	
	}
	
	void update() {
		if(flash) {
			
			a.update();
	
			if(a.this_frame==frames)flash = false;
		}
	}
	
	void rotate() {
		
		//can't rotate in AWT?????? 
		//theta (angle) sin () 
		
	}
	
	FX(int iw, int ih, int iframes, int delay){
		
		frames = iframes;
		w=iw; h = ih;
		a = new Animation(w,h,frames, delay);
		a.animation=0;
	}
	
	
	
	
	
	
}
