package Towers;

/*
	Lövész torony osztálya. A torony ösosztály leszármazottja, így a torony interface t is megvalósítja
	A lövész torony függvényeit valósítja meg.
*/

import java.awt.Color;
import java.awt.Graphics2D;

import Main.Drawer;
import Main.GameStatics;



public class SniperTower extends Tower
{
	public SniperTower()  {	}
	
	// A GameStatics osztályból elkéri a torony hatótávolságát
	public long getRange() 
	{
		double corrig = 1;
		if (Math.random() < 0.2)
			corrig = 0.4;
		return (long)(GameStatics.Towers.Sniper.fireRange[state] * corrig);
	}
	
	// A GameStatics osztályból elkéri a torony sebzését
	public long getDamage() 
	{
		return GameStatics.Towers.Sniper.fireDamage[state];
	}

	// A GameStatics osztályból elkéri a torony tűzgyorsaságát
	public long getFireRate() 
	{
		return GameStatics.Towers.Sniper.fireRate[state];
	}

	public void draw(Graphics2D g)
	{
		Drawer.getInstance().Draw(g, this);
	}
	// A torony fejlesztése, amellyel minden attribútumértéke javul
	public long upgrade(long mana) 
	{
		long cost = GameStatics.Towers.Sniper.upgradeCosts[state];
		if ((mana > cost) && (cost > 0))
		{
			state++;
			mana-=cost;
		}
		return mana;
	}

	// A GameStatics osztályból elkéri a torony árát
	public long buildPrice()
	{
		return GameStatics.Towers.Sniper.initCost;
	}
}
