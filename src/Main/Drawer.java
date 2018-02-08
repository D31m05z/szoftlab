package Main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.HashMap;

import javax.swing.ImageIcon;

import Enemies.*;
import Road.*;
import Towers.*;
import Towers.Rocks.IRock;

public class Drawer {
	private static Drawer d = new Drawer();
	private HashMap<String,Image> images = new HashMap<>();
	
	private final int towerWidth = 80;
	private final int towerHeight = 80;
	
	private final int rockWidth = 30;
	private final int rockHeight = 30;
	
	private final int blockadeWidth = 40;
	private final int blockadeHeight = 40;
	
	private final int elfWidth = 40;
	private final int elfHeight = 55;
	
	private final int humanWidth = 40;
	private final int humanHeight = 70;
	
	private final int hobbitWidth =40;
	private final int hobbitHeight =56;
	
	private final int dwarfWidth =40;
	private final int dwarfHeight =35;
	
	private Drawer() 
	{
		// load towers texture
		images.put("basicTower", new ImageIcon(".\\textures\\basicTower.png").getImage());
		images.put("cannonTower", new ImageIcon(".\\textures\\cannonTower.png").getImage());
		images.put("sniperTower", new ImageIcon(".\\textures\\sniperTower.png").getImage());
		
		// load blockade texture
		images.put("blockade", new ImageIcon(".\\textures\\blockade.png").getImage());
		
		// load rocks texture
		images.put("dragonRock", new ImageIcon(".\\textures\\dragonRock.png").getImage());
		images.put("fateRock", new ImageIcon(".\\textures\\fateRock.png").getImage());
		images.put("jarRock", new ImageIcon(".\\textures\\jarRock.png").getImage());
		
		// load enemies texture
		images.put("elfEnemy", new ImageIcon(".\\textures\\elfEnemy.png").getImage());
		images.put("humanEnemy", new ImageIcon(".\\textures\\humanEnemy.png").getImage());
		images.put("hobbitEnemy", new ImageIcon(".\\textures\\hobbitEnemy.png").getImage());
		images.put("dwarfEnemy", new ImageIcon(".\\textures\\dwarfEnemy.png").getImage());
		
		// load background
		images.put("background", new ImageIcon(".\\textures\\background.png").getImage());
	}
	
	public static Drawer getInstance() { return d; }
	
	public void Draw(Graphics2D g, String id, int x, int y, int w, int h)
	{
		g.drawImage(images.get(id), x, y,w,h,null);
	}
	
	private void Draw(Graphics2D g, IRock r, int x, int y)
	{
		//g.setColor(Color.BLUE);
		//g.fillArc(x-5, y-5, 10, 10, 0, 360);
		//TODO: get rock type: dragon,fate,jar
		switch(r.getClass().toString())
		{
			case "class Towers.Rocks.DragonRock":
				g.drawImage(images.get("dragonRock"), x-rockWidth/2, y-rockHeight/2,null);
				break;
			case "class Towers.Rocks.FateRock":
				g.drawImage(images.get("fateRock"), x-rockWidth/2, y-rockHeight/2,null);
				break;
			case "class Towers.Rocks.JarRock":
				g.drawImage(images.get("jarRock"), x-rockWidth/2, y-rockHeight/2,null);
				break;
		}
	}
	
	public void Draw(Graphics2D g, BasicTower t)
	{
		int x = t.getLocation().x;
		int y = t.getLocation().y;
		
		//g.setColor(Color.RED);
		//g.fillRect(x-10, y-10, 20,20);
		g.drawImage(images.get("basicTower"), x-towerWidth/2, y-towerHeight/2,null);
		
		if (t.getMyRock() != null)
		{
			Draw(g,t.getMyRock(),x,y);
		}
	}
	public void Draw(Graphics2D g, CannonTower t) 
	{
		int x = t.getLocation().x;
		int y = t.getLocation().y;
		
		//g.setColor(Color.RED);
		//g.fillRect(x-10, y-10, 20,20);
		g.drawImage(images.get("cannonTower"), x-towerWidth/2, y-towerHeight/2,null);
		
		if (t.getMyRock() != null)
		{
			Draw(g,t.getMyRock(),x,y);
		}
	}
	public void Draw(Graphics2D g, SniperTower t) 
	{
		int x = t.getLocation().x;
		int y = t.getLocation().y;
		
		//g.setColor(Color.RED);
		//g.fillRect(x-10, y-10, 20,20);
		g.drawImage(images.get("sniperTower"), x-towerWidth/2, y-towerHeight/2,null);
		
		if (t.getMyRock() != null)
		{
			Draw(g,t.getMyRock(),x,y);
		}
	}
	
	public void Draw(Graphics2D g, ElfEnemy t) 
	{
		int x = t.getLocation().x;
		int y = t.getLocation().y;
		//g.setColor(Color.GREEN);
		//g.fillRect(x-10, y-10, 20,20);
		g.drawImage(images.get("elfEnemy"), x-elfWidth/2, y-elfHeight/2,null);
	}
	public void Draw(Graphics2D g, HobbitEnemy t) 
	{
		int x = t.getLocation().x;
		int y = t.getLocation().y;
		//g.setColor(Color.GREEN);
		//g.fillRect(x-10, y-10, 20,20);
		g.drawImage(images.get("hobbitEnemy"), x-hobbitWidth/2, y-hobbitHeight/2,null);
	}
	public void Draw(Graphics2D g, HumanEnemy t) 
	{
		int x = t.getLocation().x;
		int y = t.getLocation().y;
		//g.setColor(Color.GREEN);
		//g.fillRect(x-10, y-10, 20,20);
		g.drawImage(images.get("humanEnemy"), x-humanWidth/2, y-humanHeight/2,null);
	}
	public void Draw(Graphics2D g, DwarfEnemy t) 
	{
		int x = t.getLocation().x;
		int y = t.getLocation().y;
		//g.setColor(Color.GREEN);
		//g.fillRect(x-10, y-10, 20,20);
		g.drawImage(images.get("dwarfEnemy"), x-dwarfWidth/2, y-dwarfHeight/2,null);
	}
	
	public void Draw(Graphics2D g, Blockade t) 
	{
		int x = t.getLocation().x;
		int y = t.getLocation().y;
		
		//g.setColor(Color.YELLOW);
		//g.fillRect(x-10, y-10, 20,20);
		g.drawImage(images.get("blockade"), x-blockadeWidth/2, y-blockadeHeight/2,null);
	}
}
