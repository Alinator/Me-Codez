#include "Ship.h"
#include "Globals.h"
#include <iostream>
#include "SDL.h"
#include "Bullet.h"
#include "Image.h"

namespace GameEngine{

	Ship* Ship::getInstance(int xx,int yy){ 
		return new Ship(xx,yy);
	}

	Ship::Ship(int x, int y):Sprite(x,y,64,64){
		r = getRect();
		b[0]=0;
		b[1]=0;
		b[2]=0;
		b[3]=0;
		b[4]=0;

		img = new Image("Ship.bmp",true);
	}

	void Ship::draw(){
		dest.x = r.x;
		dest.y = r.y;
		dest.w = 64;
		dest.h = 64;
		SDL_BlitSurface(img->getSurface(),&r,sys.screen,&dest);
	}
	void Ship::dead(){}

	SDL_Rect Ship::getDestRect(){
		return dest;
	}
	void Ship::Shoot(){
		 
	int bnr = 0;
	if(sys.bulletCounter == 0){
		bnr=0;
	}else{
		bnr= sys.bulletCounter;
		sys.bulletCounter++;
	}
	Sprite* bullet = Bullet::getInstance(dest.x+67,dest.y,bnr);
	sys.engine->addBullet(bullet);
	}
	void Ship::tick(){ 
		if(b[0]){
			dest.x++;
		}
		if(b[1]){
			dest.x--;
		}
		if(b[2]){
			dest.y--;
		}
		if(b[3]){
			dest.y++;
		}
		if(b[4]){
			Shoot();
		} 
		SDL_BlitSurface(img->getSurface(),&r,sys.screen,&dest);
	}
	void Ship::keyDown(SDL_Event k){

		switch(k.key.keysym.sym){
		case SDLK_RIGHT:
			b[0]=1;
			break;
		case SDLK_LEFT:
			b[1]=1;
			break;
		case SDLK_UP:
			b[2]=1;
			break;
		case SDLK_DOWN:
			b[3]=1;
			break;
		case SDLK_SPACE:
			b[4]=1;
			break;
		}
	}
	void Ship::keyUp(SDL_Event k){
		switch(k.key.keysym.sym){
		case SDLK_RIGHT:
			b[0]=0;
			break;
		case SDLK_LEFT:
			b[1]=0;
			break;
		case SDLK_UP:
			b[2]=0;
			break;
		case SDLK_DOWN:
			b[3]=0;
			break;
		case SDLK_SPACE:
			b[4]=0;
			break;
		}
	}
	void Ship::Collision(Sprite* sprite){
		if(r.Collides(sprite->getRect())){
			std::cout<<"hit!"<<::endl; 
		}
	}
	Ship::~Ship(void){
		delete sys.engine;
		delete img;
	}

}// gameengine