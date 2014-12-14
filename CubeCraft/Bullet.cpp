#include "Bullet.h"
#include "Globals.h"
#include <iostream>
#include "SDL.h"
#include "Ship.h"

namespace GameEngine{

	Bullet* Bullet::getInstance(int xx, int yy, int number){

		return new Bullet(xx,yy,number);
	}

	Bullet::Bullet(int x , int y,int number):Sprite(x,y,20,5){
		bulletNumber = number;
		r = getRect();
		
		image = new Image("Ship.bmp",false);
		dest.x= r.x;
		dest.y= r.y;
		dest.w= 20;
		dest.h= 5;
		SDL_BlitSurface(image->getSurface(),&r,sys.screen,&dest);
	}
	SDL_Rect Bullet::getDestRect(){
		return dest;
	}
	void Bullet::draw(){
	}
	void Bullet::dead(){
		sys.engine->removeBulletSprite(bulletNumber);
		if(sys.bulletCounter != 0){
		sys.bulletCounter--;
		}
	}
	void Bullet::tick(){
		int ax = dest.x+=3;
		if (ax >= 1300){
			dead();
		}else{
		SDL_FillRect(image->getSurface(),&r,0xf5f5fff);
		SDL_BlitSurface(image->getSurface(),&r,sys.screen,&dest);
		}
	}
	void Bullet::Collision(Sprite* sprite){
		
	}
	Bullet::~Bullet(void){
	delete image;
	}
}//gameengine