package Road;

/*
	Utat megtestest osztly
*/

import java.awt.Point;
import java.util.*;
import Enemies.IEnemy;
import Main.GameStatics;
import Main.IWorld;
import Road.Spline.*;

public class Road implements IRoad
{
	// A vilgra mutat referencia
	private IWorld myWorld = null;
	
	// Kezd s vgs tparamterek
	private long startParam = 0;
	private long endParam = 10000;
	
	// A lehetsges kvetkez tszakaszok s blokdok kollekcii
	private List<IRoad> nextRoadParts = new ArrayList<IRoad>();
	private Collection<IBlockade> blockades = new ArrayList<IBlockade> ();
	
	// Az utat megtestest splinera mutat referencia
	private Spline s = new Spline();
	
	// Az utat meghatroz spline referencija (strategy pattern)
	public Spline getSpline()
	{
		return s;
	}
	
	// Az thoz kapcsold utak (amik a kvetkezk lehetnek) kollekcijval tr vissza
	public Collection<IRoad> getNextRoadParts()
	{
		return nextRoadParts;
	}
	
	// Az ton tallhat blokdok kollekcijval tr vissza
	public Collection<IBlockade> getBlockades()
	{
		return blockades;
	}
	
	// Adott tparamterhez tartoz koordinta elkrse
	public Point getLocation(long t)
	{
		return s.getPoint(t);
	}

	// Ellensg lptetse t1 paramtertl t2-be, ennek pontos menett lsd a dokumentciban
	// Visszatr azzal, hogy sikerlt-e eljutni t2-be
	public boolean move(IEnemy e, long t1, long t2)
	{		
		// Annak eldntse, hogy melyik ton kell esetlegesen tovbbkldeni az ellensgen
		Collections.shuffle(nextRoadParts);
		IRoad nextRoadPart = null;
		if (nextRoadParts.size() > 0)
		{
			nextRoadPart = nextRoadParts.get(0);
		}

		
		// Ha a clpont mg az ton van
		if (t2 <= endParam)
		{
			// A t1 s t2 kztti blokdok lekrdezse
			IBlockade b = getMyBlockadeBetween(t1, t2);
			
			if (b == null)
			{
				// Ha b null, akkor az ellensg tparamternek belltsa t2-re
				// s clpontjnak trlse, visszatrs "igaz" rtkkel (mivel sikerlt eljutni t2-be)
				e.setRoadParameter(t2);
				e.setTarget(null);
				return true;
			}
			else
			{
				// Ha b nem null, akkor csak addig jutunk el, vagyis az ellnsg paramtere
				// b tparamtere, az ellensg clpontja b, visszatrs "hamis" rtkkel
				e.setRoadParameter(b.getT());
				e.setTarget(b);
				return false;
			}
		}
		else
		{
			// Az t vgig el kell rni, ennek megksrlse
			if (move(e, t1, endParam))
			{
				// Ezen az ton sikerlt vgigmenni
				if (nextRoadPart == null)
				{
					// Nincs mr tbb t, vagyis ez az t a vgzet hegyhez vezetett,
					// ekkor a vilg rtesl arrl, hogy egy ellensg elrte ezt
					myWorld.gameLost();
					return false;
				}
				else
				{
					// Van kvetkez tszakasz, akkor az ellensg tszakasza az lesz,
					// s a kvetkez tszakaszon megprbljuk mozgatni az ellensget
					// az t kezdettl addig amennyi mg fennmaradt a haladsbl
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

	// Blokd hozzadsa a megadot tparamterhez
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

	// Adott referencij blokd eltvoltsa
	public void removeBlockade(IBlockade b)
	{
		blockades.remove(b);
	}

	// Adott tparamteren tallhat blokd javtsa
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

	// Vilg belltsa
	public void setWorld(IWorld w)
	{
		myWorld = w;
	}

	// Vgs tparamter elkrse
	public long getEnd()
	{
		return endParam;
	}

	// Kezd tparamter elkrse
	public long getStart()
	{
		return startParam;
	}

	// Vgs tparamter belltsa
	public void setEnd(long t)
	{
		endParam = t;
	}

	// Kezd tparamter belltsa
	public void setStart(long t)
	{
		startParam = t;
	}

	// A lehetsges kvetkez tszakaszok listjnak bvtse
	public void setNext(IRoad r)
	{
		nextRoadParts.add(r);
	}

	// t1 s t2 kztti blokdok megkeresse, ezek kzl egyel visszatr
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

