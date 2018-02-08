package Enemies;

/*
	HobbitEnemy osztály, az Enemy leszármazottja
*/

import java.awt.Color;
import java.awt.Graphics2D;

import Main.Drawer;
import Main.GameStatics;

public class HobbitEnemy extends Enemy
{
	// Konstruktor, támadóerő, sebesség és hp beállítása
	public HobbitEnemy()
	{
		damagePower = GameStatics.Enemies.Hobbit.damagePower;
		speed = GameStatics.Enemies.Hobbit.speed;
		hp = GameStatics.Enemies.Hobbit.initHp;
	}
	
	// Támadás fogadása, tekintettel arra, hogy a
	// hobbit-okra hat a jar kő is
	public void action(long dt)
	{
		if (isJarOnMe)
			moveForward(dt >> 1);
		else
			moveForward(dt);
	}
	
	// Másoldó metódus (a split-hez kell)
	protected Enemy copy()
	{
		return new HobbitEnemy();
	}
	
	public void draw(Graphics2D g)
	{
		Drawer.getInstance().Draw(g, this);
	}
}
