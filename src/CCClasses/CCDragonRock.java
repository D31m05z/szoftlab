package CCClasses;
import Towers.Rocks.DragonRock;

/*
	A DragonRock ConcreteCreatora,
	visszater egy DragonRock referenciaval
	
	A DragonRock singleton, ezert nem lehet
	meghivni a konstruktorat, viszont
	van getInstance fuggvenye
*/

public class CCDragonRock implements ICreator 
{
	public Object create() 
	{
		Object result = DragonRock.getInstance();
		return result;
	}
}