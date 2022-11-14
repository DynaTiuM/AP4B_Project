package game;

import java.awt.Color;
import java.awt.Graphics;
import menu.WindowProperties;

public class Bord implements WindowProperties{
	
	private Position position_m;
	private Score score;
	private Grid grid;
	private PlayGrid playGrid;
	
	public Bord(int line, int column) {
		
		position_m = new Position(column, line);
		
		/**position_m.setY(line);
		position_m.setX(column);**/
		
		
		score = new Score(position_m);
		grid = new Grid(position_m);
		playGrid = new PlayGrid(position_m);
	}
	
	public void draw(Graphics g) {
		Color bord = new Color(193,189,180);
		g.setColor(bord);
		g.fillRect(position_m.getX() * BORD_SIZE * 2, position_m.getY() * BORD_SIZE * 2, BORD_SIZE, BORD_SIZE);
		
		score.draw(g);
		grid.draw(g);
		playGrid.draw(g);
	}

}
