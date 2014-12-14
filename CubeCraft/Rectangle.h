#ifndef RECTANGLE_H
#define RECTANGLE_H

#include <SDL.h>

namespace GameEngine{

struct Rectangle : public SDL_Rect{

Rectangle();
Rectangle(int xx,int yy,int ww,int hh);
bool contains(int xx, int yy) const;
Rectangle centeredRect(int width, int height) const;
void movement(int x, int y);
bool Collides(const Rectangle& other) const;
	};
}
#endif 