package Towers;

/*
	Ă�gyĂş torony osztĂˇlya. A torony Ă¶sosztĂˇly leszĂˇrmazottja, Ă­gy a torony interface t is megvalĂłsĂ­tja
	Az ĂˇgyĂş torony fĂĽggvĂ©nyeit valĂłsĂ­tja meg.
*/

import java.awt.Color;
import java.awt.Graphics2D;

import Main.Drawer;
import Main.GameStatics;



public class CannonTower extends Tower
{
	public CannonTower()  { }
	
	// A GameStatics osztĂˇlybĂłl elkĂ©ri a torony hatĂłtĂˇvolsĂˇgĂˇt
	public long getRange() 
	{
		double corrig = 1;
		if (Math.random() < 0.2)
			corrig = 0.4;
		return (long)(GameStatics.Towers.Cannon.fireRange[state] * corrig);
	}

	// A GameStatics osztĂˇlybĂłl elkĂ©ri a torony sebzĂ©sĂ©t
	public long getDamage()
	{
		return GameStatics.Towers.Cannon.fireDamage[state];
	}

	// A GameStatics osztĂˇlybĂłl elkĂ©ri a torony tĹ±zgyorsasĂˇgĂˇt
	public long getFireRate() 
	{
		return GameStatics.Towers.Cannon.fireRate[state];
	}
	
	public void draw(Graphics2D g)
	{
		Drawer.getInstance().Draw(g, this);
	}

	// A torony fejlesztĂ©se, amellyel minden attribĂştumĂ©rtĂ©ke javul
	public long upgrade(long mana) 
	{
		long cost = GameStatics.Towers.Cannon.upgradeCosts[state];
		if ((mana > cost) && (cost > 0))
		{
			state++;
			mana-=cost;
		}
		return mana;
	}
	
	// A GameStatics osztĂˇlybĂłl elkĂ©ri a torony ĂˇrĂˇt
	public long buildPrice()
	{
		return GameStatics.Towers.Cannon.initCost;
	}
}
