package Main;

/*
	A világ interfésze, az objektumok ezen keresztül ismerik a világot
*/


import Enemies.IEnemy;

public interface IWorld
{
	// Ellenség törlése
	public void removeEnemy(IEnemy enemy);
	
	// Ellenség felvétele
	public void addEnemy(IEnemy enemy);
	
	// Annak jelzése, hogy egy ellenség elérte a végzet hegyét
	public void gameLost();
}
