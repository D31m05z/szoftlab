package CCClasses;
import Towers.Rocks.FateRock;

/*
	A FateRock ConcreteCreatora,
	visszatér egy FateRock referenciával
	
	A FateRock singleton, ezért nem lehet
	meghívni a konstruktorát, viszont
	van getInstance függvénye
*/

public class CCFateRock implements ICreator 
{
	public Object create() 
	{
		Object result = FateRock.getInstance();
		return result;
	}
}