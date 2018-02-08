package Enemies;

/*
	Ellenségek absztrakt ősosztálya. Megvalósítja az
	IEnemy interfészt, és taralmazza az abban található
	metódusok alapértelmezett implementációját
*/

import java.awt.Point;
import Main.*;
import Road.*;
import Towers.Rocks.*;

public abstract class Enemy extends Object implements IEnemy {

	// Életpontok mennyisége
	protected long hp = 0;
	
	// A világra mutató referencia
	protected IWorld myWorld = null;
	
	// Kövek jelenlétét jelző flag-ek
	protected boolean isFateOnMe = false;
	protected boolean isJarOnMe = false;
	protected boolean isDragonOnMe = false;
	
	// Útparaméter
	protected long roadParameter = 0;
	
	// Haladási sebesség (mértéke útparaméter/ms)
	protected long speed = 0;
	
	// Támadóerő
	protected long damagePower = 0;
	
	// A megölésért járó extra mana
	protected long manabonus = 0;
	
	// Az aktuális útra mutató referencia
	protected IRoad myRoad = null;
	
	// Az aktuálisan támadandó blokádra mutató referencia
	protected IBlockade target = null;

	// Támadás fogadása, vagyis az életerő csökkentése,
	// ha elfogyott, akkor a világból az ellenség törlése
	// illetve a visszaadandó mana kiszámítása
	protected long damageMySelf(long damage, long mana) {
		hp -= damage;
		if (hp <= 0) {
			myWorld.removeEnemy(this);
			mana += manabonus;
		}
		return mana;
	}

	// Úton történő előrehaladás metódusa. A távolás a sebesség
	// és az eltelt idő (ami ms-ben értendő) szorzata). A mozgást
	// az úton kell kezdeményezni
	public void moveForward(long dt) {
		long distance = dt * speed;
		myRoad.move(this, roadParameter, roadParameter + distance);
	}

	// Akadály megtámadása
	public void attack() {
		if (target != null)
			target.damage(damagePower);
	}

	// Idő múlásáról való értesítés (ms-ben mérendő). Ekkor először
	// kísérlet a továbbhaladásra majd blokád (ha van) megtámadása
	public void action(long dt) {
		moveForward(dt);
		attack();
	}
	
	// Támadásról való értesítés
	public long damage(long damage, long mana) {
		return damageMySelf(damage, mana);
	}

	// Támadandó blokád beállítása
	public void setTarget(IBlockade blockade) {
		target = blockade;
	}

	// Értesítés kő aktiválásáról
	public void activateRock(IRock rock) {
		rock.activateOn(this);
	}

	// Értesítés kő deaktiválásáról
	public void deactivateRock(IRock rock) {
		rock.deactivateOn(this);
	}

	// Útparaméter beállítása
	public void setRoadParameter(long t) {
		roadParameter = t;
	}

	// Aktuális út beállítása
	public void setRoad(IRoad road) {
		myRoad = road;
	}

	// Koordináta lekérdezése
	public Point getLocation() {
		Point result = myRoad.getLocation(roadParameter);
		return result;
	}
	
	// Aktuális világ beállítása
	public void setWorld(IWorld w) {
		myWorld = w;
	}

	// Kettévágó támadás fogadása:
	// életerő felezése, új ellenség példány létrehozása
	public void split() {
		hp = hp / 2;
		Enemy uj = copy();

		uj.hp = hp;
		uj.myRoad = myRoad;
		uj.roadParameter = roadParameter;
		myWorld.addEnemy(uj);
	}

	// Másoláshoz kellő konsturktorhívás
	protected abstract Enemy copy();

	// Aktuális célpontra mutató referencia elkérése
	public IBlockade getTarget() {
		return target;
	}

	// Aktuális útra mutató referencia elkérése 
	public IRoad getRoad() {
		return myRoad;
	}

	// Aktuális életerő elkérése
	public long getHp() {
		return hp;
	}

	// Támadóerő elkérése
	public long getDamagePower() {
		return damagePower;
	}

	// Sebesség elkérése
	public long getSpeed() {
		return speed;
	}

	// Útparaméter elkérése
	public long getRoadParameter() {
		return roadParameter;
	}

	// Annak elkérése, hogy dragonRock aktív-e
	public boolean getDragon() {
		return isDragonOnMe;
	}

	// Annak elkérése, hogy fateRock aktív-e
	public boolean getFate() {
		return isFateOnMe;
	}

	// Annak elkérése, hogy jarRock aktív-e
	public boolean getJar() {
		return isJarOnMe;
	}

	// Annak beállítása, hogy dragonRock aktív legyen
	public void setDragon(boolean val) {
		isDragonOnMe = val;
	}

	// Annak beállítása, hogy fateRock aktív legyen
	public void setFate(boolean val) {
		isFateOnMe = val;
	}

	// Annak beállítása, hogy jarRock aktív legyen
	public void setJar(boolean val) {
		isJarOnMe = val;
	}
}
