package Enemies;

/*
	Az ellensĂ©gek interfĂ©sze, a vilĂˇg, tornyok Ă©s utak
	Ăˇltal lĂˇthatĂł metĂłdusok gyĹ±jtemĂ©nye.
*/

import java.awt.Point;

import Main.Drawable;
import Main.IWorld;
import Road.IBlockade;
import Road.IRoad;
import Towers.Rocks.IRock;

public interface IEnemy extends Drawable
{
	// Az idĹ‘ mĂşlĂˇsĂˇrĂłl valĂł Ă©rtesĂ­tĂ©s
	public void action(long dt);
	
	// A pĂˇlyĂˇn lĂ©vĹ‘ pozĂ­ciĂł lekĂ©rdezĂ©se
	public Point getLocation();
	
	// Az ellensĂ©g Ă©rtesĂ­tĂ©se arrĂłl, hogy megtĂˇmadtĂˇk. Ă�tveszi mennyi az aktuĂˇlis
	// mana mennyisĂ©ge, Ă©s visszatĂ©r azzal, mennyi az Ăşj mana mennyisĂ©ge
	// (ha az ellensĂ©g meghal, manĂˇt generĂˇl)
	public long damage(long damage, long mana);
	
	// Az ellensĂ©g Ă©rtesĂ­tĂ©se arrĂłl, hogy rajta az adott kĹ‘ hatĂˇsa innentĹ‘l Ă©rvĂ©nyes
	public void activateRock(IRock rock);
	
	// Az ellensĂ©g Ă©rtesĂ­tĂ©se arrĂłl, hogy rajta az adott kĹ‘ hatĂˇsa innentĹ‘l nem Ă©rvĂ©nyes
	public void deactivateRock(IRock rock);
	
	// Az aktuĂˇlis Ăşt beĂˇllĂ­tĂˇsa
	public void setRoad(IRoad road);
	
	// Az aktuĂˇlis ĂştparamĂ©ter beĂˇllĂ­tĂˇsa
	public void setRoadParameter(long t);
	
	// Az aktuĂˇlis vilĂˇg beĂˇllĂ­tĂˇsa
	public void setWorld(IWorld w);
	
	// A jelenleg tĂˇmadandĂł blokĂˇd beĂˇllĂ­tĂˇsa
	public void setTarget(IBlockade blockade);
	
	// "FelezĹ‘" tĂˇmadĂˇs fogadĂˇsĂˇra szolgĂˇlĂł metĂłdus
	public void split();
	
	// Az aktuĂˇlis blokĂˇd megtĂˇmadĂˇsĂˇt kezdemĂ©nyezĹ‘ metĂłdus 
	public void attack();
	
	// Ă‰rtesĂ­tĂ©s errĂłl, hogy az aktuĂˇlis Ăşton adott
	// mennyisĂ©gĹ± idĹ‘ eltelĂ©se miatt tovĂˇbb kell haladni.
	public void moveForward(long dt);
}
