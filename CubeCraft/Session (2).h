#ifndef GAMEENGINE_H
#define GAMEENGINE_H
#include <SDL.h>
class GameEngine{
public:
	bool running;
	GameEngine();
	~GameEngine();
	void keyboard();
	void spelLoop();
	SDL_Event event;
private:
	float x;
	float y;
	//keyboard buffer
	bool keyboardbuffer[256];

	//JUMPING
	float jumpvel;
	float acceleration;
	bool jumping;
	float gravity;
	bool keyPressed;
};
#endif