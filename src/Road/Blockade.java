package Road;

/*
	Blokd osztly, megvalstja az IBlockade interfszt
*/

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import Main.Drawer;
import Main.GameStatics;

public class Blockade implements IBlockade 
{
	// tparamter
	private long t = 0;
	
	// leter
	private long hp = 0;
	
	// Sajt tra mutat referencia
	private IRoad myRoad = null;
	
	// Publikus konstruktor, ktelez belltani az utat s az tparamtert
	public Blockade(IRoad r, long t_)
	{
		t = t_;
		myRoad = r;
		hp = GameStatics.BlockadeHP;
	}
	
	// tparamter elkrse
	public long getT()
	{
		return t;
	}
	
	// Javts kezdemnyezse
	public void repair()
	{
		hp = GameStatics.BlockadeHP;
	}
	
	// Blokd fogadja a tmadst, ha meghal, akkor az trl a val trls kezdemnyezse
	public void damage(long d)
	{
		hp -= d;
		if (hp < 0)
		{
			myRoad.removeBlockade(this);
		}
	}
	
	// Aktulis letpontok lekrdezse
	public long getHp() 
	{
		return hp;
	}
	
	public Point getLocation()
	{
		return myRoad.getLocation(t);
	}
	public void draw(Graphics2D g)
	{
		Drawer.getInstance().Draw(g, this);
	}
}
