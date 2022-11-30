package visual;

import java.awt.Color;
import java.awt.Graphics;

import machine.Tile;

public class GridVisual implements WindowProperties {
	
	private Position position_m;
	//private Color bord;
	
	public GridVisual(Position position_p){
		position_m = position_p;
		
	}
	
	public void draw(Graphics g, Tile[][] grid) {
		
		g.fillRect(position_m.getX() * BORD_SIZE * 2, position_m.getY() * BORD_SIZE * 2, BORD_SIZE , BORD_SIZE);
		
		
		for(int l = 0; l < 5; l++) {
			for(int c = 0; c < 5; c++) {
				if(grid[l][c].getOccupied()) {
					g.setColor(grid[l][c].getColor());
				}else g.setColor(grid[l][c].getColor().brighter());
				
				g.fillRect(position_m.getX()* BORD_SIZE * 2 + BORD_SIZE/2 + c*UNIT_GRID, position_m.getY()* BORD_SIZE * 2 + (int)(BORD_SIZE/3.5) + l*UNIT_GRID, UNIT_GRID, UNIT_GRID);
		
				g.setColor(Color.black);
				g.drawRect(position_m.getX()* BORD_SIZE * 2 + BORD_SIZE/2 + c*UNIT_GRID, position_m.getY()* BORD_SIZE * 2 + (int)(BORD_SIZE/3.5) + l*UNIT_GRID, UNIT_GRID, UNIT_GRID);
				
			}
		}
		
		Color orange = new Color(176, 90, 13);
		
			for(int l = 0; l < 5; l++) {
				for(int c = 0; c < 20; c++) {
					
						if(c%5 == 4)
							g.setColor(orange);
						else
							g.setColor(Color.gray);
					
					
					
					g.fillRect(position_m.getX() * BORD_SIZE * 2 + SIZE_GAP * 2  + (c) * (SCORE_SIZE + SIZE_GAP), position_m.getY() * BORD_SIZE * 2 + SIZE_GAP * 2 + l * (SCORE_SIZE + SIZE_GAP), SCORE_SIZE, SCORE_SIZE);				
				}
				
			}
		
		
	}
	
	public void draw(Graphics g) {
		
		g.fillRect(position_m.getX() * BORD_SIZE * 2, position_m.getY() * BORD_SIZE * 2, BORD_SIZE , BORD_SIZE);
		for(int l = 0; l < 5; l++) {
			for(int c = 0; c < 5; c++) {
				//g.fillRect(position_m.getX()* BORD_SIZE * 2 + BORD_SIZE/2 + c*UNIT_GRID, position_m.getY()* BORD_SIZE * 2 + (int)(BORD_SIZE/3.5) + l*UNIT_GRID, UNIT_GRID, UNIT_GRID);
		
				g.setColor(Color.black);
				g.drawRect(position_m.getX()* BORD_SIZE * 2 + BORD_SIZE/2 + c*UNIT_GRID, position_m.getY()* BORD_SIZE * 2 + (int)(BORD_SIZE/3.5) + l*UNIT_GRID, UNIT_GRID, UNIT_GRID);
				
			}
		}
		
		Color orange = new Color(176, 90, 13);
		
			for(int l = 0; l < 5; l++) {
				for(int c = 0; c < 20; c++) {
					
						if(c%5 == 4)
							g.setColor(orange);
						else
							g.setColor(Color.gray);
					
					
					
					g.fillRect(position_m.getX() * BORD_SIZE * 2 + SIZE_GAP * 2  + (c) * (SCORE_SIZE + SIZE_GAP), position_m.getY() * BORD_SIZE * 2 + SIZE_GAP * 2 + l * (SCORE_SIZE + SIZE_GAP), SCORE_SIZE, SCORE_SIZE);				
				}
				
			}
		
		
		
	}

	
	
}
