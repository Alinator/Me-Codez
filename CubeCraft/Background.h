#ifndef BACKGROUND_H
#define BACKGROUND_H
#include "Sprite.h"

namespace GameEngine{
class Background : public Sprite{
public:
	static Background* getInstance(int x, int y);
	~Background(void);
	void draw();
	void tick();
	Rectangle r;
protected:
	Background(int xx, int yy);

private:
	SDL_Surface* background;
	SDL_Surface* image;
	SDL_Rect dest;
};
}
#endif
