package CCClasses;
import Enemies.HumanEnemy;

/*
	A HumanEnemy ConcreteCreatora,
	visszatér egy HumanEnemy referenciával
*/

public class CCHumanEnemy implements ICreator
{
	public Object create()
	{
		Object result = new HumanEnemy();
		return result;
	}
}
