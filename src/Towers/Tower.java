package Towers;

/*
	Torony ősosztálya, amely megvalósítja az ITower interfacet. 
	Ezen osztály tartalmazza a kövek közös tulajdonságait, alapvető függvénymegvalósításokat.
*/

import java.awt.Point;
import java.util.Collection;

import Enemies.IEnemy;
import Main.GameStatics;
import Main.IWorld;
import Towers.Rocks.IRock;

public abstract class Tower implements ITower
{
	protected Point location;
	protected int state = 0;
	protected IRock myRock;
	protected IWorld myWorld;
	protected long time;
	protected IEnemy lastChoosed = null;
	
	public abstract long getRange();
	public abstract long getDamage();
	public abstract long getFireRate();	
	public abstract long upgrade(long mana);
	
	// Aktiválja az aktuális kő hatását az ellenséges egységeken
	public void useMyRock(Collection<IEnemy> victims, boolean inside, boolean activate)
	{
		if (myRock != null)
			for (IEnemy v : victims) 
				if (v.getLocation().distance(this.location) < getRange() == inside)
				{
					if (activate)
						v.activateRock(myRock);
					else
						v.deactivateRock(myRock);
				}
	}
	
	// Az idő múlásáról való értesítés
	public long action(long dt, Collection<IEnemy> victims, long mana) 
	{
		useMyRock(victims, true, true);
		useMyRock(victims, false, false);
		
		time += dt;
		while (time >= getFireRate())
		{
			chooseVictim(victims);
			mana = attackLastChoosed(mana);
			time -= getFireRate();
		}
		return mana;
	}
	
	// Megtámadja a kiválasztott ellenfelet
	public long attackLastChoosed(long mana)
	{
		if (lastChoosed != null) mana = lastChoosed.damage(getDamage(), mana);
		return mana;
	}
	
	// Beállítja a torony helyzetét
	public void setLocation(Point p)
	{
		location = p;
	}

	// Visszaadja a torony aktuális helyzetét
	public Point getLocation()
	{
		return location;		
	}

	// Felrakja a toronyra a követ
	public void applyRock(IRock r) 
	{
		myRock = r;
	}
	
	// Eltávolítja a toronyról a követ
	public void removeRock() 
	{
		myRock = null;
	}
	
	// Beállítja a világot a torony számára
	public void setWorld(IWorld w)
	{
		myWorld = w;
	}
	
	// Visszaadja a tornyon lévő követ
	public IRock getMyRock()
	{
		return myRock;
	}

	// A torony kiválasztja azt az ellenséget amelyet támadni fog
	public void chooseVictim(Collection<IEnemy> victims)
	{
		double d = -1;
		lastChoosed = null;
		for (IEnemy e : victims)
		{
			double duj;
			if (((duj = e.getLocation().distance(GameStatics.LocOfHill)) < d)||(d < -0.5))
			{
				
					if (e.getLocation().distance(location) < getRange())
					{
						lastChoosed = e;
						d = duj;
					}
				
			}
		}
	}
	
	// Visszaadja az utoljára kiválasztott ellenfél referenciáját
	public IEnemy getLastChoosed() 
	{
		return lastChoosed;
	}
}
