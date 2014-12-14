package r15;
//Map.java
//@author: Kristoffer, Ali Nazar, Neda, Li, Lou, Gokul  

/*Note
 * When you make your maps, dont put moving tiles so that they can sqeeze the player. 
 * other words, always leave 32pix of empty space between the moving tiles "end stations".
 * Or we will have a crazy bounding box bugg when enemies or the player gets in the way
 */

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

//mote

public class Map {
	
	private Tile map[];
	public Tile tiles[];

	
	Map(int l){
		
		if(l==0) {
		map = new Tile[19];
		
		//BOX left
		map[0] = new Tile(-240,	-240,		240,	0);
		map[1] = new Tile(-240,	0,			240,	0);
		map[2] = new Tile(-240,	240,		240,	0);
		map[3] = new Tile(-240,	480,		240,	0);
		
		//BOX right
		map[4] = new Tile(1088,	-240,		240,	0);
		map[5] = new Tile(1088,	0,			240,	0);
		map[6] = new Tile(1088,	240,		240,	0);
		map[7] = new Tile(1088,	480,		240,	0);
		
		//BASE PLATFORMS
		map[8] 	= new Tile(0,	448,	320,	0);
		map[9] 	= new Tile(384,	448,	320,	0);
		map[10] = new Tile(768,	448,	320,	0);
		
		map[18] = new Tile(320,	448,	64,	0);
		map[17] = new Tile(704,	448,	64,	0);
		
		
		//AIR PLATFORMS
		map[11] 	= new Tile(128,		320,	64,	0);
		map[12] 	= new Tile(128,		384,	64,	0);
		map[13] 	= new Tile(1024,	320,	64,	0);
		map[14] 	= new Tile(1024,	384,	64,	0);

		map[15] 	= new Tile(448,	128, 64, 256,	2);

		map[16] 	= new Tile(320,	128, 64, 256,	3);
		}
		
		else if(l==1) {
			
			map = new Tile[22];
			
			//BOX left
			map[0] = new Tile(-240,	-240,		240,	0);
			map[1] = new Tile(-240,	0,			240,	0);
			map[2] = new Tile(-240,	240,		240,	0);
			map[3] = new Tile(-240,	480,		240,	0);
			
			//BOX right
			map[4] = new Tile(1088,	-240,		240,	0);
			map[5] = new Tile(1088,	0,			240,	0);
			map[6] = new Tile(1088,	240,		240,	0);
			map[7] = new Tile(1088,	480,		240,	0);
			
			//BASE PLATFORMS
			map[8] 	= new Tile(0,	448,	320,	0);
			map[9] 	= new Tile(384,	448,	320,	0);
			map[10] = new Tile(768,	448,	320,	0);
			
			//AIR PLATFORMS
			map[11] 	= new Tile(128,		320,	64,	0);
			map[12] 	= new Tile(128,		384,	64,	0);
			map[13] 	= new Tile(1024,	320,	64,	0);
			map[14] 	= new Tile(1024,	384,	64,	0);

			map[15] 	= new Tile(448,	128, 64, 256,	2);

			map[16] 	= new Tile(320,	128, 64, 256,	3);
			
			map[17]=new Tile(230,110,55,225,4);
			map[18]=new Tile(590,200,80,340,5);
			map[19]=new Tile(450,250,60,600,4);	
		

			map[20] = new Tile(320,	512,	64,	0);
			map[21] = new Tile(704,	512,	64,	0);
			
		}
		
		
		tiles = map;
		
	}

		
	
	
	void multi_sample_update() { 
		
		for (int n=0; n< tiles.length ; n++){
			
			if(tiles[n].type != 0)tiles[n].ms_pos_update();
		
		}
	}
	
	void update(){
		
		for (int n=0; n< tiles.length ; n++){
			
			tiles[n].update();

		}	
	};

	void draw(Camera cam, Graphics g, Image i, Component c) {
	
		//Color tilec = new Color(8,32,32);
		
		//g.setColor(tilec);
		for (int n=0; n	< tiles.length; n++){
			
			if(cam.on_camera(tiles[n].ix, tiles[n].iy, tiles[n].w)) {
			}tiles[n].a.draw_no_krull(cam,g, i, c);

		}
		
	}	
}

