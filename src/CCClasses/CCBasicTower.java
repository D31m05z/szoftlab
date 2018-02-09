package CCClasses;
import Towers.BasicTower;

/*
	A BasicTower ConcreteCreatora,
	visszater egy BasicTower referenciaval
*/

public class CCBasicTower implements ICreator 
{
	public Object create() 
	{		
		Object result = new BasicTower();
		return result;
	}
}