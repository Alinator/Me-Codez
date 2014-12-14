#ifndef BULLET_H
#define BULLET_H
#include "Sprite.h"
#include "Image.h"

namespace GameEngine{
class Bullet : public Sprite{
public:
	static Bullet* getInstance(int xx, int yy,int number);
	~Bullet(void);
	void draw();
	void tick();
	void dead();
	Rectangle r;
	Image* image;
	void Collision(Sprite* sprite);
protected:
	Bullet(int x, int y,int number);
private:
	SDL_Surface* bullet;
	SDL_Rect dest;
	int bulletNumber;
	SDL_Rect getDestRect();
	
};
}// namespace gameengine
#endif 