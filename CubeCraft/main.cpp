#include "Sprite.h"
#include "Pointer.h"
#include "Enemy.h"
#include "Background.h"
#include "Ship.h"
#include "TheGameEngine.h"
#include "Bullet.h"
#include "Globals.h"

using namespace GameEngine;

int main(int argc, char** argv){
//FPS delayer
sys.engine->setTickInterval(100);

// created sprites
Sprite* background = Background::getInstance(0,0);

//main ship
Sprite* ship = Ship::getInstance(0,250);
//enemies
Sprite* enemy1 = Enemy::getInstance(1300,50);
Sprite* enemy2 = Enemy::getInstance(1290,200);
Sprite* enemy3 = Enemy::getInstance(1350,300);
Sprite* enemy4 = Enemy::getInstance(1200,400);
Sprite* enemy5 = Enemy::getInstance(1111,500);

// add Sprite to pointercounter
Pointer p1(enemy1);
Pointer p2(enemy2);
Pointer p3(enemy3);
Pointer p4(enemy4);
Pointer p5(background);
Pointer p6(ship);
Pointer p7(enemy5);

// add sprites alltså installera spritesen
sys.engine->addBackgroundSprite(background);
sys.engine->addSprites(ship);
sys.engine->addEnemy(enemy1);
sys.engine->addEnemy(enemy2);
sys.engine->addEnemy(enemy3);
sys.engine->addEnemy(enemy4);
sys.engine->addEnemy(enemy5);
//start the engine........... run metoden kommer köra igång gameLoopen i GameEngine klassen
sys.engine->run();
return 0;
}