#include "Background.h"
#include "Globals.h"
#include <iostream>
#include "SDL.h"

namespace GameEngine{

	Background *Background::getInstance(int xx, int yy){
		return new Background(xx,yy);
	}

	Background::Background(int x ,int y):Sprite(x,y,1400,600){
		r = getRect();
	}

	void Background::draw(){
		background = SDL_LoadBMP("space.bmp");
		image = SDL_DisplayFormat(background);
		SDL_FreeSurface(background);
		dest.x = r.x;
		dest.y = r.y;
		dest.w = 1440;
		dest.h = 720;
		SDL_BlitSurface(image,&r,sys.screen,&dest);
	}

	void Background::tick(){
		SDL_BlitSurface(image,&r,sys.screen,&dest);
		SDL_UpdateRect(background,0,0,0,0);
	}

		Background::~Background(void){
		SDL_FreeSurface(background);
	}
}//GameEngine namespace

