package Road;

/*
	Blokád osztály, megvalósítja az IBlockade interfészt
*/

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import Main.Drawer;
import Main.GameStatics;

public class Blockade implements IBlockade 
{
	// Útparaméter
	private long t = 0;
	
	// Életerő
	private long hp = 0;
	
	// Saját útra mutató referencia
	private IRoad myRoad = null;
	
	// Publikus konstruktor, kötelező beállítani az utat és az útparamétert
	public Blockade(IRoad r, long t_)
	{
		t = t_;
		myRoad = r;
		hp = GameStatics.BlockadeHP;
	}
	
	// Útparaméter elkérése
	public long getT()
	{
		return t;
	}
	
	// Javítás kezdeményezése
	public void repair()
	{
		hp = GameStatics.BlockadeHP;
	}
	
	// Blokád fogadja a támadást, ha meghal, akkor az útról a való törlés kezdeményezése
	public void damage(long d)
	{
		hp -= d;
		if (hp < 0)
		{
			myRoad.removeBlockade(this);
		}
	}
	
	// Aktuális életpontok lekérdezése
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
