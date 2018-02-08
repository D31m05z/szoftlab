package Towers.Rocks;

/*
	A végtelen korsó kőtipus osztálya, amely megvalósítja a kő interface-t.
	Tartalmazza a végtelen korsó metódusait
*/

import Enemies.Enemy;
import Main.GameStatics;

public class JarRock implements IRock
{
	private static JarRock myInstance;

	private JarRock()  { }

	// Singleton osztály példányának visszaadása, ha nincs példány létrehoz, majd azt adja vissza.
	public static JarRock getInstance() 
	{  
		if(myInstance == null)
			return new JarRock();
		else
			return myInstance;
	}

	// A megadott ellenségen kifejti a kő hatását
	public void activateOn(Enemy e)
	{
		e.setJar(true);
	}

	// A megadott ellenségen deaktiválja a kő hatását
	public void deactivateOn(Enemy e)
	{
		e.setJar(false);
	}
	
	// A GameStatics osztályból elkéri a kő árát
	public long getCost()
	{
		return GameStatics.RockCosts.Jar;
	}
}
