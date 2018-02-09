package CCClasses;
import Enemies.ElfEnemy;

/*
	A ElfEnemy ConcreteCreatora,
	visszater egy ElfEnemy referenciaval
*/

public class CCElfEnemy implements ICreator
{
	public Object create()
	{
		Object result = new ElfEnemy();
		return result;
	}
}