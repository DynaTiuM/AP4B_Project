package game;

import java.awt.Color;
import java.awt.Graphics;

import menu.WindowProperties;

public class Score implements WindowProperties {
	
	private int score = 0;
	private final int columns = 20;
	private final int lines = 5;
	private boolean[][] grid;
	private int[] position;
	
	public Score(int[] position) {
		grid = new boolean[lines][columns];
		this.position = position;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void draw(Graphics g) {
		Color orange = new Color(176, 90, 13);
		for(int l = 0; l < lines; l++) {
			for(int c = 0; c < columns; c++) {
				if(grid[l][c] == false) {
					if(c%5 == 4)
						g.setColor(orange);
					else
						g.setColor(Color.gray);
				}
				else
					g.setColor(Color.black);
				
				g.fillRect(position[1] * BORD_SIZE * 2 + SIZE_GAP * 2 + c * (SCORE_SIZE + SIZE_GAP), position[0] * BORD_SIZE * 2 + SIZE_GAP * 2 + l * (SCORE_SIZE + SIZE_GAP), SCORE_SIZE, SCORE_SIZE);				
			}
			
		}
	}

}
