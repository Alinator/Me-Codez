#include "Globals.h"

namespace GameEngine{

	Globals::Globals(){

	SDL_Init(SDL_INIT_EVERYTHING);
	screen = SDL_SetVideoMode(1400,600,32,SDL_SWSURFACE|SDL_DOUBLEBUF);
	engine = new TheGameEngine();
	bulletCounter=0;
	freopen( "CON", "w", stdout );
	freopen( "CON", "w", stderr );
	}
	Globals::~Globals(void){
		SDL_Quit();
	}

	Globals sys;

}//namespace