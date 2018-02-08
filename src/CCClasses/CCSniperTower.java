package CCClasses;
import Towers.SniperTower;

/*
	A SniperTower ConcreteCreatora,
	visszatér egy SniperTower referenciával
*/

public class CCSniperTower implements ICreator 
{
	public Object create() 
	{
		Object result = new SniperTower();
		return result;
	}
}