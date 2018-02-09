package CCClasses;
import Towers.SniperTower;

/*
	A SniperTower ConcreteCreatora,
	visszater egy SniperTower referenciaval
*/

public class CCSniperTower implements ICreator 
{
	public Object create() 
	{
		Object result = new SniperTower();
		return result;
	}
}