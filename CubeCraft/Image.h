#ifndef IMAGE_H
#define IMAGE_H
#include <SDL.h>
#include <string>
using namespace std;

namespace GameEngine{

	class Image{
	public:
		Image();
		Image(string fn,bool tp);
		SDL_Surface* getSurface() const;
		Image(const Image& other);

		const Image& operator=(const Image& other){
			if(fileName != other.fileName){
				if(mainsurface->refcount != NULL){
					SDL_FreeSurface(mainsurface);
					if(mainsurface->refcount == 0){
					SDL_FreeSurface(mainsurface);
					}
				}
			}

			fileName = other.fileName;
			mainsurface = other.mainsurface;
			if(mainsurface->refcount != NULL){
				mainsurface->refcount++;
			}
			return *this;
		}
		~Image(void);
	private:
		string fileName;
		SDL_Surface* tmpsurface;
		SDL_Surface* mainsurface;
		bool transparent;
	};
}//gameengine

#endif