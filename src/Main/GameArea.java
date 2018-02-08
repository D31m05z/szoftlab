package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Enemies.IEnemy;
import Road.Blockade;
import Road.IBlockade;
import Road.IRoad;
import Road.Road;
import Road.Spline.SplinePoint;
import Towers.ITower;

public class GameArea extends JPanel {
	private Engine e;
	
	public GameArea(Engine e_in)
	{
		e = e_in;
	}
	
	public static class Line {
	    public final int x1;
	    public final int x2;
	    public final int y1;
	    public final int y2;
	    public Line(int x1, int y1, int x2, int y2) {
	        this.x1 = x1;
	        this.x2 = x2;
	        this.y1 = y1;
	        this.y2 = y2;
	    }
	    public void paint(Graphics g) {
	    	g.setColor(Color.RED);
	        g.drawLine(this.x1, this.y1, this.x2, this.y2);
	    }
	}
	
	public void paint(Graphics g_in)
	{
		World world = e.getWorld();
		if (world != null)
		{
			Graphics2D g = (Graphics2D)g_in;
			
			// DrawBackground image
			Drawer.getInstance().Draw(g, "background", 0, 0, 600, 600);
		
			for (IEnemy e:world.getEnemies())
			{
				e.draw(g);
			}
			for (ITower t:world.getTowers())
			{
				t.draw(g);
			}
	
			for (IRoad ir : e.getWorld().getRoads())
			{
				int x_prev=-1;
				int y_prev=-1;
				
				Road r = (Road)ir;
				drawBlockades(g, r);
				
				for(SplinePoint point : r.getSpline().points)
				{
					int x = point.loc.x;
					int y = point.loc.y;
				
					if(x_prev!=-1 && y_prev!=-1)
					{
						//g.setColor(Color.RED);
						//g.fillRect(x, y, 5, 5);
						
						//Line line = new Line(x_prev, y_prev, x, y);
						//line.paint(g);
					}
					
				    x_prev = x;
				    y_prev = y;
				}
			}
		}
	}
	
	private void drawBlockades(Graphics2D g, Road r)
	{
		for (IBlockade ib : r.getBlockades())
		{
			Blockade b = (Blockade)ib;
			b.draw(g);
		}
		for (IRoad ir : r.getNextRoadParts())
		{
			Road r2 = (Road)ir;
			drawBlockades(g, r2);
		}
	}
}
