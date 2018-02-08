package Enemies;

/*
	HumanEnemy osztály, az Enemy leszármazottja
*/

import java.awt.Color;
import java.awt.Graphics2D;

import Main.Drawer;
import Main.GameStatics;

public class HumanEnemy extends Enemy 
{
	// Konstruktor, támadóerő, sebesség és hp beállítása
	public HumanEnemy()
	{
		damagePower = GameStatics.Enemies.Human.damagePower;
		speed = GameStatics.Enemies.Human.speed;
		hp = GameStatics.Enemies.Human.initHp;
	}
	
	// Másoldó metódus (a split-hez kell)
	protected Enemy copy()
	{
		return new HumanEnemy();
	}
	
	public void draw(Graphics2D g)
	{
		Drawer.getInstance().Draw(g, this);
	}
}
