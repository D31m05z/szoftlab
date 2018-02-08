package CCClasses;
import Enemies.ElfEnemy;

/*
	A ElfEnemy ConcreteCreatora,
	visszatér egy ElfEnemy referenciával
*/

public class CCElfEnemy implements ICreator
{
	public Object create()
	{
		Object result = new ElfEnemy();
		return result;
	}
}