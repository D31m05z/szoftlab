package Enemies;

/*
	ElfEnemy osztly, az Enemy leszrmazottja
*/

import java.awt.Color;
import java.awt.Graphics2D;

import Main.*;

public class ElfEnemy extends Enemy
{
	// Konstruktor, tmader, sebessg s hp belltsa
	public ElfEnemy()
	{
		damagePower = GameStatics.Enemies.Elf.damagePower;
		speed = GameStatics.Enemies.Elf.speed;
		hp = GameStatics.Enemies.Elf.initHp;
	}

	// Tmads fogadsa, tekintettel arra, hogy az
	// elf-okra hat a fate k is (s amgy nem mindig lthat ==> nem mindig tmadhat)
	public long damage(long damage, long mana)
	{

		if ((isFateOnMe) || Math.random() > 0.3 )
			return damageMySelf(damage, mana);
		
		return mana;
	}
	
	// Msold metdus (a split-hez kell)
	protected Enemy copy()
	{
		return new ElfEnemy();
	}
	
	public void draw(Graphics2D g)
	{
		Drawer.getInstance().Draw(g, this);
	}
}
