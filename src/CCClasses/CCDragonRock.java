package CCClasses;
import Towers.Rocks.DragonRock;

/*
	A DragonRock ConcreteCreatora,
	visszatér egy DragonRock referenciával
	
	A DragonRock singleton, ezért nem lehet
	meghívni a konstruktorát, viszont
	van getInstance függvénye
*/

public class CCDragonRock implements ICreator 
{
	public Object create() 
	{
		Object result = DragonRock.getInstance();
		return result;
	}
}