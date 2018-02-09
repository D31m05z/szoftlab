package CCClasses;
import Towers.Rocks.FateRock;

/*
	A FateRock ConcreteCreatora,
	visszater egy FateRock referenciaval
	
	A FateRock singleton, ezert nem lehet
	meghivni a konstruktorat, viszont
	van getInstance fuggvenye
*/

public class CCFateRock implements ICreator 
{
	public Object create() 
	{
		Object result = FateRock.getInstance();
		return result;
	}
}