package Main;

/*
	Játék konstansainak tárolása, ezt a késõbbiekben lehet helyettesíteni akár
	egy xml parsolással is, ekkor a játék újrafordítás nélkül átszabható lesz majd
*/

import java.awt.Point;
import java.util.*;


public class GameStatics
{
	// Blokád életereje
	public static long BlockadeHP = 200;
	
	// Blokáb költsége
	public static long BlockadeCost = 100;
		
	// Végzet hegyének helye
	public static Point LocOfHill = new Point(100,150);
	
	// Tornyok közötti minimális távolság
	public static float MinDistanceBetweenTowers = 50;//3
	
	// Blokádok közötti minimális távolság
	public static float MinDistanceBetweenBlockades = 100;
	
	// Tornyok adatait (ár, fejlesztések árai, hatótávolság, sebzés, lövési gyakoriság szintenként) tartalmazó osztáy
	public static class Towers
	{
		// Lövésztorony adatai
		public static class Sniper
		{
			public static long initCost = 100;
			public static long[] upgradeCosts = new long[]{100, 100, 100, 100,   0};
			public static long[] fireRate = 	new long[]{20,  30,   40,  50,  60};
			public static long[] fireRange = 	new long[]{100, 200, 300, 400, 500};
			public static long[] fireDamage = 	new long[]{10,	20,	  30,  40,  50};	
		}
		
		// Bombatorony adatai
		public static class Cannon
		{
			public static long initCost = 100;
			public static long[] upgradeCosts = new long[]{100, 100, 100, 100,   0};
			public static long[] fireRate = 	new long[]{20,  30,   40,  50,  60};
			public static long[] fireRange = 	new long[]{100, 200, 300, 400, 500};
			public static long[] fireDamage = 	new long[]{10,	20,	  30,  40,  50};	
		}
		
		// Alaptorony adatai
		public static class Basic
		{
			public static long initCost = 100;
			public static long[] upgradeCosts = new long[]{100, 100, 100, 100,   0};
			public static long[] fireRate = 	new long[]{20,  30,   40,  50,  60};
			public static long[] fireRange = 	new long[]{25, 200, 300, 400, 500};
			public static long[] fireDamage = 	new long[]{10,	20,	  30,  40,  50};	
		}
	}
	
	// Ellenségek adatait (életerõ, támadóerõ, sebesség) tartalmazó osztály
	public static class Enemies
	{
		// HumanEnemy adatai
		public static class Human
		{
			public static int initHp = 200;//100
			public static long damagePower = 10;
			public static long speed = 1;//50
		}
		
		// ElfEnemy adatai
		public static class Elf
		{
			public static int initHp = 300;//100
			public static long damagePower = 10;
			public static long speed = 1;//50
		}
		
		// HobbitEnemy adatai
		public static class Hobbit
		{
			public static int initHp = 500;//100
			public static long damagePower = 10;
			public static long speed = 2;//70
		}
		
		// DwarfEnemy adatai
		public static class Dwarf
		{
			public static int initHp = 700;//100
			public static long damagePower = 10;
			public static long speed = 1;//50
		}
	}
	
	// Kövek árai
	public static class RockCosts
	{
		public static long Jar = 1000;
		public static long Fate = 1000;
		public static long Dragon = 1000;
	}

	// Körökhöz tartozó ellenségek leírása
	public static class SpawnInfo
	{
		
		// Itt a becímzés a következõképpen néz ki:
		// 		Elsõ szám: hányadik körben spawnolunk
		//		Második szám: menyik számú útról van szó
		// Ennek az eredménye a tömb egy eleme ami Object
		// Na ezt az Objectet le kell kasztolni Map-ra
		//		Ezért itt is CSAK OLYAN cucc kerülhet bele
		//		ami HashMap vagy HashMapPutRet
		// Namost mi van ebben a Map-ban?
		//		Olyan elemek amiket az ellenség CCOsztály hivatkozási
		//		címzünk meg és az értékük meg Integer arról, hogy hány darab kell
		public static Object[][] spawn =
				new Object[][]
				{
					{
						new HashMapPutRet<String,Integer>()
							.putRet("HumanEnemy", 2)
							.putRet("ElfEnemy", 3),
						new HashMapPutRet<String,Integer>()
							.putRet("DwarfEnemy", 1)
							.putRet("HobbitEnemy", 4)
					},
					{
						new HashMapPutRet<String,Integer>()
							.putRet("DwarfEnemy", 1)
					},
					{   new HashMapPutRet<String,Integer>()
							.putRet("DwarfEnemy", 1)
							.putRet("HumanEnemy", 2)
							.putRet("ElfEnemy", 3),
					}
				};
				
		public static int maxRound = spawn.length;
	}
}
