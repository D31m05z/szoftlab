package CCClasses;
import Enemies.DwarfEnemy;

/*
	A DwarfEnemy ConcreteCreatora,
	visszatér egy DwarfEnemy referenciával
*/

public class CCDwarfEnemy implements ICreator
{
	public Object create()
	{
		Object result = new DwarfEnemy();
		return result;
	}
}