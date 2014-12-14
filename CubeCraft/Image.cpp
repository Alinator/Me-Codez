#include "Image.h"
#include <iostream>
#include <SDL.h>
using namespace std;
namespace GameEngine{

	Image::Image(string fn,bool tp){
		fileName = fn;
		transparent = tp;

		tmpsurface = SDL_LoadBMP(fileName.c_str());
		if (tmpsurface == NULL) {
		printf("Unable to load bitmap: %s\n", SDL_GetError());
	}
		if(transparent == true){
			SDL_SetColorKey( tmpsurface, SDL_SRCCOLORKEY, SDL_MapRGB(tmpsurface->format, 0xFF, 0xFF, 0xFF) );
		}
		mainsurface = SDL_DisplayFormat(tmpsurface);

		SDL_FreeSurface(tmpsurface);

	}
	Image::Image(const Image& other):fileName(other.fileName),transparent(other.transparent),mainsurface(other.mainsurface){
		mainsurface->refcount++;
	}

	SDL_Surface* Image::getSurface() const{
		return mainsurface;
	}

	Image::~Image(void){
		if(mainsurface->refcount != NULL){
			SDL_FreeSurface(mainsurface);
			if(mainsurface->refcount == 0){
				SDL_FreeSurface(mainsurface);
			}
		}
	}

}// gameengine