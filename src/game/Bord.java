package game;

import java.awt.Color;
import java.awt.Graphics;
import menu.WindowProperties;

public class Bord implements WindowProperties{
	
	private int position;
	
	public Bord() {
		
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.orange);
		if(position == 0)
			g.fillRect(0, 0, WINDOW_WIDTH/5*2, WINDOW_WIDTH/5*2);
		else if(position == 1)
			g.fillRect(WINDOW_WIDTH/5*3, 0, WINDOW_WIDTH/5*2, WINDOW_WIDTH/5*2);
		else if(position == 2)
			g.fillRect(0, WINDOW_WIDTH/5*3, WINDOW_WIDTH/5*2, WINDOW_WIDTH/5*2);
		else
			g.fillRect(WINDOW_WIDTH/5*3, WINDOW_WIDTH/5*3, WINDOW_WIDTH/5*2, WINDOW_WIDTH/5*2);
	}

}
