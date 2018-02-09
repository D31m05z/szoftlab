package CCClasses;
import Towers.Rocks.JarRock;

/*
	A JarRock ConcreteCreatora,
	visszater egy JarRock referenciaval
	
	A JarRock singleton, ezert nem lehet
	meghivni a konstruktorat, viszont
	van getInstance fuggvenye
*/

public class CCJarRock implements ICreator 
{
	public Object create() 
	{
		Object result = JarRock.getInstance();
		return result;
	}
}