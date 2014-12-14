#ifndef SHIP_H
#define SHIP_H
#include "Sprite.h"
#include "Image.h"

namespace GameEngine{
class Ship : public Sprite{
public:
	static Ship* getInstance(int xx, int yy);
	~Ship(void);
	void draw();
	void dead();
	void keyDown(SDL_Event k);
	void keyUp(SDL_Event k);
	void tick();
	void Collision(Sprite* sprite);
	Rectangle r;
	Image* img;
protected:
	Ship(int x, int y);
private:
	SDL_Surface* ship;
	SDL_Rect dest;
	SDL_Rect bulletDest;
	void Shoot();
	bool b[5];
	SDL_Rect getDestRect();
};

}//gameengine
#endif