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
	//när vi vill ha ett globalt object, så att inte alla klasser som includerar Globals får varsitt global object lägger vi extern
	// framför denna object definitionen.
	extern Globals sys;
}


#endif