package r15;
public class Camera {

	public int x, y, w, h;
	double vx, vy;
	int margin=64;
	private int map_height = 480;
	private int map_width = 1088;
	
	
	void update(int px,int py){
		
		x	=  px-w/2 + 16;
		y	=  py-h+ 256;
		
		//stop cam if outside bounds
		if(x<0-margin) 			{ x	=	0-margin; 	return;}
		if(y<0-margin) 			{ y	=	0-margin; 	return;}
		if(x+w>map_width+margin) 	{ x	=	map_width-w+margin; return;}
		if(y+h>map_height+margin ) { y	=	map_height-h+margin; return;}
	
	}
	

	void update_mp(int px,int px2){
		 
		x = ((px-w/2 + 16) + (px2-w/2 + 16))/2; 
		
		//stop cam if outside bounds
		if(x<0-margin) 			{ x	=	0-margin; 	return;}
		if(y<0-margin) 			{ y	=	0-margin; 	return;}
		if(x+w>map_width+margin) 	{ x	=	map_width-w+margin; return;}
		if(y+h>map_height+margin ) { y	=	map_height-h+margin; return;}
	
	}
	
	
	void camera_background() {}
	
	public boolean on_camera(int ox, int oy, int ow) {
		
		if(ox+ow < x)return false;
		if(ox > x+w)return false;
		
		//y unneeded for now...
		
		
		return true;
	}
	
	void soft_update(int px,int py) {

		int dx = 	px-w/2; 
		int dy = 	py-h+128; 
		 
		//update vx
			vx 	= 	(dx-x)*0.1;
		//update vy	
			vy 	=  	(dy-y)*0.1;		
		
		//update x & y
		x	+=	vx;
		y	+=  vy;
		
		//stop cam if outside bounds
		if(x<-margin)				x	=	-margin; 
		if(x+w>map_width+margin)	x	=	map_width-w+margin; 	
		if(y<-margin)				y	=	-margin; 				
		if(y+h>map_height+margin )	y	=	map_height-h+margin; 	
		
	}


	void soft_update_mp(int px, int py, int px2, int py2) {
		
			int dx = (px + px2)/2 - w/2 + 16; 
			int dy = 	(py + py2)/2 -h + 128; 
		 
			//update vx
			vx 	= 	(dx-x)*0.1;
			//update vy	
			vy 	=  	(dy-y)*0.1;		
		
		//update x & y
		x	+=	vx;
		y	+=  vy;
		
		//stop cam if outside bounds
		if(x<-margin)				x	=	-margin; 
		if(x+w>map_width+margin)	x	=	map_width-w+margin; 	
		if(y<-margin)				y	=	-margin; 				
		if(y+h>map_height+margin )	y	=	map_height-h+margin; 	
		
	}

	
	
	Camera(int px, int py){
		
		x 	= 	px;
		y	=	py;
		
		w = Main.native_width;
		h = Main.native_height;
		
	}
	
}
//