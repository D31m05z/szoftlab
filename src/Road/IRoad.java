package Road;

/*
	Az út osztály interfésze, az Engine és
	az ellenségek ezen kereszül ismerik
*/

import java.awt.Point;
import java.util.Collection;
import Enemies.IEnemy;
import Main.IWorld;
import Road.Spline.Spline;

public interface IRoad
{
	// Ellenség léptetése t1 paramétertől t2-be
	public boolean move(IEnemy enemy, long t1, long t2);
	
	// Adott referenciájú blokád eltávolítása
	public void removeBlockade(IBlockade blockade);
	
	// Adott útparaméterhez tartozó koordináta elkérése
	public Point getLocation(long t);
	
	// Kezdő útparaméter elkérése
	public long getStart();
	
	// Blokád hozzáadása a megadot útparaméterhez
	public long addBlockade(long t, long mana);
	
	// Adott útparaméteren található blokád javítása
	public void repairBlockadeAt(long roadParameter);
	
	// Világ beállítása
	public void setWorld(IWorld w);
	
	// Az utat meghatározó spline referenciája (strategy pattern)
	public Spline getSpline();
	
	// Az úthoz kapcsolódó utak (amik a következők lehetnek) kollekciójával tér vissza
	public Collection<IRoad> getNextRoadParts();
}
