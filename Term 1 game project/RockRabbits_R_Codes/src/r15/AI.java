package r15;

public class AI {

	//parameters
	int health;
	
	//actions
	boolean turned_right;
	boolean turned_left;
	boolean go_left;
	boolean go_right;
	boolean alerted;
	boolean jump;
	boolean attack;
	
	double x, y; //might notbe needed
	
	//skillcontrol

	//skills
	boolean discover(double player_x, double player_y){
		
		int viewy = 128; //could make hit_polygon as a viewfield
		
		//check in y range
		if(y < (player_y + viewy) && y > (player_y-viewy)) {
			//if so check if in x range & turned the right way
			if(turned_right && x < player_x) {
				return true;
			}
			if(turned_left && x > player_x) {
				return true;
			}
		}
		return false;

	}
		
	
	void patrol(){}
	
	void keep_distance(double player_x, double player_y){ //arg distance here
		
		int distance = 200;
		
		
		if(x<player_x)distance*=-1; 
			
		if(x>player_x+distance) {go_left=true; turned_left = true;}
		if(x<player_x+distance){go_right=true; turned_right = true;}
	}

	void hunt(double px, double py){
		
		if(y>py)jump=true; 
		
		if(x>px) {go_left=true; turned_left = true;}	else go_left=false;
		if(x<px) {go_right=true;turned_right = true;}	else go_right=false;
		
		//if(obstacle)jump; //hm obstacle avoidance.. hmmm... later
		
	}
	
	void attack(){
		
		//attac player?
		
	}	
	void stop_attack(){
		
		//should be avoidable, but damage more
		
		//vx=0;vy=0;
		//and a timer... framecounter;
		attack();
		
	}	
	void take_cover(){}
	
	void flee(double player_x, double player_y){
		
		if(x>player_x)go_right=true;
		else if(x<player_x)go_left=true;
		
	}
	/*
	void dodge(){
		
		if(player.shoot)flee();
		
	}
	
	void evade_incoming_missile();
	*/
	void hold(){
		
		go_left=false;
		go_right=false;
		
	}
	
	//in enemy x & y is fixed, so use that for xy
	void update(double player_x, double player_y ){
		
		//action
		if(!alerted) {
		alerted = discover(player_x, player_y);
		}
		
		else{
			hunt(player_x, player_y);
		}
	}
	
	//construct
	AI( double dx, double dy){
		
		x=dx; y=dy;
		
		//actions
		 turned_right = false;
		 turned_left = false;
		 go_left = false;
		 go_right = false;
		 alerted = false;
		 jump = false;
		 attack = false;
		
	}


}
