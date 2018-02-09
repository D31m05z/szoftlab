package Main;

/*
	HashMap bvtse, annyiban klnbzik a HashMap-tl
	hogy van putRet metdusa, ami miutn hozzadott a
	Map-hoz egy kulcs-rtk prt, visszatr a Map-pel
	
	Ez a SpawnInformcik felptsekor hasznos
*/

import java.util.*;

public class HashMapPutRet<T, U> extends HashMap<T, U> {
	public HashMapPutRet<T, U> putRet(T key, U value)
	{
		this.put(key, value);
		return this;
	}
}
