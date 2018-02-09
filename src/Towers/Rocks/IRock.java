package Towers.Rocks;

/*
	A kvek	interfsze, ezen keresztl ltjk a kvek fggvnyeit
*/

import Enemies.Enemy;

public interface IRock 
{
	// A megadott ellensgen kifejti a k hatst
	public void activateOn(Enemy e);
	
	// A megadott ellensgen deaktivlja a k hatst
	public void deactivateOn(Enemy e);
	
	// A GameStatics osztlybl elkri a k rt
	public long getCost();
}