package Road;

/*
	Az t osztly interfsze, az Engine s
	az ellensgek ezen kereszl ismerik
*/

import java.awt.Point;
import java.util.Collection;
import Enemies.IEnemy;
import Main.IWorld;
import Road.Spline.Spline;

public interface IRoad
{
	// Ellensg lptetse t1 paramtertl t2-be
	public boolean move(IEnemy enemy, long t1, long t2);
	
	// Adott referencij blokd eltvoltsa
	public void removeBlockade(IBlockade blockade);
	
	// Adott tparamterhez tartoz koordinta elkrse
	public Point getLocation(long t);
	
	// Kezd tparamter elkrse
	public long getStart();
	
	// Blokd hozzadsa a megadot tparamterhez
	public long addBlockade(long t, long mana);
	
	// Adott tparamteren tallhat blokd javtsa
	public void repairBlockadeAt(long roadParameter);
	
	// Vilg belltsa
	public void setWorld(IWorld w);
	
	// Az utat meghatroz spline referencija (strategy pattern)
	public Spline getSpline();
	
	// Az thoz kapcsold utak (amik a kvetkezk lehetnek) kollekcijval tr vissza
	public Collection<IRoad> getNextRoadParts();
}
