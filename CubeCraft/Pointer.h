#ifndef POINTER_H
#define POINTER_H

#include "Sprite.h"
namespace GameEngine{
class Pointer{

public:
	Pointer():ptr(0),countPtr(0){}
	Pointer(Sprite* sprite):ptr(sprite), countPtr(new int(1)){}

	//copy konstruktor, för värdesemantik, alltså 
	// Point p1;
	// p1 = p2;
	Pointer(const Pointer& other):ptr(other.ptr),countPtr(other.countPtr){
		if(countPtr != NULL)
			++*countPtr;
	}
	// för att klara av p1 = p2;
	const Pointer& operator=(const Pointer& other){

		if(ptr != other.ptr){
			if(countPtr != NULL){
			//pga av att - och * är två unära operationer så måste man göra så här. alternativt (*countptr)--;
			--*countPtr;
			if(countPtr == 0){
				delete ptr;
				delete countPtr;
			}
		}
		ptr = other.ptr;
		countPtr = other.countPtr;
		if(countPtr != NULL)
			++*countPtr;
		}
		return *this;
	}

	const Sprite& operator*()const { return *ptr; }
	const Sprite* operator->() const{ return ptr;}
	Sprite& operator*(){ return *ptr; }
	Sprite* operator->() { return ptr;}

	// converterings operator

	~Pointer(){

		if(countPtr != NULL){
		--*countPtr;
		if(countPtr == 0){
			delete ptr;
			delete countPtr;
		}
	}
	}
private:
	Sprite* ptr;
	int* countPtr;
};
}//namespace

#endif