package game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Pile {
	
	private LinkedList<Tile> tiles;
	
	public Pile() {
		tiles = new LinkedList<>();
	}
	
	public void randomDistribution() {
		int i = 0;
		while(i < 4) {
			tiles.add(Bag.getRandomTile());
		}
	}
	
	
	public void draw(Graphics g) {
		
	}
	

}
