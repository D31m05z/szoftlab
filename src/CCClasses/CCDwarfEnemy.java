package CCClasses;
import Enemies.DwarfEnemy;

/*
	A DwarfEnemy ConcreteCreatora,
	visszater egy DwarfEnemy referenciaval
*/

public class CCDwarfEnemy implements ICreator
{
	public Object create()
	{
		Object result = new DwarfEnemy();
		return result;
	}
}