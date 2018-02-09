package Towers;

/*
	Lvsz torony osztlya. A torony sosztly leszrmazottja, gy a torony interface t is megvalstja
	A lvsz torony fggvnyeit valstja meg.
*/

import java.awt.Color;
import java.awt.Graphics2D;

import Main.Drawer;
import Main.GameStatics;



public class SniperTower extends Tower
{
	public SniperTower()  {	}
	
	// A GameStatics osztlybl elkri a torony hattvolsgt
	public long getRange() 
	{
		double corrig = 1;
		if (Math.random() < 0.2)
			corrig = 0.4;
		return (long)(GameStatics.Towers.Sniper.fireRange[state] * corrig);
	}
	
	// A GameStatics osztlybl elkri a torony sebzst
	public long getDamage() 
	{
		return GameStatics.Towers.Sniper.fireDamage[state];
	}

	// A GameStatics osztlybl elkri a torony tzgyorsasgt
	public long getFireRate() 
	{
		return GameStatics.Towers.Sniper.fireRate[state];
	}

	public void draw(Graphics2D g)
	{
		Drawer.getInstance().Draw(g, this);
	}
	// A torony fejlesztse, amellyel minden attribtumrtke javul
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

	// A GameStatics osztlybl elkri a torony rt
	public long buildPrice()
	{
		return GameStatics.Towers.Sniper.initCost;
	}
}
