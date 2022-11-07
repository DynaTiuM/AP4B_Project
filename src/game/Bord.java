package game;

import java.awt.Color;
import java.awt.Graphics;
import menu.WindowProperties;

public class Bord implements WindowProperties{
	
	private int[] position;
	private Score score;
	private Grid grid;
	private PlayGrid playGrid;
	
	public Bord(int line, int column) {
		position = new int[2];
		position[0] = line;
		position[1] = column;
		score = new Score(position);
		grid = new Grid(position);
		playGrid = new PlayGrid(position);
	}
	
	public void draw(Graphics g) {
		Color bord = new Color(193,189,180);
		g.setColor(bord);
		g.fillRect(position[1] * BORD_SIZE * 2, position[0] * BORD_SIZE * 2, BORD_SIZE, BORD_SIZE);
		
		score.draw(g);
		grid.draw(g);
		playGrid.draw(g);
	}

}
