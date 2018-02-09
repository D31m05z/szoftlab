package Main;

/*
	Jtk konstansainak trolsa, ezt a ksbbiekben lehet helyettesteni akr
	egy xml parsolssal is, ekkor a jtk jrafordts nlkl tszabhat lesz majd
*/

import java.awt.Point;
import java.util.*;


public class GameStatics
{
	// Blokd letereje
	public static long BlockadeHP = 200;
	
	// Blokb kltsge
	public static long BlockadeCost = 100;
		
	// Vgzet hegynek helye
	public static Point LocOfHill = new Point(100,150);
	
	// Tornyok kztti minimlis tvolsg
	public static float MinDistanceBetweenTowers = 50;//3
	
	// Blokdok kztti minimlis tvolsg
	public static float MinDistanceBetweenBlockades = 100;
	
	// Tornyok adatait (r, fejlesztsek rai, hattvolsg, sebzs, lvsi gyakorisg szintenknt) tartalmaz oszty
	public static class Towers
	{
		// Lvsztorony adatai
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
	
	// Ellensgek adatait (leter, tmader, sebessg) tartalmaz osztly
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
	
	// Kvek rai
	public static class RockCosts
	{
		public static long Jar = 1000;
		public static long Fate = 1000;
		public static long Dragon = 1000;
	}

	// Krkhz tartoz ellensgek lersa
	public static class SpawnInfo
	{
		
		// Itt a becmzs a kvetkezkppen nz ki:
		// 		Els szm: hnyadik krben spawnolunk
		//		Msodik szm: menyik szm trl van sz
		// Ennek az eredmnye a tmb egy eleme ami Object
		// Na ezt az Objectet le kell kasztolni Map-ra
		//		Ezrt itt is CSAK OLYAN cucc kerlhet bele
		//		ami HashMap vagy HashMapPutRet
		// Namost mi van ebben a Map-ban?
		//		Olyan elemek amiket az ellensg CCOsztly hivatkozsi
		//		cmznk meg s az rtkk meg Integer arrl, hogy hny darab kell
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
