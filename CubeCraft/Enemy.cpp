#include "Enemy.h"
#include "Globals.h"
#include <iostream>
#include "SDL.h"

namespace GameEngine{

	Enemy* Enemy::getInstance(int x, int y){
		return new Enemy(x,y);
	}
	
	Enemy::Enemy(int x, int y):Sprite(x,y,64,64){
		r = getRect();
		acceleration = 1;
		image = new Image("Ship.bmp",false);
	}
	SDL_Rect Enemy::getDestRect(){
		return dest;
	}
	void Enemy::draw(){
		dest.x = r.x;
		dest.y = r.y;
		dest.w = 64;
		dest.h = 64;
		SDL_BlitSurface(image->getSurface(),&r,sys.screen,&dest);
	}

	void Enemy::tick(){
		int ax = dest.x-=acceleration;
		if (ax <= 0){
			SDL_FillRect(image->getSurface(),&r,0x000000);
			dest.x = 1350; // THIS ACTUALLY WORKS XD XD XD XD hahahahah
		}
		SDL_FillRect(image->getSurface(),&r,0xffffff);

		SDL_BlitSurface(image->getSurface(),&r,sys.screen,&dest);
		//acceleration+=0.0001;
	}

	void Enemy::dead(){
		SDL_FillRect(image->getSurface(),&r,0x000000);

		//generate new rectangles
		dest.x=1400;
		SDL_FillRect(image->getSurface(),&r,0xffffff);
		SDL_BlitSurface(image->getSurface(),&r,sys.screen,&dest);
		SDL_UpdateRect(sys.screen,0,0,0,0);
	}
	void Enemy::Collision(Sprite* sprite){
	int left1 = dest.x;
	int left2 = sprite->getDestRect().x;

	int right1 = dest.x + dest.w;
	int right2 = sprite->getDestRect().x + sprite->getDestRect().w;

	int top1 = dest.y;
	int top2 = sprite->getDestRect().y;

	int bottom1 = dest.y + dest.h;
	int bottom2 = sprite->getDestRect().y + sprite->getDestRect().h;
	
	if(bottom1 > top2 && top1 < bottom2 && right1 > left2 && left1 < right2){
		sprite->dead();
		dead();
	}
	}
	Enemy::~Enemy(void){
		delete image;
	}
}//gameengine