package Towers.Rocks;

/*
	A vgtelen kors ktipus osztlya, amely megvalstja a k interface-t.
	Tartalmazza a vgtelen kors metdusait
*/

import Enemies.Enemy;
import Main.GameStatics;

public class JarRock implements IRock
{
	private static JarRock myInstance;

	private JarRock()  { }

	// Singleton osztly pldnynak visszaadsa, ha nincs pldny ltrehoz, majd azt adja vissza.
	public static JarRock getInstance() 
	{  
		if(myInstance == null)
			return new JarRock();
		else
			return myInstance;
	}

	// A megadott ellensgen kifejti a k hatst
	public void activateOn(Enemy e)
	{
		e.setJar(true);
	}

	// A megadott ellensgen deaktivlja a k hatst
	public void deactivateOn(Enemy e)
	{
		e.setJar(false);
	}
	
	// A GameStatics osztlybl elkri a k rt
	public long getCost()
	{
		return GameStatics.RockCosts.Jar;
	}
}
