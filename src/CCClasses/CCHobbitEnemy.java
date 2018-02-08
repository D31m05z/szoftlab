package CCClasses;
import Enemies.HobbitEnemy;

/*
	A HobbitEnemy ConcreteCreatora,
	visszatér egy HobbitEnemy referenciával
*/

public class CCHobbitEnemy implements ICreator
{
	public Object create()
	{
		Object result = new HobbitEnemy();
		return result;
	}
}