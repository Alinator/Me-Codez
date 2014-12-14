#ifndef GLOBAL_H
#define GLOBALS_H
#include <SDL.h>
#include "TheGameEngine.h"

namespace GameEngine{
	class Globals{
	public:
	Globals();
	~Globals(void);
	SDL_Surface* screen;
	TheGameEngine* engine;
	int bulletCounter;
	private:
	};
	//n�r vi vill ha ett globalt object, s� att inte alla klasser som includerar Globals f�r varsitt global object l�gger vi extern
	// framf�r denna object definitionen.
	extern Globals sys;
}


#endif