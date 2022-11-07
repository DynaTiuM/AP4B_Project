package menu;

import java.awt.Dimension;
import java.awt.Graphics;

public interface WindowProperties {
	final static int WINDOW_WIDTH = 800;
	final static int WINDOW_HEIGHT = 500;
	final static int GAME_WIDTH = 800;
	final static int GAME_HEIGHT = 800;
	final static Dimension WINDOW_MENU = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
	final static Dimension WINDOW_GAME = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	final static int BORD_SIZE = GAME_WIDTH/6*2;
	final static int NUMBER_SCORE_HOR = 20;
	final static int SCORE_SIZE = BORD_SIZE/24;
	final static int SIZE_GAP = (BORD_SIZE - NUMBER_SCORE_HOR * SCORE_SIZE) / (NUMBER_SCORE_HOR + 1);
	final static int UNIT_GRID = BORD_SIZE/10;
	
	public void draw(Graphics g);
}
