package game;

import java.awt.Graphics;

import menu.WindowProperties;

public class PlayGrid implements WindowProperties {
	
	private Tile[][] playGrid;
	private Position position_m;
	
	//ça vaut vraiment le coup d'avoir une constante juste pour un for ?
	private static final int LINES = 5;

	private int iter;
	
	public PlayGrid(Position position_p) {
		playGrid = new Tile[5][5];
		this.position_m = position_p;
	}

	@Override
	public void draw(Graphics g) {
		
		iter = 1;
		for(int l = 0; l < LINES; l++) {
			for(int c = 0; c < iter; c++) {
				
				
			g.drawRect(position_m.getX()* BORD_SIZE * 2 + (4-c)*UNIT_GRID, position_m.getY()* BORD_SIZE * 2 + (int)(BORD_SIZE/3.5) + l*UNIT_GRID, UNIT_GRID, UNIT_GRID);
				
			//g.drawRect(position_m.getX()* BORD_SIZE * 2 + c*UNIT_GRID, position_m.getY()* BORD_SIZE * 2 + (int)(BORD_SIZE/3.5) + l*UNIT_GRID, UNIT_GRID, UNIT_GRID);
					
				
			}
			iter++;
		}
	}

}
