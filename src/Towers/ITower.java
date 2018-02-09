package Towers;

/*
	Torony Interface, ezen keresztl ltjk a torony fggvnyeit.
*/

import java.awt.Point;
import java.util.Collection;

import Enemies.IEnemy;
import Main.Drawable;
import Main.IWorld;
import Towers.Rocks.IRock;

public interface ITower extends Drawable
{
	// Az id mlsrl val rtests
	public long action(long dt, Collection<IEnemy> victims, long mana);
	
	// Felrakja a toronyra a kvet
	public void applyRock(IRock r);
	
	// Eltvoltja a toronyrl a kvet
	public void removeRock();
	
	// Visszaadja a tornyon lv kvet
	public IRock getMyRock();
	
	// Belltja a vilgot a torony szmra
	public void setWorld(IWorld w);
	
	// Belltja a torony helyzett
	public void setLocation(Point p);
	
	// Visszaadja a torony aktulis helyzett
	public Point getLocation();
	
	// A torony kivlasztja azt az ellensget amelyet tmadni fog
	public void chooseVictim(Collection<IEnemy> victims);
	
	// Megtmadja a kivlasztott ellenfelet
	public long attackLastChoosed(long mana);
	
	// A torony fejlesztse
	public long upgrade(long myMana);
	
	// Kikri a GameStatic tl a torony manart
	public long buildPrice();
	
	// Aktivlja az aktulis k hatst az ellensges egysgeken
	public void useMyRock(Collection<IEnemy> victims, boolean inside, boolean activate);
}
