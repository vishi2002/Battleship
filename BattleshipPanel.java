import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BattleshipPanel extends JPanel 

{
	private JButton Start;
	private JButton Place;
	int count = 0;
	boolean rotate = false;
	boolean shipplaced = false;
	boolean computerWon = false;
	boolean playerWon = false;
	int[][] gridComputer = { {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
		       {11, 12, 13, 14, 15, 16, 17, 18, 19, 20},
            {21, 22, 23, 24, 25, 26, 27, 28, 29, 30},
            {31, 32, 33, 34, 35, 36, 37, 38, 39, 40},
            {41, 42, 43, 44, 45, 46, 47, 48, 49, 50},
            {51, 52, 53, 54, 55, 56, 57, 58, 59, 60},
            {61, 62, 63, 64, 65, 66, 67, 68, 69, 70},
            {71, 72, 73, 74, 75, 76, 77, 78, 79, 80},
            {81, 82, 83, 84, 85, 86, 87, 88, 89, 90},
            {91, 92, 93, 94, 95, 96, 97, 98, 99, 100} };

	
	int[][] gridPlayer = { {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
			       {11, 12, 13, 14, 15, 16, 17, 18, 19, 20},
	               {21, 22, 23, 24, 25, 26, 27, 28, 29, 30},
	               {31, 32, 33, 34, 35, 36, 37, 38, 39, 40},
	               {41, 42, 43, 44, 45, 46, 47, 48, 49, 50},
	               {51, 52, 53, 54, 55, 56, 57, 58, 59, 60},
	               {61, 62, 63, 64, 65, 66, 67, 68, 69, 70},
	               {71, 72, 73, 74, 75, 76, 77, 78, 79, 80},
	               {81, 82, 83, 84, 85, 86, 87, 88, 89, 90},
	               {91, 92, 93, 94, 95, 96, 97, 98, 99, 100} };
	ArrayList <Integer> Hits = new ArrayList<Integer>();
	ArrayList <Integer> ComputerHits = new ArrayList<Integer>();
	ArrayList <Integer> PlayerHits = new ArrayList<Integer>();
	ArrayList <Integer> PlayerMisses = new ArrayList<Integer>();
	ArrayList <Integer> ComputerMisses = new ArrayList<Integer>();
	ArrayList <Integer> ComputerStrikes = new ArrayList<Integer>();
	public BattleshipPanel()
	{
		Start = new JButton("Start");
		Place = new JButton("Place");
		
		Start.addActionListener(new StartListener());
		add(Start);
		
		Place.addActionListener(new PlaceListener());
		add(Place);
		//for (int row = 0; row < gridPlayer.length; row++)
		//{
			//for (int col = 0; col < gridPlayer[row].length; col++)
				//System.out.print(gridPlayer[row][col] + "\t");
			//System.out.println();
		//}
		addMouseListener(new ShipListener());
		addMouseListener(new ShipPlacer());
		addMouseListener(new Player());
		
		Random generator = new Random();
		int ComputerSubmarine = generator.nextInt(100) + 1;
		ComputerHits.add(ComputerSubmarine);
		int ComputerDestroyer = generator.nextInt(90) + 11;
			while (ComputerHits.contains(ComputerDestroyer) || ComputerHits.contains(ComputerDestroyer - 10))
			{
				ComputerDestroyer = generator.nextInt(100) + 1;
			}
		ComputerHits.add(ComputerDestroyer);
		ComputerHits.add(ComputerDestroyer - 10);
		int ComputerCruiser = generator.nextInt(100) + 1;
			while (ComputerHits.contains(ComputerCruiser) || ComputerHits.contains(ComputerCruiser + 1) || ComputerHits.contains(ComputerCruiser + 2) || ComputerCruiser % 10 == 0 || (ComputerCruiser + 1) % 10 == 0)
			{
				ComputerCruiser = generator.nextInt(100) + 1;
			}
		ComputerHits.add(ComputerCruiser);
		ComputerHits.add(ComputerCruiser + 1);
		ComputerHits.add(ComputerCruiser + 2);
		int ComputerBattleship = generator.nextInt(70) + 31;
		while (ComputerHits.contains(ComputerBattleship) || ComputerHits.contains(ComputerBattleship - 10) || ComputerHits.contains(ComputerBattleship - 20) || ComputerHits.contains(ComputerBattleship - 30))
		{
			ComputerBattleship = generator.nextInt(100) + 1;
		}
		ComputerHits.add(ComputerBattleship);
		ComputerHits.add(ComputerBattleship - 10);
		ComputerHits.add(ComputerBattleship - 20);
		ComputerHits.add(ComputerBattleship - 30);
		int ComputerCarrier = generator.nextInt(60) + 41;
		while (ComputerHits.contains(ComputerCarrier) || ComputerHits.contains(ComputerCarrier - 10) || ComputerHits.contains(ComputerCarrier - 20) || ComputerHits.contains(ComputerCarrier - 30) || ComputerHits.contains(ComputerCarrier - 40) )
		{
			ComputerCarrier = generator.nextInt(100) + 1;
		}
		ComputerHits.add(ComputerCarrier);
		ComputerHits.add(ComputerCarrier - 10);
		ComputerHits.add(ComputerCarrier - 20);
		ComputerHits.add(ComputerCarrier - 30);
		ComputerHits.add(ComputerCarrier - 40);
		//System.out.print(ComputerHits);
		
	}
	
	private class StartListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			count = 1;
			repaint();
		}
		
	}
	
	private class PlaceListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			shipplaced = true;
			count++;
			repaint();
		}
		
	}
	
	private class ShipPlacer implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			int x1 = e.getX();
			int y1 = e.getY();
			
			for (int i = 1; i < 11; i++)
			{
				int a = (50 * i) - 25;
				int b = (50 * (i + 1)) - 25;
			for (int j = 0; j < 11; j++)
			{
				int c = (50 * j) + 150;
				int d = (50 * (j + 1)) + 150;
				
				if  ((x1 >= a && x1 < b) && (y1 >= c && y1 <= d))
				{
					
					if (!Hits.contains(i + (10 * j) - 10))
					{
						shipplaced = false;
						Hits.add(i + (10 * j) - 10);
					}
					else
					{
						Hits.remove(Hits.indexOf(i + (10 * j) - 10));
					}
				}
			}
			}
		
			//System.out.print(Hits);
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class Player implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			int x1 = e.getX();
			int y1 = e.getY();
			//System.out.println("x: " +x1);
			//System.out.println("y: " +y1);
			for (int i = 1; i < 11; i++)
			{
				int a = (50 * i) + 875;
				int b = (50 * (i + 1)) + 875;
			for (int j = 0; j < 11; j++)
			{
				int c = (50 * j) + 150;
				int d = (50 * (j + 1)) + 150;
				
				if  ((x1 >= a && x1 < b) && (y1 >= c && y1 <= d))
				{
				
				int attempt = (i + (10 * j) - 10);
				//System.out.print(attempt);
				if (ComputerHits.contains(attempt))
				{
					ComputerHits.remove(ComputerHits.indexOf(attempt));
					PlayerHits.add(attempt);
				}
				else
				PlayerMisses.add(attempt);
				
				Random generator = new Random();
				int compattempt = generator.nextInt(100) + 1;
				while (ComputerStrikes.contains(compattempt) || ComputerMisses.contains(compattempt))
				{
				compattempt = generator.nextInt(100) + 1;
				}
				if (Hits.contains(compattempt))
				{
					ComputerStrikes.add(compattempt);
				}
				else
				ComputerMisses.add(compattempt);
				
				if (ComputerStrikes.size() == 15)
				{
					count = 7;
				}
				
				if (ComputerHits.size() == 0 || PlayerHits.size() == 15)
				{
					count = 8;
				}
				
				
				}
			}
			}
			
			
	
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	private class ShipListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			int x1 = e.getX();
			int y1 = e.getY();
		if (x1 >= 530 && x1 <= 780 && y1 >= 300 && y1 <= 550)
			{
			if (!rotate)
			{
			rotate = true;
			}
			else
			rotate = false;
			}
		
	
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
		
	}
	
	public void paintComponent(Graphics g)
	{
	super.paintComponent(g);
	setBackground(Color.cyan);
	g.setColor(Color.PINK);
	Font z = new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 130);
	g.setFont(z);
	g.drawString("BATTLESHIP", 430, 150);
	g.setColor(Color.RED);
	g.drawString("BATTLESHIP", 433, 145);
	g.setColor(Color.BLACK);
	Font x = new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20);
	g.setFont(x);
	g.drawString("Player", 250, 170);
	g.drawString("Computer", 1100, 170);
	for (int row = 0; row < gridPlayer.length * 50; row+= 50)
	{
		for (int col = 0; col < (gridPlayer.length * 50); col+= 50)
		g.drawRect(25 + row, 200 + col, 50, 50);
		
	}
	
	for (int row = 0; row < gridComputer.length * 50; row+= 50)
	{
		for (int col = 0; col < (gridComputer.length * 50); col+= 50)
		g.drawRect(925 + row, 200 + col, 50, 50);
		
	}
	
	Graphics2D g2d = (Graphics2D)g;
	if (count == 0)
	{
	g.drawString("Welcome to Battleship!", 530, 200);
	g.drawString("Click the Start Button to begin", 530, 225);
	}
	if (count == 1)
	{
	g.drawString("Let's start with setting your ships.", 530, 250);
	g.drawString("Your first ship is an Aircraft Carrier (5 Squares)", 530, 275);
	g2d.setColor(Color.RED);
	Rectangle Carrier = new Rectangle(530, 300, 250, 50);
	Rectangle CarrierRotated = new Rectangle(530, 300, 50, 250);
		if (!rotate)
		{
		g2d.draw(Carrier);
		g2d.fill(Carrier);
		}
	g.setColor(Color.black);
	g.drawString("To rotate the ship, click on it", 530, 575);
		if (rotate)
		{
		g2d.setColor(Color.RED);
		//g2d.translate(Carrier.x+(Carrier.width/2), Carrier.y+(Carrier.height/2));
		//g2d.rotate(Math.toRadians(45));
		g2d.draw(CarrierRotated);
		g2d.fill(CarrierRotated);
		}
	g.setColor(Color.black);
	g.drawString("Click on 5 consecutive blocks, where the ship goes", 530, 600);
	g.drawString("The blocks you select will light up", 530, 625);
	g.drawString("Click the place button when finished", 530, 650);
	}
	
	if (count == 2)
	{
		g.setColor(Color.black);
		g.drawString("The next ship is the Battleship (4 squares)", 530, 250);
		g2d.setColor(Color.RED);
		Rectangle BattleShip = new Rectangle(530, 300, 200, 50);
		Rectangle BattleShipRotated = new Rectangle(530, 300, 50, 200);
			if (!rotate)
			{
			g2d.draw(BattleShip);
			g2d.fill(BattleShip);
			}
		g.setColor(Color.black);
		g.drawString("To rotate the ship, click on it", 530, 575);
			if (rotate)
			{
			g2d.setColor(Color.RED);
			//g2d.translate(Carrier.x+(Carrier.width/2), Carrier.y+(Carrier.height/2));
			//g2d.rotate(Math.toRadians(45));
			g2d.draw(BattleShipRotated);
			g2d.fill(BattleShipRotated);
			}
		g.setColor(Color.black);
		g.drawString("Click on 4 consecutive blocks, where the ship goes", 530, 600);
		g.drawString("The blocks you select will light up", 530, 625);
		g.drawString("Click the place button when finished", 530, 650);
	}
	
	if (count == 3)
	{
		g.setColor(Color.black);
		g.drawString("The next ship is the Cruiser (3 squares)", 530, 250);
		g2d.setColor(Color.RED);
		Rectangle Cruiser = new Rectangle(530, 300, 150, 50);
		Rectangle CruiserRotated = new Rectangle(530, 300, 50, 150);
			if (!rotate)
			{
			g2d.draw(Cruiser);
			g2d.fill(Cruiser);
			}
		g.setColor(Color.black);
		g.drawString("To rotate the ship, click on it", 530, 575);
			if (rotate)
			{
			g2d.setColor(Color.RED);
			//g2d.translate(Carrier.x+(Carrier.width/2), Carrier.y+(Carrier.height/2));
			//g2d.rotate(Math.toRadians(45));
			g2d.draw(CruiserRotated);
			g2d.fill(CruiserRotated);
			}
		g.setColor(Color.black);
		g.drawString("Click on 3 consecutive blocks, where the ship goes", 530, 600);
		g.drawString("The blocks you select will light up", 530, 625);
		g.drawString("Click the place button when finished", 530, 650);
	}
	
	if (count == 4)
	{
		g.setColor(Color.black);
		g.drawString("The next ship is the Destroyer (2 squares)", 530, 250);
		g2d.setColor(Color.RED);
		Rectangle Destroyer = new Rectangle(530, 300, 100, 50);
		Rectangle DestroyerRotated = new Rectangle(530, 300, 50, 100);
			if (!rotate)
			{
			g2d.draw(Destroyer);
			g2d.fill(Destroyer);
			}
		g.setColor(Color.black);
		g.drawString("To rotate the ship, click on it", 530, 575);
			if (rotate)
			{
			g2d.setColor(Color.RED);
			//g2d.translate(Carrier.x+(Carrier.width/2), Carrier.y+(Carrier.height/2));
			//g2d.rotate(Math.toRadians(45));
			g2d.draw(DestroyerRotated);
			g2d.fill(DestroyerRotated);
			}
		g.setColor(Color.black);
		g.drawString("Click on 2 consecutive blocks, where the ship goes", 530, 600);
		g.drawString("The blocks you select will light up", 530, 625);
		g.drawString("Click the place button when finished", 530, 650);
	}
	
	if (count == 5)
	{
		g.setColor(Color.black);
		g.drawString("The last ship is the Submarine (1 square)", 530, 250);
		g2d.setColor(Color.RED);
		Rectangle Submarine = new Rectangle(530, 300, 50, 50);
			g2d.draw(Submarine);
			g2d.fill(Submarine);
		g.setColor(Color.black);
		g.drawString("Click on the block where the ship goes", 530, 600);
		g.drawString("The block you select will light up", 530, 625);
		g.drawString("Click the place button when finished", 530, 650);
	}
	
	if (count == 6)
	{
		g.drawString("You have placed all of your ships", 530, 250);
		g.drawString("The computer has placed all of its ships", 530, 275);
		g.drawString("It's time to start the game", 530, 300);
		g.drawString("Click where you believe the computer's ships are", 530, 325);
		g.drawString("A hit will light up red", 530, 350);
		g.drawString("A miss will light up white", 530, 375);
	}
	if (count == 7)
	{
		g.drawString("The Computer Wins!", 530, 250);
	}
	if (count == 8)
	{
		g.drawString("The Player Wins!", 530, 250);
	}
	
	
	for (int count = 1; count < 100; count++)
	{
		if (Hits.contains(count))
		{
			g.setColor(Color.white);
			if (shipplaced)
			{
				g.setColor(Color.red);
			}
			if (count <= 10)
			{
			g.fillRect((50 * count) - 25, 200, 50, 50);
			}
			else if (count <= 20)
			{
			g.fillRect((50 * (count - 10)) - 25, 250, 50, 50);
			}
			else if (count <= 30)
			{
			g.fillRect((50 * (count - 20)) - 25, 300, 50, 50);
			}
			else if (count <= 40)
			{
			g.fillRect((50 * (count - 30)) - 25, 350, 50, 50);
			}
			else if (count <= 50)
			{
			g.fillRect((50 * (count - 40)) - 25, 400, 50, 50);
			}
			else if (count <= 60)
			{
			g.fillRect((50 * (count - 50)) - 25, 450, 50, 50);
			}
			else if (count <= 70)
			{
			g.fillRect((50 * (count - 60)) - 25, 500, 50, 50);
			}
			else if (count <= 80)
			{
			g.fillRect((50 * (count - 70)) - 25, 550, 50, 50);
			}
			else if (count <= 90)
			{
			g.fillRect((50 * (count - 80)) - 25, 600, 50, 50);
			}
			else if (count <= 100)
			{
			g.fillRect((50 * (count - 90)) - 25, 650, 50, 50);
			}
		}
		
		if (PlayerMisses.contains(count))
		{
			g.setColor(Color.white);
			if (count <= 10)
			{
			g.fillRect((50 * count) + 875, 200, 50, 50);
			}
			else if (count <= 20)
			{
			g.fillRect((50 * (count - 10)) + 875, 250, 50, 50);
			}
			else if (count <= 30)
			{
			g.fillRect((50 * (count - 20)) + 875, 300, 50, 50);
			}
			else if (count <= 40)
			{
			g.fillRect((50 * (count - 30)) + 875, 350, 50, 50);
			}
			else if (count <= 50)
			{
			g.fillRect((50 * (count - 40)) + 875, 400, 50, 50);
			}
			else if (count <= 60)
			{
			g.fillRect((50 * (count - 50)) + 875, 450, 50, 50);
			}
			else if (count <= 70)
			{
			g.fillRect((50 * (count - 60)) + 875, 500, 50, 50);
			}
			else if (count <= 80)
			{
			g.fillRect((50 * (count - 70)) + 875, 550, 50, 50);
			}
			else if (count <= 90)
			{
			g.fillRect((50 * (count - 80)) + 875, 600, 50, 50);
			}
			else if (count <= 100)
			{
			g.fillRect((50 * (count - 90)) + 875, 650, 50, 50);
			}
		}
		
		if (PlayerHits.contains(count))
		{
			g.setColor(Color.RED);
			if (count <= 10)
			{
			g.fillRect((50 * count) + 875, 200, 50, 50);
			}
			else if (count <= 20)
			{
			g.fillRect((50 * (count - 10)) + 875, 250, 50, 50);
			}
			else if (count <= 30)
			{
			g.fillRect((50 * (count - 20)) + 875, 300, 50, 50);
			}
			else if (count <= 40)
			{
			g.fillRect((50 * (count - 30)) + 875, 350, 50, 50);
			}
			else if (count <= 50)
			{
			g.fillRect((50 * (count - 40)) + 875, 400, 50, 50);
			}
			else if (count <= 60)
			{
			g.fillRect((50 * (count - 50)) + 875, 450, 50, 50);
			}
			else if (count <= 70)
			{
			g.fillRect((50 * (count - 60)) + 875, 500, 50, 50);
			}
			else if (count <= 80)
			{
			g.fillRect((50 * (count - 70)) + 875, 550, 50, 50);
			}
			else if (count <= 90)
			{
			g.fillRect((50 * (count - 80)) + 875, 600, 50, 50);
			}
			else if (count <= 100)
			{
			g.fillRect((50 * (count - 90)) + 875, 650, 50, 50);
			}
		}
		
		if (ComputerStrikes.contains(count))
		{
			g.setColor(Color.BLUE);
			if (count <= 10)
			{
			g.fillOval((50 * count) - 25, 200, 50, 50);
			}
			else if (count <= 20)
			{
			g.fillOval((50 * (count - 10)) - 25, 250, 50, 50);
			}
			else if (count <= 30)
			{
			g.fillOval((50 * (count - 20)) - 25, 300, 50, 50);
			}
			else if (count <= 40)
			{
			g.fillOval((50 * (count - 30)) - 25, 350, 50, 50);
			}
			else if (count <= 50)
			{
			g.fillOval((50 * (count - 40)) - 25, 400, 50, 50);
			}
			else if (count <= 60)
			{
			g.fillOval((50 * (count - 50)) - 25, 450, 50, 50);
			}
			else if (count <= 70)
			{
			g.fillOval((50 * (count - 60)) - 25, 500, 50, 50);
			}
			else if (count <= 80)
			{
			g.fillOval((50 * (count - 70)) - 25, 550, 50, 50);
			}
			else if (count <= 90)
			{
			g.fillOval((50 * (count - 80)) - 25, 600, 50, 50);
			}
			else if (count <= 100)
			{
			g.fillOval((50 * (count - 90)) - 25, 650, 50, 50);
			}
		}
		
		if (ComputerMisses.contains(count))
		{
			g.setColor(Color.white);
			if (count <= 10)
			{
			g.fillRect((50 * count) - 25, 200, 50, 50);
			}
			else if (count <= 20)
			{
			g.fillRect((50 * (count - 10)) - 25, 250, 50, 50);
			}
			else if (count <= 30)
			{
			g.fillRect((50 * (count - 20)) - 25, 300, 50, 50);
			}
			else if (count <= 40)
			{
			g.fillRect((50 * (count - 30)) - 25, 350, 50, 50);
			}
			else if (count <= 50)
			{
			g.fillRect((50 * (count - 40)) - 25, 400, 50, 50);
			}
			else if (count <= 60)
			{
			g.fillRect((50 * (count - 50)) - 25, 450, 50, 50);
			}
			else if (count <= 70)
			{
			g.fillRect((50 * (count - 60)) - 25, 500, 50, 50);
			}
			else if (count <= 80)
			{
			g.fillRect((50 * (count - 70)) - 25, 550, 50, 50);
			}
			else if (count <= 90)
			{
			g.fillRect((50 * (count - 80)) - 25, 600, 50, 50);
			}
			else if (count <= 100)
			{
			g.fillRect((50 * (count - 90)) - 25, 650, 50, 50);
			}
		}
		
		
		
	}
	}

	
	

}