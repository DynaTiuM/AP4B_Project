package game;

import java.awt.Color;
import java.awt.Graphics;

import menu.WindowProperties;

public class Grid implements WindowProperties{
	
	private Tile[][] grid;
	private int lines = 5;
	private int columns = 5;
	private int[] position;
	
	public Grid(int[] position) {
		grid = new Tile[lines][columns];
		this.position = position;
	}

	@Override
	public void draw(Graphics g) {
		for(int l = 0; l < lines; l++) {
			for(int c = 0; c < columns; c++) {
				for(int i = 0; i < lines; i++) {
					if(l%4 == i && c%4 == i)
						g.setColor(Color.BLUE);
					if(l%4 == i && c%4 == i+1 || l%4 == i && c%4 == i-4)
						g.setColor(Color.ORANGE);
					if(l%4 == i && c%4 == i+2 || l%4 == i && c%4 == i-3)
						g.setColor(Color.RED);
					if(l%4 == i && c%4 == i+3 || l%4 == i && c%4 == i-2)
						g.setColor(Color.BLACK);
					if(l%4 == i && c%4 == i+4 || l%4 == i && c%4 == i-1)
						g.setColor(Color.WHITE);
				}
				g.fillRect(position[1]* BORD_SIZE * 2 + BORD_SIZE/2 + c*UNIT_GRID, position[0]* BORD_SIZE * 2 + (int)(BORD_SIZE/3.5) + l*UNIT_GRID, UNIT_GRID, UNIT_GRID);
			
				g.setColor(Color.black);
				g.drawRect(position[1]* BORD_SIZE * 2 + BORD_SIZE/2 + c*UNIT_GRID, position[0]* BORD_SIZE * 2 + (int)(BORD_SIZE/3.5) + l*UNIT_GRID, UNIT_GRID, UNIT_GRID);
				}
		}
	}
	
	

}
