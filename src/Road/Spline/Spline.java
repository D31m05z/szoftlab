package Road.Spline;

/*
	Splinet megtestesítő osztály,
	protohoz egyszerűsítve, 2 pontra feszítve
*/

import java.awt.*;
import java.util.*;

public class Spline 
{
	// Kontrollpontok halmaza
	public ArrayList<SplinePoint> points = new ArrayList<SplinePoint>();	

	// Kontrollpont hozzáadása
	public void addControlPoint(SplinePoint sp)
	{
		points.add(sp);
	}

	// Spline kezdetének lekérdezése
	public Point getStart() 
	{
		return points.get(0).loc;
	}

	// Spline végének lekérdezése
	public Point getEnd() 
	{
		return points.get(1).loc;
	}
	
	// Spline adott útparaméterhez
	// tartozó poontjának lekérdezése
	// (súlyozott átlaga a kezdő és a végpontnak)
	public Point getPoint(long t)
	{
		if (points.size() != 2)
			return null;
		
		Point p1 = points.get(0).loc;
		Point p2 = points.get(1).loc;
		
		double t01 = ((double)t) / 10000;
		
		Point p = new Point();
		
		p.setLocation(p1.x * (1-t01) + p2.x * t01, p1.y * (1-t01) + p2.y * t01);
		
		return p;
	}
}
