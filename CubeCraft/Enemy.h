#ifndef ENEMY_H
#define ENEMY_H
#include <iostream>
#include "Sprite.h"
#include "Image.h"
namespace GameEngine{
class Enemy : public Sprite{
public:
	static Enemy* getInstance(int x,int y);
	~Enemy();
	void dead();
	void draw();
	void tick();
	void Collision(Sprite* sprite);
	Rectangle r;
	Uint32 red;
	Image* image;
protected:
	Enemy(int xx, int yy);
private:
	SDL_Surface* enemy;
	SDL_Rect dest;
	float acceleration;
	SDL_Rect getDestRect();
};
}
#endif 