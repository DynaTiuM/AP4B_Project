package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.lang.Math;
import javax.swing.*;

public class Pile {
	final static int GAME_WIDTH = 800;
	final static int BORD_SIZE = GAME_WIDTH/6*2;
	
	final static int PILE_SIZE = (int) Math.sqrt(2*Math.pow((BORD_SIZE/10)*2, 2));
	private LinkedList<Tile> tiles;
	private Position position_m;
	
	private JButton take_tiles;
	
	public Pile(Position position_p) {
		tiles = new LinkedList<>();
		position_m = position_p;
		System.out.println("ho");
		
		
	}
	
	public void randomDistribution() {
		int i = 0;
		while(i < 4) {
			tiles.add(Bag.getRandomTile());
		}
	}
	
	public void draw(Graphics g) {
		
		g.setColor(Color.CYAN);
		g.fillOval(BORD_SIZE + position_m.getX() * (PILE_SIZE + BORD_SIZE/10) , 100 + position_m.getY() * (PILE_SIZE + BORD_SIZE/10) , PILE_SIZE, PILE_SIZE);
		g.setColor(Color.BLACK);
		//for thicker lines
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1));
		g2.drawOval(BORD_SIZE + position_m.getX() * (PILE_SIZE + BORD_SIZE/10) , 100 + position_m.getY() * (PILE_SIZE + BORD_SIZE/10) , PILE_SIZE, PILE_SIZE);
		
	}

	
	
	
	

}
