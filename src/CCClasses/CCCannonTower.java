package CCClasses;
import Towers.CannonTower;

/*
	A CannonTower ConcreteCreatora,
	visszatér egy CannonTower referenciával
*/

public class CCCannonTower implements ICreator 
{
	public Object create() 
	{	
		Object result = new CannonTower();
		return result;
	}
}