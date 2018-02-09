package Towers.Rocks;

/*
	Szauron szeme ktipus osztlya, amely megvalstja a k interface-t.
	Tartalmazza a Szauron szeme metdusait
*/

import Enemies.Enemy;
import Main.GameStatics;

public class FateRock implements IRock
{
	private static FateRock myInstance;
	  
	private FateRock()  { }
	
	// Singleton osztly pldnynak visszaadsa, ha nincs pldny ltrehoz, majd azt adja vissza.
	public static FateRock getInstance() 
	{  
		if(myInstance == null)
			return new FateRock();
		else
			return myInstance;
	}

	// A megadott ellensgen kifejti a k hatst
	public void activateOn(Enemy e) 
	{	
		e.setFate(true);
	}

	// A megadott ellensgen deaktivlja a k hatst
	public void deactivateOn(Enemy e) 
	{
		e.setFate(false);
	}
	
	// A GameStatics osztlybl elkri a k rt
	public long getCost()
	{
		return GameStatics.RockCosts.Fate;
	}
}
