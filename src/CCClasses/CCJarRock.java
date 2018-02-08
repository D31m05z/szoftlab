package CCClasses;
import Towers.Rocks.JarRock;

/*
	A JarRock ConcreteCreatora,
	visszatér egy JarRock referenciával
	
	A JarRock singleton, ezért nem lehet
	meghívni a konstruktorát, viszont
	van getInstance függvénye
*/

public class CCJarRock implements ICreator 
{
	public Object create() 
	{
		Object result = JarRock.getInstance();
		return result;
	}
}