package CCClasses;
import Enemies.HobbitEnemy;

/*
	A HobbitEnemy ConcreteCreatora,
	visszater egy HobbitEnemy referenciaval
*/

public class CCHobbitEnemy implements ICreator
{
	public Object create()
	{
		Object result = new HobbitEnemy();
		return result;
	}
}