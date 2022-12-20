package menu;

import java.awt.Dimension;
import java.awt.Graphics;


 // This interface defines constants and a method signature related to window and game properties.
 
public interface WindowProperties {
	
	// Constant for the width of the window in pixels
	final static int WINDOW_WIDTH = 800;
	
	// Constant for the height of the window in pixels
	final static int WINDOW_HEIGHT = 500;
	
	// Constant for the width of the game in pixels
	final static int GAME_WIDTH = 800;
	
	// Constant for the height of the game in pixels
	final static int GAME_HEIGHT = 800;
	
	// Constant for the dimensions of the window in a Dimension object
	final static Dimension WINDOW_MENU = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
	
	// Constant for the dimensions of the game in a Dimension object
	final static Dimension WINDOW_GAME = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	
	// Constant for the size of the border in pixels
	final static int BORD_SIZE = GAME_WIDTH/6*2;
	
	// Constant for the number of horizontal scores
	final static int NUMBER_SCORE_HOR = 20;
	
	// Constant for the size of the score in pixels
	final static int SCORE_SIZE = BORD_SIZE/24;
	
	// Constant for the size of the gap between scores in pixels
	final static int SIZE_GAP = (BORD_SIZE - NUMBER_SCORE_HOR * SCORE_SIZE) / (NUMBER_SCORE_HOR + 1);
	
	// Constant for the size of the grid unit in pixels
	final static int UNIT_GRID = BORD_SIZE/10;
	
	
	 // Method signature for a method that draws graphical elements.
	 // @param g a Graphics object used for drawing
	 
	public void draw(Graphics g);
}
