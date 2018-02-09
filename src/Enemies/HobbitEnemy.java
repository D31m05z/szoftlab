package Enemies;

/*
	HobbitEnemy osztly, az Enemy leszrmazottja
*/

import java.awt.Color;
import java.awt.Graphics2D;

import Main.Drawer;
import Main.GameStatics;

public class HobbitEnemy extends Enemy
{
	// Konstruktor, tmader, sebessg s hp belltsa
	public HobbitEnemy()
	{
		damagePower = GameStatics.Enemies.Hobbit.damagePower;
		speed = GameStatics.Enemies.Hobbit.speed;
		hp = GameStatics.Enemies.Hobbit.initHp;
	}
	
	// Tmads fogadsa, tekintettel arra, hogy a
	// hobbit-okra hat a jar k is
	public void action(long dt)
	{
		if (isJarOnMe)
			moveForward(dt >> 1);
		else
			moveForward(dt);
	}
	
	// Msold metdus (a split-hez kell)
	protected Enemy copy()
	{
		return new HobbitEnemy();
	}
	
	public void draw(Graphics2D g)
	{
		Drawer.getInstance().Draw(g, this);
	}
}
