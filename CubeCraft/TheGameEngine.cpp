#include "TheGameEngine.h"
#include "Sprite.h"
#include "Globals.h"
#include <iostream>
#include <SDL.h>


#define FPS 60 // frames per second
namespace GameEngine{

	TheGameEngine::TheGameEngine(){
	t = false;
	}
	// för att adda in ny sprite i sprites vectorn
	void TheGameEngine::addBackgroundSprite(Sprite* sprite){
		backgroundsprite.push_back(sprite);
	}
	void TheGameEngine::addSprites(Sprite* sprite){
		sprites.push_back(sprite);
	}
	void TheGameEngine::addBullet(Sprite* sprite){
		bulletSprites.push_back(sprite);
	}
	void TheGameEngine::addEnemy(Sprite* enemy){
		enemysprites.push_back(enemy);
	}

	void TheGameEngine::removeBulletSprite(int bulletnumber){
		if (bulletnumber < 0 || bulletnumber >= bulletSprites.size()) return;
		// because we have a vector of pointers, we will have to delete the pointer
		delete bulletSprites[bulletnumber];
		// then we delete it from the vector
		bulletSprites.erase(bulletSprites.begin()+bulletnumber);
	}
	void TheGameEngine::setTickInterval(int mseconds){
		tickinterval = mseconds / FPS;
	}

	// denna metoden innehåller spelloopen och kör igång spelet
	void TheGameEngine::run(){
		//rita ut alla sprites innan game loopen startar

	for(int n=0; n < backgroundsprite.size(); n++){
		backgroundsprite[n]->draw();	
		}
	for(int i=0; i< sprites.size(); i++){
		sprites[i]->draw();
	}
	for(int x=0; x < enemysprites.size(); x++){
		enemysprites[x]->draw();
	}
	bool quit = false;

	while(quit == false){
	nextTick = SDL_GetTicks() + tickinterval;
	SDL_Event event;
		while(SDL_PollEvent(&event)){
			switch(event.type){
			
			case SDL_QUIT:
				quit = true;
				break;
			case SDL_KEYDOWN:
				
				for(unsigned int x=0; x < sprites.size(); x++){
					sprites[x]->keyDown(event);
				}
				break;
			case SDL_KEYUP:
				for(unsigned int x=0; x < sprites.size(); x++){
					sprites[x]->keyUp(event);
				}
			}
		}// end while

		delay = nextTick - SDL_GetTicks();
		if(delay > 0){
			SDL_Delay(delay);
		}
		// gå igenom alla installerade sprite object och utropa dess tick function.
		for(int n=0; n < backgroundsprite.size(); n++){
			backgroundsprite[n]->tick();
			}
		for(int x=0; x < sprites.size(); x++){
			sprites[x]->tick();
		}
		for(int x=0; x < enemysprites.size(); x++){
			enemysprites[x]->tick();
		}
		// gå igenom alla installerade bullet object.
		for(int y=0; y < bulletSprites.size(); y++){
			if(bulletSprites.size() != 0){
				bulletSprites[y]->tick();
			}
		}
		// för collision detection
		for(int x=0; x < enemysprites.size();x++){
			for(int y=0; y < bulletSprites.size();y++){
				enemysprites[x]->Collision(bulletSprites[y]);
			}
		}
		// updatera de nuvarande spritesen efter eventuell event
		SDL_Flip(sys.screen);
	}
	}
	TheGameEngine::~TheGameEngine(void){
	SDL_FreeSurface(sys.screen);
	SDL_Quit();
	}

}//namespace gameengine