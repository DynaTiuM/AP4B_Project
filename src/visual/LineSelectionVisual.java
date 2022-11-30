package visual;

import java.awt.Color;
import java.awt.Graphics;

import machine.Line;



public class LineSelectionVisual implements WindowProperties {
	
	Position position_m;
	
	public LineSelectionVisual(Position position_p) {
		position_m = position_p;
	}
	
	public void draw(Graphics g, Line[] lines_p) {
		
		int iter = 1;
		for(int l = 0; l < 5; l++) {
			for(int c = 0; c < iter; c++) {
				
				
			g.drawRect(position_m.getX()* BORD_SIZE * 2 + (4-c)*UNIT_GRID, position_m.getY()* BORD_SIZE * 2 + (int)(BORD_SIZE/3.5) + l*UNIT_GRID, UNIT_GRID, UNIT_GRID);
				
					
				
			}
			iter++;
		}
	}
	
	public void draw(Graphics g) {
		int iter = 1;
		g.setColor(Color.black);
		for(int l = 0; l < 5; l++) {
			for(int c = 0; c < iter; c++) {
				
				
			g.drawRect(position_m.getX()* BORD_SIZE * 2 + (4-c)*UNIT_GRID, position_m.getY()* BORD_SIZE * 2 + (int)(BORD_SIZE/3.5) + l*UNIT_GRID, UNIT_GRID, UNIT_GRID);
				
					
				
			}
			iter++;
		}
	}
}
