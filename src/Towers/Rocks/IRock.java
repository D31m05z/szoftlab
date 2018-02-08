package Towers.Rocks;

/*
	A kövek	interfésze, ezen keresztül látják a kövek függvényeit
*/

import Enemies.Enemy;

public interface IRock 
{
	// A megadott ellenségen kifejti a kő hatását
	public void activateOn(Enemy e);
	
	// A megadott ellenségen deaktiválja a kő hatását
	public void deactivateOn(Enemy e);
	
	// A GameStatics osztályból elkéri a kő árát
	public long getCost();
}