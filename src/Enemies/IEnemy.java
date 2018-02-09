package Enemies;

/*
	Az ellensgek interfsze, a vilg, tornyok s utak
	ltal lthat metdusok gyjtemnye.
*/

import java.awt.Point;

import Main.Drawable;
import Main.IWorld;
import Road.IBlockade;
import Road.IRoad;
import Towers.Rocks.IRock;

public interface IEnemy extends Drawable
{
	// Az id mlsrl val rtests
	public void action(long dt);
	
	// A plyn lv pozci lekrdezse
	public Point getLocation();
	
	// Az ellensg rtestse arrl, hogy megtmadtk. tveszi mennyi az aktulis
	// mana mennyisge, s visszatr azzal, mennyi az j mana mennyisge
	// (ha az ellensg meghal, mant generl)
	public long damage(long damage, long mana);
	
	// Az ellensg rtestse arrl, hogy rajta az adott k hatsa innentl rvnyes
	public void activateRock(IRock rock);
	
	// Az ellensg rtestse arrl, hogy rajta az adott k hatsa innentl nem rvnyes
	public void deactivateRock(IRock rock);
	
	// Az aktulis t belltsa
	public void setRoad(IRoad road);
	
	// Az aktulis tparamter belltsa
	public void setRoadParameter(long t);
	
	// Az aktulis vilg belltsa
	public void setWorld(IWorld w);
	
	// A jelenleg tmadand blokd belltsa
	public void setTarget(IBlockade blockade);
	
	// "Felez" tmads fogadsra szolgl metdus
	public void split();
	
	// Az aktulis blokd megtmadst kezdemnyez metdus 
	public void attack();
	
	// rtests errl, hogy az aktulis ton adott
	// mennyisg id eltelse miatt tovbb kell haladni.
	public void moveForward(long dt);
}
