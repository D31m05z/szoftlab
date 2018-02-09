package CCClasses;
import Towers.CannonTower;

/*
	A CannonTower ConcreteCreatora,
	visszater egy CannonTower referenciaval
*/

public class CCCannonTower implements ICreator 
{
	public Object create() 
	{	
		Object result = new CannonTower();
		return result;
	}
}