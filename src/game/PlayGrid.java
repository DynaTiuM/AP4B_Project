package game;

import java.awt.Graphics;

import menu.WindowProperties;

public class PlayGrid implements WindowProperties {
	
	private Tile[][] playGrid;
	private int[] position;
	private int lines = 5;
	private int columns = 5;
	
	public PlayGrid(int[] position) {
		playGrid = new Tile[5][5];
		this.position = position;
	}

	@Override
	public void draw(Graphics g) {
		for(int l = 0; l < lines; l++) {
			for(int c = 0; c < columns; c++) {
				for(int i = lines; i > 4; i--) {
					if(c == i-l) {
						g.drawRect(position[1]* BORD_SIZE * 2 + c*UNIT_GRID, position[0]* BORD_SIZE * 2 + (int)(BORD_SIZE/3.5) + l*UNIT_GRID, UNIT_GRID, UNIT_GRID);
					}
				}
			}
		}
	}

}
