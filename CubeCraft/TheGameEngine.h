#ifndef SESSION_H
#define SESSION_H


#include <vector>
#include "Sprite.h"

namespace GameEngine{
	class TheGameEngine{
		public:
			TheGameEngine();
			void addBackgroundSprite(Sprite* sprite);
			void addSprites(Sprite* sprite);
			void addBullet(Sprite* sprite);
			void addEnemy(Sprite* enemy);
			void run();
			void setTickInterval(int sec);
			void removeBulletSprite(int number);
			~TheGameEngine();
		private:
			std::vector<Sprite*> sprites;
			std::vector<Sprite*> bulletSprites;
			std::vector<Sprite*> enemysprites;
			std::vector<Sprite*> backgroundsprite;
			// tickvariables för att kunna sänka hastigheten av gameloopen
			int tickinterval;
			Uint32 nextTick;
			int delay;
			bool t;
			
	};
}// namespace

#endif