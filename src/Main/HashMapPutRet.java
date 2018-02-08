package Main;

/*
	HashMap bővítése, annyiban különbözik a HashMap-tól
	hogy van putRet metódusa, ami miután hozzáadott a
	Map-hoz egy kulcs-érték párt, visszatér a Map-pel
	
	Ez a SpawnInformációk felépítésekor hasznos
*/

import java.util.*;

public class HashMapPutRet<T, U> extends HashMap<T, U> {
	public HashMapPutRet<T, U> putRet(T key, U value)
	{
		this.put(key, value);
		return this;
	}
}
