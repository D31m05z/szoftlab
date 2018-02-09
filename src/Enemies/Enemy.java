package Enemies;

/*
	Ellensgek absztrakt sosztlya. Megvalstja az
	IEnemy interfszt, s taralmazza az abban tallhat
	metdusok alaprtelmezett implementcijt
*/

import java.awt.Point;
import Main.*;
import Road.*;
import Towers.Rocks.*;

public abstract class Enemy extends Object implements IEnemy {

	// letpontok mennyisge
	protected long hp = 0;
	
	// A vilgra mutat referencia
	protected IWorld myWorld = null;
	
	// Kvek jelenltt jelz flag-ek
	protected boolean isFateOnMe = false;
	protected boolean isJarOnMe = false;
	protected boolean isDragonOnMe = false;
	
	// tparamter
	protected long roadParameter = 0;
	
	// Haladsi sebessg (mrtke tparamter/ms)
	protected long speed = 0;
	
	// Tmader
	protected long damagePower = 0;
	
	// A meglsrt jr extra mana
	protected long manabonus = 0;
	
	// Az aktulis tra mutat referencia
	protected IRoad myRoad = null;
	
	// Az aktulisan tmadand blokdra mutat referencia
	protected IBlockade target = null;

	// Tmads fogadsa, vagyis az leter cskkentse,
	// ha elfogyott, akkor a vilgbl az ellensg trlse
	// illetve a visszaadand mana kiszmtsa
	protected long damageMySelf(long damage, long mana) {
		hp -= damage;
		if (hp <= 0) {
			myWorld.removeEnemy(this);
			mana += manabonus;
		}
		return mana;
	}

	// ton trtn elrehalads metdusa. A tvols a sebessg
	// s az eltelt id (ami ms-ben rtend) szorzata). A mozgst
	// az ton kell kezdemnyezni
	public void moveForward(long dt) {
		long distance = dt * speed;
		myRoad.move(this, roadParameter, roadParameter + distance);
	}

	// Akadly megtmadsa
	public void attack() {
		if (target != null)
			target.damage(damagePower);
	}

	// Id mlsrl val rtests (ms-ben mrend). Ekkor elszr
	// ksrlet a tovbbhaladsra majd blokd (ha van) megtmadsa
	public void action(long dt) {
		moveForward(dt);
		attack();
	}
	
	// Tmadsrl val rtests
	public long damage(long damage, long mana) {
		return damageMySelf(damage, mana);
	}

	// Tmadand blokd belltsa
	public void setTarget(IBlockade blockade) {
		target = blockade;
	}

	// rtests k aktivlsrl
	public void activateRock(IRock rock) {
		rock.activateOn(this);
	}

	// rtests k deaktivlsrl
	public void deactivateRock(IRock rock) {
		rock.deactivateOn(this);
	}

	// tparamter belltsa
	public void setRoadParameter(long t) {
		roadParameter = t;
	}

	// Aktulis t belltsa
	public void setRoad(IRoad road) {
		myRoad = road;
	}

	// Koordinta lekrdezse
	public Point getLocation() {
		Point result = myRoad.getLocation(roadParameter);
		return result;
	}
	
	// Aktulis vilg belltsa
	public void setWorld(IWorld w) {
		myWorld = w;
	}

	// Kettvg tmads fogadsa:
	// leter felezse, j ellensg pldny ltrehozsa
	public void split() {
		hp = hp / 2;
		Enemy uj = copy();

		uj.hp = hp;
		uj.myRoad = myRoad;
		uj.roadParameter = roadParameter;
		myWorld.addEnemy(uj);
	}

	// Msolshoz kell konsturktorhvs
	protected abstract Enemy copy();

	// Aktulis clpontra mutat referencia elkrse
	public IBlockade getTarget() {
		return target;
	}

	// Aktulis tra mutat referencia elkrse 
	public IRoad getRoad() {
		return myRoad;
	}

	// Aktulis leter elkrse
	public long getHp() {
		return hp;
	}

	// Tmader elkrse
	public long getDamagePower() {
		return damagePower;
	}

	// Sebessg elkrse
	public long getSpeed() {
		return speed;
	}

	// tparamter elkrse
	public long getRoadParameter() {
		return roadParameter;
	}

	// Annak elkrse, hogy dragonRock aktv-e
	public boolean getDragon() {
		return isDragonOnMe;
	}

	// Annak elkrse, hogy fateRock aktv-e
	public boolean getFate() {
		return isFateOnMe;
	}

	// Annak elkrse, hogy jarRock aktv-e
	public boolean getJar() {
		return isJarOnMe;
	}

	// Annak belltsa, hogy dragonRock aktv legyen
	public void setDragon(boolean val) {
		isDragonOnMe = val;
	}

	// Annak belltsa, hogy fateRock aktv legyen
	public void setFate(boolean val) {
		isFateOnMe = val;
	}

	// Annak belltsa, hogy jarRock aktv legyen
	public void setJar(boolean val) {
		isJarOnMe = val;
	}
}
