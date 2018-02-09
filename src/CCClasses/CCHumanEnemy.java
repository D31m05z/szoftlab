package CCClasses;
import Enemies.HumanEnemy;

/*
	A HumanEnemy ConcreteCreatora,
	visszater egy HumanEnemy referenciaval
*/

public class CCHumanEnemy implements ICreator
{
	public Object create()
	{
		Object result = new HumanEnemy();
		return result;
	}
}
