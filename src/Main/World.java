package Main;

/*
	Vilg implementci, IWorld-ot implementlja
*/

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import CCClasses.ICreator;
import Enemies.IEnemy;
import Road.IRoad;
import Road.Road;
import Road.Spline.Spline;
import Towers.ITower;
import Towers.Tower;
import Towers.Rocks.IRock;


public class World implements IWorld
{
	// Jtk vgzdtt versggel
	private boolean gameOver = false;
	
	// Jtk vgzdtt gyzelemmel
	private boolean gameWin = false;
	
	// Ellensgek kollekcija
	private Collection<IEnemy> enemies;
	
	// Tornyok kollekcija
	private Collection<ITower> towers;
	
	// ConcreteCreatorok kollekcija
	private Map<String, ICreator> creators;
	
	// Mr kifizetett kvek kollekcija
	private Map<String, IRock> rocks = new HashMap<String, IRock>();

	private List<IRoad> roads = new ArrayList<IRoad>();
	
	// Kollekcik lekrdezse
	public Map<String, IRock> getRocks() { return rocks; }
	public Collection<IEnemy> getEnemies() { return enemies; }
	public Collection<ITower> getTowers() { return towers; }
	public List<IRoad> getRoads() { return roads; }
	
	
	// Konstruktor, ktelez megadni a ConcreteCreatorok-at tartalmaz asszociatv tmbt
	public World(Map<String,ICreator> c)
	{
		creators = c;		
		towers = new ArrayList<ITower>();
		enemies = new ArrayList<IEnemy>();
	}

	// rtests arrl, hogy id telt el, ekkor elszr a tornyok rteslnek
	// errl, majd az ellensek, ezek utn ha mr minden ellensg mehalt
	// s minden krn tlvagyunk, annak belltsa, hogy a jtk vget rt
	public long Update(long dt, long mana)
	{
		for (ITower t : towers) 
			mana = t.action(dt, enemies, mana);
		for (IEnemy e : enemies) 
			e.action(dt);
		
		if ((enemies.size() == 0)&&(spawnoltKorokSzama == GameStatics.SpawnInfo.spawn.length))
		{
			gameWin = true;
		}
		spawner(dt);
		return mana;
	}
	private int spawnoltKorokSzama = -1;
	
	// 1-es s 4-es utakon jhet ellensg
	// mivel a roads kollekcit cmezzk ezrt figyelembe kell venni az indexeket
	// le kell vonni 1-et
	private int[] road_ids = {1-1,4-1};
	private int road_id = 0;
	private long spawn_time = 5000;
	private long spawn_dt = 0;
	private long spawn_elapsed = 0;
	private void spawner(long dt)
	{
		spawn_dt +=dt;
		if(spawn_dt > spawn_elapsed + spawn_time)
		{
			spawn_elapsed += spawn_dt;
			spawnoltKorokSzama++;
		}
		else
			return;
		
		if (spawnoltKorokSzama >= GameStatics.SpawnInfo.spawn.length) 
		{
			return;
		}
		
		road_id = 0;
		
		for (Object info : GameStatics.SpawnInfo.spawn[spawnoltKorokSzama])
		{
			IRoad ezenAzUton = roads.get(road_ids[road_id]);
			for (String CCNev : ((Map<String, Integer>)info).keySet())
			{
				for (int i = 0; i<((Map<String, Integer>)info).get(CCNev);i++)
				{
					addEnemy(CCNev,ezenAzUton);
				}
			}
			
			if(road_id < road_ids.length)
				road_id++;
			else 
				road_id = 0;
		}
	}

	// K ltrehozsa, aktulis mana tvtele. A kvet a ConcreteCreator-orjnak neve azonstja
	// K csak akkor hozhat ltre, ha van r elg mana, akkor ennek tke cskken a k rval
	// Visszatr a fennmarad mana mennyisgvel
	public long createRock(long mana, String name)
	{
		if (!rocks.containsKey(name))
		{
			IRock t = (IRock) creators.get(name).create();
			if(mana >= t.getCost())
			{
				mana -= t.getCost();
				rocks.put(name,t);
			}
		}
		return mana;
	}
	
	// Annak eldntse, hogy az adott helyre lehet-e tornyok ltrehozni
	// annak figyelembevtelvel, hogy milyen utak lteznek a vilgban
	private boolean canAddTower(Collection<IRoad> roads, Point location)
	{
		boolean result = true;
		for (IRoad road : roads)
		{
			// Spline lekrdezse
			Spline spline = road.getSpline();
			Point start = spline.getStart();
			Point end = spline.getEnd();
			
			// Hromszgelses mdszer alkalmazsa azrt, mert a spline 2 pontra feszt
			if(-start.distance(end) + location.distance(end) + location.distance(start) - 1 < 0)
			{
				result = false;
			}
						
			// Rekurzi megvalstsa
			boolean insideResult = true;
			if(road.getNextRoadParts() != null)
				insideResult = canAddTower(road.getNextRoadParts(), location);
			
			if(!insideResult)
				result = false;
		}
		return result;
	}
	
	// Torony hozzadsa, a ltez utak figyelembevtelvel, a tonyot a ConcreteCreator-orjnak neve
	// azonstja, valamit megadjuk a pontot is
	// Visszatr azzal, mennyi mana maradt
	public long addTower(long mana,Collection<IRoad> roads, String name, Point location)
	{		
		// Ha nem lehet tornyot lerakni, visszatrs
		if(!canAddTower(roads, location)) 
			return mana;
		
		// Torony ltrehozsa
		ITower t = (ITower) creators.get(name).create();
		t.setWorld(this);
		t.setLocation(location);
		
		// Ha van elg mana, akkor megvizsgljuk a tbbi toronyhoz nincs-e tl kzel
		if(mana >= t.buildPrice())
		{
			double minDist = Double.MAX_VALUE;
			for (ITower alreadyBuilt : towers)
			{
				double dist = alreadyBuilt.getLocation().distance(location);
				if (dist < minDist)
				{
					minDist = dist;
				}
			}
			// Ha nincs tl kzel ms toronyhoz, akkor fel lehet venni
			if (minDist > GameStatics.MinDistanceBetweenTowers || minDist == Double.MAX_VALUE)
			{
				// Kollekcihoz a pldny hozzadsa, valamit mana cskkentse a torony rval
				towers.add(t);
				mana -= t.buildPrice();
			}
		}
		return mana;
	}

	// Ellensg hozzadsa az ellensgek kollekcijhoz
	public void addEnemy(IEnemy enemy)
	{
		enemies.add(enemy);
	}
	
	// Ellensg hozzadsa az ellensek kollekcija
	// valamint a megadott t belltsa az ellensgnek
	public void addEnemy(String name, IRoad spawnOn)
	{
		IEnemy e = (IEnemy) creators.get(name).create();
		e.setRoadParameter(spawnOn.getStart());
		e.setRoad(spawnOn);
		e.setWorld(this);
		enemies.add(e);
	}
	
	// Annak feldertse, az adott ponton melyik torony van
	// (vagyis melyik torony van a ponthoz a legkzelebb)
	private ITower whoIsOn(Point p)
	{
		ITower result = null;
		double d = -1;
		for (ITower e : towers)
		{
			double ujd;
			if (((ujd = p.distance(e.getLocation())) < d) || (d < -0.5))
			{
				result = e;
				d = ujd;
			}
		}
		return result;
	}
	
	// K hozzadsa ahhoz a toronyhoz ami a megadott
	// ponton tallhat. Csak akkor lehet ezt megtenni,
	// ha ehhez van elg mana. A megmaradt manval visszatr
	public long addRock(long mana, String r, Point location)
	{		
		ITower result = whoIsOn(location);
		if (rocks.containsKey(r))
		{
			if(mana >= rocks.get(r).getCost())
			{
				for (ITower t : towers)
				{
					if (t.getMyRock() == rocks.get(r))
					{
						t.removeRock();
					}
				}
				result.applyRock(rocks.get(r));
				mana -= rocks.get(r).getCost();
			}
		}
		return mana;
	}
	
	// Toronyrl k levtele, ekkor az ellensgeken deaktivljuk a
	// k hatst is
	public void removeRock(Point location)
	{
		ITower result = whoIsOn(location);
		if(result != null)
		{
			for (IEnemy e:enemies)
			{
				e.deactivateRock(result.getMyRock());
			}
			result.removeRock();
		}
	}
	
	// Adott ponton lv torony fejlesztse,
	// ha van r elg mana. A megmaradt manval visszatr
	public long upgradeTower(long mana, Point location)
	{
		ITower result = whoIsOn(location);
		
		if (result != null)
			mana = result.upgrade(mana);
		return mana;
	}

	// Ellensgek szmnak lekrdezse
	public int getEnemyCount()
	{
		return enemies.size();
	}
	
	// Veresg lekrdezse
	public boolean getGameOver()
	{
		return gameOver;
	}
	
	// Nyeresg lekrdezse
	public boolean getGameWin()
	{
		return gameWin;
	}

	// Ellensg eltvoltsa a listrl
	public void removeEnemy(IEnemy enemy) 
	{
		enemies.remove(enemy);
	}

	// Veresg jelzs belltsa
	public void gameLost() 
	{
		gameOver = true;
	}
}