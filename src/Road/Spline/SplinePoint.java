package Road.Spline;

/*
	Spline egy pontját reprezentáló osztály,
	egységbe zár egy pontot és egy útparamétert
*/

import java.awt.Point;

public class SplinePoint 
{
	//Elhelyezkedés
	public Point loc = null;
	
	//Útparaméter
	public long t = 0;
	
	// Publikus konstruktor, közelező megadni az útparamétert és a helyet
	public SplinePoint(long t_, Point p)
	{
		loc = p;
		t = t_;
	}
}
