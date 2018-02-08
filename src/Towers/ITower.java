package Towers;

/*
	Torony Interface, ezen keresztül látják a torony függvényeit.
*/

import java.awt.Point;
import java.util.Collection;

import Enemies.IEnemy;
import Main.Drawable;
import Main.IWorld;
import Towers.Rocks.IRock;

public interface ITower extends Drawable
{
	// Az idő múlásáról való értesítés
	public long action(long dt, Collection<IEnemy> victims, long mana);
	
	// Felrakja a toronyra a követ
	public void applyRock(IRock r);
	
	// Eltávolítja a toronyról a követ
	public void removeRock();
	
	// Visszaadja a tornyon lévő követ
	public IRock getMyRock();
	
	// Beállítja a világot a torony számára
	public void setWorld(IWorld w);
	
	// Beállítja a torony helyzetét
	public void setLocation(Point p);
	
	// Visszaadja a torony aktuális helyzetét
	public Point getLocation();
	
	// A torony kiválasztja azt az ellenséget amelyet támadni fog
	public void chooseVictim(Collection<IEnemy> victims);
	
	// Megtámadja a kiválasztott ellenfelet
	public long attackLastChoosed(long mana);
	
	// A torony fejlesztése
	public long upgrade(long myMana);
	
	// Kikéri a GameStatic tól a torony manaárát
	public long buildPrice();
	
	// Aktiválja az aktuális kő hatását az ellenséges egységeken
	public void useMyRock(Collection<IEnemy> victims, boolean inside, boolean activate);
}
