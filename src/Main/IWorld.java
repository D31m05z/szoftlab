package Main;

/*
	A vilg interfsze, az objektumok ezen keresztl ismerik a vilgot
*/


import Enemies.IEnemy;

public interface IWorld
{
	// Ellensg trlse
	public void removeEnemy(IEnemy enemy);
	
	// Ellensg felvtele
	public void addEnemy(IEnemy enemy);
	
	// Annak jelzse, hogy egy ellensg elrte a vgzet hegyt
	public void gameLost();
}
