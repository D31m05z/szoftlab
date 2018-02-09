package Towers.Rocks;

/*
	Az Ezer srkny dhe ktipus osztlya, amely megvalstja a k interface-t.
	Tartalmazza az ezer srkny dhe metdusait
*/

import Enemies.Enemy;
import Main.GameStatics;

public class DragonRock implements IRock
{
	private static DragonRock myInstance;
	
	private DragonRock()  { }
	
	// Singleton osztly pldnynak visszaadsa, ha nincs pldny ltrehoz, majd azt adja vissza.
	public static DragonRock getInstance() 
	{  
		if(myInstance == null)
			return new DragonRock();
		else
			return myInstance;
	}
	
	// A megadott ellensgen kifejti a k hatst
	public void activateOn(Enemy e) 
	{
		e.setDragon(true);
	}
	
	// A megadott ellensgen deaktivlja a k hatst
	public void deactivateOn(Enemy e) 
	{
		e.setDragon(false);
	}
	
	// A GameStatics osztlybl elkri a k rt
	public long getCost()
	{
		return GameStatics.RockCosts.Dragon;
	}
}
