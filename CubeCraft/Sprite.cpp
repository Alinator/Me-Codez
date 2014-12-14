#include "Sprite.h"
#include "Globals.h"
#include <SDL.h>

namespace GameEngine{
Sprite::Sprite(int x, int y, int w, int h):
rect(x,y,w,h){}
	

Rectangle& Sprite::getRect(){
		return rect;
}
	//denna metoden ska skapa olika typer av sprites och rita ut dom.
	void Sprite::draw(){
	}//= 0;
		void Sprite::Collision(Sprite* sprite){}
	void Sprite::tick(){}
	void Sprite::keyDown(SDL_Event k){}
	void Sprite::keyUp(SDL_Event k){}
	void Sprite::dead(){}
	SDL_Rect Sprite::getDestRect(){
	return dest;
	}
	Sprite::~Sprite(void){}

}//namespacee