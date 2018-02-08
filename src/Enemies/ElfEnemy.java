package Enemies;

/*
	ElfEnemy osztály, az Enemy leszármazottja
*/

import java.awt.Color;
import java.awt.Graphics2D;

import Main.*;

public class ElfEnemy extends Enemy
{
	// Konstruktor, támadóerő, sebesség és hp beállítása
	public ElfEnemy()
	{
		damagePower = GameStatics.Enemies.Elf.damagePower;
		speed = GameStatics.Enemies.Elf.speed;
		hp = GameStatics.Enemies.Elf.initHp;
	}

	// Támadás fogadása, tekintettel arra, hogy az
	// elf-okra hat a fate kő is (és amúgy nem mindig látható ==> nem mindig támadható)
	public long damage(long damage, long mana)
	{

		if ((isFateOnMe) || Math.random() > 0.3 )
			return damageMySelf(damage, mana);
		
		return mana;
	}
	
	// Másoldó metódus (a split-hez kell)
	protected Enemy copy()
	{
		return new ElfEnemy();
	}
	
	public void draw(Graphics2D g)
	{
		Drawer.getInstance().Draw(g, this);
	}
}
