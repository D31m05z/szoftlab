package Towers.Rocks;

/*
	Az Ezer sárkány dühe kőtipus osztálya, amely megvalósítja a kő interface-t.
	Tartalmazza az ezer sárkány dühe metódusait
*/

import Enemies.Enemy;
import Main.GameStatics;

public class DragonRock implements IRock
{
	private static DragonRock myInstance;
	
	private DragonRock()  { }
	
	// Singleton osztály példányának visszaadása, ha nincs példány létrehoz, majd azt adja vissza.
	public static DragonRock getInstance() 
	{  
		if(myInstance == null)
			return new DragonRock();
		else
			return myInstance;
	}
	
	// A megadott ellenségen kifejti a kő hatását
	public void activateOn(Enemy e) 
	{
		e.setDragon(true);
	}
	
	// A megadott ellenségen deaktiválja a kő hatását
	public void deactivateOn(Enemy e) 
	{
		e.setDragon(false);
	}
	
	// A GameStatics osztályból elkéri a kő árát
	public long getCost()
	{
		return GameStatics.RockCosts.Dragon;
	}
}
