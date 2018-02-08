package Enemies;

/*
	DwarfEnemy osztály, az Enemy leszármazottja
*/

import java.awt.Color;
import java.awt.Graphics2D;

import Main.Drawer;
import Main.GameStatics;

public class DwarfEnemy extends Enemy
{
	// Konstruktor, támadóerő, sebesség és hp beállítása
	public DwarfEnemy()
	{
		damagePower = GameStatics.Enemies.Dwarf.damagePower;
		speed = GameStatics.Enemies.Dwarf.speed;
		hp = GameStatics.Enemies.Dwarf.initHp;
	}

	// Támadás fogadása, tekintettel arra, hogy a
	// dwarf-okra hat a dragon kő is
	public long damage(long damage, long mana)
	{
		if (isDragonOnMe)
			return damageMySelf(2*damage, mana);
		else
			return damageMySelf(damage, mana);
	}
	
	// Másoldó metódus (a split-hez kell)
	protected Enemy copy()
	{
		return new DwarfEnemy();
	}
	
	public void draw(Graphics2D g)
	{
		Drawer.getInstance().Draw(g, this);
	}
}
