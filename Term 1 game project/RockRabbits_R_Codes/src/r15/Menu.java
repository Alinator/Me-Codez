package r15;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;


public class Menu {
	
	public boolean visible = true;
	Button b[];// = {mp,sp,exit};
	Marker m;// = new Marker(mp.x - 16,mp.y - 8);
	Color fill = new Color(185, 128, 0);
	Color mark = new Color(255, 185, 0);
	
	Button[] init_buttons(){	
		//0
		Button mp = new Button(500, 288, "Multiplayer");
		//1
		Button sp = new Button(500, 288+48, "Singleplayer");
		//2
		Button exit = new Button(500,288+96, "Exit");
		
		Button r[] = {mp,sp,exit};
		
		return r;
	}
	
	Menu (){
		visible = true;
		b = init_buttons();
		m = new Marker(b);
		
	}
	
	
	void update() {
		m.update(b);
		if(b[0].pushed)visible=false;
		if(b[1].pushed)visible=false;
		if(b[2].pushed)visible=false;
		
	}
	
	public boolean is_visible() {
		return visible;
	}
	
	//DRAW_FUNC

	void render(Graphics g, Image img, Component c) {
		g.drawImage(img, 0, 0, c);
	
		g.setColor(mark);
		g.fillRect(m.x, m.y, m.w, m.h);

		if(b != null) {
			//marker
		
			//bs
			for(int n = 0; n<b.length;n++) {
			
			g.setColor(fill);
			g.fillRect(b[n].x, b[n].y, b[n].w, b[n].h);
		
			g.setColor(Color.black);
			g.drawString(b[n].text, b[n].x+16, b[n].y+20);
			}
			
		g.setColor(Color.white);
		g.drawString("USE UP OR DOWN KEYS TO NAVIGATE MENU, HIT SPACE TO ACCEPT", 16, 456);
			
		}
	}
	
	/*
	//button
			
			how pushed
			
			what action
				return something?
				
	//marker//pointer
*/
}
