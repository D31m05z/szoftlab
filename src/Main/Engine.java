package Main;

/// TODO: hegy koordinátát beállítani
/// TODO: utak felépítése a gép alapján

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.*;

import javax.swing.*;

import CCClasses.CCBasicTower;
import CCClasses.CCCannonTower;
import CCClasses.CCDragonRock;
import CCClasses.CCDwarfEnemy;
import CCClasses.CCElfEnemy;
import CCClasses.CCFateRock;
import CCClasses.CCHobbitEnemy;
import CCClasses.CCHumanEnemy;
import CCClasses.CCJarRock;
import CCClasses.CCSniperTower;
import CCClasses.ICreator;
import Enemies.*;
import Road.*;
import Road.Spline.SplinePoint;
import Towers.*;

public class Engine extends JFrame implements ActionListener {
	private World world = null;
	private long mana = 100000;
	
	public static void main(String[] args)
	{
		new Engine();
	}
	
	public World getWorld() { return world; }
	
	private JButton newGame;
	
	private ButtonGroup creatorsAndPlacers;
	private JRadioButton newBasicTower;
	private JRadioButton newCannonTower;
	private JRadioButton newSniperTower;
	private JRadioButton newBlockade;
	
	private JButton newDragonRock;
	private JButton newFateRock;
	private JButton newJarRock;
	
	private JRadioButton placeDragonRock;
	private JRadioButton placeFateRock;
	private JRadioButton placeJarRock;
	
	private JPanel gameArea;
	private JLabel manaStatus;
	
	private Engine()
	{
		super();
		
		JToolBar toolbar = new JToolBar();
		
		newGame = new JButton(new ImageIcon(".\\icons\\start-icon.png"));
		newGame.setToolTipText("New game");
		newGame.setRolloverIcon(new ImageIcon(".\\icons\\start-roll-icon.png"));
		newGame.addActionListener(this);
		toolbar.add(newGame);
		
		creatorsAndPlacers = new ButtonGroup();
	
		newBasicTower = new JRadioButton(new ImageIcon(".\\icons\\basicTower-icon.png"));
		newBasicTower.setToolTipText("New Basic Tower");
		newBasicTower.setSelectedIcon(new ImageIcon(".\\icons\\basicTower-roll-icon.png"));
		creatorsAndPlacers.add(newBasicTower);
		toolbar.add(newBasicTower);
		
		newCannonTower = new JRadioButton(new ImageIcon(".\\icons\\cannonTower-icon.png"));
		newCannonTower.setToolTipText("New Cannon Tower");
		newCannonTower.setSelectedIcon(new ImageIcon(".\\icons\\cannonTower-roll-icon.png"));
		creatorsAndPlacers.add(newCannonTower);
		toolbar.add(newCannonTower);
		
		newSniperTower = new JRadioButton(new ImageIcon(".\\icons\\sniperTower-icon.png"));
		newSniperTower.setToolTipText("New Sniper Tower");
		newSniperTower.setSelectedIcon(new ImageIcon(".\\icons\\sniperTower-roll-icon.png"));
		creatorsAndPlacers.add(newSniperTower);
		toolbar.add(newSniperTower);
		
		newBlockade = new JRadioButton(new ImageIcon(".\\icons\\blockade-icon.png"));
		newBlockade.setToolTipText("New Blockade");
		newBlockade.setSelectedIcon(new ImageIcon(".\\icons\\blockade-roll-icon.png"));
		creatorsAndPlacers.add(newBlockade);
		toolbar.add(newBlockade);
		
		newDragonRock = new JButton(new ImageIcon(".\\icons\\dragonRock-create-icon.png"));
		newDragonRock.setToolTipText("New Dragon Rock");
		newDragonRock.setRolloverIcon(new ImageIcon(".\\icons\\dragonRock-roll-icon.png"));
		newDragonRock.addActionListener(this);
		toolbar.add(newDragonRock);
		
		newFateRock = new JButton(new ImageIcon(".\\icons\\fateRock-create-icon.png"));
		newFateRock.setToolTipText("New Fate Rock");
		newFateRock.setRolloverIcon(new ImageIcon(".\\icons\\fateRock-roll-icon.png"));
		newFateRock.addActionListener(this);
		toolbar.add(newFateRock);
		
		newJarRock = new JButton(new ImageIcon(".\\icons\\jarRock-create-icon.png"));
		newJarRock.setToolTipText("New Jar Rock");
		newJarRock.setRolloverIcon(new ImageIcon(".\\icons\\jarRock-roll-icon.png"));
		newJarRock.addActionListener(this);
		toolbar.add(newJarRock);
		
		placeDragonRock = new JRadioButton(new ImageIcon(".\\icons\\dragonRock-icon.png"));
		placeDragonRock.setToolTipText("Place Dragon Rock");
		placeDragonRock.setSelectedIcon(new ImageIcon(".\\icons\\dragonRock-roll-icon.png"));
		creatorsAndPlacers.add(placeDragonRock);
		toolbar.add(placeDragonRock);
		
		placeFateRock = new JRadioButton(new ImageIcon(".\\icons\\fateRock-icon.png"));
		placeFateRock.setToolTipText("Place Fate Rock");
		placeFateRock.setSelectedIcon(new ImageIcon(".\\icons\\fateRock-roll-icon.png"));
		creatorsAndPlacers.add(placeFateRock);
		toolbar.add(placeFateRock);
		
		placeJarRock = new JRadioButton(new ImageIcon(".\\icons\\jarRock-icon.png"));
		placeJarRock.setToolTipText("Plave Jar Rock");
		placeJarRock.setSelectedIcon(new ImageIcon(".\\icons\\jarRock-roll-icon.png"));
		creatorsAndPlacers.add(placeJarRock);
		toolbar.add(placeJarRock);
		
		gameArea = new GameArea(this);
		gameArea.addMouseListener(new mouse());
		
		manaStatus = new JLabel("");
		manaStatus.setFont(new Font("Arial",Font.ITALIC,14));
		manaStatus.setHorizontalAlignment(JLabel.CENTER);
		
		add(toolbar,BorderLayout.NORTH);
		add(gameArea,BorderLayout.CENTER);
		add(manaStatus,BorderLayout.SOUTH);
		
		setPreferredSize(new Dimension(620,750));
		setMinimumSize(new Dimension(620,750));
		setMaximumSize(new Dimension(620,750));
		setLocation(100, 100);
		setTitle("Xanax defender");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	

	private void createNewWorld()
	{
		Map<String,ICreator> iCreators = new HashMap<String,ICreator>();
		iCreators.put("BasicTower",  new CCBasicTower());
		iCreators.put("CannonTower", new CCCannonTower());
		iCreators.put("DragonRock",  new CCDragonRock());
		iCreators.put("DwarfEnemy",  new CCDwarfEnemy());
		iCreators.put("ElfEnemy", 	 new CCElfEnemy());
		iCreators.put("FateRock", 	 new CCFateRock());
		iCreators.put("HobbitEnemy", new CCHobbitEnemy());
		iCreators.put("HumanEnemy",  new CCHumanEnemy());
		iCreators.put("JarRock",     new CCJarRock());
		iCreators.put("SniperTower", new CCSniperTower());
		world = new World(iCreators);
		
		///TODO
		// Road 1----------------------------------------------------------------
		Road r1 = new Road();
		r1.getSpline().addControlPoint(new SplinePoint(0,new Point(530,530)));
		r1.getSpline().addControlPoint(new SplinePoint(100,new Point(430,430)));
		r1.setWorld(world);
		
		// Road 2----------------------------------------------------------------
		Road r2 = new Road();
		r2.getSpline().addControlPoint(new SplinePoint(100,new Point(430,430)));
		r2.getSpline().addControlPoint(new SplinePoint(300,new Point(300,300)));
		r2.setWorld(world);
		
		r1.setNext(r2);
		
		// Road 3----------------------------------------------------------------
		Road r3 = new Road();
		r3.getSpline().addControlPoint(new SplinePoint(100,new Point(430,430)));
		r3.getSpline().addControlPoint(new SplinePoint(400,new Point(400,100)));
		r3.setWorld(world);
		
		r1.setNext(r3);
		
		// Road 4----------------------------------------------------------------
		Road r4 = new Road();
		r4.getSpline().addControlPoint(new SplinePoint(0,new Point(80,550)));
		r4.getSpline().addControlPoint(new SplinePoint(200,new Point(150,400)));
		r4.setWorld(world);
		
		// Road 5----------------------------------------------------------------
		Road r5 = new Road();
		r5.getSpline().addControlPoint(new SplinePoint(200,new Point(150,400)));
		r5.getSpline().addControlPoint(new SplinePoint(300,new Point(300,300)));
		r5.setWorld(world);
				
		r4.setNext(r5);
		
		// Road 6----------------------------------------------------------------
		Road r6 = new Road();
		r6.getSpline().addControlPoint(new SplinePoint(300,new Point(300,300)));
		r6.getSpline().addControlPoint(new SplinePoint(1000,new Point(100,150)));
		r6.setWorld(world);
		
		r2.setNext(r6);
		r5.setNext(r6);
		
		// Road 7----------------------------------------------------------------
		Road r7 = new Road();
		r7.getSpline().addControlPoint(new SplinePoint(400,new Point(400,100)));
		r7.getSpline().addControlPoint(new SplinePoint(1000,new Point(100,150)));
		r7.setWorld(world);
		
		r3.setNext(r7);
		
	
		world.getRoads().add(r1);
		world.getRoads().add(r2);
		world.getRoads().add(r3);
		world.getRoads().add(r4);
		world.getRoads().add(r5);
		world.getRoads().add(r6);
		world.getRoads().add(r7);
		
		mana = world.Update(1, mana);
		updatew = true;
		
		Thread t = new Thread(new updater());
		t.setDaemon(true);
		t.start();
	}

	public void actionPerformed(ActionEvent arg0) {
		Object s = arg0.getSource();
		if (s == newGame)
		{
			createNewWorld();
		}
		if ((s == newDragonRock)&&(world != null))
		{
			mana = world.createRock(mana, "DragonRock");
		}
		if ((s == newFateRock)&&(world != null))
		{
			mana = world.createRock(mana, "FateRock");
		}
		if ((s == newJarRock)&&(world != null))
		{
			mana = world.createRock(mana, "JarRock");
		}
	}
	
	private volatile boolean updatew = false;
	private class updater implements Runnable
	{	
		private long last = System.currentTimeMillis();
		public void run()
		{
			while (updatew)
			{
				manaStatus.setText("Current mana: "+mana);
				
				long newt = System.currentTimeMillis();
				mana = world.Update((newt-last)/300, mana);
				repaint();
				
				if (world.getGameOver())
				{
					JOptionPane.showConfirmDialog(
							getRootPane(),
							"A játék vereséggel ért véget!",
							"Ön veszített!",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					updatew = false;
					world = null;
				} else if (world.getGameWin())
				{
					JOptionPane.showConfirmDialog(
							getRootPane(),
							"A játék gyõzelemmel ért véget!",
							"Ön nyert!",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					updatew = false;
					world = null;
				}
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private class mouse extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
			if (world == null)
			{
				return;
			}
			
			String creatorName = "";
			if (newBasicTower.isSelected())  { creatorName = "BasicTower";  }
			if (newCannonTower.isSelected()) { creatorName = "CannonTower"; }
			if (newSniperTower.isSelected()) { creatorName = "SniperTower"; }
			if (!creatorName.equals(""))
			{
				mana = world.addTower(mana, world.getRoads(), creatorName, e.getPoint());
			}
			
			if (newBlockade.isSelected()) 
			{
				IRoad r = getNearestRoad(e.getPoint(), world.getRoads());
				if (r != null)
				{
					long t = getRoadParameter(r, e.getPoint());
					mana = r.addBlockade(t,mana);
				}
			}
			
			String placeName = "";
			if (placeDragonRock.isSelected()) { placeName = "DragonRock"; }
			if (placeFateRock.isSelected())   { placeName = "FateRock";   }
			if (placeJarRock.isSelected())    { placeName = "JarRock";    }
			if (!placeName.equals(""))
			{
				mana = world.addRock(mana, placeName, e.getPoint());
			}
			
		}
	}
	
	public void finalize()
	{
		updatew = false;
	}
	
	public IRoad getNearestRoad(Point loc, Collection<IRoad> roads) 
	{
		for (IRoad r: roads)
		{
			long now = getRoadParameter(r, loc);
			if (now > -0.5)
			{
				return r;
			}
			
			IRoad tmpRes = getNearestRoad(loc, r.getNextRoadParts());
			if (tmpRes != null)
			{
				return tmpRes;
			}
		}
		
		return null;
	}
	public long getRoadParameter(IRoad r, Point loc) 
	{
		Point startPoint = r.getLocation(0);
		Point endPoint = r.getLocation(10000);
		
		double sideA = startPoint.distance(endPoint);
		double sideB = startPoint.distance(loc);
		double sideC = endPoint.distance(loc);
		
		if (sideB + sideC < sideA*1.1)
		{
			return (long)((sideB / sideA) * 10000);
		}
		return -1;
	}
}
