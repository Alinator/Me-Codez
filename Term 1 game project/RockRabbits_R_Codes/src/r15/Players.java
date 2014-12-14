package r15;
//@author: Kristoffer Gustavsson	


public class Players {
	
	public Player p[];
	
	//Manage
	Players(){
		
		p = new Player[2];
	
	}
	
	
	//IN player funcs
	//update all enemies in list
	void update(double player_x, double player_y)
	{
		for(int n=0; n<p.length; n++) {
				p[n].update();
		}
		
	}

//update all enemies in list
	void multi_sample_update(WorldObject []m, WorldObject []b){
	
		for(int n=0; n<p.length; n++) {

		p[n].physics_multi_sample_mp(m);

		}
	}
}
