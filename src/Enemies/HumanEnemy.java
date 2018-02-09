package Enemies;

/*
	HumanEnemy osztly, az Enemy leszrmazottja
*/

import java.awt.Color;
import java.awt.Graphics2D;

import Main.Drawer;
import Main.GameStatics;

public class HumanEnemy extends Enemy 
{
	// Konstruktor, tmader, sebessg s hp belltsa
	public HumanEnemy()
	{
		damagePower = GameStatics.Enemies.Human.damagePower;
		speed = GameStatics.Enemies.Human.speed;
		hp = GameStatics.Enemies.Human.initHp;
	}
	
	// Msold metdus (a split-hez kell)
	protected Enemy copy()
	{
		return new HumanEnemy();
	}
	
	public void draw(Graphics2D g)
	{
		Drawer.getInstance().Draw(g, this);
	}
}
