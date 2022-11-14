package game;

import java.awt.Color;
import java.awt.Graphics;

import menu.WindowProperties;

public class Grid implements WindowProperties{
	
	private Tile[][] grid;
	private int lines = 5;
	private int columns = 5;
	private Position position_m;
	
	private Color[] colorpos;
	private Color[] new_colorpos;
	
	public Grid(Position position_p) {
		grid = new Tile[lines][columns];
		this.position_m = position_p;
		colorpos = new Color[5];
		new_colorpos = new Color[5];
	}

	@Override
	public void draw(Graphics g) {
		
		
		colorpos[0] = Color.BLUE;
		colorpos[1] = Color.ORANGE;
		colorpos[2] = Color.RED;
		colorpos[3] = Color.BLACK;
		colorpos[4] = Color.WHITE;
		

		
		for(int l = 0; l < lines; l++) {
			for(int c = 0; c < columns; c++) {
				
				g.setColor(colorpos[c]);
				
				if(c + 1 >= 5) {
					new_colorpos[0] = colorpos[c];
					
				}else {
					new_colorpos[c+1] = colorpos[c];
				}
					
				g.fillRect(position_m.getX()* BORD_SIZE * 2 + BORD_SIZE/2 + c*UNIT_GRID, position_m.getY()* BORD_SIZE * 2 + (int)(BORD_SIZE/3.5) + l*UNIT_GRID, UNIT_GRID, UNIT_GRID);
			
				g.setColor(Color.black);
				g.drawRect(position_m.getX()* BORD_SIZE * 2 + BORD_SIZE/2 + c*UNIT_GRID, position_m.getY()* BORD_SIZE * 2 + (int)(BORD_SIZE/3.5) + l*UNIT_GRID, UNIT_GRID, UNIT_GRID);
				}
			for(int i = 0; i<5;i++) {
				colorpos[i] = new_colorpos[i];
			}
		}
	}
	
	
	

}
