package Main;

/*
	Világ implementáció, IWorld-ot implementálja
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
	// Játék végződött verséggel
	private boolean gameOver = false;
	
	// Játék végződött győzelemmel
	private boolean gameWin = false;
	
	// Ellenségek kollekciója
	private Collection<IEnemy> enemies;
	
	// Tornyok kollekciója
	private Collection<ITower> towers;
	
	// ConcreteCreatorok kollekciója
	private Map<String, ICreator> creators;
	
	// Már kifizetett kövek kollekciója
	private Map<String, IRock> rocks = new HashMap<String, IRock>();

	private List<IRoad> roads = new ArrayList<IRoad>();
	
	// Kollekciók lekérdezése
	public Map<String, IRock> getRocks() { return rocks; }
	public Collection<IEnemy> getEnemies() { return enemies; }
	public Collection<ITower> getTowers() { return towers; }
	public List<IRoad> getRoads() { return roads; }
	
	
	// Konstruktor, kötelező megadni a ConcreteCreatorok-at tartalmazó asszociatív tömböt
	public World(Map<String,ICreator> c)
	{
		creators = c;		
		towers = new ArrayList<ITower>();
		enemies = new ArrayList<IEnemy>();
	}

	// Értesítés arról, hogy idő telt el, ekkor elször a tornyok értesülnek
	// erről, majd az ellenséek, ezek után ha már minden ellenség mehalt
	// és minden körön túlvagyunk, annak beállítása, hogy a játék véget ért
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
	
	// 1-es �s 4-es utakon j�het ellens�g
	// mivel a roads kollekci�t c�mezz�k ez�rt figyelembe kell venni az indexeket
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

	// Kő létrehozása, aktuális mana átvétele. A követ a ConcreteCreator-orjának neve azonsítja
	// Kő csak akkor hozható létre, ha van rá elég mana, akkor ennek étéke csökken a kő árával
	// Visszatér a fennmaradó mana mennyiségével
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
	
	// Annak eldöntése, hogy az adott helyre lehet-e tornyok létrehozni
	// annak figyelembevételével, hogy milyen utak léteznek a világban
	private boolean canAddTower(Collection<IRoad> roads, Point location)
	{
		boolean result = true;
		for (IRoad road : roads)
		{
			// Spline lekérdezése
			Spline spline = road.getSpline();
			Point start = spline.getStart();
			Point end = spline.getEnd();
			
			// Háromszögeléses módszer alkalmazása azért, mert a spline 2 pontra feszít
			if(-start.distance(end) + location.distance(end) + location.distance(start) - 1 < 0)
			{
				result = false;
			}
						
			// Rekurzió megvalósítása
			boolean insideResult = true;
			if(road.getNextRoadParts() != null)
				insideResult = canAddTower(road.getNextRoadParts(), location);
			
			if(!insideResult)
				result = false;
		}
		return result;
	}
	
	// Torony hozzáadása, a létező utak figyelembevételével, a tonyot a ConcreteCreator-orjának neve
	// azonsítja, valamit megadjuk a pontot is
	// Visszatér azzal, mennyi mana maradt
	public long addTower(long mana,Collection<IRoad> roads, String name, Point location)
	{		
		// Ha nem lehet tornyot lerakni, visszatérés
		if(!canAddTower(roads, location)) 
			return mana;
		
		// Torony létrehozása
		ITower t = (ITower) creators.get(name).create();
		t.setWorld(this);
		t.setLocation(location);
		
		// Ha van elég mana, akkor megvizsgáljuk a többi toronyhoz nincs-e túl közel
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
			// Ha nincs túl közel más toronyhoz, akkor fel lehet venni
			if (minDist > GameStatics.MinDistanceBetweenTowers || minDist == Double.MAX_VALUE)
			{
				// Kollekcióhoz a példány hozzáadása, valamit mana csökkentése a torony árával
				towers.add(t);
				mana -= t.buildPrice();
			}
		}
		return mana;
	}

	// Ellenség hozzáadása az ellenségek kollekciójához
	public void addEnemy(IEnemy enemy)
	{
		enemies.add(enemy);
	}
	
	// Ellenség hozzáadása az ellenséek kollekciója
	// valamint a megadott út beállítása az ellenségnek
	public void addEnemy(String name, IRoad spawnOn)
	{
		IEnemy e = (IEnemy) creators.get(name).create();
		e.setRoadParameter(spawnOn.getStart());
		e.setRoad(spawnOn);
		e.setWorld(this);
		enemies.add(e);
	}
	
	// Annak felderítése, az adott ponton melyik torony van
	// (vagyis melyik torony van a ponthoz a legközelebb)
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
	
	// Kő hozzáadása ahhoz a toronyhoz ami a megadott
	// ponton található. Csak akkor lehet ezt megtenni,
	// ha ehhez van elég mana. A megmaradt manával visszatér
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
	
	// Toronyről kő levétele, ekkor az ellenségeken deaktiváljuk a
	// kő hatását is
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
	
	// Adott ponton lévő torony fejlesztése,
	// ha van rá elég mana. A megmaradt manával visszatér
	public long upgradeTower(long mana, Point location)
	{
		ITower result = whoIsOn(location);
		
		if (result != null)
			mana = result.upgrade(mana);
		return mana;
	}

	// Ellenségek számának lekérdezése
	public int getEnemyCount()
	{
		return enemies.size();
	}
	
	// Vereség lekérdezése
	public boolean getGameOver()
	{
		return gameOver;
	}
	
	// Nyereség lekérdezése
	public boolean getGameWin()
	{
		return gameWin;
	}

	// Ellenség eltávolítása a listáról
	public void removeEnemy(IEnemy enemy) 
	{
		enemies.remove(enemy);
	}

	// Vereség jelzés beállítása
	public void gameLost() 
	{
		gameOver = true;
	}
}