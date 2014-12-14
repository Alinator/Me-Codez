#ifndef SPRITE_H
#define SPRITE_H
#include "Rectangle.h"

namespace GameEngine{
class Sprite{
public:
	Sprite(int x, int y, int w, int h);
	virtual void draw(); //= 0;
	virtual void tick();
	Rectangle& getRect();
	virtual void keyDown(SDL_Event k);
	virtual void keyUp(SDL_Event k);
	virtual void Collision(Sprite* sprite);
	virtual void dead();
	virtual SDL_Rect getDestRect();
	virtual ~Sprite(void);
private:
	Sprite(const Sprite&);
	const Sprite& operator=(const Sprite&);
	Rectangle rect;
	SDL_Rect dest;
};
}//namespace


#endif