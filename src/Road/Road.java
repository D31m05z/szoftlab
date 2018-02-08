package Road;

/*
	Utat megtestesítő osztály
*/

import java.awt.Point;
import java.util.*;
import Enemies.IEnemy;
import Main.GameStatics;
import Main.IWorld;
import Road.Spline.*;

public class Road implements IRoad
{
	// A világra mutató referencia
	private IWorld myWorld = null;
	
	// Kezdő és végső útparaméterek
	private long startParam = 0;
	private long endParam = 10000;
	
	// A lehetséges következő útszakaszok és blokádok kollekciói
	private List<IRoad> nextRoadParts = new ArrayList<IRoad>();
	private Collection<IBlockade> blockades = new ArrayList<IBlockade> ();
	
	// Az utat megtestesítő splinera mutató referencia
	private Spline s = new Spline();
	
	// Az utat meghatározó spline referenciája (strategy pattern)
	public Spline getSpline()
	{
		return s;
	}
	
	// Az úthoz kapcsolódó utak (amik a következők lehetnek) kollekciójával tér vissza
	public Collection<IRoad> getNextRoadParts()
	{
		return nextRoadParts;
	}
	
	// Az úton található blokádok kollekciójával tér vissza
	public Collection<IBlockade> getBlockades()
	{
		return blockades;
	}
	
	// Adott útparaméterhez tartozó koordináta elkérése
	public Point getLocation(long t)
	{
		return s.getPoint(t);
	}

	// Ellenség léptetése t1 paramétertől t2-be, ennek pontos menetét lásd a dokumentációban
	// Visszatér azzal, hogy sikerült-e eljutni t2-be
	public boolean move(IEnemy e, long t1, long t2)
	{		
		// Annak eldöntése, hogy melyik úton kell esetlegesen továbbküldeni az ellenségen
		Collections.shuffle(nextRoadParts);
		IRoad nextRoadPart = null;
		if (nextRoadParts.size() > 0)
		{
			nextRoadPart = nextRoadParts.get(0);
		}

		
		// Ha a célpont még az úton van
		if (t2 <= endParam)
		{
			// A t1 és t2 közötti blokádok lekérdezése
			IBlockade b = getMyBlockadeBetween(t1, t2);
			
			if (b == null)
			{
				// Ha b null, akkor az ellenség útparaméterének beállítása t2-re
				// és célpontjának törlése, visszatérés "igaz" értékkel (mivel sikerült eljutni t2-be)
				e.setRoadParameter(t2);
				e.setTarget(null);
				return true;
			}
			else
			{
				// Ha b nem null, akkor csak addig jutunk el, vagyis az ellnség paramétere
				// b útparamétere, az ellenség célpontja b, visszatérés "hamis" értékkel
				e.setRoadParameter(b.getT());
				e.setTarget(b);
				return false;
			}
		}
		else
		{
			// Az út végéig el kell érni, ennek megkísérlése
			if (move(e, t1, endParam))
			{
				// Ezen az úton sikerült végigmenni
				if (nextRoadPart == null)
				{
					// Nincs már több út, vagyis ez az út a végzet hegyéhez vezetett,
					// ekkor a világ értesül arról, hogy egy ellenség elérte ezt
					myWorld.gameLost();
					return false;
				}
				else
				{
					// Van következő útszakasz, akkor az ellenség útszakasza az lesz,
					// és a következő útszakaszon megpróábáljuk mozgatni az ellenséget
					// az út kezdetétől addig amennyi még fennmaradt a haladásból
					e.setRoad(nextRoadPart);
					e.setRoadParameter(nextRoadPart.getStart());
					//System.out.println("next road");
					//boolean tmp =  nextRoadPart.move(e, nextRoadPart.getStart(), nextRoadPart.getStart() + t2 - (endParam - t1));
					//return tmp;
					return false;
				}
			}
			return false;
		}
	}

	// Blokád hozzáadása a megadot útparaméterhez
	public long addBlockade(long t, long mana)
	{
		if (mana >= GameStatics.BlockadeCost)
		{
			Blockade b = new Blockade(this,t);		
			blockades.add(b);
			mana -= GameStatics.BlockadeCost;
		}
		return mana;
	}

	// Adott referenciájú blokád eltávolítása
	public void removeBlockade(IBlockade b)
	{
		blockades.remove(b);
	}

	// Adott útparaméteren található blokád javítása
	public void repairBlockadeAt(long roadParameter)
	{
		for (IBlockade b : blockades)
		{
			if (b.getT() == roadParameter)
			{
				b.repair();
			}
		}
	}

	// Világ beállítása
	public void setWorld(IWorld w)
	{
		myWorld = w;
	}

	// Végső útparaméter elkérése
	public long getEnd()
	{
		return endParam;
	}

	// Kezdő útparaméter elkérése
	public long getStart()
	{
		return startParam;
	}

	// Végső útparaméter beállítása
	public void setEnd(long t)
	{
		endParam = t;
	}

	// Kezdő útparaméter beállítása
	public void setStart(long t)
	{
		startParam = t;
	}

	// A lehetséges következő útszakaszok listájának bővítése
	public void setNext(IRoad r)
	{
		nextRoadParts.add(r);
	}

	// t1 és t2 közötti blokádok megkeresése, ezek közül egyel visszatér
	public IBlockade getMyBlockadeBetween(long t1, long t2)
	{
		IBlockade result = null;
		for (IBlockade temp : blockades)
		{
			long loc = temp.getT();
			if ((loc >= t1) && (loc <= t2))
				result = temp;
		}
		return result;
	}
}

