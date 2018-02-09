package Enemies;

/*
	DwarfEnemy osztly, az Enemy leszrmazottja
*/

import java.awt.Color;
import java.awt.Graphics2D;

import Main.Drawer;
import Main.GameStatics;

public class DwarfEnemy extends Enemy
{
	// Konstruktor, tmader, sebessg s hp belltsa
	public DwarfEnemy()
	{
		damagePower = GameStatics.Enemies.Dwarf.damagePower;
		speed = GameStatics.Enemies.Dwarf.speed;
		hp = GameStatics.Enemies.Dwarf.initHp;
	}

	// Tmads fogadsa, tekintettel arra, hogy a
	// dwarf-okra hat a dragon k is
	public long damage(long damage, long mana)
	{
		if (isDragonOnMe)
			return damageMySelf(2*damage, mana);
		else
			return damageMySelf(damage, mana);
	}
	
	// Msold metdus (a split-hez kell)
	protected Enemy copy()
	{
		return new DwarfEnemy();
	}
	
	public void draw(Graphics2D g)
	{
		Drawer.getInstance().Draw(g, this);
	}
}
