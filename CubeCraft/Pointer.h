#ifndef POINTER_H
#define POINTER_H

#include "Sprite.h"
namespace GameEngine{
class Pointer{

public:
	Pointer():ptr(0),countPtr(0){}
	Pointer(Sprite* sprite):ptr(sprite), countPtr(new int(1)){}

	//copy konstruktor, f�r v�rdesemantik, allts� 
	// Point p1;
	// p1 = p2;
	Pointer(const Pointer& other):ptr(other.ptr),countPtr(other.countPtr){
		if(countPtr != NULL)
			++*countPtr;
	}
	// f�r att klara av p1 = p2;
	const Pointer& operator=(const Pointer& other){

		if(ptr != other.ptr){
			if(countPtr != NULL){
			//pga av att - och * �r tv� un�ra operationer s� m�ste man g�ra s� h�r. alternativt (*countptr)--;
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