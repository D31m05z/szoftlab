package Towers;

/*
	Torony sosztlya, amely megvalstja az ITower interfacet. 
	Ezen osztly tartalmazza a kvek kzs tulajdonsgait, alapvet fggvnymegvalstsokat.
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
	
	// Aktivlja az aktulis k hatst az ellensges egysgeken
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
	
	// Az id mlsrl val rtests
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
	
	// Megtmadja a kivlasztott ellenfelet
	public long attackLastChoosed(long mana)
	{
		if (lastChoosed != null) mana = lastChoosed.damage(getDamage(), mana);
		return mana;
	}
	
	// Belltja a torony helyzett
	public void setLocation(Point p)
	{
		location = p;
	}

	// Visszaadja a torony aktulis helyzett
	public Point getLocation()
	{
		return location;		
	}

	// Felrakja a toronyra a kvet
	public void applyRock(IRock r) 
	{
		myRock = r;
	}
	
	// Eltvoltja a toronyrl a kvet
	public void removeRock() 
	{
		myRock = null;
	}
	
	// Belltja a vilgot a torony szmra
	public void setWorld(IWorld w)
	{
		myWorld = w;
	}
	
	// Visszaadja a tornyon lv kvet
	public IRock getMyRock()
	{
		return myRock;
	}

	// A torony kivlasztja azt az ellensget amelyet tmadni fog
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
	
	// Visszaadja az utoljra kivlasztott ellenfl referencijt
	public IEnemy getLastChoosed() 
	{
		return lastChoosed;
	}
}
