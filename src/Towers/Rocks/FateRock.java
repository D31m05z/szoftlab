package Towers.Rocks;

/*
	Szauron szeme kőtipus osztálya, amely megvalósítja a kő interface-t.
	Tartalmazza a Szauron szeme metódusait
*/

import Enemies.Enemy;
import Main.GameStatics;

public class FateRock implements IRock
{
	private static FateRock myInstance;
	  
	private FateRock()  { }
	
	// Singleton osztály példányának visszaadása, ha nincs példány létrehoz, majd azt adja vissza.
	public static FateRock getInstance() 
	{  
		if(myInstance == null)
			return new FateRock();
		else
			return myInstance;
	}

	// A megadott ellenségen kifejti a kő hatását
	public void activateOn(Enemy e) 
	{	
		e.setFate(true);
	}

	// A megadott ellenségen deaktiválja a kő hatását
	public void deactivateOn(Enemy e) 
	{
		e.setFate(false);
	}
	
	// A GameStatics osztályból elkéri a kő árát
	public long getCost()
	{
		return GameStatics.RockCosts.Fate;
	}
}
