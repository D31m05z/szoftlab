package Main;

/*
	J�t�k konstansainak t�rol�sa, ezt a k�s�bbiekben lehet helyettes�teni ak�r
	egy xml parsol�ssal is, ekkor a j�t�k �jraford�t�s n�lk�l �tszabhat� lesz majd
*/

import java.awt.Point;
import java.util.*;


public class GameStatics
{
	// Blok�d �letereje
	public static long BlockadeHP = 200;
	
	// Blok�b k�lts�ge
	public static long BlockadeCost = 100;
		
	// V�gzet hegy�nek helye
	public static Point LocOfHill = new Point(100,150);
	
	// Tornyok k�z�tti minim�lis t�vols�g
	public static float MinDistanceBetweenTowers = 50;//3
	
	// Blok�dok k�z�tti minim�lis t�vols�g
	public static float MinDistanceBetweenBlockades = 100;
	
	// Tornyok adatait (�r, fejleszt�sek �rai, hat�t�vols�g, sebz�s, l�v�si gyakoris�g szintenk�nt) tartalmaz� oszt�y
	public static class Towers
	{
		// L�v�sztorony adatai
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
	
	// Ellens�gek adatait (�leter�, t�mad�er�, sebess�g) tartalmaz� oszt�ly
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
	
	// K�vek �rai
	public static class RockCosts
	{
		public static long Jar = 1000;
		public static long Fate = 1000;
		public static long Dragon = 1000;
	}

	// K�r�kh�z tartoz� ellens�gek le�r�sa
	public static class SpawnInfo
	{
		
		// Itt a bec�mz�s a k�vetkez�k�ppen n�z ki:
		// 		Els� sz�m: h�nyadik k�rben spawnolunk
		//		M�sodik sz�m: menyik sz�m� �tr�l van sz�
		// Ennek az eredm�nye a t�mb egy eleme ami Object
		// Na ezt az Objectet le kell kasztolni Map-ra
		//		Ez�rt itt is CSAK OLYAN cucc ker�lhet bele
		//		ami HashMap vagy HashMapPutRet
		// Namost mi van ebben a Map-ban?
		//		Olyan elemek amiket az ellens�g CCOszt�ly hivatkoz�si
		//		c�mz�nk meg �s az �rt�k�k meg Integer arr�l, hogy h�ny darab kell
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
