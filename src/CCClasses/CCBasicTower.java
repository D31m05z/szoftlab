package CCClasses;
import Towers.BasicTower;

/*
	A BasicTower ConcreteCreatora,
	visszatér egy BasicTower referenciával
*/

public class CCBasicTower implements ICreator 
{
	public Object create() 
	{		
		Object result = new BasicTower();
		return result;
	}
}